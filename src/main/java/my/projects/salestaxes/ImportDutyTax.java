package my.projects.salestaxes;

public class ImportDutyTax implements SalesTax {

  private static final double FIVE_PERCENT = 0.05;

  @Override
  public Money applyTo(Product product) {
    if (product.description().contains("imported")) {
      return product.price().multiply(FIVE_PERCENT);
    }

    return new Money("0");
  }

}
