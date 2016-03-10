package my.projects.salestaxes;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import my.projects.salestaxes.dummies.FileContent;
import my.projects.salestaxes.dummies.InMemoryPrinter;

public class AcceptanceTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private File purchase;
  private InMemoryPrinter printer;

  @Before
  public void setUp() throws Exception {
    purchase = folder.newFile("purchase.txt");
    printer = new InMemoryPrinter();
  }

  @Test
  public void testTaxesOnGoodsCategories() throws Exception {

    new FileContent(purchase)
          .append("1 book at 12.49")
          .append("1 music CD at 14.99")
          .append("1 chocolate bar at 0.85")
          .save();
    
    new ShoppingBasket().process(new FileScanner(purchase), printer);
    
    assertThat(printer.output(), contains(  "1 book: 12.49", 
                                            "1 music CD: 16.49",
                                            "1 chocolate bar: 0.85", 
                                            "Sales Taxes: 1.50", 
                                            "Total: 29.83"
    ));
  }
  
  @Test
  public void testTaxesOnImportedGoods() throws Exception {

    new FileContent(purchase)
          .append("1 imported box of chocolates at 10.00")
          .append("1 imported bottle of perfume at 47.50")
          .save();
    
    new ShoppingBasket().process(new FileScanner(purchase), printer);
    
    assertThat(printer.output(), contains(  "1 imported box of chocolates: 10.50",
                                            "1 imported bottle of perfume: 54.65",
                                            "Sales Taxes: 7.65",
                                            "Total: 65.15"
    ));
  }
  
  @Test
  public void testRounding() throws Exception {
    new FileContent(purchase)
          .append("1 imported bottle of perfume at 27.99")
          .append("1 bottle of perfume at 18.99")
          .append("1 packet of headache pills at 9.75")
          .append("1 box of imported chocolates at 11.25")
          .save();

    new ShoppingBasket().process(new FileScanner(purchase), printer);

    assertThat(printer.output(), contains(  "1 imported bottle of perfume: 32.19",
                                            "1 bottle of perfume: 20.89", 
                                            "1 packet of headache pills: 9.75", 
                                            "1 imported box of chocolates: 11.85", 
                                            "Sales Taxes: 6.70",
                                            "Total: 74.68"
    ));
  }

}
