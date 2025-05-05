package serialization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interface.FormatSerializer;
import model.Film;
import model.FilmBuilder;

/**
 * The StringSerializer class provides methods to serialize and deserialize Film objects 
 * to and from a custom string format.
 * 
 * Implements:
 * - FormatSerializer: Defines the methods for serialization and deserialization.
 * 
 * Methods:
 * - serialize(Film film): Serializes a single Film object to a custom string format.
 * - serialize(List<Film> filmList): Serializes a list of Film objects to a custom string format.
 * - deserialize(String data): Deserializes a custom string format to a Film object.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * StringSerializer serializer = new StringSerializer();
 * String stringData = serializer.serialize(film);
 * Film film = serializer.deserialize(stringData);
 * }
 * </pre>
 * 
 * @see Interface.FormatSerializer
 * @see model.Film
 * 
 * Author: Muhammad Rizwan Saleem
 */
public class StringSerializer implements FormatSerializer {

    /**
     * Serializes a single Film object to a custom string format.
     * 
     * @param film The Film object to serialize.
     * @return The custom string representation of the Film object.
     */
    @Override
    public String serialize(Film film) {
        StringBuilder sb = new StringBuilder();
        sb.append("id:").append(film.getId()).append("#");
        sb.append("title:").append(film.getTitle()).append("#");
        sb.append("year:").append(film.getYear()).append("#");
        sb.append("director:").append(film.getDirector()).append("#");
        sb.append("stars:").append(film.getStars()).append("#");
        sb.append("review:").append(film.getReview());
        return sb.toString();
    }
    
    /**
     * Serializes a list of Film objects to a custom string format.
     * 
     * @param filmList The list of Film objects to serialize.
     * @return The custom string representation of the list of Film objects.
     */
    @Override
    public String serialize(List<Film> filmList) {
        StringBuilder sb = new StringBuilder();
        for (Film film : filmList) {
            sb.append(serialize(film));
            sb.append("$");
        }
        // Remove the trailing delimiter
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    /**
     * Deserializes a custom string format to a Film object.
     * 
     * @param data The custom string representation of a Film object.
     * @return The Film object deserialized from the custom string format.
     */
    @Override
    public Film deserialize(String data) {
        String[] valuePairs = data.split("#");
        
        Map<String, String> keyValueMap = new HashMap<>();
        for (String pair : valuePairs) {
            String[] parts = pair.split(":");
            keyValueMap.put(parts[0], parts[1]);
        }
        
        FilmBuilder builder = new FilmBuilder()
            .setTitle(keyValueMap.get("title"))
            .setYear(Integer.parseInt(keyValueMap.get("year")))
            .setDirector(keyValueMap.get("director"))
            .setStars(keyValueMap.get("stars"))
            .setReview(keyValueMap.get("review"));
        
        if (keyValueMap.containsKey("id")) {
            builder.setId(Integer.parseInt(keyValueMap.get("id")));
        }
        
        return builder.build();
    }
}
