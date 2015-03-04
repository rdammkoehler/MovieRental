package refactor;

public class FeeProfile {

  enum PriceCode {  REGULAR, NEW_RELEASE, CHILDRENS };
  
  private PriceCode priceCode;
  
  public FeeProfile(PriceCode movieType) {
    priceCode = movieType;
  }

  protected PriceCode getPriceCode() {
    return priceCode;
  }

}
