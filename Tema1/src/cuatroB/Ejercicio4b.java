/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cuatroB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author miguel
 */

class Aux {

    public int ComprobarCampo(String camp, String linea)
    {
        boolean encontrado = false;

        String[] campos = linea.split(" +");

        int pos = 0;
        
        System.out.println(linea);

        while((pos < campos.length) && !(campos[pos].equals(camp)) ){
            pos++;
        }
        
        if(pos == campos.length)
            pos = -1;
        
        return pos;
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

public class Ejercicio4b {


    public static void main(String[] args) {
        
        if(args.length != 1){
            System.out.println("Debe introducir el campo de ordenación");
            System.exit(1);
        }
        
        String camp = args[0];
        
        Aux au = new Aux();
        
        BufferedReader salida = au.SalidaComando("ps aux");
        
        String linea = salida.readLine();
        
        String[] campos;
        
        int pos = au.ComprobarCampo(camp, linea);
        
        if(pos >= 0){
            
            Map<String,String> map = new HashMap<>();
            
            while ((linea = salida.readLine())!= null){
                campos = linea.split(" +");
                map.put(linea, campos[pos]);
            }
                
            List<Entry<String,String>> list = new ArrayList<>(map.entrySet());
            
            list.sort(Entry.comparingByValue());
            
            list.forEach(System.out::println);                       
            
        }
        
    }
    
}
