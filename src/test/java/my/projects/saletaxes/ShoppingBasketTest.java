package my.projects.saletaxes;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import my.projects.salestaxes.dummies.InMemoryPrinter;
import my.projects.salestaxes.dummies.InMemoryScanner;

public class ShoppingBasketTest extends ShoppingBasket {

  private InMemoryPrinter printer;


  @Before
  public void setUp() {
    printer = new InMemoryPrinter();
  }

  @Test
  public void testEmptyBasketTotal() throws Exception {
    new ShoppingBasket().process(new InMemoryScanner(), printer);
    assertThat(printer.output(), hasItem("Total: 0.00"));
  }

  @Test
  public void testEmptyBasketTaxes() throws Exception {
    new ShoppingBasket().process(new InMemoryScanner(), printer);
    assertThat(printer.output(), hasItem("Sales Taxes: 0.00"));
  }

  @Test
  public void testItemsTotal() throws Exception {
    InMemoryScanner scanner = new InMemoryScanner()
                                  .append("1 book at 12.49")
                                  .append("1 music CD at 16.49")
                                  .append("1 chocolate bar at 0.85");
    
    new ShoppingBasket().process(scanner, printer);
    
    assertThat(printer.output(), hasItem("Total: 29.83"));
  }

}
