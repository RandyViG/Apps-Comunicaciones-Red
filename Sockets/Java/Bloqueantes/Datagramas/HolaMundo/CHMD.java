import java.net.*;
import java.io.*;

public class CHMD{
    public static void main(String[]args){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int pto = 5000;
            String host = "localhost";
            DatagramSocket cl = new DatagramSocket();
            InetAddress dst = null;
            try{
                dst = InetAddress.getByName(host);
            }catch(UnknownHostException u){
                System.err.println("Dirección no valida");
                cl.close();
                System.exit(1);
            }
            System.out.print("\nEscribe tu nombre: ");
            String nombre = br.readLine();
            byte[] b = nombre.getBytes();
            DatagramPacket p = new DatagramPacket(b,b.length,dst,pto);
            cl.send(p);
            System.out.println("Enviando nombre al servidor y preparando para recibir el saludo");
            DatagramPacket p1 = new DatagramPacket(new byte[65500], 65500);
            cl.receive(p1);
            String saludo = new String(p1.getData(),0,p1.getLength());
            System.out.println("\nMensaje recibido " + saludo);
            br.close();
            cl.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
