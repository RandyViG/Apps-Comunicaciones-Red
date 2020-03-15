import java.net.*;
import java.io.*;
/**
 *
 * @author FERNANDO
 */
public class Servidor {
    String palabrasAna[];
    public void anagrama(String[] pal){
    
    }
    public void servidor(){
        try{
            int pto = 5678;
            ServerSocket s = new ServerSocket(pto);
            s.setReuseAddress( true );
            System.out.println("Servidor iniciado en el puerto " + pto + " Esperando archivo");
            for(;;){
                Socket cl = s.accept();
                ObjectOutputStream oos = new ObjectOutputStream( cl.getOutputStream() );
                String [][] palabras = {
                    {"maestro", "alumno", "plumon", "lapicero", "cuaderno", "prefecto", "salon", "goma","escuadra", "regla", "compas", "libro", "colores", "tijeras", "lapicera"},
                    {"traemos", "maulon", "pulmon","copiarle", "educaron", "perfecto", "lonas", "mago", "acuerdas", "glera", "campos", "birlo", "coserlo", "tirajes", "aplicare"}
                };
                oos.writeObject(palabras);
                oos.flush();
                oos.close();
                cl.close();
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }    
}
