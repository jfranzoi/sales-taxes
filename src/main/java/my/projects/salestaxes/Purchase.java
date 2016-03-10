package my.projects.salestaxes;

import java.util.List;
import java.util.stream.Collectors;

public class Purchase {

  private static final String LINE_FORMAT = "(?<amount>\\d*) (?<description>[\\w\\s]*) at (?<price>\\d*\\.\\d*)";

  private Scanner scanner;

  public Purchase(Scanner scanner) {
    this.scanner = scanner;
  }

  public List<Product> items() {
    return scanner.lines().stream()
        .map(line -> new PatternGroups(line, LINE_FORMAT))
        .map(groups -> new Product(groups.at("description"), groups.at("price")))
        .collect(Collectors.toList());
  }

}
