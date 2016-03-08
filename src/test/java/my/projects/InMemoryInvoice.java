package my.projects;

import java.util.ArrayList;
import java.util.List;

public class InMemoryInvoice implements Invoice {

  private List<String> output = new ArrayList<String>();

  public List<String> output() {
    return output;
  }

  @Override
  public void add(String line) {
    output.add(line);
  }

}
