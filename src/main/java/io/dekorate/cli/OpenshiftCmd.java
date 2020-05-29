package io.dekorate.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "openshift", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Openshift manifests.")
public class OpenshiftCmd implements Runnable {

  @Mixin
  MetaOptions meta;

  @Mixin
  ContainerOptions container;

  @Override
  public void run() {
    Generator.init("openshift");
    Generator.applyMeta(meta);
    Generator.applyMeta(container);
    Generator.generate();
  }
}
