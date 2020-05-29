
package io.dekorate.cli;

import java.util.Map;

import picocli.CommandLine.Option;

public class MetaOptions {

  // @Option(names = { "-n", "--name" }, description = {"Specify the name of the application.", "This is going to be used as the default name of the generated manifests." })
  // protected String name;

  // @Option(names = { "-v", "--version" }, description = {"Specify the version of the application.", "This is going to be used as the default version/tag of the generated manifests." })
  // protected String version;

  @Option(names = { "-l", "--label" }, description = {"Specify one or more key/value pairs as labels (FOO=BAR).", "These are going to be added to all generated resources." })
  protected Map<String, String> labels;

  @Option(names = { "-a", "--annotation" }, description = {"Specify one or more key/value pairs as annotations (FOO=BAR).", "These are going to be added to all generated resources." })
  protected Map<String, String> annotations;

}
