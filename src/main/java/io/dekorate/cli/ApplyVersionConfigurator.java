package io.dekorate.cli;

import io.dekorate.kubernetes.config.ApplicationConfigurationFluent;
import io.dekorate.kubernetes.config.Configurator;

public class ApplyVersionConfigurator extends Configurator<ApplicationConfigurationFluent<?>> {

  private final String version;

  public ApplyVersionConfigurator(String version) {
    this.version = version;
  }

  @Override
  public void visit(ApplicationConfigurationFluent<?> app) {
    app.withVersion(version);
  }

}
