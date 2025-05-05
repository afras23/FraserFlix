package dao;
import java.util.ArrayList;

import Interface.DAO;
import model.Film;
import model.FilmBuilder;

import java.sql.*;

/**
 * The DAO (Data Access Object) defines the standard operations
 * to be performed on a Film object in the persistence layer.
 * This class provides methods for basic CRUD (Create, Read, Update, Delete) operations.
 * 
 * Methods:
 * - getAllFilms(): Retrieves all Film objects.
 * - getFilmByID(int id): Retrieves a Film object by its ID.
 * - insertFilm(Film film): Inserts a new Film object.
 * - updateFilm(Film film): Updates an existing Film object.
 * - deleteFilm(int id): Deletes a Film object by its ID.
 * 
 * Usage Example:
 * <pre>
 * {@code
 * DAO filmDAO = new FilmDAOImpl();
 * ArrayList<Film> films = filmDAO.getAllFilms();
 * Film film = filmDAO.getFilmByID(1);
 * filmDAO.insertFilm(new Film("Title", 2021, "Director", "Stars", "Review"));
 * filmDAO.updateFilm(film);
 * filmDAO.deleteFilm(1);
 * }
 * </pre>
 * 
 * @see model.Film
 * 
 */
public class FilmDAO implements DAO {
	
	private static FilmDAO filmDAO;
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "fraseran";
    String password = "Drestwal9";
    // Note none default port used, 6306 not 3306
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user+"?characterEncoding=UTF-8";

	private FilmDAO() {}
		
		public static synchronized FilmDAO getInstance() {
			if(filmDAO == null) {
				filmDAO = new FilmDAO();
			}
			return filmDAO;
	}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new FilmBuilder()
					.setId(rs.getInt("id"))
					.setTitle(rs.getString("title"))
					.setYear(rs.getInt("year"))
					.setDirector(rs.getString("director"))
					.setStars(rs.getString("stars"))
					.setReview(rs.getString("review"))
					.build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }
   
   public ArrayList<Film> getAllFilms(String search){
		ArrayList<Film> allFilms = new ArrayList<Film>();
		String selectSQL;
		PreparedStatement preparedStatement;
		openConnection();
		try{
			if(search != null && search != "") {
				selectSQL = "SELECT * FROM films WHERE UPPER(CONCAT(title, year, director, stars, review))";
				preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, "%" + search + "%");
			} else {
				selectSQL = "select * from films LIMIT ? OFFSET ?";	
				preparedStatement = conn.prepareStatement(selectSQL);
			}
		    
		    ResultSet rs1 = preparedStatement.executeQuery();
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
  }
  
   
   public int getTotalFilmsCount(String search, int pageNo, int pageSize){
		int totalCount = 0;
	   	String selectSQL;
		PreparedStatement preparedStatement;
		openConnection();
		try{
			if(search != null && search != "") {
				selectSQL = "SELECT COUNT(*) AS total FROM films WHERE UPPER(CONCAT(title, year, director, stars, review)) LIKE ?";
				preparedStatement = conn.prepareStatement(selectSQL);
				preparedStatement.setString(1, "%" + search + "%");
			} else {
				selectSQL = "select COUNT(*) AS total from films";	
				preparedStatement = conn.prepareStatement(selectSQL);
			}
		    ResultSet rs1 = preparedStatement.executeQuery();
	    // Retrieve the results
		    while(rs1.next()){
		    	totalCount = rs1.getInt("total");
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return totalCount;
   	}

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   
   public void insertFilm(Film film) {
	   openConnection();
	   try {
		   int generateId = 0;
		   String query = "SELECT ROW_NUMBER() OVER(PARTITION BY 'id' ) AS newId FROM films";
       	   Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(query);
           while (rs.next()) {
        	   generateId = rs.getInt("newId");
           }
		   String INSERT_FILM_SQL = "INSERT INTO films " + "(id, title,year,director,stars,review) VALUES " + "(?,?,?,?,?,?)";
		   PreparedStatement insertFilmStmt =  conn.prepareStatement(INSERT_FILM_SQL);
		   insertFilmStmt.setInt(1, generateId);
		   insertFilmStmt.setString(2, film.getTitle());
		   insertFilmStmt.setInt(3, film.getYear());
		   insertFilmStmt.setString(4, film.getDirector());
		   insertFilmStmt.setString(5, film.getStars());
		   insertFilmStmt.setString(6, film.getReview());
		   insertFilmStmt.executeUpdate();
		   insertFilmStmt.close();
	   } catch (Exception ex) {
		   ex.printStackTrace();
	   } finally {
		   closeConnection();
	   }
   }
   
   public void updateFilm(Film film) {
	   openConnection();
	   try {
		   String UPDATE_FILM_SQL = "UPDATE films SET title= ?, year= ?, director= ?, stars = ?, review= ? WHERE id = ?" ;
		   PreparedStatement updateFilmStmt =  conn.prepareStatement(UPDATE_FILM_SQL);
		   updateFilmStmt.setString(1, film.getTitle());
		   updateFilmStmt.setInt(2, film.getYear());
		   updateFilmStmt.setString(3, film.getDirector());
		   updateFilmStmt.setString(4, film.getStars());
		   updateFilmStmt.setString(5, film.getReview());
		   updateFilmStmt.setInt(6, film.getId());
		   updateFilmStmt.executeUpdate();
		   updateFilmStmt.close();
	   } catch (Exception ex) {
		   ex.printStackTrace();
	   } finally {
		   closeConnection();
	   }
   }
   
   public void deleteFilm(int id) {
	   openConnection();
	   try {
		   String DELETE_FILM_SQL = "DELETE FROM films WHERE id = ?";
		   PreparedStatement deleteFilmStmt =  conn.prepareStatement(DELETE_FILM_SQL);
		   deleteFilmStmt.setInt(1, id);
		   deleteFilmStmt.executeUpdate();
		   deleteFilmStmt.close();
	   } catch (Exception ex) {
		   ex.printStackTrace();
	   } finally {
		   closeConnection();
	   }
   }
   
   
}
