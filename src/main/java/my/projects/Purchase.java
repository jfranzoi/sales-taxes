package my.projects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Purchase {

  private static final String LINE_FORMAT = "(?<amount>\\d*) (?<description>[\\w\\s]*) at (?<price>\\d*\\.\\d*)";
  private File source;

  public Purchase(File source) {
    this.source = source;
  }

  public List<Product> items() {
    return linesAt(source).stream()
        .map(line -> new PatternGroups(line, LINE_FORMAT))
        .map(groups -> new Product(groups.at("description"), groups.at("price")))
        .collect(Collectors.toList());
  }

  private List<String> linesAt(File file) {
    List<String> lines = new ArrayList<String>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (Exception e) {
    }

    return lines;
  }

}
