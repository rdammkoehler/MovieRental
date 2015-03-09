package refactor;

public class Rental {

  private Movie movie;
  private int daysRented;
  private PriceCode priceCode;

  public Rental(Movie movie, PriceCode priceCode, int daysRented) {
    this.movie = movie;
    this.priceCode = priceCode;
    this.daysRented = daysRented;
  }

  public int getDaysRented() {
    return daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  public PriceCode getPriceCode() {
    return priceCode;
  }
}
