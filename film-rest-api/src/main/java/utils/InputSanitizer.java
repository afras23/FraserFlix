package utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * The InputSanitizer class provides utility methods to sanitize input strings
 * to prevent Cross-Site Scripting (XSS) attacks by cleaning input data.
 * This class uses the Jsoup library to perform the sanitization.
 * 
 * Method:
 * - sanitize(String value): Cleans the input string using a basic safelist to remove any potentially harmful content.
 * 
 * @see org.jsoup.Jsoup
 * @see org.jsoup.safety.Safelist
 * @author Muhammad Rizwan Saleem
 */
public class InputSanitizer {

    /**
     * Sanitizes the input string by removing any potentially harmful content,
     * using Jsoup's basic safelist.
     * 
     * @param value The input string to be sanitized.
     * @return The sanitized string, or null if the input was null.
     */
    public static String sanitize(String value) {
        if (value == null) {
            return null;
        }
        return Jsoup.clean(value, Safelist.basic());
    }
}
