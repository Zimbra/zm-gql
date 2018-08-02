# `zm-gql`

How to Run zm-gql

    fetch zm-mailbox:feature/graphql branch.
    build and deploy at least the zmsoap project
    
    Add the following dependencies to the service web-inf folder:
    /opt/zimbra/jetty/webapps/service/WEB-INF/lib/
    spqr-0.9.7.jar
    geantyref-1.3.4.jar
    graphql-java-9.0.jar
    reactive-streams-1.0.2.jar
    antlr4-runtime-4.7.1.jar

    fetch zm-gql:develop and ant clean deploy
    restart mailbox (no configs needed)
    
    login to webclient -> copy the auth header cookie
    use graphiql -> put the service url in as https://<hostname>/service/extension/graphql -> check the zm-gql service docs live from the api on the right side. add the cookie as a header (with key: Cookie, and value: ZM_AUTH_TOKEN=<token-here>) and do a few requests for sanity
