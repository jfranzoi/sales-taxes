package my.projects.salestaxes;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import my.projects.salestaxes.dummies.FixedSalesTax;
import my.projects.salestaxes.dummies.InMemoryPrinter;
import my.projects.salestaxes.dummies.InMemoryScanner;

@SuppressWarnings("unchecked")
public class ShoppingBasketTest {

  private List<SalesTax> noTaxes = fixedTaxes("0");
  private InMemoryPrinter printer;

  @Before
  public void setUp() {
    printer = new InMemoryPrinter();
  }

  @Test
  public void testEmptyBasketTotal() throws Exception {
    new ShoppingBasket(noTaxes).process(new InMemoryScanner(), printer);

    assertThat(printer.output(), hasItem("Total: 0.00"));
  }

  @Test
  public void testEmptyBasketTaxes() throws Exception {
    new ShoppingBasket(noTaxes).process(new InMemoryScanner(), printer);
    
    assertThat(printer.output(), hasItem("Sales Taxes: 0.00"));
  }

  @Test
  public void testItemsTotal() throws Exception {
    InMemoryScanner scanner = new InMemoryScanner().append("1 book at 12.49")
        .append("1 music CD at 16.49").append("1 chocolate bar at 0.85");

    new ShoppingBasket(noTaxes).process(scanner, printer);

    assertThat(printer.output(), hasItem("Total: 29.83"));
  }

  @Test
  public void testItemDetails() throws Exception {
    InMemoryScanner scanner = new InMemoryScanner()
                                    .append("1 book at 12.49")
                                    .append("1 music CD at 16.49")
                                    .append("1 chocolate bar at 0.85");
    
    new ShoppingBasket(noTaxes).process(scanner, printer);
    
    assertThat(printer.output(), hasItems(  "1 book: 12.49", 
                                            "1 music CD: 16.49",
                                            "1 chocolate bar: 0.85"));
  }
  
  @Test
  public void testSectionsOrdered() throws Exception {
    new ShoppingBasket(noTaxes).process(new InMemoryScanner().append("1 book at 10.50"), printer);
    
    assertThat(printer.output(),
        contains(startsWith("1 book"), startsWith("Sales Taxes"), startsWith("Total")));
  }
  
  @Test
  public void testItemsWithTaxes() throws Exception {
    InMemoryScanner scanner = new InMemoryScanner().append("1 book at 10.50").append("1 music CD at 5.50");

    new ShoppingBasket( fixedTaxes("1") ).process(scanner, printer);

    assertThat(printer.output(), hasItems("1 book: 11.50", "1 music CD: 6.50"));
  }
  
  @Test
  public void testSalesTaxesPartial() throws Exception {
    InMemoryScanner scanner = new InMemoryScanner().append("1 any at 1.00").append("1 any at 2.00");
    
    new ShoppingBasket( fixedTaxes("1") ).process(scanner, printer);
    
    assertThat(printer.output(), hasItem("Sales Taxes: 2.00"));
  }
  
  @Test
  public void testManyTaxes() throws Exception {
    InMemoryScanner scanner = new InMemoryScanner().append("1 any at 1.00").append("1 any at 2.00");
    
    new ShoppingBasket(fixedTaxes("1", "1")).process(scanner, printer);
    
    assertThat(printer.output(), hasItem("Sales Taxes: 4.00"));
  }

  private List<SalesTax> fixedTaxes(String... amounts) {
    return Arrays.asList(amounts).stream()
                    .map(x -> new FixedSalesTax(new Money(x)))
                    .collect(Collectors.toList());
  }

}
