package Interface;

import java.util.List;

import jakarta.xml.bind.JAXBException;
import model.Film;

/**
 * The FormatSerializer interface defines the methods for serializing and 
 * deserializing Film objects to and from various formats.
 * Implementations of this interface should handle specific formats such as JSON, XML, and custom string formats.
 * 
 * Methods:
 * - serialize(Film data): Serializes a single Film object.
 * - serialize(List<Film> data): Serializes a list of Film objects.
 * - deserialize(String data): Deserializes a string into a Film object.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * FormatSerializer serializer = new JSONSerializer();
 * String json = serializer.serialize(film);
 * Film film = serializer.deserialize(json);
 * }
 * </pre>
 * 
 * @see model.Film
 * @see jakarta.xml.bind.JAXBException
 * 
 * Author: YourName
 */
public interface FormatSerializer {
    
    /**
     * Serializes a single Film object.
     * 
     * @param data The Film object to serialize.
     * @return The serialized representation of the Film object.
     * @throws JAXBException If an error occurs during the serialization process.
     */
    String serialize(Film data) throws JAXBException;

    /**
     * Serializes a list of Film objects.
     * 
     * @param data The list of Film objects to serialize.
     * @return The serialized representation of the list of Film objects.
     * @throws JAXBException If an error occurs during the serialization process.
     */
    String serialize(List<Film> data) throws JAXBException;

    /**
     * Deserializes a string into a Film object.
     * 
     * @param data The string to deserialize.
     * @return The deserialized Film object.
     * @throws JAXBException If an error occurs during the deserialization process.
     */
    Film deserialize(String data) throws JAXBException;
}
