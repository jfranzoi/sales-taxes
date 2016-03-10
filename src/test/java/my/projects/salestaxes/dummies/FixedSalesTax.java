package my.projects.salestaxes.dummies;

import my.projects.salestaxes.Money;
import my.projects.salestaxes.Product;
import my.projects.salestaxes.SalesTax;

public class FixedSalesTax implements SalesTax {

  private Money taxes;

  public FixedSalesTax(Money taxes) {
    this.taxes = taxes;
  }

  @Override
  public Money applyTo(Product product) {
    return taxes;
  }

}
