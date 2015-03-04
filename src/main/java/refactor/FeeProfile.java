package refactor;

public class FeeProfile {

  private int priceCode;
  
  public FeeProfile(int movieType) {
    priceCode = movieType;
  }

  protected int getPriceCode() {
    return priceCode;
  }

}
