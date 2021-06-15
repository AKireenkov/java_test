package ru.stqa.test.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("AKireenkov", "java_test")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
