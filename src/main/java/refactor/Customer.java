package refactor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {

  private String name;
  private List<Rental> rentals = new ArrayList<Rental>();
  private BigDecimal totalAmount = BigDecimal.ZERO;
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

  
  public BigDecimal getOwed() {
    return totalAmount;
  }

  public int getPointsEarned() {
    return frequentRenterPoints;
  }

  protected List<Rental> getRentals() {
    return rentals;
  }

  protected void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  protected void setFrequentRenterPoints(int frequentRenterPoints) {
    this.frequentRenterPoints = frequentRenterPoints;
  }
}