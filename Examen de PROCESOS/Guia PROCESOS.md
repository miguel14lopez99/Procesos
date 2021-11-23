CREAR PROCESOS

Con la clase ProcessBuilder

```java
ProcessBuilder pb = new ProcessBuilder("comando","opciones del comando y atributos");
Process p = pb.start();
```

Con la clase Runtime

```java
Runtime rt = Runtime.getRuntime();
Process p = rt.exec("comando mas opciones", null, "atributos");
```

LECTURA DE LA SALIDA DEL PROCESO

```java
InputStreamReader Isr = new InputStreamReader(p.getInputStream());
BufferedReader br =  new BufferedReader(Isr);
String linea = br.readLine();
while(linea != null){
    System.out.println(linea);
    linea = br.readLine();
}
```

DEVOLVER LA SALIDA DEL COMANDO EN BUFFEREDREADER, se le pasa el comando entero

```java
public BufferedReader RetSalida(String com){    
             
    ProcessBuilder pb = new ProcessBuilder(com.split(" +"));            
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //Inicilizamos el BufferReader
            
    try {

        Process p = pb.start(); 
        InputStreamReader Isr =new InputStreamReader(p.getInputStream()) ;
        br=new  BufferedReader(Isr);

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
```



CONTROL DE EXCEPCIONES

```java
try{
    // LECTURA DE LA SALIDA DEL PROCESO
} catch (IOException ex) {
    System.err.println("Error durante ejecución del proceso");
    System.err.println("Información detallada");
    System.err.println("---------------------");
    ex.printStackTrace();
    System.err.println("---------------------");
    System.exit(2);
}
```

COMPROBAR QUE EL MAIN TIENE ARGUMENTOS

```java
if(args.length != 1){
    System.out.println("Debe introducir ..."); // NO TIENE ARGS
    System.exit(1); // SE SALE DEL PROGRAMA
}
```

COMPROBAR QUE UN ARCHIVO EXISTE

```java
File file = new File (arch);
        
if (!(file.isFile())){
    System.out.println("Error, el archivo introducido no es válido");
    System.exit(2);
}  
```

COMPROBAR QUE UN DIRECTORIO EXISTE

```java
if(!dir.exists() || !dir.isDirectory()){
    // AQUI NO EXISTE
    System.out.println("El directorio introducido no existe o no es un directorio");
} else {
    // AQUI EXISTE
}
```

COMPROBAR QUE UN COMANDO EXISTE

```java
String comando = args[0];
File file = new File("/bin/"+comando);

if(!file.isFile()){
    // AQUI EL COMANDO NO EXISTE
    System.out.println("Error el comando introducido, no es un comando de usuario valido");
} else {
    // AQUI EL COMANDO EXISTE
}
```

COMPROBAR QUE UN USUARIO EXISTE

```java
public boolean ExUsuario(String usu){                   //Comprueba si ese usuario existe en el archivo de usuarios del sistema
    
    boolean encontrado = false;
    BufferedReader br= RetSalida("cat /etc/passwd");

    try{
        String linea= br.readLine();

        while ( (linea!=null) && !(encontrado   )  )
        {     
            String[] campos =linea.split(":");           //Nos quedamos con el primer campo
            encontrado=campos[0].equals(usu);   //Si el campo coincide con el usuario
            linea= br.readLine();
        }    

    } catch(IOException e) {
        System.err.println("Error durante ejecución del proceso");
        System.err.println("Información detallada");
        System.err.println("---------------------");
        e.printStackTrace();
        System.err.println("---------------------");
        System.exit(2);
    }

    return encontrado;

}
```

COMPROBAR SI UN CAMPO ES VALIDO, devuelve la posición

```java
public int ComprobarCampo(String camp,String linea){      //Comprueba si ese campo de ordenacion es válido
    
    boolean encontrado = false;
    String[] campos =linea.split(" +");           //Dividimos la linea en campos
    int pos=0;
    System.out.println(linea);

    while ((pos<campos.length) && !(campos[pos].equals(camp))) {           
        pos++;
    }    

    if (pos==campos.length){   // Si no lo ha encontrado devolvemos un valor de error, no devolvemos 0  poe ser un valor válido
        pos=-1;
    }   

    return pos;
        
}
```

ORDENAR LA SALIDA POR CAMPO

```java
//Rellenamos en un HashMap la salida del comando
               
Map<String,String> map = new HashMap<>();
               
while ((linea=Salida.readLine())!=null){ 
    campos =linea.split(" +");
    map.put(linea,campos[pos] );  //La clave es la linea y el valor el campo de ordenacion
}
              
List<Entry<String, String>> list = new ArrayList<>(map.entrySet());   //Convertimos el map a un arraylist
list.sort(Entry.comparingByValue());  //Ordenamos el List por valor
list.forEach(System.out::println);
```

HACER QUE UN PROCESO DURE EL TIEMPO QUE QUERAMOS

```java
if (!p.waitFor(5, TimeUnit.SECONDS)) //Si el proceso p no se detiene en 5 segundos
    p.destroy();  //Eliminamos el proceso
```


