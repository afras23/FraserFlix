package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The CorsFilter class implements the Filter interface to handle Cross-Origin Resource Sharing (CORS) settings.
 * This filter allows all origins and specified HTTP methods and headers for incoming requests.
 * 
 * Annotations:
 * - @WebFilter(urlPatterns = "/*"): Specifies that this filter will be applied to all URL patterns.
 * 
 * Methods:
 * - doFilter(ServletRequest request, ServletResponse response, FilterChain chain): Adds CORS headers to the response.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * // This filter is automatically applied to all incoming requests.
 * }
 * </pre>
 * 
 * @see javax.servlet.Filter
 * @see javax.servlet.FilterChain
 * @see javax.servlet.ServletRequest
 * @see javax.servlet.ServletResponse
 * @see javax.servlet.annotation.WebFilter
 * 
 * Author: Muhammad Rizwan Saleem
 */
@WebFilter(urlPatterns = "/*")
public class CorsFilter implements Filter {
    
    /**
     * Adds CORS headers to the response to allow cross-origin requests.
     * 
     * @param request The ServletRequest object, which contains the client's request.
     * @param response The ServletResponse object, which contains the filter's response.
     * @param chain The FilterChain object, which allows the request to proceed to the next filter or servlet.
     * @throws IOException If an input or output exception occurs during filtering.
     * @throws ServletException If a servlet exception occurs during filtering.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("CORSFilter HTTP Request: " + req.getMethod());

        // Add CORS headers to the response
        HttpServletResponse res = (HttpServletResponse) response;
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
        res.addHeader("Access-Control-Allow-Headers", "*");

        // Continue with the next filter or servlet in the chain
        chain.doFilter(request, response);
    }
}
