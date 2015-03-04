package refactor;

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
    double thisAmount = determineAmountForRental(rental);

    calculateFrequentRenterPoints(rental);

    customer.setTotalAmount(customer.getOwed() + thisAmount);
    lineItem.append(TAB).append(rental.getMovie().getTitle()).append(TAB).append(thisAmount).append(NEW_LINE);

    return lineItem.toString();
  }

  private void calculateFrequentRenterPoints(Rental rental) {
    customer.setFrequentRenterPoints(customer.getPointsEarned() + 1);

    if (rental.getFeeProfile().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1)
      customer.setFrequentRenterPoints(customer.getPointsEarned() + 1);
  }

  private double determineAmountForRental(Rental rental) {
    double thisAmount = 0;

    switch (rental.getFeeProfile().getPriceCode()) {
    case Movie.REGULAR:
      thisAmount += 2;
      if (rental.getDaysRented() > 2)
        thisAmount += (rental.getDaysRented() - 2) * 1.5;
      break;
    case Movie.NEW_RELEASE:
      thisAmount += rental.getDaysRented() * 3;
      break;
    case Movie.CHILDRENS:
      thisAmount += 1.5;
      if (rental.getDaysRented() > 3)
        thisAmount += (rental.getDaysRented() - 3) * 1.5;
      break;
    }
    return thisAmount;
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
