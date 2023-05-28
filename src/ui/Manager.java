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
	static Scanner inFile; 
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
	        String tipoString = inFile.next();
	        try {
	        	int periodicidad= Integer.parseInt(dato);
	        	MagazineType productType = MagazineType.valueOf(tipoString.toUpperCase());
	        	coleccionProductos.add(new Magazine(titulo, codigo, publicationDate, autor, precio, periodicidad, productType));
	        } catch (NumberFormatException e) {
	        	BookType productType = BookType.valueOf(tipoString.toUpperCase());
	        	coleccionProductos.add(new Book(titulo, codigo, publicationDate, autor, precio, dato, productType));
	        
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
		
		 
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("1.Registrar un Producto bibliográfico"
				+ "\n 2.Registrar Usuario" + "\n 3. Eliminar producto bibliográfico");
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
						System.out.println("Ingrese la categoría de su producto: CIENCIA_FICCION,\r\n"
								+ "	FANTASÍA,\r\n"
								+ "	NOVELA_HISTÓRICA)");
						String tipoString = scanner.nextLine();
			                BookType productType = BookType.valueOf(tipoString.toUpperCase());
					    Book book = new Book(name, numbPages, publicationDate, url, prize, review, productType);
					    Library.coleccionProductos.add(book); // Acceder directamente al ArrayList estático
					    System.out.println(book);
					} else if (type.equalsIgnoreCase("R")) {
						System.out.println("Ingrese la periodicidad de su producto: ");
						String periodo = scanner.nextLine();
						int periodicidad= Integer.parseInt(periodo);
						System.out.println("Ingrese la categoría de su producto: (VARIEDAD,\r\n"
								+ "	DISEÑO,\r\n"
								+ "	CIENTÍFICA)");
						String tipoString = scanner.nextLine();
						MagazineType categoria = MagazineType.valueOf(tipoString.toUpperCase());
					    Magazine magazine = new Magazine(name, numbPages, publicationDate, url, prize, periodicidad,categoria);
					    Library.coleccionProductos.add(magazine);
					    System.out.println(magazine);
					    System.out.println(Library.coleccionProductos);
					} else {
					    System.out.println("Tipo de producto inválido.");
					}
				} catch (ParseException e) {
				    System.out.println("Fecha inválida. Por favor, ingrese una fecha en el formato dd/MM/yyyy.");
				}

			} 
		} else if (option == 2) {
            System.out.println("Es un usuario Regular (R) o Premium (P)?");
            String userType = scanner.nextLine();
            System.out.println("Ingrese el nombre del usuario: ");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el apellido del usuario: ");
            String apellido = scanner.nextLine();

            if (userType.equalsIgnoreCase("R")) {
                usuarios.add(new Regular(nombre, apellido));
                System.out.println("Usuario Regular registrado exitosamente.");
            } else if (userType.equalsIgnoreCase("P")) {
                usuarios.add(new Premium(nombre, apellido));
                System.out.println("Usuario Premium registrado exitosamente.");
            } else {
                System.out.println("Tipo de usuario inválido.");
            }
        } else if (option == 3) {
            // Eliminar un Producto bibliográfico
            System.out.println("Ingrese el ID del producto a eliminar: ");
            String nombreProducto = scanner.nextLine();
            boolean eliminado = ReadX.eliminarProducto(nombreProducto);
            if (eliminado) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró un producto con el ID especificado.");
            }
    } else if (option == 4) {
        // Modificar un Producto bibliográfico
    	System.out.println("Ingrese el ID del producto a modificar: ");
    	String idProducto = scanner.nextLine();

    	boolean modificado = false;
    	for (BibliographicProduct producto : ReadX.getColeccionProductos()) {
    	    if (producto.getId().equalsIgnoreCase(idProducto)) {
    	        System.out.println("Ingrese el nuevo título del producto: ");
    	        String nuevoTitulo = scanner.nextLine();
    	        System.out.println("Ingrese el nuevo número de páginas del producto: ");
    	        int nuevoNumeroPaginas = Integer.parseInt(scanner.nextLine());
    	        System.out.println("Ingrese la nueva fecha de publicación del producto (dd/MM/yyyy): ");
    	        String nuevaFecha = scanner.nextLine();
    	        System.out.println("Ingrese el nuevo autor del producto: ");
    	        String nuevoAutor = scanner.nextLine();
    	        System.out.println("Ingrese el nuevo precio del producto: ");
    	        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
    	        System.out.println("Ingrese el nuevo tipo de producto (L para libro, R para revista): ");
    	        String nuevoTipoProducto = scanner.nextLine();

    	        producto.setName(nuevoTitulo);
    	        producto.setNumbPages(nuevoNumeroPaginas);
    	        try {
    	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    	            formatoFecha.setLenient(false);
    	            Calendar nuevaFechaPub = Calendar.getInstance();
    	            nuevaFechaPub.setTime(formatoFecha.parse(nuevaFecha));
    	            producto.setPublicationDate(nuevaFechaPub);
    	        } catch (ParseException e) {
    	            System.out.println("Fecha inválida. No se modificó la fecha de publicación.");
    	        }
    	        producto.setUrl(nuevoAutor);
    	        producto.setPrize(nuevoPrecio);

    	        if (nuevoTipoProducto.equalsIgnoreCase("L")) {
    	            if (producto instanceof Book) {
    	                System.out.println("Ingrese la nueva reseña del libro: ");
    	                String nuevaResena = scanner.nextLine();
    	                System.out.println("Ingrese la nueva categoría del libro (CIENCIA_FICCION, FANTASÍA, NOVELA_HISTÓRICA): ");
    	                String nuevaCategoria = scanner.nextLine();
    	                BookType nuevoTipoLibro = BookType.valueOf(nuevaCategoria.toUpperCase());
    	                ((Book) producto).setReview(nuevaResena);
    	                ((Book) producto).setBookType(nuevoTipoLibro);
    	            } else {
    	                System.out.println("El producto no es un libro. No se modificaron la reseña ni la categoría.");
    	            }
    	        } else if (nuevoTipoProducto.equalsIgnoreCase("R")) {
    	            if (producto instanceof Magazine) {
    	                System.out.println("Ingrese la nueva periodicidad de la revista: ");
    	                int nuevaPeriodicidad = Integer.parseInt(scanner.nextLine());
    	                System.out.println("Ingrese la nueva categoría de la revista (VARIEDAD, DISEÑO, CIENTÍFICA): ");
    	                String nuevaCategoria = scanner.nextLine();
    	                MagazineType nuevoTipoRevista = MagazineType.valueOf(nuevaCategoria.toUpperCase());
    	                ((Magazine) producto).setPeriodicBroadcast(nuevaPeriodicidad);
    	                ((Magazine) producto).setMagazineType(nuevoTipoRevista);
    	            } else {
    	                System.out.println("El producto no es una revista. No se modificaron la periodicidad ni la categoría.");
    	            }
    	        } else {
    	            System.out.println("Tipo de producto inválido. No se modificó el tipo de producto.");
    	        }

    	        modificado = true;
    	        break;
    	    }
    	}

    	if (modificado) {
    	    System.out.println("Producto modificado correctamente.");
    	} else {
    	    System.out.println("No se encontró un producto con el ID especificado.");
    	}} else {
            System.out.println("Opción inválida.");
        }

        System.out.println(ReadX.getColeccionProductos());
		System.out.println(Library.coleccionProductos);
	
	}
	
}



