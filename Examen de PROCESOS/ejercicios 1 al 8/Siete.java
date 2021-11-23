/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Siete;

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

public class Siete 
{

    public static void main(String[] args) {

        if (args.length != 2) //Si no hemos introducido dos parámetros
        {
            System.out.println("Número de parámetros incorrecto. Uso: patron archivo.txt");
            System.exit(1);
        }

        String pat = args[0];    //Patrón
        String arch = args[1];   //Archivo
        
        //Comprobamos que existe el archivo
        
        File Fil = new File (arch);
        
        if ( !(Fil.isFile() ) )
        {
          System.out.println("Error, el archivo introducido no es válido");
          System.exit(2);
        }  

        Auxiliar aux = new Auxiliar(); //Instanciamos la clase auxiliar 

        BufferedReader Salida=aux.RetSalida("cat "+arch);   //Obtenemos el contenido del archivo
        try 
        {
            //Mostramos la salida del comando
            String linea;    //Declaramos una variable para ir procesando las lineas   
            
            while (  (linea=Salida.readLine())!=null)
            {
               if (linea.contains(pat))    //Si la linea contiene el patrón
               { 
                System.out.println(linea);
               }    
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