/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dos;

import java.util.Arrays;
import java.io.File;
//import java.lang.String;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.util.Locale;
//import java.util.concurrent.TimeUnit;


public class Ejercicio2 
{
    
    public static void main(String[] args) {
      
    if (args.length != 1) 
    {
      System.out.println("Debe indicarse el nombre del directorio");
      System.exit(1);
    }
     

    String NomDir = args[0]; // Recogemos el nombre del directorio
    
    File Fil = new File(NomDir);  //Creamos un objeto File 
    
    if ( !(Fil.exists() )  ||  (  !(Fil.isDirectory() )    )  )
    {
      System.out.println("Debe introducir un nombre de directorio válido");
      System.exit(2);
    }        
    else     //Si es un directorio válido
    {    
                Runtime Rt = Runtime.getRuntime();    //Instanciamos la clase RunTime
    
                try {
                        Process p = Rt.exec("ls",null,Fil);  //Creamos una instancia de ese comando en el directorio indicado
                                
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
