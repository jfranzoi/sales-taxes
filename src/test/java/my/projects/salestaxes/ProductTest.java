package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import my.projects.salestaxes.Money;
import my.projects.salestaxes.Product;

public class ProductTest {

  @Test
  public void testPrice() throws Exception {
    assertThat(new Product("any", "10.50").price(), is(new Money("10.50")));
  }

}
