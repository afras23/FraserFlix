package serialization;

import utils.ContentType;
import Interface.FormatSerializer;

/**
 * The FormatSerializerFactory class provides a factory method to obtain 
 * the appropriate FormatSerializer implementation based on the given format.
 * This class helps in creating instances of serializers dynamically at runtime
 * based on the specified content type.
 * 
 * Method:
 * - getSerializer(String format): Returns a FormatSerializer instance based on the format.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * FormatSerializer serializer = FormatSerializerFactory.getSerializer(ContentType.APPLICATION_JSON);
 * String json = serializer.serialize(film);
 * }
 * </pre>
 * 
 * @see utils.ContentType
 * @see Interface.FormatSerializer
 * @see serialization.XMLSerializer
 * @see serialization.JSONSerializer
 * @see serialization.StringSerializer
 * 
 * Author: Muhammad Rizwan Saleem
 */
public class FormatSerializerFactory {

    /**
     * Returns a FormatSerializer instance based on the provided format.
     * 
     * @param format The content type format for which a serializer is needed.
     * @return A FormatSerializer implementation for the specified format.
     * @throws IllegalArgumentException If the provided format is unsupported.
     */
    public static FormatSerializer getSerializer(String format) {
        switch (format) {
            case ContentType.TEXT_XML:
                return new XMLSerializer();
            case ContentType.APPLICATION_JSON:
                return new JSONSerializer();
            case ContentType.TEXT_STRING:
                return new StringSerializer();
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}
