package my.projects.salestaxes;

import java.util.Arrays;
import java.util.List;

public class ShoppingBasket {

  private SalesTax salesTax;

  public ShoppingBasket(SalesTax salesTax) {
    this.salesTax = salesTax;
  }

  public void process(Scanner scanner, Printer printer) {

    List<Product> items = new Purchase(scanner).items();

    for (Product product : items) {
      printer.add(concat("1", " ", product.description(), ": ", priceFor(product).describe()));
    }

    printer.add(concat("Sales Taxes: ", taxesIn(items).describe()));
    printer.add(concat("Total: ", totalIn(items).describe()));
  }

  private Money priceFor(Product product) {
    return salesTax.priceFor(product);
  }

  private Money taxesIn(List<Product> items) {
    Money result = new Money("0.00");
    for (Product product : items) {
      result = result.sum(priceFor(product));
    }
    return result;
  }

  private Money totalIn(List<Product> items) {
    Money result = new Money("0.00");
    for (Product product : items) {
      result = result.sum(priceFor(product));
    }
    return result;
  }

  private String concat(String... items) {
    return String.join("", Arrays.asList(items));
  }

}
