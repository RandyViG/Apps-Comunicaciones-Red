

import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
/**
 *
 * @author randy
 */
public class Cliente {
    int puerto;
    String host;
    DatagramSocket cl;
    InetAddress dst = null;
    ByteArrayInputStream bais;
    
    public Cliente( int puerto , String host ){
        this.puerto = puerto;
        this.host = host;
    }
    
    public String start( String d ){
        String palabra = "";
        try {
            cl = new DatagramSocket();
            try{
                dst = InetAddress.getByName(host);
            }catch(UnknownHostException u){
                System.err.println("Dirección no valida");
                cl.close();
                System.exit(1);
            }
            byte[] b = d.getBytes();
            bais = new ByteArrayInputStream(b);
            byte[] b1 = new byte[30];
            int n = bais.read(b1);
            DatagramPacket p1 = new DatagramPacket(b1,n,dst,puerto);
            cl.send(p1);
            DatagramPacket p2 = new DatagramPacket(new byte[100], 100);
            cl.receive(p2);
            palabra = new String(p2.getData(),0,p2.getLength());
        }catch(Exception e){
            e.printStackTrace();
        }
        return palabra;
    }

    public void enviarTiempo( String tiempo ){
        try {
            cl = new DatagramSocket();
            try{
                dst = InetAddress.getByName(host);
            }catch(UnknownHostException u){
                System.err.println("Dirección no valida");
                cl.close();
                System.exit(1);
            }
            byte[] b = tiempo.getBytes();
            bais = new ByteArrayInputStream(b);
            byte[] b1 = new byte[30];
            int n = bais.read(b1);
            DatagramPacket p1 = new DatagramPacket(b1,n,dst,puerto);
            cl.send(p1);
            DatagramPacket p2 = new DatagramPacket(new byte[100], 100);
            cl.receive(p2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
