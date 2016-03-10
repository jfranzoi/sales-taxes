package my.projects.salestaxes;

import java.util.List;

public class ShoppingBasket {

  public void process(Scanner scanner, Printer printer) {

    List<Product> items = new Purchase(scanner).items();

    for (Product product : items) {
      printer.add(new StringBuilder().append("1").append(" ").append(product.description())
          .append(": ").append(product.price().describe()).toString());
    }

    printer.add("Sales Taxes: 0.00");
    printer.add(new StringBuilder().append("Total: ").append(totalIn(items).describe()).toString());
  }

  private Money totalIn(List<Product> items) {
    Money total = new Money("0.00");
    for (Product product : items) {
      total = total.sum(product.price());
    }
    return total;
  }

}
