/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CuatroB;

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
import java.util.*;
import java.util.Map.Entry;

import java.util.Arrays.*;
import java.util.concurrent.TimeUnit;

 
class Auxiliar 
{
   
   public int ComprobarCampo(String camp,String linea)      //Comprueba si ese campo de ordenacion es válido
    {
        boolean encontrado = false;
       
        String[] campos =linea.split(" +");           //Dividimos la linea en campos

        int pos=0;

        System.out.println(linea);
        
        while ( (pos<campos.length) && !(campos[pos].equals(camp) )  )
        {           
            pos++;
        }    

     if (pos==campos.length)   // Si no lo ha encontrado devolvemos un valor de error, no devolvemos 0  poe ser un valor válido
     {
      pos=-1;
     }   
     
      return pos;
        
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


public class CuatroB
{    
  
    
    public static void main(String[] args) 
    {
        
        if (args.length != 1)       //Si no hemos introducido el campo de ordenar
        {
           System.out.println("Debe introducir el campo de ordenación");
           System.exit(1);
        }
        
        String camp=args[0];     //Recogemos el nombre del campo introducido
       
        Auxiliar aux = new Auxiliar(); //Instanciamos la clase auxiliar para validaciones
        
        BufferedReader Salida=aux.RetSalida("ps aux");   //Obtenemos la salida del comando
          
          try
          {
           String linea= Salida.readLine();    //Cogemos la primera linea
           
           String[] campos;
           
           int pos=aux.ComprobarCampo(camp,linea);   //Comprobamos si el campo está en la primera linea y su posición
          
           if (pos>=0)                            //Si ese campo esta dentro de la linea
           {
               //Rellenamos en un HashMap la salida del comando
               
               Map<String,String> map = new HashMap<>();
               
              while ( (linea=Salida.readLine())!=null )
               { 
                   campos =linea.split(" +");
                   
                   map.put(linea,campos[pos] );  //La clave es la linea y el valor el campo de ordenacion
               }
               
              
               List<Entry<String, String>> list = new ArrayList<>(map.entrySet());   //Convertimos el map a un arraylist
               
               list.sort(Entry.comparingByValue());  //Ordenamos el List por valor
               
               list.forEach(System.out::println);
               
               /*
               //Ordenamos por clave el Mapa anterior
               
               TreeMap<String, String> sorted = new TreeMap<>();
 
               // Copiamos todos los datos al TreeMap
               
               sorted.putAll(map);
 
               //Mostramos el Mapa ordenado por pantalla
               
                for (Map.Entry<String, String> entry : sorted.entrySet())
                {    
                    System.out.println(entry.getValue());
                 }
              
               */
              
           }
           else
           {    System.out.println("ERROR; El parámetro introducido no es un campo válido");               }    
           
          
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
    

