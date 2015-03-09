package refactor;


public class FrequentRenterPointsCalculator {
  public int calculateFrequentRenterPoints(Rental rental) {
    int pointsEarned = 1;

    if (rental.getFeeProfile().getPriceCode().equals(PriceCode.NEW_RELEASE) && rental.getDaysRented() > 1) {
      pointsEarned += 1;
    }
    return pointsEarned;
  }
}
