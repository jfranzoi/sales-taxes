package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BasicSalesTaxTest {

  @Test
  public void testPriceWithRate() throws Exception {
    assertThat(new BasicSalesTax().applyTo(new Product("music CD", "10")), is(new Money("1")));
    assertThat(new BasicSalesTax().applyTo(new Product("perfume", "10")), is(new Money("1")));
  }

  @Test
  public void testNotAppliedOnSpecialCategories() throws Exception {
    assertThat(new BasicSalesTax().applyTo(new Product("book", "10")), is(new Money("0")));
    assertThat(new BasicSalesTax().applyTo(new Product("chocolate bar", "10")), is(new Money("0")));
    assertThat(new BasicSalesTax().applyTo(new Product("headache pills", "10")), is(new Money("0")));
  }

  @Test
  public void testTaxRate() throws Exception {
    assertThat(new BasicSalesTax().applyTo(new Product("music CD", "10")), is(new Money("1")));
  }

  @Test
  public void testTaxRateNotApplied() throws Exception {
    assertThat(new BasicSalesTax().applyTo(new Product("book", "10")), is(new Money("0")));
  }

}
