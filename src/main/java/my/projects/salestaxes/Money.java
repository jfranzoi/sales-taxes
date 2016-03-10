package my.projects.salestaxes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Money {

  private static final int SCALE = 2;
  private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

  private BigDecimal amount;

  public Money(String string) {
    this(new BigDecimal(string));
  }

  private Money(BigDecimal amount) {
    this.amount = amount.setScale(SCALE, ROUNDING);
  }

  public Money sum(Money money) {
    return new Money(amount.add(money.amount));
  }

  public Money multiply(double times) {
    return new Money(amount.multiply(new BigDecimal(times), roundTo(SCALE * 2)));
  }

  public Money divide(double factor) {
    return new Money(amount.divide(new BigDecimal(factor)));
  }

  public Money ceil() {
    double doubleValue = amount.doubleValue();
    return new Money(new BigDecimal(Math.ceil(doubleValue)));
  }

  public String describe() {
    return amount.toPlainString();
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
    return new MathContext(precision, ROUNDING);
  }

}
