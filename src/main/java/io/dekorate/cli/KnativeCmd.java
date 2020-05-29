package io.dekorate.cli;

import picocli.CommandLine.Command;

@Command(name = "knative", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Knative manifests.")
public class KnativeCmd implements Runnable {

  @Override
  public void run() {
    Generator.generate("knative");
  }
}
