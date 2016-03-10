package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProductTest {

  @Test
  public void testPrice() throws Exception {
    assertThat(new Product("any", "10.50").price(), is(new Money("10.50")));
  }

  @Test
  public void testDescription() throws Exception {
    assertThat(new Product("box of chocolates", "0").description(), is("box of chocolates"));
  }
  
  @Test
  public void testDescriptionImported() throws Exception {
    assertThat(new Product("box of imported chocolates", "0").description(), is("imported box of chocolates"));
  }

}
