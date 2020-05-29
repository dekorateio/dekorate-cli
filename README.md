# Dekorate CLI

Dekorate as a Command Line tool.

# Overview
Decorate CLI is a native binary through which you can generate Kubernetes manifests for your application.
It's written in Java, but compiled as a native binary by leveraging the Quarkus framework. It can be used
for Java and non-Java application.

# Features
  - Generate resources
  - Modify existing resources
  
  
# Known issues
This tool requires `quarkus-picocli` which is not yet releashed.
So, at the moment is not possible to build a native binary (it's only possible using quarkus snapshot builds).
  
# Building and installing

The easiest apprroach to creating a native binary is:

```
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

The command above will generate `/target/dekorate-cli-1.0-SNAPSHOT-runner`.
You can copy the generated binary to your `$PATH`. For example:

```
cp `/target/dekorate-cli-1.0-SNAPSHOT-runner $HOME/bin/dekorate`
```

The rest of this document will assume that the tool is available in the `$PATH` as `dekorate`.


# Usage

## Generating Kubernetes manifests

```
dekorate kubernetes
```

### Labels

To add one or more labels to the generated manifests, you can use the `-l, --label` option:

```
dekorate kubernetes -l foo=bar
```

The option can be used multiple times:

```
dekorate kubernetes -l foo=bar -l bar=baz
```

### Annotations

To add one or more labels to the generated manifests, you can use the `-a, --annotation` option:

```
dekorate kubernetes -a foo=bar
```

The option can be used multiple times:

```
dekorate kubernetes -a foo=bar -a bar=baz
```


### Environment variables

To add one or more environment variables to the containers inside the generated manifests, you can use the `-e, --env-var` option:

```
dekorate kubernetes -e FOO=BAR
```

In addition to simple key value pairs its also possible to define evironment variables sources using:
- Secret
- ConfigMap

### Secret Envrionment variable sources

To add a secret as an environment variable source, you can use the `-es, --env-from-secret` option:

```
dekorate kubernetes -es my-secret
```

### Configmap Envrionment variable sources

To add a configmap as an environment variable source, you can use the `-es, --env-from-configmap` option:

```
dekorate kubernetes -es my-configmap
```


## Generating Openshift manifests

```
dekorate openshfit
```


## Generating Knative manifests

```
dekorate knative
```

## Generating Tekton manifests

```
dekorate tekton
```
