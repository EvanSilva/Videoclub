package org.example.vista;

import org.example.modelo.Pelicula;

import java.sql.SQLException;
import java.util.Scanner;

import static org.example.controlador.Interacciones.*;
import static org.example.modelo.Tools.*;

public class MethodCalls {


    public static void header(){

        System.out.println("""
 __     __  __        __                                __            __       
/  |   /  |/  |      /  |                              /  |          /  |      
$$ |   $$ |$$/   ____$$ |  ______    ______    _______ $$ | __    __ $$ |____  
$$ |   $$ |/  | /    $$ | /      \\  /      \\  /       |$$ |/  |  /  |$$      \\ 
$$  \\ /$$/ $$ |/$$$$$$$ |/$$$$$$  |/$$$$$$  |/$$$$$$$/ $$ |$$ |  $$ |$$$$$$$  |
 $$  /$$/  $$ |$$ |  $$ |$$    $$ |$$ |  $$ |$$ |      $$ |$$ |  $$ |$$ |  $$ |
  $$ $$/   $$ |$$ \\__$$ |$$$$$$$$/ $$ \\__$$ |$$ \\_____ $$ |$$ \\__$$ |$$ |__$$ |
   $$$/    $$ |$$    $$ |$$       |$$    $$/ $$       |$$ |$$    $$/ $$    $$/ 
    $/     $$/  $$$$$$$/  $$$$$$$/  $$$$$$/   $$$$$$$/ $$/  $$$$$$/  $$$$$$$/  
""");

    }

    public static void despedida(){

        System.out.println("""
 __    __                        __                      __                                                          __                         
/  |  /  |                      /  |                    /  |                                                        /  |                        
$$ |  $$ |  ______    _______  _$$ |_     ______        $$ |  ______          ______    ______    ______   __    __ $$/  _____  ____    ______  
$$ |__$$ | /      \\  /       |/ $$   |   /      \\       $$ | /      \\        /      \\  /      \\  /      \\ /  \\  /  |/  |/     \\/    \\  /      \\ 
$$    $$ | $$$$$$  |/$$$$$$$/ $$$$$$/    $$$$$$  |      $$ | $$$$$$  |      /$$$$$$  |/$$$$$$  |/$$$$$$  |$$  \\/$$/ $$ |$$$$$$ $$$$  | $$$$$$  |
$$$$$$$$ | /    $$ |$$      \\   $$ | __  /    $$ |      $$ | /    $$ |      $$ |  $$ |$$ |  $$/ $$ |  $$ | $$  $$<  $$ |$$ | $$ | $$ | /    $$ |
$$ |  $$ |/$$$$$$$ | $$$$$$  |  $$ |/  |/$$$$$$$ |      $$ |/$$$$$$$ |      $$ |__$$ |$$ |      $$ \\__$$ | /$$$$  \\ $$ |$$ | $$ | $$ |/$$$$$$$ |
$$ |  $$ |$$    $$ |/     $$/   $$  $$/ $$    $$ |      $$ |$$    $$ |      $$    $$/ $$ |      $$    $$/ /$$/ $$  |$$ |$$ | $$ | $$ |$$    $$ |
$$/   $$/  $$$$$$$/ $$$$$$$/     $$$$/   $$$$$$$/       $$/  $$$$$$$/       $$$$$$$/  $$/        $$$$$$/  $$/   $$/ $$/ $$/  $$/  $$/  $$$$$$$/ 
                                                                            $$ |                                                                
                                                                            $$ |                                                                
                                                                            $$/                                                              
""");

    }

    public static void bodyExecution(){

        try {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Qué tipo de operacion quieres hacer: [Consultar] [CE (Consulta especifica)] [Añadir] [Actualizar] [Eliminar] \n");

            String operacion = scanner.nextLine();

            switch (operacion.toLowerCase()) {
                case "consultar":

                    consultarAll();

                    break;

                case "ce":

                    consultarOne();

                    break;

                case "añadir":

                    añadir();

                    break;

                case "actualizar":

                    actualizar();

                    break;

                case "eliminar":

                   eliminar();

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
