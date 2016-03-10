package my.projects.salestaxes;

import java.util.Arrays;
import java.util.List;

public class BasicSalesTax implements SalesTax {

  private static final List<String> SPECIAL_CATEGORIES = Arrays.asList("book", "chocolate", "pill");
  private static final double TEN_PERCENT = 0.10;

  @Override
  public Money applyTo(Product product) {
    if (isInAny(SPECIAL_CATEGORIES, product.description())) {
      return new Money("0.00");
    }
  
    return product.price().multiply(TEN_PERCENT);
  }

  private boolean isInAny(List<String> items, String text) {
    return items.stream().anyMatch(x -> text.contains(x));
  }
}
