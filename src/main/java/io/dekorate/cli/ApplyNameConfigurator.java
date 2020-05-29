
package io.dekorate.cli;

import io.dekorate.kubernetes.config.ApplicationConfigurationFluent;
import io.dekorate.kubernetes.config.Configurator;

public class ApplyNameConfigurator extends Configurator<ApplicationConfigurationFluent<?>> {

  private final String name;

  public ApplyNameConfigurator(String name) {
    this.name = name;
  }

  @Override
  public void visit(ApplicationConfigurationFluent<?> app) {
    app.withName(name);
  }

}
