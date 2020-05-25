package io.dekorate.cli;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import io.dekorate.Session;
import io.dekorate.SessionReader;
import io.dekorate.SessionWriter;
import io.dekorate.processor.SimpleFileReader;
import io.dekorate.processor.SimpleFileWriter;
import io.dekorate.project.FileProjectFactory;
import io.dekorate.project.Project;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main implements QuarkusApplication {

  public int run(String... args) throws Exception {
    Set<String> targets = new HashSet<>();
    targets.add("kubernetes");
    Project project = new FileProjectFactory().create(new File("."));
    final SessionWriter sessionWriter = new SimpleFileWriter(project.getBuildInfo().getClassOutputDir().getParent().resolve("kubernetes"), true);
    final SessionReader sessionReader = new SimpleFileReader(project.getRoot().resolve("src").resolve("main").resolve("kubernetes"), targets);
    sessionWriter.setProject(project);
    final Session session = Session.getSession();
    session.setWriter(sessionWriter);
    session.setReader(sessionReader);
    session.close();
    return 1;
  }
}

