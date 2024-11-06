package org.example.controlador;

import org.example.modelo.Pelicula;

import java.sql.SQLException;
import java.util.Scanner;

import static org.example.modelo.Tools.*;
import static org.example.vista.MethodCalls.*;

public class Interacciones {

    static Scanner scanner = new Scanner(System.in);

    public static void consultarAll() throws SQLException {


        System.out.println("Has seleccionado la operación de consulta.");
        showPeliculas();

    }

    public static void consultarOne() throws SQLException {


        System.out.println("Has seleccionado la operación de busqueda específica por nombre.");

        System.out.println("Dajme el nombre de la pelicula a consultar\n");
        String peliculaSearch = scanner.nextLine();

        buscarPeliculaPorTitulo(peliculaSearch);
    }

    public static void añadir() throws SQLException {


        System.out.println("Has seleccionado la operación de añadir.\n");

        System.out.println("Dajme el Titulo\n");
        String tituloNew = scanner.nextLine();

        System.out.println("Dajme el Protagonista\n");
        String protagonistaNew = scanner.nextLine();

        System.out.println("Dajme la Tematica: " + YELLOW + "Accion" + RESET + ", " + YELLOW + "Aventura" + RESET +", " + YELLOW + "Ciencia Ficcion" + RESET +", " + YELLOW + "Romance"+ RESET + ", " + YELLOW + "Terror" + RESET + "\n");
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

    }

    public static void actualizar() throws SQLException {



        System.out.println("Has seleccionado la operación de actualización.");

        System.out.println("Dame el ID de la película:\n");
        int peliculaUpdate = scanner.nextInt();
        scanner.nextLine();

        buscarPeliculaPorId(peliculaUpdate);

        System.out.println("Dime qué quieres modificar: " + YELLOW + "Titulo" + RESET + ", " + YELLOW + "Protagonista" + RESET +", " + YELLOW + "Tematica" + RESET +", " + YELLOW + "Gion"+ RESET + ", " + YELLOW + "Disponible" + RESET + "\n");

        String columnaAModificar = scanner.nextLine().toLowerCase();


        System.out.println("Dame el valor nuevo de la columna:");

        if (columnaAModificar.toLowerCase().equals("disponible")) {
            System.out.println("[1] -> Disponible | [0] -> No disponible");
        }

        String valorNuevo = scanner.nextLine();

        modificarPelicula(columnaAModificar, valorNuevo, peliculaUpdate);

    }

    public static void eliminar() throws SQLException {


        System.out.println("Has seleccionado la operación de eliminación.");

        System.out.println("Dajme el ID de la pelicula a eliminar\n");
        int peliculaDelete = scanner.nextInt();

        deletePelicula(peliculaDelete);

    }


}
