// FilmDAOTest.java

import java.util.List;

public class FilmDAOTest {
    public static void main(String[] args) {
        FilmDAO dao = new FilmDAO();
        List<Film> films = dao.getAllFilms();
        assert films != null : "Failed to retrieve film list";
        assert films.size() >= 0 : "Film list size invalid";
        System.out.println("FilmDAO test passed!");
    }
}
