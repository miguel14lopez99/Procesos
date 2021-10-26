/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicios.procesos_tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguel
 */

class Aux{
    
    public BufferedReader SalidaComando(String com){
        
        BufferedReader Br = null;
        
        try {  
            
            String[] campos = com.split(" +");
            
            ProcessBuilder pb = new ProcessBuilder(campos);
            
            Process p = pb.start();   //  Creamos una instancia de la clase process heredando la E/S

            InputStreamReader isr = new InputStreamReader (p.getInputStream());    //Cogemos la salida y la convertimos a un StreamReader
            Br = new BufferedReader(isr) ;

        } catch (IOException e) {
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

public class Ejercicio4 {
    
    public static void main(String[] args) {
        
        Aux au = new Aux();
        
        BufferedReader Br = au.SalidaComando("ps aux");
        
        String linea;    //Declaramos una variable para ir procesando las lineas
        
        try {
            
            while ((linea=Br.readLine())!=null) {     
                String[] campos = linea.split(" +");
                
                if(campos[0].equals("miguel")){
                    
                    System.out.println(linea);
                    
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
