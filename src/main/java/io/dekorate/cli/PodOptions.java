
package io.dekorate.cli;

import picocli.CommandLine.Option;

public class PodOptions {

  @Option(names = { "-r", "--replicas" }, description = {"The number of replicas to set." })
  protected int replicas;
}
