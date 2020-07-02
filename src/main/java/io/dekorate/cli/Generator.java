
package io.dekorate.cli;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.dekorate.Session;
import io.dekorate.SessionReader;
import io.dekorate.SessionWriter;
import io.dekorate.WithProject;
import io.dekorate.kubernetes.config.Annotation;
import io.dekorate.kubernetes.config.Env;
import io.dekorate.kubernetes.config.EnvBuilder;
import io.dekorate.kubernetes.config.Label;
import io.dekorate.kubernetes.config.PortBuilder;
import io.dekorate.kubernetes.configurator.AddPort;
import io.dekorate.kubernetes.decorator.AddAnnotationDecorator;
import io.dekorate.kubernetes.decorator.AddEnvVarDecorator;
import io.dekorate.kubernetes.decorator.AddLabelDecorator;
import io.dekorate.processor.SimpleFileReader;
import io.dekorate.processor.SimpleFileWriter;
import io.dekorate.project.FileProjectFactory;
import io.dekorate.project.Project;
import io.dekorate.utils.Strings;

public class Generator implements WithProject {

  private static final String DOT = ".";
  private static final String DOT_DEKORATE = ".dekorate";
  private static final String KUBERNETES = "kubernetes";
  private static final String SRC = "src";
  private static final String CONFIG = "config";
  private static final String MAIN = "main";
  private static final String RESOURCES = "resources";

  public static void init(String... platforms)  {
    Set<String> targets = new HashSet<>(Arrays.asList(platforms));

    Project project = null;

    try {
      project = new FileProjectFactory().create(new File(DOT));
    } catch (Throwable t) {
      throw new RuntimeException("Failed to collect project info!\nMake sure that a project is of a supported type: [maven, gralde, sbt, bazel, npm].");
    }

    final SessionWriter sessionWriter = new SimpleFileWriter(project.getRoot().resolve(DOT_DEKORATE), project.getRoot().resolve(DOT_DEKORATE).resolve(KUBERNETES), true, targets);

    final SessionReader sessionReader = new SimpleFileReader(getInputPath(project), targets);

    sessionWriter.setProject(project);

    final Session session = Session.getSession();
    session.setWriter(sessionWriter);
    session.setReader(sessionReader);
    session.enable(platforms);
  } 

  public static void generate() {
    final Session session = Session.getSession();
    session.close();
  }

  public static void apply(MetaOptions meta) {
    Session session = Session.getSession();
    if (Strings.isNotNullOrEmpty(meta.name)) {
      session.configurators().add(new ApplyNameConfigurator(meta.name));
    }

    if (Strings.isNotNullOrEmpty(meta.version)) {
      session.configurators().add(new ApplyVersionConfigurator(meta.version));
    }
    
    if (meta.annotations != null) {
      meta.annotations.forEach((key,value) -> session.resources().decorate(new AddAnnotationDecorator(new Annotation(key, value))));
    }
    if (meta.labels != null) {
      meta.labels.forEach((key,value) -> session.resources().decorate(new AddLabelDecorator(new Label(key, value))));
    }
  }

  public static void apply(PodOptions pod) {
    Session session = Session.getSession();
    if (pod.replicas != 1) {
      session.resources().decorate(new io.dekorate.kubernetes.decorator.ApplyReplicasDecorator(null, pod.replicas));
      session.resources().decorate(new io.dekorate.openshift.decorator.ApplyReplicasDecorator(null, pod.replicas));
    }
  }

  public static void apply(ContainerOptions container) {
    Session session = Session.getSession();
    if (container.envVars != null) {
      container.envVars.forEach((key,value) -> session.resources().decorate(new AddEnvVarDecorator(new Env(key, value, null, null, null))));
    }

    if (container.envFromSecrets != null) {
      container.envFromSecrets.forEach(s -> session.resources().decorate(new AddEnvVarDecorator(new EnvBuilder().withSecret(s).build())));
    }

    if (container.envFromConfigMaps != null) {
      container.envFromConfigMaps.forEach(c -> session.resources().decorate(new AddEnvVarDecorator(new EnvBuilder().withConfigmap(c).build())));
    }

if (container.ports != null) {
  container.ports.forEach((key,value) -> session.configurators().add(new AddPort(new PortBuilder().withName(key).withContainerPort(value).build())));
    }

if (container.exposeEnabled) {
  session.configurators().add(new ExposeServiceConfigurator());
}
  }



  public static Path getInputPath(Project project) {
    String tool = project.getBuildInfo().getBuildTool();
    switch (tool) {
    case "npm":
      return project.getRoot().resolve(CONFIG).resolve(KUBERNETES);
    default:
      return project.getRoot().resolve(SRC).resolve(MAIN).resolve(KUBERNETES);
    }
  }
}

