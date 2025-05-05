package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FilmService;
import utils.InputSanitizer;
import utils.NumberHelper;
import utils.Response;


@WebServlet("/films")
public class FilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final FilmService filmService;
    
    public FilmController() {
        super();
        this.filmService = new FilmService();
    }

	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    String responseFormat = request.getHeader("Accept");
		    String sanitizedId = InputSanitizer.sanitize(request.getParameter("id"));
		    int id = NumberHelper.parseIntWithDefault(sanitizedId, 0);
		    String title = InputSanitizer.sanitize(request.getParameter("title"));
		    String result;
		    
		    if(id != 0) {
		        result = filmService.getFilmById(responseFormat, id);
		        if(result == null) {
		            Response.sendResponse(response, HttpServletResponse.SC_NOT_FOUND, "Not Found!");
		            return;
		        }
		    } else if (title != null && !title.isEmpty()) {
		    	result = filmService.getFilmsByTitle(responseFormat, title);
                if (result.isEmpty()) {
                	Response.sendResponse(response, HttpServletResponse.SC_NOT_FOUND, "Not Found!");
                	return;
                }
            } else {
		        result = filmService.getAllFilms(responseFormat);
		    }
		    
		    response.setContentType(responseFormat);
		    Response.sendResponse(response, HttpServletResponse.SC_OK, result);
		} catch (Exception ex) {	
		    Response.sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "OOPS! Something Went Wrong!");
		}
	}

	/**
	 * @author MuhammadRizwanSaleem
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This is used for POST Request to create a new film
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    String responseFormat = request.getHeader("Accept");
		    String requestFormat = request.getHeader("Content-Type");
		    String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);
		    
		    filmService.createFilm(requestFormat, body);
		    
		    response.setContentType(responseFormat);
		    Response.sendResponse(response, HttpServletResponse.SC_CREATED, "Film Created Successfully!");
		} catch (Exception ex) {	
		    Response.sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "OOPS! Something Went Wrong!");
		}
	}
	
	/**
	 * @author MuhammadRizwanSaleem
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 * This is used for PUT Request to update film.
	 */
    @Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    String responseFormat = request.getHeader("Accept");
		    String requestFormat = request.getHeader("Content-Type");
		    String body = request.getReader().lines().reduce("", (acc, line) -> acc + line);
		    
		    filmService.updateFilm(requestFormat, body);
		    
		    response.setContentType(responseFormat);
		    Response.sendResponse(response, HttpServletResponse.SC_OK, "Film Updated Successfully!");
		} catch (Exception ex) {	
		    Response.sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "OOPS! Something Went Wrong!");
		}
	}
	
	/**
	 * @author MuhammadRizwanSaleem
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 * This is used for DELETE Request to delete film
	 */
    @Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(InputSanitizer.sanitize(request.getParameter("id")));
			
			filmService.deleteFilm(id);
			
			Response.sendResponse(response, HttpServletResponse.SC_OK, "Film deleted Successfully");
		} catch (Exception e) {
			Response.sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "OOPS! Something Went Wrong!");
		}
	}
}
