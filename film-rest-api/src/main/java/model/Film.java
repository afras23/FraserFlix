package model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "film")
public class Film {
	Film(FilmBuilder builder) {
	       this.id = builder.id;
	       this.title = builder.title;
	       this.year = builder.year;
	       this.director = builder.director;
	       this.stars = builder.stars;
	       this.review = builder.review;
	}
	
	
	int id;
	   String title;
	   int year;
	   String director;
	   String stars;
	   String review;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year
				+ ", director=" + director + ", stars=" + stars + ", review="
				+ review + "]";
	}   
}
