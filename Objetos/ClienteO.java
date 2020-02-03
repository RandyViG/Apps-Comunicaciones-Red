import java.net.*;
import java.io.*;

public class ClienteO{

    public static void main( String[] args ){
        try{
            int pto = 8000;
            String host = "localhost";
            Socket cl = new Socket(host,pto);
            System.out.println("Conexión al servidor establecida, comienza intercambio de objetos... ");
            ObjectOutputStream oos = new ObjectOutputStream( cl.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( cl.getInputStream() );
            Dato o1 = new Dato(1,2.0f,"Fernando");
            System.out.println("Enviando objeto con la sig información -> v1: " + o1.getV1() + " v2: " + o1.getV2() + " v3: " + o1.getV3() );
            oos.writeObject(o1);
            oos.flush();
            Dato o2 = (Dato) ois.readObject();
            System.out.println("Objeto recibivo con la sig información -> v1: " + o2.getV1() + " v2: " + o2.getV2() + " v3: " + o2.getV3() );
            System.out.println("Termina programa");
            ois.close();
            oos.close();
            cl.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}