import java.net.*;
import java.io.*;

/**
 *
 * @author FERNANDO
 */
public class Cliente {
    public void cliente(){
        try{
            int pto = 5678;
            String host = "localhost";
            Socket cl = new Socket(host,pto);
            System.out.println("Conexión al servidor establecida, comienza intercambio de objetos... ");
            ObjectInputStream ois = new ObjectInputStream( cl.getInputStream() );
            
            String palabras[][] = (String[][]) ois.readObject();
            for(int i = 0; i < palabras.length; i++){
                System.out.println(palabras[i] + "\n");
            }
            
            System.out.println("Termina programa");
            ois.close();
            cl.close();
            Sopa sopita = new Sopa(palabras);
            sopita.setVisible(true);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
