package refactor;

public class Rental {

  private Movie movie;
  private int daysRented;
  private FeeProfile feeProfile;

  public Rental(Movie movie2, FeeProfile feeProfile, int daysRented2) {
    this.movie = movie2;
    this.feeProfile = feeProfile;
    this.daysRented = daysRented2;
  }

  public int getDaysRented() {
    return daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  protected FeeProfile getFeeProfile() {
    return feeProfile;
  }
}
