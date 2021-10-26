/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuatro;

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


class Aux {

        public boolean ValidarUsu(String usu)
        {
            boolean valido=false;
            
            try {   
                    ProcessBuilder pb = new ProcessBuilder("cat","/etc/passwd");
                   
                    Process p = pb.start();   //  Creamos una instancia de la clase process heredando la E/S

                    InputStreamReader isr = new InputStreamReader (p.getInputStream());    //Cogemos la salida y la convertimos a un StreamReader
                    BufferedReader Br = new BufferedReader(isr) ;

                    String linea;    //Declaramos una variable para ir procesando las lineas

                    while (  ((linea=Br.readLine())!=null)  &&  !(valido)      )
                    {
                        String[] campos =linea.split(":");  //Dividimos la linea en un array de String
                               
                        valido=campos[0].equals(usu);         //Comprobamos si  el primer campo  coincide con el  usuario
                        
                        
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
            
        
            return valido;
        }
        
        public BufferedReader SalidaComando(String com)
        {
           
            BufferedReader Br=null;   
            
            try {   
                    String[] campos=com.split(" +");  
                
                    ProcessBuilder pb = new ProcessBuilder(campos);
                   
                    Process p = pb.start();   //  Creamos una instancia de la clase process heredando la E/S

                    InputStreamReader isr = new InputStreamReader (p.getInputStream());    //Cogemos la salida y la convertimos a un StreamReader
                    Br = new BufferedReader(isr) ;

     
               } catch (IOException e) 
                 {
                    System.err.println("Error durante ejecución del proceso");
                    System.err.println("Información detallada");
                    System.err.println("---------------------");
                    e.printStackTrace();
                    System.err.println("---------------------");
                    System.exit(2);
                 } 
            
        
            return Br;
        }
        

    }

public class Ejercicio4a {
    
    
    public static void main(String[] args) {
 
    
    if (args.length !=1 ) {
      System.out.println("Debe indicar un usuario del sistema");
      System.exit(1);
    }
    
    String usu = args[0];   //Recogemos el usuario introducido
    
    Aux au= new Aux();
    
    if (au.ValidarUsu(usu) )
    {
      System.out.println("El usuario "+usu +" es valido ");
    }    
    else
    {
     System.out.println("El usuario "+usu +" no está en el sistema");
    }    
        
    BufferedReader Br=au.SalidaComando("ps aux");
    
    String linea;
    
    try {  
    
            while (  (linea=Br.readLine())!=null )
            {
               String[] campos=linea.split(" +");
               
               if (campos[0].equals(usu) )
               {
                 System.out.println(linea);
               }    
                  

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
