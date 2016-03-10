package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BasicSalesTaxTest {

  @Test
  public void testPriceWithRate() throws Exception {
    assertThat(new BasicSalesTax().priceFor(new Product("music CD", "10")), is(new Money("11")));
    assertThat(new BasicSalesTax().priceFor(new Product("perfume", "10")), is(new Money("11")));
  }

  @Test
  public void testNotAppliedOnSpecialCategories() throws Exception {
    assertThat(new BasicSalesTax().priceFor(new Product("book", "10")), is(new Money("10")));
    assertThat(new BasicSalesTax().priceFor(new Product("chocolate bar", "10")), is(new Money("10")));
    assertThat(new BasicSalesTax().priceFor(new Product("headache pills", "10")), is(new Money("10")));
  }

  @Test
  public void testTaxRate() throws Exception {
    assertThat(new BasicSalesTax().taxesFor(new Product("music CD", "10")), is(new Money("1")));
  }

  @Test
  public void testTaxRateNotApplied() throws Exception {
    assertThat(new BasicSalesTax().taxesFor(new Product("book", "10")), is(new Money("0")));
  }

}
