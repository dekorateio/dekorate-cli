
package io.dekorate.cli;

import java.util.List;
import java.util.Map;

import picocli.CommandLine.Option;

public class ContainerOptions {

  @Option(names = { "-e", "--env-var" }, description = {"Specify one or more key/value pairs as environment variables (FOO=BAR).", "These are going to be added to all containers." })
  protected Map<String, String> envVars;
  
  @Option(names = { "-es", "--env-from-secret" }, description = {"Specify one or more secrets to be added as envrionment variable sources.", "These are going to be added to all containers." })
  protected List<String> envFromSecrets;

  @Option(names = { "-ec", "--env-from-configmap" }, description = {"Specify one or more configmaps to be added as envrionment variable sources.", "These are going to be added to all containers." })
  protected List<String> envFromConfigMaps;

  @Option(names = { "-p", "--port" }, description = {"Specify one or more name/port pairs (e.g. http=8080).", "These are going to be added to all containers." })
  protected Map<String, Integer> ports;
 

}
