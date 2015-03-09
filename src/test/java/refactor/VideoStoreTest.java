package refactor;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {

  private static final String REGULAR_MOVIE3 = "Eraserhead";
  private static final String REGULAR_MOVIE2 = "8 1/2";
  private static final String REGULAR_MOVIE1 = "Plan 9 from Outer Space";
  private static final String MOVIE_TITLE2 = "The Tigger Movie";
  private static final String MOVIE_TITLE1 = "The Cell";
  private static final String CUSTOMER_NAME = "Fred";
  private static final String NEW_LINE = "\n";
  private static final String TAB = "\t";
  private Customer customer;

  @Before
  public void setUp() {
    customer = new Customer(CUSTOMER_NAME);
  }

  @Test
  public void calculatesAccountForSingleMovie() {
    addMovieRentalToCustomer(PriceCode.NEW_RELEASE, MOVIE_TITLE1, 3);

    customer.createStatement();

    assertThat(customer.getOwed(), is(BigDecimal.valueOf(9.0d)));
    assertEquals(2, customer.getPointsEarned());
  }

  @Test
  public void calculatesAccountForMultipleNewRelease() {
    addMovieRentalToCustomer(PriceCode.NEW_RELEASE, MOVIE_TITLE1, 3);
    addMovieRentalToCustomer(PriceCode.NEW_RELEASE, MOVIE_TITLE2, 3);

    customer.createStatement();

    assertThat(customer.getOwed(), is(BigDecimal.valueOf(18.0d)));
    assertEquals(4, customer.getPointsEarned());

  }

  @Test
  public void calculatesAccountForChildrensMovie() {
    addMovieRentalToCustomer(PriceCode.CHILDRENS, MOVIE_TITLE2, 3);

    customer.createStatement();

    assertThat(customer.getOwed(), is(BigDecimal.valueOf(1.5d)));
    assertEquals(1, customer.getPointsEarned());
  }

  @Test
  public void calculatesAccountForMultipleRegularMovies() {
    addMovieRentalToCustomer(PriceCode.REGULAR, REGULAR_MOVIE1, 1);
    addMovieRentalToCustomer(PriceCode.REGULAR, REGULAR_MOVIE2, 2);
    addMovieRentalToCustomer(PriceCode.REGULAR, REGULAR_MOVIE3, 3);

    customer.createStatement();

    assertThat(customer.getOwed(), is(BigDecimal.valueOf(7.5d)));
    assertEquals(3, customer.getPointsEarned());
  }

  @Test
  public void formatsStatement() {
    addMovieRentalToCustomer(PriceCode.REGULAR, REGULAR_MOVIE1, 1);
    addMovieRentalToCustomer(PriceCode.REGULAR, REGULAR_MOVIE2, 2);
    addMovieRentalToCustomer(PriceCode.REGULAR, REGULAR_MOVIE3, 3);

    String statement = customer.createStatement();

    assertEquals("Rental Record for Fred" + NEW_LINE + TAB + REGULAR_MOVIE1 + TAB + "2.0" + NEW_LINE + TAB
        + REGULAR_MOVIE2 + TAB + "2.0" + NEW_LINE + TAB + REGULAR_MOVIE3 + TAB + "3.5" + NEW_LINE + "You owed 7.5"
        + NEW_LINE + "You earned 3 frequent renter points" + NEW_LINE, statement);
  }

  @Test
  public void childrensMoviesThatAreOverdueAreChargedALateFee() {
    addMovieRentalToCustomer(PriceCode.CHILDRENS, MOVIE_TITLE2, 4);
    customer.createStatement();
    assertThat(customer.getOwed(), is(BigDecimal.valueOf(3.0d)));
  }

  private void addMovieRentalToCustomer(PriceCode movieType, String movieTitle, int daysRented) {
    customer.addRental(new Rental(new Movie(movieTitle), new FeeProfile(movieType), daysRented));
  }

  @Test
  public void ctTwoDayNewReleaseRentalGetsDoubleFrequentRenterPoints() {
    addMovieRentalToCustomer(PriceCode.NEW_RELEASE, MOVIE_TITLE1, 2);
    customer.createStatement();
    assertThat(customer.getPointsEarned(), is(2));
  }

  @Test
  public void ctOneDayNewReleaseRentalGetsNormalFrequentRenterPoints() {
    addMovieRentalToCustomer(PriceCode.NEW_RELEASE, MOVIE_TITLE1, 1);
    customer.createStatement();
    assertThat(customer.getPointsEarned(), is(1));
  }
}