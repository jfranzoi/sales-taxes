package my.projects.salestaxes;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import my.projects.salestaxes.Product;
import my.projects.salestaxes.Purchase;
import my.projects.salestaxes.Scanner;
import my.projects.salestaxes.dummies.InMemoryScanner;

public class PurchaseTest {

  @Test
  public void testSimpleDescription() throws Exception {
    Scanner scanner = new InMemoryScanner().append("1 book at 12.49");
    assertThat(new Purchase(scanner).items(), contains(new Product("book", "12.49")));
  }

  @Test
  public void testComplexDescription() throws Exception {
    Scanner scanner = new InMemoryScanner().append("1 music CD at 14.99");
    assertThat(new Purchase(scanner).items(), contains(new Product("music CD", "14.99")));
  }

  @Test
  public void testLowPrice() throws Exception {
    Scanner scanner = new InMemoryScanner().append("1 book at 0.35");
    assertThat(new Purchase(scanner).items(), contains(new Product("book", "0.35")));
  }

}
