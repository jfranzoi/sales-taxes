package my.projects.saletaxes;

public class Product {

  private String description;
  private Money price;

  public Product(String description, String price) {
    this.description = description;
    this.price = new Money(price);
  }

  @Override
  public boolean equals(Object obj) {
    Product other = (Product) obj;
    return description.equals(other.description) && price.equals(other.price);
  }

  public Money price() {
    return price;
  }

}
