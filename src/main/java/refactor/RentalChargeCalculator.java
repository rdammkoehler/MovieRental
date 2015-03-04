package refactor;

import java.math.BigDecimal;

public class RentalChargeCalculator {
  public BigDecimal determineAmountForRental(Rental rental) {
    return rental.getFeeProfile().getPriceCode().calculateCharges(rental.getDaysRented());
  }
}
