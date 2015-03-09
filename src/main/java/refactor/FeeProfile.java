package refactor;


public class FeeProfile {

  private PriceCode priceCode;

  public FeeProfile(PriceCode movieType) {
    priceCode = movieType;
  }

  protected PriceCode getPriceCode() {
    return priceCode;
  }

}
