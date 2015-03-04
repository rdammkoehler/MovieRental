package refactor;

import java.math.BigDecimal;

public class FeeProfile {

  enum PriceCode {
    REGULAR(BigDecimal.valueOf(2d), BigDecimal.valueOf(1.5d), 2), 
    NEW_RELEASE(BigDecimal.ZERO, BigDecimal.valueOf(3d), 0), 
    CHILDRENS(BigDecimal.valueOf(1.5d), BigDecimal.valueOf(1.5d), 3);

    private BigDecimal rentalFee;
    private BigDecimal lateFee;
    private Integer lateAfterDays;

    private PriceCode(BigDecimal rentalFee, BigDecimal lateFee, Integer lateAfterDays) {
      this.rentalFee = rentalFee;
      this.lateFee = lateFee;
      this.lateAfterDays = lateAfterDays;
    }

    public BigDecimal calculateCharges(Integer daysRented) {
      BigDecimal charge = new BigDecimal(rentalFee.doubleValue());
      if (daysRented > lateAfterDays) {
        charge = charge.add(lateFee.multiply(BigDecimal.valueOf(daysRented - lateAfterDays)));
      }
      return charge;
    }
  }

  private PriceCode priceCode;

  public FeeProfile(PriceCode movieType) {
    priceCode = movieType;
  }

  protected PriceCode getPriceCode() {
    return priceCode;
  }

}
