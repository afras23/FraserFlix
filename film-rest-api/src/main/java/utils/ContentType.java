package utils;

/**
 * The ContentType class defines constants for various content types
 * that are used in HTTP requests and responses.
 * This class helps to avoid hardcoding strings throughout the application,
 * promoting better maintainability and readability.
 * 
 * Constants:
 * - APPLICATION_JSON: Represents the "application/json" content type.
 * - TEXT_XML: Represents the "text/xml" content type.
 * - TEXT_STRING: Represents the "text/string" content type.
 * 
 * @author Muhammad Rizwan Saleem
 */
public class ContentType {
    
    /**
     * The content type for JSON data: "application/json".
     */
    public static final String APPLICATION_JSON = "application/json";
    
    /**
     * The content type for XML data: "text/xml".
     */
    public static final String TEXT_XML = "text/xml";
    
    /**
     * The content type for plain text data: "text/string".
     */
    public static final String TEXT_STRING = "text/string";
}
