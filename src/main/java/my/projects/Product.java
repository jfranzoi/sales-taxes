package my.projects;

import java.math.BigDecimal;

public class Product {

  private String description;
  private BigDecimal price;

  public Product(String description, String price) {
    this.description = description;
    this.price = new BigDecimal(price);
  }
  
  @Override
  public boolean equals(Object obj) {
    Product other = (Product) obj;
    return description.equals(other.description) && price.equals(other.price);
  }

}
