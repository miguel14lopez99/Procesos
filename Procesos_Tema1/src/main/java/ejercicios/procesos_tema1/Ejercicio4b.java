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
    
    public boolean ValidarCampo(String cam, String linea) {
        
        boolean valido=false;
  
        String[] campos = linea.split(" +");  //Dividimos la linea en un array de String

        for (String campo : campos) {
            if (campo.equals(cam)){
                valido = true;
            }
        }

        return valido;
    }
    
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

public class Ejercicio4b {
    
    public static void main(String[] args) {
        
        try {
            
            if (args.length !=1 ) {
                System.out.println("Debe indicar el campo de ordenación");
                System.exit(1);
            }

            String cam = args[0];   //Recogemos el usuario introducido
            
            Aux au= new Aux();
            
            BufferedReader br = au.SalidaComando("ps aux"); //Obtenemos la salida del comando
            
            
            
            String linea = br.readLine();   //Nos quedamos con la primera linea del comando
            
            if (au.ValidarCampo(cam, linea) ) {
                System.out.println("El campo "+cam +" es valido ");
            } else {
                System.out.println("El campo "+cam +" no se encuentra en la lista");
            }

        }     
        catch (IOException e) {
          System.err.println("Error durante ejecución del proceso");
          System.err.println("Información detallada");
          System.err.println("---------------------");
          e.printStackTrace();
          System.err.println("---------------------");
          System.exit(2);
        }         
    }  
}
