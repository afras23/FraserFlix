package service;

/*import java.util.ArrayList;*/
import java.util.List;
import dao.FilmDAO;
import jakarta.xml.bind.JAXBException;
import model.Film;
import serialization.FormatSerializerFactory;
import Interface.FormatSerializer;

public class FilmService {

    private final FilmDAO filmDAO;

    public FilmService() {
        this.filmDAO = FilmDAO.getInstance();
    }

    public String getFilmById(String format, int id) throws JAXBException {
        Film film = filmDAO.getFilmByID(id);
        if (film == null) {
            return null;
        }
        FormatSerializer serializer = FormatSerializerFactory.getSerializer(format);
        return serializer.serialize(film);
    }

    public String getAllFilms(String format) throws JAXBException {
        List<Film> allFilms = filmDAO.getAllFilms();
        FormatSerializer serializer = FormatSerializerFactory.getSerializer(format);
        return serializer.serialize(allFilms);
    }
    
    public String getFilmsByTitle(String format, String title) throws JAXBException {
        List<Film> allFilms = filmDAO.getAllFilms(title);
        FormatSerializer serializer = FormatSerializerFactory.getSerializer(format);
        return serializer.serialize(allFilms);
    }

    public void createFilm(String format, String data) throws JAXBException {
        FormatSerializer serializer = FormatSerializerFactory.getSerializer(format);
        Film film = serializer.deserialize(data);
        filmDAO.insertFilm(film);
    }

    public void updateFilm(String format, String data) throws JAXBException {
        FormatSerializer serializer = FormatSerializerFactory.getSerializer(format);
        Film film = serializer.deserialize(data);
        filmDAO.updateFilm(film);
    }

    public void deleteFilm(int id) {
        filmDAO.deleteFilm(id);
    }
}
