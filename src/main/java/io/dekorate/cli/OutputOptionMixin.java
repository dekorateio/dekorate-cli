package io.dekorate.cli;

import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;

public class OutputOptionMixin {
  
    @CommandLine.Spec(CommandLine.Spec.Target.MIXEE)
    CommandSpec mixee;

    private static OutputOptionMixin getOutput(CommandSpec commandSpec) {
        return ((BaseCommand) commandSpec.root().userObject()).getOutput();
    }

    boolean verbose = false;

    @CommandLine.Option(names = { "--verbose" }, description = "Verbose mode.")
    public void setVerbose(boolean verbose) {
        getOutput(mixee).verbose = verbose;
    }

    public boolean getVerbose() {
        return getOutput(mixee).verbose;
    }

    public boolean isVerbose() {
        return getVerbose();
    }
}
