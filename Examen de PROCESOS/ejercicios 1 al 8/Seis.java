/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seis;

/**
 *
 * @author joaquin
 */
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

        ProcessBuilder pb = new ProcessBuilder(com.split(" +"));

        pb.redirectOutput(new File("Salida.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //Inicilizamos el BufferReader);  //Inicilizamos el BufferReader

        try {
            Process p = pb.start();

            try {
                
                if (!p.waitFor(6, TimeUnit.SECONDS)) //Lo detenemos en 5 segundos
                {
                    p.destroy();
                }
         
            } catch (InterruptedException ex) {
                System.err.println("Proceso interrumpido");
                System.exit(2);
            }
         
          br = new BufferedReader(new FileReader("Salida.txt"));  

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

public class Seis 
{

    public static void main(String[] args) {

        if (args.length != 2) //Si no hemos introducido dos parámetros
        {
            System.out.println("Debe introducir el directorio de búsqueda y el archivo a buscar");
            System.exit(1);
        }

        String dir = args[0];    //Directorio donde buscar
        String fich = args[1];   //Archivo que buscar
        

        Auxiliar aux = new Auxiliar(); //Instanciamos la clase auxiliar 

        BufferedReader Salida=aux.RetSalida("find "+dir+" -name "+fich);   //Obtenemos la salida del comando
        try 
        {
            //Mostramos la salida del comando
            String linea;    //Declaramos una variable para ir procesando las lineas   
            
            while (  (linea=Salida.readLine())!=null)
            {
                   System.out.println(linea);
                   
            }    
                       
                     
            
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
