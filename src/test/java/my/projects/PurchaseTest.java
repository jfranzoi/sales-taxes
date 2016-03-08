package my.projects;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PurchaseTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private File source;

  @Before
  public void setUp() throws Exception {
    source = folder.newFile();
  }

  @Test
  public void testNoProduct() throws Exception {
    assertThat(new Purchase(source).items(), empty());
  }

  @Test
  public void testWithProducts() throws Exception {
    new FileContent(source).append("1 book at 12.49").append("1 music CD at 14.99").save();

    assertThat(new Purchase(source).items(),
        contains(new Product("book", "12.49"), new Product("music CD", "14.99")));
  }
}
