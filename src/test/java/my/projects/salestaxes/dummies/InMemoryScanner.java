package my.projects.salestaxes.dummies;

import java.util.ArrayList;
import java.util.List;

import my.projects.saletaxes.Scanner;

public class InMemoryScanner implements Scanner {

  private ArrayList<String> lines = new ArrayList<String>();

  public InMemoryScanner append(String line) {
    lines.add(line);
    return this;
  }

  @Override
  public List<String> lines() {
    return lines;
  }

}
