package ui;

import java.util.Calendar; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class Manager {
	static ArrayList<BibliographicProduct> coleccionProductos = new ArrayList<BibliographicProduct>();
	static ArrayList<User> usuarios = new ArrayList<User>();

	private static void leeProductos(ArrayList<BibliographicProduct> coleccionProductos2) throws FileNotFoundException {
	    File archivo = new File("./src/dataIn/productos.txt");
	    Scanner inFile = new Scanner(archivo);
	    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    formatoFecha.setLenient(false);

	    while (inFile.hasNext()) {
	        String titulo = inFile.next();
	        int codigo = Integer.parseInt(inFile.next());
	        Calendar publicationDate = Calendar.getInstance();
	        try {
	        	publicationDate.setTime(formatoFecha.parse(inFile.next()));
	        } catch (ParseException e) { 
	            System.out.println("Error al analizar la fecha.");
	            continue;
	        } 
	        String autor = inFile.next();
	        double precio = Double.parseDouble(inFile.next());
	        /**the next String is initialize as "dato", después se verifica que se pueda convertir a int,
	          si es así se crea un elemento Magazine, ya que eso significaría la periodicdad de emisión**/
	        String dato=inFile.next();  
	        try {
	        	int periodicidad= Integer.parseInt(dato);
	        	coleccionProductos.add(new Magazine(titulo, codigo, publicationDate, autor, precio, periodicidad));
	        } catch (NumberFormatException e) {
	        	coleccionProductos.add(new Book(titulo, codigo, publicationDate, autor, precio, dato));
	        
	            continue;
	        }
	        
	    }
	}
	public static void leeUsuarios(ArrayList<User> usuarios) throws FileNotFoundException {
	    File archivo = new File("./src/dataIn/usuarios.txt");
	    Scanner inFile = new Scanner(archivo);
	   while(inFile.hasNext()) { if (inFile.next().equals("R")) {
	    	String nombre=inFile.next();
	    	String apellido=inFile.next();
	        usuarios.add(new Regular(nombre, apellido)); //aquí estoy teniendo problemas para agregar a más usuarios
	    }else {
	    	String nombre=inFile.next();
	    	String apellido=inFile.next();
	    	usuarios.add(new Premium(nombre, apellido));
	    }
	   }
		
}
	
	public static void main(String[] args) throws FileNotFoundException  {
		
		leeUsuarios(usuarios);
		// System.out.println(usuarios);
		leeProductos(coleccionProductos);
		// System.out.println(herramientas);
		Library ReadX = new Library("ReadX", usuarios, coleccionProductos);
		
		/*Esto es para probar que el sistema esté funcioannado correctamente
		 *  
		 * 
		 * */
		System.out.println(Library.coleccionProductos);
		System.out.println(Library.usuarios);
		
		System.out.println("matriz");
		System.out.println(User.imprime(User.soldProducts));
		
		 
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("1.Registrar un Producto bibliográfico"
				+ "\n 2.Registrar Usuario");
		int option = Integer.parseInt(scanner.nextLine());

		if (option == 1) {
			System.out.println("Es un libro o una revista? L/R");
			String type = scanner.nextLine();
			if (!type.equalsIgnoreCase("L") && !type.equalsIgnoreCase("R")) {
				System.out.println("Aún no existe ese producto"); //If the argument is not "L" or "R"
			} else {
				System.out.println("Ingrese el nombre de su producto: ");
				String name = scanner.nextLine();
				System.out.println("Ingrese el número de páginas de su producto: ");
				int numbPages = Integer.parseInt(scanner.nextLine());
				System.out.println("Ingrese la fecha de publicación de su producto (dd/MM/yyyy): ");
				String fecha = scanner.nextLine();
				System.out.println("Ingrese el URL de su producto: ");
				String url = scanner.nextLine();
				System.out.println("Ingrese el precio de su producto: ");
				double prize = Double.parseDouble(scanner.nextLine());


				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				formatoFecha.setLenient(false);

				try {
				    Calendar publicationDate = Calendar.getInstance();
				    publicationDate.setTime(formatoFecha.parse(fecha));
				   
				    if (type.equalsIgnoreCase("L")) {
						System.out.println("Ingrese la reseña de su producto: ");
						String review = scanner.nextLine();
					    Book book = new Book(name, numbPages, publicationDate, url, prize, review);
					    Library.coleccionProductos.add(book); // Acceder directamente al ArrayList estático
					    System.out.println(book);
					} else if (type.equalsIgnoreCase("R")) {
					    // Create a Magazine object and perform operations
					    Magazine magazine = new Magazine(name, numbPages, publicationDate, url, prize, 6);
					    Library.coleccionProductos.add(magazine);
					    System.out.println(magazine);
					} else {
					    System.out.println("Tipo de producto inválido.");
					}
				} catch (ParseException e) {
				    System.out.println("Fecha inválida. Por favor, ingrese una fecha en el formato dd/MM/yyyy.");
				}

			}
		} else {
			System.out.println("Opción inválida.");
		}
		System.out.println(Library.coleccionProductos);
	
	}
	
}



