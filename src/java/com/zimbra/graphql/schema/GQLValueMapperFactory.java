package com.zimbra.graphql.schema;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.zimbra.common.util.ZimbraLog;

import io.leangen.geantyref.GenericTypeReflector;
import io.leangen.geantyref.TypeFactory;
import io.leangen.graphql.execution.GlobalEnvironment;
import io.leangen.graphql.metadata.InputField;
import io.leangen.graphql.metadata.exceptions.TypeMappingException;
import io.leangen.graphql.metadata.strategy.InclusionStrategy;
import io.leangen.graphql.metadata.strategy.type.TypeTransformer;
import io.leangen.graphql.metadata.strategy.value.InputFieldDiscoveryStrategy;
import io.leangen.graphql.metadata.strategy.value.ValueMapper;
import io.leangen.graphql.metadata.strategy.value.ValueMapperFactory;
import io.leangen.graphql.util.ClassUtils;

public class GQLValueMapperFactory implements ValueMapperFactory<ValueMapper> {

    protected final ObjectMapper mapper;

    public GQLValueMapperFactory(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ValueMapper getValueMapper(@SuppressWarnings("rawtypes") Map<Class, List<Class>> concreteSubTypes, GlobalEnvironment environment) {
        return new GQLValueDiscoveryStrategy(mapper);
    }

    public class GQLValueDiscoveryStrategy implements ValueMapper, InputFieldDiscoveryStrategy {
        private final ObjectMapper objectMapper;

        public GQLValueDiscoveryStrategy(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public <T> T fromInput(Object graphQLInput, Type sourceType, AnnotatedType outputType) {
            return objectMapper.convertValue(graphQLInput, objectMapper.getTypeFactory().constructType(outputType.getType()));
        }

        @Override
        public <T> T fromString(String json, AnnotatedType type) {
            try {
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(type.getType()));
            } catch (final IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        @Override
        public String toString(Object output) {
            try {
                return objectMapper.writeValueAsString(output);
            } catch (final JsonProcessingException e) {
                throw new UncheckedIOException(e);
            }
        }

        @Override
        public Set<InputField> getInputFields(AnnotatedType type, InclusionStrategy inclusionStrategy, TypeTransformer typeTransformer) {
            final JavaType javaType = objectMapper.getTypeFactory().constructType(type.getType());
            final BeanDescription desc = objectMapper.getDeserializationConfig().introspect(javaType);
            return desc.findProperties().stream()
                    .filter(BeanPropertyDefinition::couldDeserialize)
                    .flatMap(prop -> toInputField(type, prop, inclusionStrategy, typeTransformer))
                    .collect(Collectors.toSet());
        }

        private Stream<InputField> toInputField(AnnotatedType type, BeanPropertyDefinition prop, InclusionStrategy inclusionStrategy, TypeTransformer typeTransformer) {
            final AnnotatedParameter ctorParam = prop.getConstructorParameter();
            if (ctorParam != null) {
                final Constructor<?> constructor = (Constructor<?>) ctorParam.getOwner().getMember();
                final AnnotatedType fieldType = transform(ClassUtils.getParameterTypes(constructor, type)[ctorParam.getIndex()],
                        typeTransformer, constructor.getParameters()[ctorParam.getIndex()]);
                return inclusionStrategy.includeInputField(
                        constructor.getDeclaringClass(), constructor.getParameters()[ctorParam.getIndex()], fieldType)
                        ? toInputField(prop, fieldType, ctorParam) : Stream.empty();
            }
            if (prop.getSetter() != null) {
                final Method setter = prop.getSetter().getAnnotated();
                final AnnotatedType fieldType = transform(ClassUtils.getParameterTypes(setter, type)[0], typeTransformer, setter, type);
                return inclusionStrategy.includeInputField(setter.getDeclaringClass(), setter, fieldType)
                        ? toInputField(prop, fieldType, prop.getSetter()) : Stream.empty();
            }
            if (prop.getGetter() != null) {
                final Method getter = prop.getGetter().getAnnotated();
                final AnnotatedType fieldType = transform(ClassUtils.getReturnType(getter, type), typeTransformer, getter, type);
                return inclusionStrategy.includeInputField(getter.getDeclaringClass(), getter, fieldType)
                        ? toInputField(prop, fieldType, prop.getGetter()) : Stream.empty();
            }
            if (prop.getField() != null) {
                final Field field = prop.getField().getAnnotated();
                final AnnotatedType fieldType = transform(ClassUtils.getFieldType(field, type), typeTransformer, field, type);
                return inclusionStrategy.includeInputField(field.getDeclaringClass(), field, fieldType)
                        ? toInputField(prop, fieldType, prop.getField()) : Stream.empty();
            }
            throw new TypeMappingException("Unknown input field mapping style encountered");
        }

        private Stream<InputField> toInputField(BeanPropertyDefinition prop, AnnotatedType fieldType, Annotated accessor) {
            final AnnotatedType deserializableType = resolveDeserializableType(accessor, fieldType, accessor.getType(), objectMapper);
            return Stream.of(new InputField(prop.getName(), prop.getMetadata().getDescription(), fieldType, deserializableType));
        }

        private AnnotatedType transform(AnnotatedType type, TypeTransformer typeTransformer, Member member, AnnotatedType declaringType) {
            try {
                return typeTransformer.transform(type);
            } catch (final TypeMappingException e) {
                throw new TypeMappingException(member, declaringType, e);
            }
        }

        private AnnotatedType transform(AnnotatedType type, TypeTransformer typeTransformer, Parameter parameter) {
            try {
                return typeTransformer.transform(type);
            } catch (final TypeMappingException e) {
                throw new TypeMappingException(parameter.getDeclaringExecutable(), parameter, e);
            }
        }

        private AnnotatedType resolveDeserializableType(Annotated accessor, AnnotatedType realType, JavaType baseType, ObjectMapper objectMapper) {
            final AnnotationIntrospector introspector = objectMapper.getDeserializationConfig().getAnnotationIntrospector();
            try {
                objectMapper.getDeserializationContext().getFactory().mapAbstractType(objectMapper.getDeserializationConfig(), objectMapper.constructType(Map.class));
                final JavaType refined = introspector.refineDeserializationType(objectMapper.getDeserializationConfig(), accessor, baseType);
                final Class<?> raw = ClassUtils.getRawType(realType.getType());
                if (!refined.getRawClass().equals(raw)) {
                    if (GenericTypeReflector.isSuperType(realType.getType(), refined.getRawClass())) {
                        final AnnotatedType candidate = GenericTypeReflector.getExactSubType(realType, refined.getRawClass());
                        if (!GenericTypeReflector.isMissingTypeParameters(candidate.getType())) {
                            return candidate;
                        }
                    }
                    return GenericTypeReflector.updateAnnotations(toJavaType(refined), realType.getAnnotations());
                }
            } catch (final JsonMappingException e) {
                /*no-op*/
            } catch (final Exception e) {
                ZimbraLog.extensions.warn("Failed to determine the deserializable type for %s due to an exception", GenericTypeReflector.getTypeName(realType.getType()));
                ZimbraLog.extensions.debug(e);
            }
            return realType;
        }

        private AnnotatedType toJavaType(JavaType jacksonType) {
            if (jacksonType.getRawClass().getTypeParameters().length > 0) {
                final AnnotatedType[] paramTypes = jacksonType.getBindings().getTypeParameters().stream()
                        .map(this::toJavaType)
                        .toArray(AnnotatedType[]::new);
                return TypeFactory.parameterizedAnnotatedClass(jacksonType.getRawClass(), jacksonType.getRawClass().getAnnotations(), paramTypes);
            }
            if (jacksonType.isArrayType()) {
                return TypeFactory.arrayOf(toJavaType(jacksonType.getContentType()), new Annotation[0]);
            }
            return GenericTypeReflector.annotate(jacksonType.getRawClass());
        }
    }
}