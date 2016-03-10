package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import my.projects.saletaxes.Money;
import my.projects.saletaxes.Product;

public class ProductTest {

  @Test
  public void testPrice() throws Exception {
    assertThat(new Product("any", "10.50").price(), is(new Money("10.50")));
  }

}
