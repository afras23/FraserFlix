package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * The Response class provides utility methods for sending HTTP responses.
 * This class includes methods to send responses with specified status codes and data.
 * 
 * Method:
 * - sendResponse(HttpServletResponse response, int statusCode, String data): Sends an HTTP response with the given status code and data.
 * 
 * @see javax.servlet.http.HttpServletResponse
 * 
 * Author: Muhammad Rizwan Saleem
 */
public class Response {
    
    /**
     * Sends an HTTP response with the specified status code and data.
     * 
     * @param response The HttpServletResponse object used to send the response.
     * @param statusCode The HTTP status code to set for the response.
     * @param data The data to write to the response body.
     * @throws IOException If an input or output exception occurs while writing the response.
     */
    public static void sendResponse(HttpServletResponse response, int statusCode, String data) throws IOException {
        response.setStatus(statusCode);
        PrintWriter out = response.getWriter();
        out.write(data);
        out.close();
    }
}
