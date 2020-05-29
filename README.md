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

### Name and version

To specify the name of the resources inside the generated manifests, you can use the `-n, --name` option:

```
dekorate kubernetes -n my-app
```

This name, will be used to: 
- name resources
- in the `app.kubernetes.io/name` label
- as container image name

In the same spirit you can specify the version using `-v, --ver` option:

```
dekorate kubernetes -v 1.0.0
```

This version, will be used to: 
- in the `app.kubernetes.io/version` label
- as container image tag

**Note**: If no name/version is specified, dekorate will try to guess them by parsing the project configuration.

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
