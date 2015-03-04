package refactor;

import refactor.FeeProfile.PriceCode;

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
    double thisAmount = new RentalChargeCalculator().determineAmountForRental(rental);

    customer.setFrequentRenterPoints(customer.getPointsEarned() + calculateFrequentRenterPoints(rental));

    customer.setTotalAmount(customer.getOwed() + thisAmount);
    lineItem.append(TAB).append(rental.getMovie().getTitle()).append(TAB).append(thisAmount).append(NEW_LINE);

    return lineItem.toString();
  }

  private int calculateFrequentRenterPoints(Rental rental) {
    int pointsEarned = 1;

    if (rental.getFeeProfile().getPriceCode().equals(PriceCode.NEW_RELEASE) && rental.getDaysRented() > 1) {
      pointsEarned += 1;
    }
    return pointsEarned;
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
