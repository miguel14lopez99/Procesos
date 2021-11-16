/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tres;

//import java.util.Arrays;
//import java.lang.String;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.File;
//import java.io.OutputStreamWriter;
//import java.util.Locale;
//import java.util.concurrent.TimeUnit;

/**
 *
 * @author joaquin
 */
public class Ejercicio3 
{
   public static void main(String[] args) {
 
    
    if (args.length !=1 ) {
      System.out.println("Debe indicarse comando a ejecutar.");
      System.exit(1);
    }
    
    String com = args[0];
    
    File Fil = new File("/bin/"+com);
    
    if (!Fil.isFile() )
    {
      System.out.println("Error, debe indicar un ejecutable valido");
      System.exit(2);
      
    }  
    else  //El comando existe
    {
        
            ProcessBuilder pb = new ProcessBuilder(com );
          
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

}