package io.dekorate.cli;

import io.dekorate.docker.config.DockerBuildConfigBuilder;
import io.dekorate.kubernetes.config.KubernetesConfigBuilder;
import io.dekorate.utils.Strings;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "kubernetes", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Kubernetes manifests.")
public class KubernetesCmd implements Runnable {

  @Mixin
  MetaOptions meta;

  @Mixin
  PodOptions pod;

  @Mixin
  ContainerOptions container;

  @Override
  public void run() {
    Generator.init("kubernetes");
    Generator.apply(meta);
    Generator.apply(pod);
    Generator.apply(container);
    Generator.generate();
  }

}
