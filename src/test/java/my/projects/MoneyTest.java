package my.projects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MoneyTest {

  @Test
  public void testApplyTaxes() throws Exception {
    assertThat(new Money("100.00").multiply(1.10), is(new Money("110.00")));
  }

  @Test
  public void testEquality() throws Exception {
    assertThat(new Money("1.0"), is(new Money("1.0")));
    assertThat(new Money("1.00"), is(new Money("1.0")));
    assertThat(new Money("1"), is(new Money("1.0")));
  }

}
