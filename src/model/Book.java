package model;

import java.util.Calendar;
import java.util.UUID;

public class Book extends BibliographicProduct {
	
	private String review;
	private BookType bookType;

	public Book(String name, int numbPages, Calendar publicationDate, String url, double prize, String review, BookType bookType) {
		super(name, numbPages, publicationDate, url, prize);
		this.review = review;
		this.id = UUID.randomUUID().toString().substring(0, 3);
		this.bookType = bookType;
	}
	 
	
	public String getReview() {
		return review;
	}


	public void setReview(String review) {
		this.review = review;
	}


	public BookType getBookType() {
		return bookType;
	}


	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}


	@Override
	public String toString() {
		return "Book [review=" + review + ", fecha=" + calendarToString(publicationDate) + ", ID=" + id + ", bookType=" + bookType + "]";
	}
}