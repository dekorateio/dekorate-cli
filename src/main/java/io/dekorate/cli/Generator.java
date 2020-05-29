
package io.dekorate.cli;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.dekorate.Session;
import io.dekorate.SessionReader;
import io.dekorate.SessionWriter;
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

  public static void generate(String... platforms)  {
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
    session.close();
 
  } 
}
