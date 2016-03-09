package my.projects;

import java.util.ArrayList;
import java.util.List;

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
