package refactor;

public class FeeProfile {

  private int priceCode;
  public static final int NEW_RELEASE = 1;
  public static final int REGULAR = 0;
  public static final int CHILDRENS = 2;
  
  public FeeProfile(int movieType) {
    priceCode = movieType;
  }

  protected int getPriceCode() {
    return priceCode;
  }

}
