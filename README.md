# Dekorate CLI

Dekorate as a Command Line tool.

# Overview
Decorate CLI is a native binary through which you can generate Kubernetes manifests for your application.
It's written in Java, but compiled as a native binary by leveraging the Quarkus framework. It can be used
for Java and non-Java application.

# Features
  - Generate resources
  - Modify existing resources
  
# Supported Runtimes / Frameworks

The list below shows the runtimes and frameworks supported:

- Java
  - Spring Boot
  - Thorntail
  - Generic Java Applications
    - maven
    - gralde
    - sbt
    - bazel
    
- Nodejs

For supported runtimes the tool will be able to extract information like:

- name
- version
- ports used
- how to run

That will be applied to the generated manifests. 

If the project type is unsupported the command will fail:

    # dekorate kuberentes
    
    Failed to collect project info!
    Make sure that a project is of a supported type: [maven, gralde, sbt, bazel, npm].

# Known issues

Currently, it is not possible to build a native binary (see https://github.com/dekorateio/dekorate-cli/issues/16).
  
# Building and installing

Dekorate cli comes in two flavours:

- Uberjar

- Native binary

## Uberjar

To build the uberjar:

    ./mvnw clean install

The command above will generate `/target/dekorate-cli-1.0-SNAPSHOT-runner`.

You can then use the script found under the `bin` folder to invoke the uberjar.
You can copy it to your `$PATH`. For example:

    cp `bin/dekorate $HOME/bin/dekorate`
    chmod +x $HOME/bin/dekorate

The rest of this document will assume that the tool is available in the `$PATH` as `dekorate`.

## Native binary

The easiest apprroach to creating a native binary is:

    ./mvnw package -Pnative -Dquarkus.native.container-build=true

****Note*: The command above requires access to the docker daemon.

If no docker daemon is not available, then you can try to build the native binary locally using:

    ./mvnw package -Pnative

This will only work provided that you have `GraalVM 19.3.1`. For more details please consult: https://quarkus.io/guides/building-native-image


# Usage

## Generating Kubernetes manifests

    dekorate kubernetes

### Generator output

For all project types the output directory is always: `.dekorate/kubernetes`.

### Name and version

To specify the name of the resources inside the generated manifests, you can use the `-n, --name` option:

    dekorate kubernetes -n my-app

This name, will be used to: 
- name resources
- in the `app.kubernetes.io/name` label
- as container image name

In the same spirit you can specify the version using `-v, --ver` option:

    dekorate kubernetes -v 1.0.0

This version, will be used to: 
- in the `app.kubernetes.io/version` label
- as container image tag

**Note**: If no name/version is specified, dekorate will try to guess them by parsing the project configuration.

### Labels

To add one or more labels to the generated manifests, you can use the `-l, --label` option:

    dekorate kubernetes -l foo=bar

The option can be used multiple times:

    dekorate kubernetes -l foo=bar -l bar=baz

### Annotations

To add one or more labels to the generated manifests, you can use the `-a, --annotation` option:

    dekorate kubernetes -a foo=bar

The option can be used multiple times:

    dekorate kubernetes -a foo=bar -a bar=baz


### Environment variables

To add one or more environment variables to the containers inside the generated manifests, you can use the `-e, --env-var` option:

    dekorate kubernetes -e FOO=BAR

In addition to simple key value pairs its also possible to define evironment variables sources using:
- Secret
- ConfigMap

### Secret Envrionment variable sources

To add a secret as an environment variable source, you can use the `-es, --env-from-secret` option:

    dekorate kubernetes -es my-secret

### Configmap Envrionment variable sources

To add a configmap as an environment variable source, you can use the `-es, --env-from-configmap` option:

    dekorate kubernetes -es my-configmap


### Ports

To add ports to the container, you can use the `-p, --port` option:

    dekorate kubernetes -p http=8080
    
The option accepts a key/value pair where the key is the port name and the value the container port number.

#### Exposing Http Ports

Http ports (ports named: http, https, web etc) can be exposed via Ingress (or Route if using Openshift), using the `-x, --expose` option:

    dekorate kuberentes -p http=8080 -x

## Generating Openshift manifests

    dekorate openshfit


## Generating Knative manifests

    dekorate knative

## Generating Tekton manifests

    dekorate tekton
