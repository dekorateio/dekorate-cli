
package io.dekorate.cli;

import java.util.concurrent.Callable;

import picocli.CommandLine;

public class BaseCommand implements Callable<Integer> {

  @CommandLine.Mixin(name = "output")
  OutputOptionMixin output;

  boolean verbose;

  public void execute() {
  }

  @Override
  public Integer call() throws Exception {
    try {
      execute();
      return 0;
    } catch (Throwable t) {
      if (output.isVerbose()) {
        t.printStackTrace(System.err);
      } else {
        System.err.println(getRootCauseMessage(t));
      }
      return 1;
    }
  }

  public OutputOptionMixin getOutput() {
    return output;
  }

  String getRootCauseMessage(Throwable t) {
    while (t.getCause() != null) {
      t = t.getCause();
    }
    return t.getMessage();
  }
}
