package refactor;

import java.util.ArrayList;
import java.util.List;

public class Customer {

  private String name;
  private List<Rental> rentals = new ArrayList<Rental>();
  private double totalAmount = 0;
  private int frequentRenterPoints = 0;
  
  public Customer(String name) {
    this.name = name;
  }

  public String createStatement() {
    return new StatementCreator().createStatement(this);
  }
  
  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String getName() {
    return name;
  }

  
  public Double getOwed() {
    return totalAmount;
  }

  public int getPointsEarned() {
    return frequentRenterPoints;
  }

  protected List<Rental> getRentals() {
    return rentals;
  }

  protected void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  protected void setFrequentRenterPoints(int frequentRenterPoints) {
    this.frequentRenterPoints = frequentRenterPoints;
  }
}