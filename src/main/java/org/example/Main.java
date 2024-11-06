package org.example;

import org.example.modelo.Pelicula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static org.example.modelo.Tools.*;
import static org.example.vista.MethodCalls.*;

public class Main {


    public static void main(String[] args) throws SQLException {

        connectDB();

        Scanner sc = new Scanner(System.in);

        String repetir = "PlacerHolderIneedToExiStForFirstLoop";

        header();


        while (!repetir.equals("no")) {

            bodyExecution();

            System.out.println("Quieres hacer otra consulta?");
            repetir = sc.nextLine().toLowerCase();
        }

        despedida();

    }
}