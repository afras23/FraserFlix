package model;

/**
 * The FilmBuilder class is a builder for constructing Film objects.
 * This class provides methods to set the properties of a Film object
 * and a build method to create a Film instance.
 * 
 * Methods:
 * - setId(int id): Sets the id of the film.
 * - setTitle(String title): Sets the title of the film.
 * - setYear(int year): Sets the year of the film.
 * - setDirector(String director): Sets the director of the film.
 * - setStars(String stars): Sets the stars of the film.
 * - setReview(String review): Sets the review of the film.
 * - build(): Constructs and returns a Film object with the set properties.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * Film film = new FilmBuilder()
 *     .setId(1)
 *     .setTitle("Inception")
 *     .setYear(2010)
 *     .setDirector("Christopher Nolan")
 *     .setStars("Leonardo DiCaprio, Joseph Gordon-Levitt")
 *     .setReview("A mind-bending thriller")
 *     .build();
 * }
 * </pre>
 * 
 * @see model.Film
 * 
 * Author: Muhammad Rizwan Saleem
 */
public class FilmBuilder {
    int id;
    String title;
    int year;
    String director;
    String stars;
    String review;

    /**
     * Sets the id of the film.
     * 
     * @param id The id of the film.
     * @return The current instance of FilmBuilder.
     */
    public FilmBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the title of the film.
     * 
     * @param title The title of the film.
     * @return The current instance of FilmBuilder.
     */
    public FilmBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the year of the film.
     * 
     * @param year The year of the film.
     * @return The current instance of FilmBuilder.
     */
    public FilmBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * Sets the director of the film.
     * 
     * @param director The director of the film.
     * @return The current instance of FilmBuilder.
     */
    public FilmBuilder setDirector(String director) {
        this.director = director;
        return this;
    }

    /**
     * Sets the stars of the film.
     * 
     * @param stars The stars of the film.
     * @return The current instance of FilmBuilder.
     */
    public FilmBuilder setStars(String stars) {
        this.stars = stars;
        return this;
    }

    /**
     * Sets the review of the film.
     * 
     * @param review The review of the film.
     * @return The current instance of FilmBuilder.
     */
    public FilmBuilder setReview(String review) {
        this.review = review;
        return this;
    }

    /**
     * Constructs and returns a Film object with the set properties.
     * 
     * @return A Film object with the properties set via the builder.
     */
    public Film build() {
        return new Film(this);
    }
}
