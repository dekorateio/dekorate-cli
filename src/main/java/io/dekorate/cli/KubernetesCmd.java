package io.dekorate.cli;

import picocli.CommandLine.Command;

@Command(name = "kubernetes", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Kubernetes manifests.")
public class KubernetesCmd implements Runnable {

  @Override
  public void run() {
    Generator.generate("kubernetes");
  }

}
