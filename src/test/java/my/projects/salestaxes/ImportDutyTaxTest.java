package my.projects.salestaxes;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ImportDutyTaxTest {

  @Test
  public void testOnAllImportedGoods() throws Exception {
    assertThat(new ImportDutyTax().applyTo(new Product("any imported good", "10.00")), is(new Money("0.50")));
  }
  
  @Test
  public void testNotApplied() throws Exception {
    assertThat(new ImportDutyTax().applyTo(new Product("any local good", "10.00")), is(new Money("0")));
  }

}
