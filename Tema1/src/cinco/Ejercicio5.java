/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cinco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author miguel
 */

class Aux {       
        
    //validar ip; si + de 255 mal
    
    
    public BufferedReader SalidaComando(String com)
    {
        
        ProcessBuilder pb = new ProcessBuilder(com.split(" +"));

        pb.redirectOutput(new File("Salida.txt"));  //Redireccionamos la salida del comando a un archivo

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try{
           
            Process p = pb.start();
            
            try{
                
                if(!p.waitFor(5, TimeUnit.SECONDS)){    // se el proceso no se detiene en 5 sec
                    p.destroy();                        // se destruye
                }
                                
            } catch(InterruptedException ex){
                System.err.println("Proceso interrumpido");
                System.exit(2);
            }
            
            br = new BufferedReader(new FileReader("Salida.txt"));  // Inicializamos el BuferedReader 
            
        }catch (IOException e) {
            
            System.err.println("Error durante ejecución del proceso");
            System.err.println("Información detallada");
            System.err.println("---------------------");
            e.printStackTrace();
            System.err.println("---------------------");
            System.exit(2);
            
        }

        
    }
        

    }

public class Ejercicio5 {

    public static void main(String[] args) {

        if(args.length != 1){
            
        }
        
    }
    
}
