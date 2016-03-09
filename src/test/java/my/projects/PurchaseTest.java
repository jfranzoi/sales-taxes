package my.projects;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PurchaseTest {

  @Test
  public void testWithProducts() throws Exception {
    Scanner scanner = new InMemoryScanner().append("1 book at 12.49").append("1 music CD at 14.99");

    assertThat(new Purchase(scanner).items(),
        contains(new Product("book", "12.49"), new Product("music CD", "14.99")));
  }

}
