import java.net.*;
import java.io.*;

public class SHMD{
    public static void main(String[]args){
        try{
            int pto = 5000;
            DatagramSocket s = new DatagramSocket(pto);
            s.setReuseAddress(true);
            System.out.println("Servidor iniciado en el pto: " + pto + " escuchando paquetes...");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[65500], 65500);
                s.receive(p);
                String nombre = new String(p.getData(),0,p.getLength());
                String saludo = "Hola " + nombre + " saludo desde el servidor";
                System.out.println("\nEnviando saludo a " + nombre);
                byte[] b = saludo.getBytes();
                DatagramPacket p1 = new DatagramPacket(b,b.length,p.getAddress(),p.getPort());
                s.send(p1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
