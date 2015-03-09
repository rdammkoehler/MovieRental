package refactor;

import java.math.BigDecimal;

public class StatementCreator {

  private static final String NEW_LINE = "\n";
  private static final String TAB = "\t";
  private Customer customer;

  public String createStatement(Customer customer) {
    this.customer = customer;
    StringBuilder statement = new StringBuilder();
    statement.append(createHeader());
    statement.append(createBody());
    statement.append(createFooter());
    return statement.toString();
  }

  private String createBody() {
    StringBuilder lineItems = new StringBuilder();
    for (Rental rental : customer.getRentals()) {
      lineItems.append(createLineItem(rental));
    }
    return lineItems.toString();
  }

  private String createLineItem(Rental rental) {
    StringBuilder lineItem = new StringBuilder();
    BigDecimal thisAmount = rental.getPriceCode().calculateCharges(rental.getDaysRented());

    customer.setFrequentRenterPoints(customer.getPointsEarned()
        + new FrequentRenterPointsCalculator().calculateFrequentRenterPoints(rental));

    customer.setTotalAmount(customer.getOwed().add(thisAmount));
    lineItem.append(TAB).append(rental.getMovie().getTitle()).append(TAB).append(thisAmount.doubleValue()).append(NEW_LINE);

    return lineItem.toString();
  }

  private String createFooter() {
    StringBuilder footer = new StringBuilder();
    footer.append("You owed ").append(customer.getOwed()).append(NEW_LINE);
    footer.append("You earned ").append(customer.getPointsEarned()).append(" frequent renter points\n");
    return footer.toString();
  }

  private String createHeader() {
    return new StringBuilder("Rental Record for ").append(customer.getName()).append(NEW_LINE).toString();
  }

}
