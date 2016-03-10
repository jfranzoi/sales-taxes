package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SalesTaxesRoundingTest {

  @Test
  public void testRoundUpToHalf() throws Exception {
    assertThat(new SalesTaxesRounding().applyTo(new Money("10.11")), is(new Money("10.15")));
    assertThat(new SalesTaxesRounding().applyTo(new Money("10.13")), is(new Money("10.15")));
  }

  @Test
  public void testRoundUpToOne() throws Exception {
    assertThat(new SalesTaxesRounding().applyTo(new Money("10.16")), is(new Money("10.20")));
    assertThat(new SalesTaxesRounding().applyTo(new Money("10.19")), is(new Money("10.20")));
  }

  @Test
  public void testAnyPrecisionAmount() throws Exception {
    assertThat(new SalesTaxesRounding().applyTo(new Money("0.563")), is(new Money("0.60")));
  }

}
