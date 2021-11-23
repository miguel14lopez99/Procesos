
package Dos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import java.util.Arrays;
//import java.lang.String;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.util.Locale;
import java.io.File;
//import java.util.concurrent.TimeUnit;

public class Ejercicio2 
{
   
    public static void main(String[] args) 
    {
        
        if (args.length != 1) 
        {
           System.out.println("Debe introducir el nombre del directorio");
           System.exit(1);
        }
        
        //Comprobamos si el parámetro es un directorio valido
        
        String NombreDir=args[0];
        
        File Dir = new File ( NombreDir);
        
        if ( !(Dir.exists() ) || !(Dir.isDirectory())  )
        {
          System.out.println("Error, el nombre introducido no existe o no es un directorio");
        
        }    
        else
        {
            Runtime rt = Runtime.getRuntime();
            
            
            try 
            {
              Process p = rt.exec("ls -ln",null,Dir);
            
             InputStreamReader Isr =new InputStreamReader( p.getInputStream() ) ;
         
             BufferedReader br = new BufferedReader(Isr);
         
             String linea= br.readLine();
         
             while ( linea!=null     )
             {
               System.out.println(linea);
           
               linea= br.readLine();
           
              }    
             
        
            } 
            catch (IOException e) 
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
