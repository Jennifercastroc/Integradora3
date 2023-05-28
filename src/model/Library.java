package model;
import java.util.ArrayList;


public class Library {
	private String name;
	public static ArrayList<BibliographicProduct> coleccionProductos = new ArrayList<BibliographicProduct>();
	public static ArrayList<User> usuarios = new ArrayList<User>();
	 
	 
	public Library(String name, ArrayList<User> usuarios, ArrayList<BibliographicProduct> coleccionProductos) {
		super();
		this.name = name;
		Library.usuarios = usuarios;
		Library.coleccionProductos = coleccionProductos;
	}
	
	
	
	@Override
	public String toString() {
		return "Library [name=" + name + ", usuarios=" + usuarios + ", coleccionProductos=" + coleccionProductos + "]";
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<BibliographicProduct> getColeccionProductos() {
		return coleccionProductos;
	}
	public void setColeccionProductos(ArrayList<BibliographicProduct> coleccionProductos) {
		Library.coleccionProductos = coleccionProductos;
	}
	
	

	

}
