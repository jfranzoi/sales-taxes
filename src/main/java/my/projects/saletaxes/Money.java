package my.projects.saletaxes;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Money {

  private static final int PRECISION = 4;
  private BigDecimal amount;

  public Money(String string) {
    this(precise(new BigDecimal(string)));
  }

  private Money(BigDecimal amount) {
    this.amount = amount;
  }

  public Money sum(Money money) {
    return new Money(amount.add(money.amount));
  }

  public Money multiply(double times) {
    return new Money(amount.multiply(precise(new BigDecimal(times)), rounded()));
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
    return new StringBuilder().append(this.getClass().getName()).append(": ").append(amount)
        .toString();
  }

  private static BigDecimal precise(BigDecimal result) {
    result.setScale(PRECISION, RoundingMode.CEILING);
    return result;
  }

  private MathContext rounded() {
    return new MathContext(PRECISION, RoundingMode.HALF_UP);
  }

}
