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
                /*String[][] palabras = { {"maestro", "alumno", "lapiz", "lapicero", "cuaderno", "crayon", "computadora", "goma",
				"gis", "regla", "compas", "libro", "colores", "tijeras", "pizarron"},{},{}};*/
                String[][] palabras = { {"maestro", "alumno", "lapiz", "lapicero", "cuaderno", "crayon", "computadora", "goma",
				"gis", "regla", "compas", "libro", "colores", "tijeras", "pizarron"}, {}};
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
