package io.dekorate.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "knative", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Knative manifests.")
public class KnativeCmd implements Runnable {

  @Mixin
  MetaOptions meta;

  @Mixin
  PodOptions pod;

  @Mixin
  ContainerOptions container;

  @Override
  public void run() {
    Generator.init("knative");
    Generator.apply(meta);
    Generator.apply(pod);
    Generator.apply(container);
    Generator.generate();
  }
}
