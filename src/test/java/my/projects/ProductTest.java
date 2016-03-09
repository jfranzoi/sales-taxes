package my.projects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProductTest {

  @Test
  public void testPrice() throws Exception {
    assertThat(new Product("any", "10.50").price(), is(new Money("10.50")));
  }

}
