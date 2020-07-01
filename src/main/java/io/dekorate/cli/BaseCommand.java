
package io.dekorate.cli;

import java.util.concurrent.Callable;

public interface BaseCommand extends Callable<Integer> {

  public void execute();
  
  default Integer call() {
    try {
      execute();
      return 0;
    } catch (Throwable t) {
      System.err.println(t.getMessage());
      return 1;
    }
  }
}
