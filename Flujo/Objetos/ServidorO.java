import java.net.*;
import java.io.*;

public class ServidorO{

    public static  void main( String[] args ){
        try{
            int pto = 8000;
            ServerSocket s= new ServerSocket(pto);
            s.setReuseAddress( true );
            System.out.println("Servidor iniciado en el puerto " + pto + " Esperando archivo");
            for(;;){
                Socket cl = s.accept();
                ObjectOutputStream oos = new ObjectOutputStream( cl.getOutputStream() );
                ObjectInputStream ois = new ObjectInputStream( cl.getInputStream() );
                Dato o1 = (Dato) ois.readObject(); 
                System.out.println("Recibiendo objeto con la sig información -> v1: " + o1.getV1() + " v2: " + o1.getV2() + " v3: " + o1.getV3() );
                Dato o2 = new Dato(5,5.0f,"Randy");
                System.out.println("Enviando objeto con la sig información -> v1: " + o2.getV1() + " v2: " + o2.getV2() + " v3: " + o2.getV3() );
                oos.writeObject(o2);
                oos.flush();
                ois.close();
                oos.close();
                cl.close();
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    } 
}