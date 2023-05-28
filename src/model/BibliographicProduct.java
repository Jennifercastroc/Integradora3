package model;

import java.util.Calendar;

import interfaces.Fecha;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class BibliographicProduct implements Fecha {
	protected String id;
	protected String name;
	protected int numbPages;
	protected Calendar publicationDate;
	protected String url;
	protected double prize;
	
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	public BibliographicProduct( String name, int numbPages, Calendar publicationDate, String url,
			double prize) {
		super();
		this.id = "";
		this.name = name;
		this.numbPages = numbPages;
		this.publicationDate = publicationDate;
		this.url = url;
		this.prize = prize; 
	}
	
	public Calendar stringToCalendar(String fecha) { 
	    Calendar inscription = Calendar.getInstance();
	    try {
	        inscription.setTime(formatoFecha.parse(fecha));
	        return inscription;
	    } catch (ParseException e) {
	        System.out.println("Error al analizar la fecha.");
	        return null;
	    }
	     // Retornar null en caso de error
	}

	
	public String calendarToString(Calendar fecha) {
		String a;
		a=formatoFecha.format(fecha.getTime());; 
		return a;
	};
	
	
	





	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumbPages() {
		return numbPages;
	}

	public void setNumbPages(int numbPages) {
		this.numbPages = numbPages;
	}

	public Calendar getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Calendar publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public SimpleDateFormat getFormatoFecha() {
		return formatoFecha;
	}

	public void setFormatoFecha(SimpleDateFormat formatoFecha) {
		this.formatoFecha = formatoFecha;
	}

	@Override
	public String toString() {
		return "BibliographicProduct [id=" + id + ", name=" + name + ", numbPages=" + numbPages + ", publicationDate="
				+ calendarToString(publicationDate) + ", url=" + url + ", prize=" + prize + "]";
	}
	
	

}
