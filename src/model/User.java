package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import interfaces.Fecha;

public abstract class User implements Fecha{
	private String nombre;
	private String id;
	private Calendar vinculationDate;
	public static BibliographicProduct soldProducts [] [] = new BibliographicProduct[5][5];
	
	
	public User(String nombre, String id) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.vinculationDate = Calendar.getInstance();
	}

	
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	public String calendarToString(Calendar fecha) {
		String a;
		a=formatoFecha.format(fecha.getTime());;
		return a;
	}
 
	@Override
	public String toString() {
		return "User [nombre=" + nombre + ", id=" + id + ", vinculationDate=" + calendarToString(vinculationDate) +  "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getVinculationDate() {
		return vinculationDate;
	}
	
	public static String imprime(BibliographicProduct soldProducts [][]) {
		String cadena = "";
		
		for(int i = 0; i < soldProducts.length; i++) {
			for(int j = 0; j < soldProducts[i].length; j++) {
				if (soldProducts[i][j]==null) {
					break;
				}else {
					cadena += soldProducts[i][j].getId()+ ",";
				}
			} 
			cadena += "\n";
		} 
		return cadena; 
	}

	
	
	
	
	
	

	
	

}
