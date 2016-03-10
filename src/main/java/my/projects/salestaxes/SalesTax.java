package my.projects.salestaxes;

public interface SalesTax {

  Money taxesFor(Product product);

  Money priceFor(Product product);

}
