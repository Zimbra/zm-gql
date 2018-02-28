package com.zimbra.graphql.models;

import java.util.List;

public class Signature {

    private String id;
    private String name;
    private List<SignatureContent> content;

    public Signature(String id, String name, List<SignatureContent> content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SignatureContent> getContent() {
        return content;
    }

    public void setContent(List<SignatureContent> content) {
        this.content = content;
    }

}
