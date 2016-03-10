package my.projects.salestaxes;

public class SalesTaxesRounding {

  private static final double FIVE_CENTS = 0.05;

  public Money applyTo(Money money) {
    double factor = 1 / FIVE_CENTS;
    return money.multiply(factor).ceil().divide(factor);
  }

}
