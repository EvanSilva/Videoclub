package org.example.modelo;

import java.sql.*;

public class Tools {


    public static Connection conexion;

        public static void connectDB() {
            try {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/videoclub", "root", "root");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    private static String checkDisponibilidad(int booleano) {
        if (booleano == 0) {
            return "ALQUILADA";
        } else {
            return "DISPONIBLE";
        }
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
                    "Disponibilidad: " + checkDisponibilidad(disponible) + "\n");
        }
    }

    public static void buscarPeliculaPorTitulo(String titulo) throws SQLException {
        String query = "SELECT * FROM peliculas WHERE titulo = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, titulo);
            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    int peliculaId = resultado.getInt("id");
                    String tituloPelicula = resultado.getString("titulo");
                    String actorPrincipal = resultado.getString("protagonista");
                    String tematica = resultado.getString("tematica");
                    String guion = resultado.getString("guion");
                    int disponibilidad = resultado.getInt("disponible");

                    System.out.println("La información de " + tituloPelicula + " es: \n" +
                            "Id: " + peliculaId + "\n" +
                            "Protagonista: " + actorPrincipal + "\n" +
                            "Tematica: " + tematica + "\n" +
                            "Guion: " + guion + "\n" +
                            "Disponibilidad: " + checkDisponibilidad(disponibilidad) + "\n");
                } else {
                    System.out.println("No se encontró ninguna película con el nombre proporcionado.");
                }
            }
        }
    }

    public static void buscarPeliculaPorId(int id) throws SQLException {
        String query = "SELECT * FROM peliculas WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    int peliculaId = resultado.getInt("id");
                    String tituloPelicula = resultado.getString("titulo");
                    String actorPrincipal = resultado.getString("protagonista");
                    String tematica = resultado.getString("tematica");
                    String guion = resultado.getString("guion");
                    int disponibilidad = resultado.getInt("disponible");

                    System.out.println("La información de " + tituloPelicula + " es: \n" +
                            "Id: " + peliculaId + "\n" +
                            "Protagonista: " + actorPrincipal + "\n" +
                            "Tematica: " + tematica + "\n" +
                            "Guion: " + guion + "\n" +
                            "Disponibilidad: " + checkDisponibilidad(disponibilidad) + "\n");
                } else {
                    System.out.println("No se encontró ninguna película con el ID proporcionado.");
                }
            }
        }
    }

    public static void modificarPelicula(String tipoDatoACambiar, String nuevoDato, int idPeliculaModificar) throws SQLException {

        String sqUpdate = "";


        if (tipoDatoACambiar.equals("disponible")) {

            sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
            nuevoDato = nuevoDato.equalsIgnoreCase("true") ? "1" : "0";

        } else {

            sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
        }

        /*

        MANERA MUY REBUSCADA Y ASQUEROSA DE HACERLO

        switch (tipoDatoACambiar.toLowerCase()) {
            case "titulo":
                sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
                break;
            case "protagonista":
                sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
                break;
            case "tematica":
                sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
                break;
            case "guion":
                sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
                break;

            case "disponible":
                sqUpdate = "UPDATE peliculas SET " + tipoDatoACambiar + " = ? WHERE id = ?";
                nuevoDato = nuevoDato.equalsIgnoreCase("true") ? "1" : "0";
                break;

            default:
                System.out.println("Operación no válida. Por favor, selecciona una opción válida.");
                return;
        }

         */


        try (PreparedStatement statementUpdate = conexion.prepareStatement(sqUpdate)) {
            if (tipoDatoACambiar.equals("disponible")) {
                statementUpdate.setInt(1, Integer.parseInt(nuevoDato));
            } else {
                statementUpdate.setString(1, nuevoDato);
            }
            statementUpdate.setInt(2, idPeliculaModificar);

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
        try (PreparedStatement statementDelete = conexion.prepareStatement(sqDelete)) {
            statementDelete.setInt(1, idPelicula);
            int filasEliminadas = statementDelete.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Película eliminada exitosamente.");
            } else {
                System.out.println("No se encontró ninguna película con el ID proporcionado.");
            }
        }
    }

    public static void insertPelicula(Pelicula pelicula) throws SQLException {
        String sqlInsert = "INSERT INTO peliculas (titulo, protagonista, tematica, guion, disponible) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statementInsert = conexion.prepareStatement(sqlInsert)) {
            statementInsert.setString(1, pelicula.getTitulo());
            statementInsert.setString(2, pelicula.getProtagonista());
            statementInsert.setString(3, pelicula.getTematica());
            statementInsert.setString(4, pelicula.getGuion());
            statementInsert.setBoolean(5, pelicula.getDisponible());
            statementInsert.executeUpdate();
            System.out.println("Película insertada exitosamente.");
        }
    }
}




