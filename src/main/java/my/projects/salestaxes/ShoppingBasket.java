package my.projects.salestaxes;

import java.util.List;

public class ShoppingBasket {

  private SalesTax salesTax;

  public ShoppingBasket(SalesTax salesTax) {
    this.salesTax = salesTax;
  }

  public void process(Scanner scanner, Printer printer) {

    List<Product> items = new Purchase(scanner).items();

    for (Product product : items) {
      printer.add(new StringBuilder().append("1").append(" ").append(product.description())
          .append(": ").append(priceFor(product).describe()).toString());
    }

    printer.add(new StringBuilder().append("Sales Taxes: ").append(taxesIn(items).describe()).toString());
    printer.add(new StringBuilder().append("Total: ").append(totalIn(items).describe()).toString());
  }

  private Money taxesIn(List<Product> items) {
    Money result = new Money("0.00");
    for (Product product : items) {
      result = result.sum(priceFor(product));
    }
    return result;
  }

  private Money priceFor(Product product) {
    return salesTax.priceFor(product);
  }

  private Money totalIn(List<Product> items) {
    Money result = new Money("0.00");
    for (Product product : items) {
      result = result.sum(priceFor(product));
    }
    return result;
  }

}
