package my.projects;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Money {

  private static final MathContext PRECISION = new MathContext(2, RoundingMode.HALF_UP);

  private BigDecimal amount;

  public Money(String amount) {
    this(bigDecimalFor(amount));
  }

  private static BigDecimal bigDecimalFor(String amount) {
    return new BigDecimal(amount, PRECISION);
  }

  private Money(BigDecimal amount) {
    this.amount = amount;
  }

  public Money multiply(double times) {
    return new Money(amount.multiply(new BigDecimal(times), PRECISION));
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

}
