package io.dekorate.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "kubernetes", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Kubernetes manifests.")
public class KubernetesCmd implements Runnable {

  @Mixin
  MetaOptions meta;
  
  @Override
  public void run() {
    Generator.init("kubernetes");
    Generator.applyMeta(meta);
    Generator.generate();
  }

}
