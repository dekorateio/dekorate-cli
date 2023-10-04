package io.dekorate.cli;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import picocli.CommandLine;
import picocli.CommandLine.RunAll;

@QuarkusMain
public class DekorateCli implements QuarkusApplication {

  public int run(String... args) throws Exception {
    CommandLine cmd = new CommandLine(new DekorateCmd())
      .addSubcommand(new KubernetesCmd())
      .addSubcommand(new KnativeCmd())
      .addSubcommand(new TektonCmd())
      .addSubcommand(new OpenshiftCmd());

    cmd.setExecutionStrategy(new RunAll());
    return cmd.execute(args);
  }

}
