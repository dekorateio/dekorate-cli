package io.dekorate.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "knative", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Knative manifests.")
public class KnativeCmd implements Runnable {
  @Mixin
  MetaOptions meta;

  @Override
  public void run() {
    Generator.init("knative");
    Generator.applyMeta(meta);
    Generator.generate();
  }
}
