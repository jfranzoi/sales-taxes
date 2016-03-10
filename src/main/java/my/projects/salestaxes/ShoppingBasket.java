package my.projects.salestaxes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ShoppingBasket {

  private List<SalesTax> salesTaxes;

  public ShoppingBasket() {
    this(Arrays.asList(new BasicSalesTax(), new ImportDutyTax()));
  }

  public ShoppingBasket(List<SalesTax> salesTaxes) {
    this.salesTaxes = salesTaxes;
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
    return product.price().sum(taxesFor(product));
  }

  private Money taxesIn(List<Product> items) {
    return sumUp(items, p -> taxesFor(p));
  }

  private Money taxesFor(Product product) {
    return new SalesTaxesRounding().applyTo(sumUp(salesTaxes, t -> t.applyTo(product)));
  }

  private Money totalIn(List<Product> items) {
    return sumUp(items, p -> priceFor(p));
  }

  private <T> Money sumUp(List<T> items, Function<T, Money> mapper) {
    return items.stream()
              .map(mapper)
              .reduce(new Money("0.00"), (x, y) -> x.sum(y));
  }

  private String concat(String... items) {
    return String.join("", Arrays.asList(items));
  }

}
