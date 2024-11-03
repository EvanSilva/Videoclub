package org.example;

import org.example.pelicula.Pelicula;

import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.pelicula.tools.*;

public class Main {

    public static Connection conexion;

    static {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/videoclub", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        try {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Qué tipo de operacion quieres hacer: [Consultar] [Añadir] [Actualizar] [Eliminar] \n");

            String operacion = scanner.nextLine();

            switch (operacion.toLowerCase()) {
                case "consultar":
                    System.out.println("Has seleccionado la operación de consulta.");
                    showPeliculas();
                    break;

                case "añadir":

                    System.out.println("Has seleccionado la operación de añadir.\n");

                    System.out.println("Dajme el Titulo\n");
                    String tituloNew = scanner.nextLine();

                    System.out.println("Dajme el Protagonista\n");
                    String protagonistaNew = scanner.nextLine();

                    System.out.println("Dajme la Tematica ['Accion', 'Aventura', 'Ciencia Ficcion', 'Romance', 'Terror']\n");
                    String tematicaNew = scanner.nextLine();

                    System.out.println("Dajme el Resumen\n");
                    String resumenNew = scanner.nextLine();

                    System.out.println("Dajme la Disponibilidad [1 -> Disponible | 0 -> NO disponible]\n");
                    int disponibilidadNew = scanner.nextInt();

                    boolean disponibilidad;

                    if (disponibilidadNew == 0) {
                        disponibilidad = false;
                    } else
                        disponibilidad = true;


                    Pelicula newPelicula = new Pelicula(tituloNew, protagonistaNew, tematicaNew, resumenNew, disponibilidad);

                    insertPelicula(newPelicula);

                    break;

                case "actualizar":
                    System.out.println("Has seleccionado la operación de actualización.");

                    System.out.println("Dame el ID de la película:");
                    int peliculaUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()

                    System.out.println("Dime qué quieres modificar (Nombre de la columna):");
                    String columnaAModificar = scanner.nextLine();

                    System.out.println("Dame el valor nuevo de la columna:");
                    String valorNuevo = scanner.nextLine();

                    modificarPelicula(columnaAModificar, valorNuevo, peliculaUpdate);

                    break;

                case "eliminar":
                    System.out.println("Has seleccionado la operación de eliminación.");

                    System.out.println("Dajme el ID de la pelicula a eliminar\n");
                    int peliculaDelete = scanner.nextInt();

                    deletePelicula(peliculaDelete);

                    break;

                default:
                    System.out.println("Operación no válida. Por favor, selecciona una opción válida.");
                    break;
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }




    }
}