package org.example.vista;

import org.example.modelo.Pelicula;

import java.sql.SQLException;
import java.util.Scanner;

import static org.example.controlador.Interacciones.*;
import static org.example.modelo.Tools.*;

public class MethodCalls {


    public static void bodyExecution(){

        try {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Qué tipo de operacion quieres hacer: [" + CYAN + "Consultar"+ RESET +"] [" + CYAN + "CE (Consulta especifica)"+ RESET +"] [" + CYAN + "Añadir"+ RESET +"] [" + CYAN + "Actualizar"+ RESET +"] [" + CYAN + "Eliminar"+ RESET +"] \n");

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

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void header(){

        System.out.println(BLUE + """
 __     __  __        __                                __            __       
/  |   /  |/  |      /  |                              /  |          /  |      
$$ |   $$ |$$/   ____$$ |  ______    ______    _______ $$ | __    __ $$ |____  
$$ |   $$ |/  | /    $$ | /      \\  /      \\  /       |$$ |/  |  /  |$$      \\ 
$$  \\ /$$/ $$ |/$$$$$$$ |/$$$$$$  |/$$$$$$  |/$$$$$$$/ $$ |$$ |  $$ |$$$$$$$  |
 $$  /$$/  $$ |$$ |  $$ |$$    $$ |$$ |  $$ |$$ |      $$ |$$ |  $$ |$$ |  $$ |
  $$ $$/   $$ |$$ \\__$$ |$$$$$$$$/ $$ \\__$$ |$$ \\_____ $$ |$$ \\__$$ |$$ |__$$ |
   $$$/    $$ |$$    $$ |$$       |$$    $$/ $$       |$$ |$$    $$/ $$    $$/ 
    $/     $$/  $$$$$$$/  $$$$$$$/  $$$$$$/   $$$$$$$/ $$/  $$$$$$/  $$$$$$$/  
"""+  RESET);

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

}
