package io.dekorate.cli;

import picocli.CommandLine.Command;

@Command(name = "openshift", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Openshift manifests.")
public class OpenshiftCmd implements Runnable {

  @Override
  public void run() {
    Generator.generate("openshift");
  }
}
