package model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * The FilmCollection class is a wrapper for a list of Film objects,
 * designed for XML serialization and deserialization using JAXB.
 * This class is annotated with JAXB annotations to define how the
 * list of films should be represented in XML format.
 * 
 * Annotations:
 * - @XmlAccessorType(XmlAccessType.FIELD): Specifies that JAXB should access fields directly.
 * - @XmlRootElement(name = "films"): Specifies the root element name for the XML representation.
 * - @XmlElement(name = "film"): Specifies the element name for each Film object in the list.
 * 
 * Fields:
 * - filmList: The list of Film objects to be serialized/deserialized.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * List<Film> films = new ArrayList<>();
 * films.add(new Film("Title", 2021, "Director", "Stars", "Review"));
 * FilmCollection filmCollection = new FilmCollection(films);
 * 
 * // JAXB serialization example
 * JAXBContext context = JAXBContext.newInstance(FilmCollection.class);
 * Marshaller marshaller = context.createMarshaller();
 * marshaller.marshal(filmCollection, System.out);
 * }
 * </pre>
 * 
 * @see model.Film
 * @see jakarta.xml.bind.annotation.XmlAccessorType
 * @see jakarta.xml.bind.annotation.XmlElement
 * @see jakarta.xml.bind.annotation.XmlRootElement
 * 
 * Author: Muhammad Rizwan Saleem
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "films")
public class FilmCollection {

    @XmlElement(name = "film")
    private List<Film> filmList;

    /**
     * Default no-argument constructor required by JAXB.
     */
    public FilmCollection() {}

    /**
     * Constructs a FilmCollection with the specified list of films.
     * 
     * @param filmList The list of Film objects to be wrapped by this collection.
     */
    public FilmCollection(List<Film> filmList) {
        this.filmList = filmList;
    }

    /**
     * Returns the list of Film objects.
     * 
     * @return The list of Film objects.
     */
    public List<Film> getFilmCollection() {
        return filmList;
    }

    /**
     * Sets the list of Film objects.
     * 
     * @param filmList The list of Film objects to set.
     */
    public void setFilmCollection(List<Film> filmList) {
        this.filmList = filmList;
    }
}
