package Interface;

import java.util.ArrayList;
import model.Film;

/**
 * The DAO (Data Access Object) interface defines the standard operations
 * to be performed on a Film object in the persistence layer.
 * This interface provides methods for basic CRUD (Create, Read, Update, Delete) operations.
 * 
 * Methods:
 * - getAllFilms(): Retrieves all Film objects.
 * - getFilmByID(int id): Retrieves a Film object by its ID.
 * - insertFilm(Film film): Inserts a new Film object.
 * - updateFilm(Film film): Updates an existing Film object.
 * - deleteFilm(int id): Deletes a Film object by its ID.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * DAO filmDAO = new FilmDAOImpl();
 * ArrayList<Film> films = filmDAO.getAllFilms();
 * Film film = filmDAO.getFilmByID(1);
 * filmDAO.insertFilm(new Film("Title", 2021, "Director", "Stars", "Review"));
 * filmDAO.updateFilm(film);
 * filmDAO.deleteFilm(1);
 * }
 * </pre>
 * 
 * @see model.Film
 * 
 * Author: Muhammad Rizwan Saleem
 */
public interface DAO {

    /**
     * Retrieves all Film objects from the data source.
     * 
     * @return An ArrayList of all Film objects.
     */
    ArrayList<Film> getAllFilms();

    /**
     * Retrieves a Film object by its ID.
     * 
     * @param id The ID of the Film to retrieve.
     * @return The Film object with the specified ID.
     */
    Film getFilmByID(int id);

    /**
     * Inserts a new Film object into the data source.
     * 
     * @param film The Film object to insert.
     */
    void insertFilm(Film film);

    /**
     * Updates an existing Film object in the data source.
     * 
     * @param film The Film object to update.
     */
    void updateFilm(Film film);

    /**
     * Deletes a Film object by its ID from the data source.
     * 
     * @param id The ID of the Film to delete.
     */
    void deleteFilm(int id);
}
