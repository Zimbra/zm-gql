# `zm-gql`

> Zimbra GraphQL Service

This service provides a GraphQL interface for users to perform operations on the Zimbra platform.

---

## Installation

**Pre-Requisites**

The `zm-mailbox` project must be built and deployed to the `.zcs-deps` folder.

The `zm-build` and `zm-zcs` projects should also reside in the same local parent folder as this project.


**Deploying the extension from CLI**

For testing purposes you can build and and deploy the extension to `/opt/zimbra/lib/ext/zm-gql` by running the following:

```sh
ant clean deploy
```

Afterwards, become the `zimbra` user and perform a `zmmailboxdctl restart`.

---

## Testing

**Unit testing the extension from CLI**

```sh
ant clean test
```

---

## Usage

**API**

Download and install a GraphQL explorer and view the docs built by introspection.

The general usage flow involves performing an auth, then using the acquired auth token to perform subsequent GraphQL requests.

The service url should appear as: `https://<hostname>/service/extension/graphql`.

The auth token should be used in a `Cookie` header as `ZM_AUTH_TOKEN=<auth_token_value>`.