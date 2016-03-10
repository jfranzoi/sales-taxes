package my.projects.salestaxes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Money {

  private static final int PRECISION = 3;
  private BigDecimal amount;

  public Money(String string) {
    this(new BigDecimal(string));
  }

  private Money(BigDecimal amount) {
    this.amount = amount;
  }

  public Money sum(Money money) {
    return new Money(amount.add(money.amount));
  }

  public Money multiply(double times) {
    return new Money(amount.multiply(new BigDecimal(times), roundTo(PRECISION)));
  }

  public Money divide(double factor) {
    return new Money(amount.divide(new BigDecimal(factor)));
  }

  public Money withoutDecimals() {
    return new Money(amount.round(new MathContext(0)));
  }

  public Money scaleTo(double scale) {
    double factor = 1 / scale;
    return multiply(factor).withoutDecimals().divide(factor);
  }

  public String describe() {
    return amount.setScale(2).toPlainString();
  }

  @Override
  public boolean equals(Object obj) {
    Money other = (Money) obj;
    return this.amount.compareTo(other.amount) == 0;
  }

  @Override
  public String toString() {
    return describe();
  }

  private MathContext roundTo(int precision) {
    return new MathContext(precision, RoundingMode.HALF_UP);
  }

}
