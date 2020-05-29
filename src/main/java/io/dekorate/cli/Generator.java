
package io.dekorate.cli;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.dekorate.Session;
import io.dekorate.SessionReader;
import io.dekorate.SessionWriter;
import io.dekorate.kubernetes.config.Annotation;
import io.dekorate.kubernetes.config.Label;
import io.dekorate.kubernetes.decorator.AddAnnotationDecorator;
import io.dekorate.kubernetes.decorator.AddLabelDecorator;
import io.dekorate.processor.SimpleFileReader;
import io.dekorate.processor.SimpleFileWriter;
import io.dekorate.project.FileProjectFactory;
import io.dekorate.project.Project;

public class Generator {

  private static final String DOT = ".";
  private static final String DOT_DEKORATE = ".dekorate";
  private static final String KUBERNETES = "kubernetes";
  private static final String SRC = "src";
  private static final String MAIN = "main";
  private static final String RESOURCES = "resources";

  public static void init(String... platforms)  {
    Set<String> targets = new HashSet<>(Arrays.asList(platforms));

    Project project = new FileProjectFactory().create(new File(DOT));

    final SessionWriter sessionWriter = new SimpleFileWriter(
        project.getBuildInfo().getClassOutputDir().getParent().resolve(DOT_DEKORATE),
        project.getBuildInfo().getClassOutputDir().getParent().resolve(KUBERNETES), true, targets);

    final SessionReader sessionReader = new SimpleFileReader(project.getRoot().resolve(SRC).resolve(MAIN).resolve(KUBERNETES), targets);
    sessionWriter.setProject(project);

    final Session session = Session.getSession();
    session.setWriter(sessionWriter);
    session.setReader(sessionReader);
  } 

  public static void generate() {
    final Session session = Session.getSession();
    session.close();
  }


  public static void applyMeta(MetaOptions meta) {
    Session session = Session.getSession();
    if (meta.annotations != null) {
      meta.annotations.forEach((key,value) -> session.resources().decorate(new AddAnnotationDecorator(new Annotation(key, value))));
    }
    if (meta.labels != null) {
      meta.labels.forEach((key,value) -> session.resources().decorate(new AddLabelDecorator(new Label(key, value))));
    }
  }
}
