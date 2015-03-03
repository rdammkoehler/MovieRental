package refactor;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private static final String NEW_LINE = "\n";
  private static final String TAB = "\t";

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String getName() {
    return name;
  }

  public String createStatement() {
    StringBuilder statement = new StringBuilder();
    statement.append(createHeader());
    statement.append(createBody());
    statement.append(createFooter());
    return statement.toString();
  }

  private String createBody() {
    StringBuilder lineItems = new StringBuilder();
    for (Rental rental : rentals) {
      lineItems.append(createLineItem(rental));
    }
    return lineItems.toString();
  }

  private String createLineItem(Rental rental) {
    StringBuilder lineItem = new StringBuilder();
    double thisAmount = determineAmountForRental(rental);

    calculateFrequentRenterPoints(rental);

    totalAmount += thisAmount;
    lineItem.append(TAB).append(rental.getMovie().getTitle()).append(TAB).append(thisAmount).append(NEW_LINE);

    return lineItem.toString();
  }

  private void calculateFrequentRenterPoints(Rental rental) {
    frequentRenterPoints++;

    if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1)
      frequentRenterPoints++;
  }

  private double determineAmountForRental(Rental rental) {
    double thisAmount = 0;

    // determines the amount for each line
    switch (rental.getMovie().getPriceCode()) {
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
    footer.append("You owed ").append(totalAmount).append(NEW_LINE);
    footer.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");
    return footer.toString();
  }

  private String createHeader() {
    return "Rental Record for " + getName() + NEW_LINE;
  }

  private String name;
  private List<Rental> rentals = new ArrayList<Rental>();
  private double totalAmount = 0;
  private int frequentRenterPoints = 0;

  public Double getOwed() {
    return totalAmount;
  }

  public int getPointsEarned() {

    return frequentRenterPoints;
  }
}