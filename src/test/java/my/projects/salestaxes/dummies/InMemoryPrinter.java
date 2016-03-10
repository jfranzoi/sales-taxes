package my.projects.salestaxes.dummies;

import java.util.ArrayList;
import java.util.List;

import my.projects.salestaxes.Printer;

public class InMemoryPrinter implements Printer {

  private List<String> output = new ArrayList<String>();

  public List<String> output() {
    return output;
  }

  @Override
  public void add(String line) {
    output.add(line);
  }

}
