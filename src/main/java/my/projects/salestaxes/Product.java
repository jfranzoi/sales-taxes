package my.projects.salestaxes;

public class Product {

  private static final String IMPORTED = "imported ";

  private String description;
  private Money price;

  public Product(String description, String price) {
    this.description = description;
    this.price = new Money(price);
  }

  public String description() {
    if (imported()) {
      return String.join("", IMPORTED, description.replace(IMPORTED, ""));
    }
    return description;
  }

  public boolean imported() {
    return description.contains(IMPORTED);
  }

  public Money price() {
    return price;
  }

  @Override
  public boolean equals(Object obj) {
    Product other = (Product) obj;
    return description.equals(other.description) && price.equals(other.price);
  }

}
