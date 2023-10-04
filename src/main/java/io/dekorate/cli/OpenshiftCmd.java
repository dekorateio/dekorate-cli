package io.dekorate.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "openshift", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Openshift manifests.")
public class OpenshiftCmd extends BaseCommand {

  @Mixin
  MetaOptions meta;

  @Mixin
  PodOptions pod;

  @Mixin
  ContainerOptions container;

  @Override
  public void execute() {
    Generator.init("openshift");
    Generator.apply(meta);
    Generator.apply(container);
    Generator.generate();
  }
}
