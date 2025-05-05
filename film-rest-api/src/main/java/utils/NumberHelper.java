package utils;

/**
 * The NumberHelper class provides utility methods for handling numeric operations.
 * This class includes methods to parse integers with default values to handle potential
 * number format exceptions gracefully.
 * 
 * Method:
 * - parseIntWithDefault(String value, int defaultValue): Parses a string into an integer, returning a default value if the string cannot be parsed.
 * 
 * @see java.lang.Integer#parseInt(String)
 * @see java.lang.NumberFormatException
 * 
 * Author: Muhammad Rizwan Saleem
 */
public class NumberHelper {
    
    /**
     * Parses a string into an integer, returning a default value if the string cannot be parsed.
     * 
     * @param value The string to be parsed into an integer.
     * @param defaultValue The default value to return if parsing fails.
     * @return The parsed integer value, or the default value if a NumberFormatException occurs.
     */
    public static int parseIntWithDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
