
package Cinco;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.FileReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.util.Locale;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import java.io.FileWriter;
import java.lang.*;

import java.util.Arrays.*;
import java.util.concurrent.TimeUnit;

class Auxiliar {

   
    public BufferedReader RetSalida(String com) 
    {

        String[] campos = com.split(" +");
        
        ProcessBuilder pb = new ProcessBuilder(campos);

        pb.redirectOutput(new File("Salida.txt"));    //Redirecionamos la salida del comando a un archivo 

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //Inicilizamos el BufferReader);  //Inicilizamos el BufferReader

        try {
            Process p = pb.start();

            try {
                
                if (!p.waitFor(5, TimeUnit.SECONDS)) //Si el proceso p no se detiene en 5 segundos
                {

                    p.destroy();  //Eliminamos el proceso
                }
         
            } catch (InterruptedException ex) {
                System.err.println("Proceso interrumpido");
                System.exit(2);
            }
         
          br = new BufferedReader(new FileReader("Salida.txt"));  //Llenamos el Buffer Reader con el archivo de la salida del comando

        } catch (IOException e) {
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

public class Cinco {

    public static void main(String[] args) {

        if (args.length != 1) //Si no hemos introducido el campo de ordenar
        {
            System.out.println("Debe introducir la dirección IP");
            System.exit(1);
        }

        String camp = args[0];     //Recibimos la IP

        Auxiliar aux = new Auxiliar(); //Instanciamos la clase auxiliar para validaciones

        BufferedReader Salida=aux.RetSalida("ping "+camp);   //Obtenemos la salida del comando
        
        try {
            //Mostramos la salida del comando
            String linea;    //Declaramos una variable para ir procesando las lineas   
            
            int cont=0;     //Contador del numero de replys recibidos
            
            float SumaTime= 0; //Suma de todos los segundos de Replys
            
            while (  (linea=Salida.readLine())!=null)
            {
                   String[] campos =linea.split(" ");
                   
                   for(int i=0;i<campos.length;i++)         //Cogemos los campos de esa linea
                   {
                      if (campos[i].contains("time=")  )    //Nos quedamos con la columna que contenga el tiempo
                      {
                          String[] camposT=campos[i].split("=");  //Separamos a partir del igual
                      
                          System.out.println(camposT[1]);         //Mostramos esa marca de tiempo
                          
                          SumaTime+=Float.valueOf(camposT[1]);  //Sumamos la marca al acumulador
                          
                           cont++;  // Incrementar el contador de lineas (Replys)
                      
                      } 
                   
                   }    
                      
                

            }
            
            System.out.println("Replys realizados:"+cont); 
             
            System.out.println("La media de los Replys es:"+( SumaTime/cont)); 
            
            
        } catch (IOException e) {
            System.err.println("Error durante ejecución del proceso");
            System.err.println("Información detallada");
            System.err.println("---------------------");
            e.printStackTrace();
            System.err.println("---------------------");
            System.exit(2);

        }

    }

}
