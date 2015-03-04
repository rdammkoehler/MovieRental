package refactor;

public class RentalChargeCalculator {
  public double determineAmountForRental(Rental rental) {
    return rental.getFeeProfile().getPriceCode().calculateCharges(rental.getDaysRented()).doubleValue();
//
//    switch (rental.getFeeProfile().getPriceCode()) {
//    case REGULAR:
//      thisAmount += 2;
//      if (rental.getDaysRented() > 2)
//        thisAmount += (rental.getDaysRented() - 2) * 1.5;
//      break;
//    case NEW_RELEASE:
//      thisAmount += rental.getDaysRented() * 3;
//      break;
//    case CHILDRENS:
//      thisAmount += 1.5;
//      if (rental.getDaysRented() > 3)
//        thisAmount += (rental.getDaysRented() - 3) * 1.5;
//      break;
//    }
//    return thisAmount;
  }
}
