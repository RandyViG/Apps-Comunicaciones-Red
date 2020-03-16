import java.net.*;
import java.io.*;
/**
 *
 * @author FERNANDO
 */
public class Cliente1 {
    public void cliente(){
        try{
            int pto = 5678;
            String host = "localhost";
            Socket cl = new Socket(host,pto);
            System.out.println("Conexión al servidor establecida, comienza intercambio de objetos... ");
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream( cl.getInputStream() );
            dos.writeInt(1);
            String palabras[][] = (String[][]) ois.readObject();
            for(int i = 0; i < 15; i++){
                System.out.println(palabras[0][i] + "\n");//Mostrando palabras recibidas
                System.out.println(palabras[1][i] + "\n");//Mostrando definiciones recibidas
            }
            
            System.out.println("Termina programa");
            ois.close();
            dos.close();
            cl.close();
            Sopa1 sopita = new Sopa1(palabras);
            sopita.setVisible(true);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
