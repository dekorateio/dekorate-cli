package io.dekorate.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "tekton", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Tekton manifests.")
public class TektonCmd implements Runnable {
  @Mixin
  MetaOptions meta;

  @Override
  public void run() {
    Generator.init("tekton-pipeline", "tekton-pipeline-run", "tekton-task", "tekton-task-run");
    Generator.apply(meta);
    Generator.generate();
  }
}
