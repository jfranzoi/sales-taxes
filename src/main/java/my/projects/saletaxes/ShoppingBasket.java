package my.projects.saletaxes;

import java.util.List;

public class ShoppingBasket {

  public void process(Scanner scanner, Printer printer) {

    List<Product> items = new Purchase(scanner).items();
    Money total = new Money("0.00");
    for (Product product : items) {
      total = total.sum(product.price());
    }

    printer.add("Sales Taxes: 0.00");
    printer.add(new StringBuilder().append("Total: ").append(total.describe()).toString());
  }

}
