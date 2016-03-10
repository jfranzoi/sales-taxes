package my.projects.salestaxes;

public class SalesTaxesRounding {

  private static final double _0_05 = 0.05;

  public Money applyTo(Money money) {
    double factor = 1 / _0_05;
    return money.multiply(factor).withoutDecimals().divide(factor);
  }

}
