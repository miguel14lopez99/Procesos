
import java.util.Arrays;
import java.lang.String;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Ejercicio1 
{
   
     public static void main(String[] args) 
     {

        ProcessBuilder pb = new ProcessBuilder("ls","-ln");

        try 
         {  //pb.inheritIO();   //Heredamos la entrada salida del proceso padre

            Process p=pb.start(); 

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
    

