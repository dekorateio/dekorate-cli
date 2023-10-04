package io.dekorate.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "tekton", mixinStandardHelpOptions = true, version = "0.1", description = "Generates Tekton manifests.")
public class TektonCmd extends BaseCommand {

  @Mixin
  MetaOptions meta;

  @Override
  public void execute() {
    Generator.init("tekton", "tekton-pipeline", "tekton-pipeline-run", "tekton-task", "tekton-task-run");
    Generator.apply(meta);
    Generator.generate();
  }
}
