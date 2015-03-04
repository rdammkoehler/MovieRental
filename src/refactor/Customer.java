package refactor;

import java.util.ArrayList;
import java.util.List;

public class Customer {

  String name;
  List<Rental> rentals = new ArrayList<Rental>();
  double totalAmount = 0;
  int frequentRenterPoints = 0;
  
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
}