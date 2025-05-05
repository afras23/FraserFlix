package serialization;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import Interface.FormatSerializer;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Film;
import model.FilmCollection;

/**
 * The XMLSerializer class provides methods to serialize and deserialize Film objects 
 * to and from XML format using the JAXB library.
 * 
 * Implements:
 * - FormatSerializer: Defines the methods for serialization and deserialization.
 * 
 * Methods:
 * - serialize(Film data): Serializes a single Film object to XML format.
 * - serialize(List<Film> data): Serializes a list of Film objects to XML format.
 * - deserialize(String data): Deserializes an XML string to a Film object.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * XMLSerializer serializer = new XMLSerializer();
 * String xml = serializer.serialize(film);
 * Film film = serializer.deserialize(xml);
 * }
 * </pre>
 * 
 * @see jakarta.xml.bind.JAXBContext
 * @see jakarta.xml.bind.Marshaller
 * @see jakarta.xml.bind.Unmarshaller
 * @see model.Film
 * @see model.FilmCollection
 * 
 * Author: Muhammad Rizwan Saleem
 */
public class XMLSerializer implements FormatSerializer {

    /**
     * Serializes a single Film object into an XML string.
     *
     * @param data The Film object to serialize.
     * @return The XML representation of the Film object.
     * @throws JAXBException If an error occurs during the serialization process.
     */
    @Override
    public String serialize(Film data) throws JAXBException {
        // Create a StringWriter to hold the XML output
        StringWriter sw = new StringWriter();
        
        // Create JAXB context and marshaller for the Film class
        JAXBContext context = JAXBContext.newInstance(Film.class);
        Marshaller m = context.createMarshaller();
        
        // Set the marshaller property to format the XML output
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        // Marshal the Film object to the StringWriter
        m.marshal(data, sw);
        
        // Return the XML string
        return sw.toString();
    }

    /**
     * Serializes a list of Film objects into an XML string.
     *
     * @param data The list of Film objects to serialize.
     * @return The XML representation of the list of Film objects.
     * @throws JAXBException If an error occurs during the serialization process.
     */
    @Override
    public String serialize(List<Film> data) throws JAXBException {
        // Create a StringWriter to hold the XML output
        StringWriter sw = new StringWriter();
        
        // Create a FilmCollection object to wrap the list of films
        FilmCollection films = new FilmCollection(data);
        
        // Create JAXB context and marshaller for the FilmCollection class
        JAXBContext context = JAXBContext.newInstance(FilmCollection.class);
        Marshaller m = context.createMarshaller();
        
        // Set the marshaller property to format the XML output
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        // Marshal the FilmCollection object to the StringWriter
        m.marshal(films, sw);
        
        // Return the XML string
        return sw.toString();
    }

    /**
     * Deserializes an XML string into a Film object.
     *
     * @param data The XML string to deserialize.
     * @return The deserialized Film object.
     * @throws JAXBException If an error occurs during the deserialization process.
     */
    @Override
    public Film deserialize(String data) throws JAXBException {
        // Create JAXB context and unmarshaller for the Film class
        JAXBContext context = JAXBContext.newInstance(Film.class);
        Unmarshaller m = context.createUnmarshaller();
        
        // Unmarshal the XML string to a Film object
        return (Film) m.unmarshal(new StringReader(data));
    }
}
