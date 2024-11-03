package org.example.pelicula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.Main.conexion;

public class tools {

    private static String checkDisponibilidad(int booleano) {

        if (booleano == 0) {
            return "ALQUILADA";
        } else
            return "DISPONIBLE";
    }

    public static void showPeliculas() throws SQLException {

        Statement statement = conexion.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT * FROM peliculas");

        while (resultado.next()) {

            String id = resultado.getString(1);
            String titulo = resultado.getString(2);
            String protagonista = resultado.getString(3);
            String tematica = resultado.getString(4);
            String guion = resultado.getString(5);
            int disponible = resultado.getInt(6);

            System.out.println("La información de " + titulo + " es: \n" +
                    "Id: " + id + "\n" +
                    "Protagonista: " + protagonista + "\n" +
                    "Tematica: " + tematica + "\n" +
                    "Guion: " + guion + "\n" +
                    "Disponibilidad: " + checkDisponibilidad(disponible) + "\n" );

        }

    }

    public static void modificarPelicula(String tipoDatoACambiar, String nuevoDato, int idPeliculaModificar) throws SQLException {
        // Validar que el tipo de dato que se desea cambiar es válido
        String sqUpdate = "";

        switch (tipoDatoACambiar.toLowerCase()) {
            case "titulo":
            case "protagonista":
            case "tematica":
            case "guion":
                sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
                break;

            case "disponible":
                sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
                // Convertir el nuevo dato a booleano
                nuevoDato = nuevoDato.equalsIgnoreCase("true") ? "1" : "0"; // Convertir a "1" o "0"
                break;

            default:
                System.out.println("Operación no válida. Por favor, selecciona una opción válida.");
                return; // Salir del método si la operación no es válida
        }

        try (PreparedStatement statementUpdate = conexion.prepareStatement(sqUpdate)) {
            // Establecer los parámetros según el tipo de dato a modificar
            if (tipoDatoACambiar.equals("disponible")) {
                statementUpdate.setInt(1, Integer.parseInt(nuevoDato));
            } else {
                statementUpdate.setString(1, nuevoDato);
            }
            statementUpdate.setInt(2, idPeliculaModificar); // ID de la película

            int filasActualizadas = statementUpdate.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Película actualizada exitosamente.");
            } else {
                System.out.println("No se encontró ninguna película con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la película: " + e.getMessage());
        }
    }

    public static void deletePelicula(int idPelicula) throws SQLException {


        String sqDelete = "DELETE FROM peliculas WHERE id = ?";

        PreparedStatement statementDelete = conexion.prepareStatement(sqDelete);

        statementDelete.setInt(1, idPelicula);
        statementDelete.executeUpdate();

    }

    public static void insertPelicula(Pelicula pelicula) throws SQLException {
        String sqlInsert = "INSERT INTO peliculas (titulo, protagonista, tematica, guion, disponible) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statementInsert = conexion.prepareStatement(sqlInsert);

        statementInsert.setString(1, pelicula.getTitulo()); // Título
        statementInsert.setString(2, pelicula.getProtagonista()); // Protagonista
        statementInsert.setString(3, pelicula.getTematica()); // Temática
        statementInsert.setString(4, pelicula.getGuion()); // Guion
        statementInsert.setBoolean(5, pelicula.getDisponible()); // Disponible

        statementInsert.executeUpdate();
    }



}
