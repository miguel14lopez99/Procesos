/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

//import java.util.Arrays;
//import java.lang.String;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.util.Locale;
//import java.util.concurrent.TimeUnit;

/**
 *
 * @author joaquin
 */
public class Ejercicio1 {
    
    public static void main(String[] args) {
 
    /*       
    if (args.length <= 0) {
      System.out.println("Debe indicarse comando a ejecutar.");
      System.exit(1);
    }
     */

    ProcessBuilder pb = new ProcessBuilder("ls","-ln");    //Indicamos el comando a ejecutar
   
     
    
    try {
            Process p = pb.start();   //  Creamos una instancia de la clase process heredando la E/S
         
            InputStreamReader isr = new InputStreamReader (p.getInputStream());    //Cogemos la salida y la convertimos a un StreamReader
            BufferedReader Br = new BufferedReader(isr) ;
            
            String linea;    //Declaramos una variable para ir procesando las lineas

            while (  (linea=Br.readLine())!=null)
            {
               
                   System.out.println(linea);    
              
            }

 
    } catch (IOException e) 
      {
      System.err.println("Error durante ejecución del proceso");
      System.err.println("Información detallada");
      System.err.println("---------------------");
      e.printStackTrace();
      System.err.println("---------------------");
      System.exit(2);
     } 
    
     

  }
    
    
    
    
    
    
}

