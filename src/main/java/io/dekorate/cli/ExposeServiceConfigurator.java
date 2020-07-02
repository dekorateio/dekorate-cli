
package io.dekorate.cli;

import io.dekorate.kubernetes.config.Configurator;
import io.dekorate.kubernetes.config.KubernetesConfigFluent;

public class ExposeServiceConfigurator extends Configurator<KubernetesConfigFluent<?>> {

  @Override
  public void visit(KubernetesConfigFluent<?> config) {
    config.withExpose(true);
  }
}
