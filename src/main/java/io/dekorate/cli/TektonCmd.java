package io.dekorate.cli;

import picocli.CommandLine.Command;

@Command(name = "tekton", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Tekton manifests.")
public class TektonCmd implements Runnable {

  @Override
  public void run() {
    Generator.generate("tekton-pipeline", "tekton-pipeline-run", "tekton-task", "tekton-task-run");
  }
}
