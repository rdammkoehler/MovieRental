package refactor;

public class Movie {
  public static final int CHILDRENS = 2;
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;

  private String title;

  public Movie(String movieTitle) {
    title = movieTitle;
  }

  public String getTitle() {
    return title;
  }

}