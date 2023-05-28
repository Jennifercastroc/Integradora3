package model;

import java.util.Calendar;
import java.util.UUID;

public class Book extends BibliographicProduct{
	
	private String review;

	public Book(String name, int numbPages, Calendar publicationDate, String url, double prize, String review) {
		super(name, numbPages, publicationDate, url, prize);
		this.review = review;
		this.id=UUID.randomUUID().toString().substring(0,3);
	}
	 
	@Override
	public String toString() {
		return "Book [review=" + review + " fecha= " + calendarToString(publicationDate) + " ID= " + id + "]";
	}
	 
	 

} 
