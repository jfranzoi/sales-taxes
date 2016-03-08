package my.projects;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@Ignore("WIP")
public class AcceptanceTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private File purchase;
  private InMemoryInvoice display;

  @Before
  public void setUp() throws Exception {
    purchase = folder.newFile("purchase.txt");
    display = new InMemoryInvoice();
  }

  @Test
  public void testOne() throws Exception {

    new FileContent(purchase)
      .append("1 book at 12.49")
      .append("1 music CD at 14.99")
      .append("1 chocolate bar at 0.85")
      .save();
    
    new ShoppingBasket( display ).process(purchase);
    
    assertThat(display.output(), contains(  "1 book: 12.49", 
                                            "1 music CD: 16.49",
                                            "1 chocolate bar: 0.85", 
                                            "Sales Taxes: 1.50", 
                                            "Total: 29.83"
    ));
  }

}
