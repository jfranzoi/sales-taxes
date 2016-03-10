package my.projects.salestaxes;

import java.util.Arrays;
import java.util.List;

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
      printer.add(concat("1", " ", product.description(), ": ", format(priceFor(product))));
    }

    printer.add(concat("Sales Taxes: ", format(taxesIn(items))));
    printer.add(concat("Total: ", format(totalIn(items))));
  }

  private String format(Money money) {
    return money.describe();
  }

  private Money taxesIn(List<Product> items) {
    Money result = new Money("0.00");
    for (Product product : items) {
      result = result.sum(taxesFor(product));
    }
    return result;
  }

  private Money taxesFor(Product product) {
    Money result = new Money("0.00");
    for (SalesTax tax : salesTaxes) {
      result = result.sum(tax.applyTo(product));
    }

    return new SalesTaxesRounding().applyTo(result);
  }

  private Money totalIn(List<Product> items) {
    Money result = new Money("0.00");
    for (Product product : items) {
      result = result.sum(priceFor(product));
    }
    return result;
  }

  private Money priceFor(Product product) {
    return product.price().sum(taxesFor(product));
  }

  private String concat(String... items) {
    return String.join("", Arrays.asList(items));
  }

}
