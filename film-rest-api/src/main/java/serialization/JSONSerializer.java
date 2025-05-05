package serialization;

import java.util.List;

import com.google.gson.Gson;

import Interface.FormatSerializer;
import model.Film;

/**
 * The JSONSerializer class provides methods to serialize and deserialize Film objects 
 * to and from JSON format using the Gson library.
 * 
 * Implements:
 * - FormatSerializer: Defines the methods for serialization and deserialization.
 * 
 * Methods:
 * - serialize(Film data): Serializes a single Film object to JSON format.
 * - serialize(List<Film> data): Serializes a list of Film objects to JSON format.
 * - deserialize(String data): Deserializes a JSON string to a Film object.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * JSONSerializer serializer = new JSONSerializer();
 * String json = serializer.serialize(film);
 * Film film = serializer.deserialize(json);
 * }
 * </pre>
 * 
 * @see com.google.gson.Gson
 * @see Interface.FormatSerializer
 * @see model.Film
 * 
 * Author: Muhammad Rizwan Saleem
 */
public class JSONSerializer implements FormatSerializer {
    private Gson gson = new Gson();

    /**
     * Serializes a single Film object to JSON format.
     * 
     * @param data The Film object to serialize.
     * @return The JSON representation of the Film object.
     */
    @Override
    public String serialize(Film data) {
        return gson.toJson(data);
    }

    /**
     * Serializes a list of Film objects to JSON format.
     * 
     * @param data The list of Film objects to serialize.
     * @return The JSON representation of the list of Film objects.
     */
    @Override
    public String serialize(List<Film> data) {
        return gson.toJson(data);
    }

    /**
     * Deserializes a JSON string to a Film object.
     * 
     * @param data The JSON string to deserialize.
     * @return The Film object deserialized from the JSON string.
     */
    @Override
    public Film deserialize(String data) {
        return gson.fromJson(data, Film.class);
    }
}
