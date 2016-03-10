package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MoneyTest {

  @Test
  public void testMultipyIdentity() throws Exception {
    assertThat(new Money("100.00").multiply(1), is(new Money("100.00")));
  }

  @Test
  public void testMultipyAugmented() throws Exception {
    assertThat(new Money("100.00").multiply(1.10), is(new Money("110.00")));
    assertThat(new Money("100.00").multiply(1.01), is(new Money("101.00")));
  }

  @Test
  public void testMultipyDiminished() throws Exception {
    assertThat(new Money("100.00").multiply(0.90), is(new Money("90.00")));
    assertThat(new Money("100.00").multiply(0.01), is(new Money("1.00")));
  }

  @Test
  public void testSum() throws Exception {
    assertThat(new Money("10.50").sum(new Money("0.50")), is(new Money("11.00")));
    assertThat(new Money("10.51").sum(new Money("0.01")), is(new Money("10.52")));
  }

  @Test
  public void testDescribe() throws Exception {
    assertThat(new Money("12.3").describe(), is("12.30"));
    assertThat(new Money("12.34").describe(), is("12.34"));
    assertThat(new Money("12.340").describe(), is("12.34"));
  }

  @Test
  public void testComplexCalculations() throws Exception {
    Money money = new Money("14.99");
    assertThat(money.sum(money.multiply(0.10)), is(new Money("16.49")));
    
    assertThat( new Money("10.11").multiply(20), is(new Money("202.20")));
  }

  @Test
  public void testRounding() throws Exception {
    Money money = new Money("47.50");
    assertThat(money.multiply(0.05).sum(money.multiply(0.10)), is(new Money("7.13")));
  }

  @Test
  public void testEquality() throws Exception {
    assertThat(new Money("1.0"), is(new Money("1.0")));
    assertThat(new Money("1.00"), is(new Money("1.0")));
    assertThat(new Money("1"), is(new Money("1.0")));
  }

}
