/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuatro;

/**
 *
 * @author joaquin
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.util.Locale;
import java.io.File;
//import java.util.concurrent.TimeUnit;

 
class Auxiliar 
{

   public boolean ExUsuario(String usu)                    //Comprueba si ese usuario existe en el archivo de usuarios del sistema
    {
       boolean encontrado = false;
       
       BufferedReader br= RetSalida("cat /etc/passwd");
       
     
      try{
            String linea= br.readLine();
            
            while ( (linea!=null) && !(encontrado   )  )
            {     
             
              String[] campos =linea.split(":");           //Nos quedamos con el primer campo

              //System.out.println( campos[0]);
              
              encontrado=campos[0].equals(usu);   //Si el campo coincide con el usuario
              
              linea= br.readLine();
             }    

          }        
      catch(IOException e) 
              {
             System.err.println("Error durante ejecución del proceso");
             System.err.println("Información detallada");
             System.err.println("---------------------");
             e.printStackTrace();
             System.err.println("---------------------");
             System.exit(2);
           }
      
      return encontrado;
        
  }
    
    public BufferedReader  RetSalida(String  com)
    {    
             
             ProcessBuilder pb = new ProcessBuilder(com.split(" +"));    
        
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //Inicilizamos el BufferReader
            
            try {
                Process p = pb.start(); 

                InputStreamReader Isr =new InputStreamReader( p.getInputStream() ) ;

                br=new  BufferedReader(Isr);

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

            return br;
           
              
       }


}        


public class Ejercicio4 
{    
  
    
    public static void main(String[] args) 
    {
        
        if (args.length != 1) 
        {
           System.out.println("Debe introducir el nombre de usuario");
           System.exit(1);
        }
        
        String usu=args[0];
       
        Auxiliar aux = new Auxiliar(); 
        
        if (!aux.ExUsuario(usu)   )
        {
           System.out.println("Error ese usuario no existe en el sistema");
           System.exit(2);
        }    
        else
        {
          System.out.println("El usuario "+usu +" es valido ");
          System.out.println("Mostramos su salida ");
          
          BufferedReader Salida=aux.RetSalida("ps aux");
          
          try
          {
           String linea= Salida.readLine();
          
            while ( (linea!=null)   )
            {
               String[] campos =linea.split(" ");           //Nos quedamos con el primer campo
              
               if (campos[0].equals(usu))   //Si el campo coincide con el usuario
               {
                System.out.println(linea);
               
               }
              linea= Salida.readLine();
            
            
            }        
                    
          }
          catch(IOException e)
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
