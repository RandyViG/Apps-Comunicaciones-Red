import java.net.*;
import java.io.*;

public class EcoCliente{
    public static void main(String[]args){
        int limite = 60, tp = 30; //65535 //3000
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
                br.close();
                cl.close();
                System.exit(1);
            }
            for(;;){
                System.out.print("\nEscribe los datos: ");
                String datos = br.readLine();
                if(datos.compareToIgnoreCase("Salir") == 0){
                    br.close();
                    cl.close();
                    System.exit(0);
                }
                byte[] b = datos.getBytes();
                if(b.length > limite){
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);
                    int np = (int)(b.length / tp);
                    if(b.length % tp > 0)
                        np = np + 1;
                    for(int i=0; i < np; i++){
                        byte[] b1 = new byte[tp];
                        int n = bais.read(b1);
                        DatagramPacket p1 = new DatagramPacket(b1,n,dst,pto);
                        cl.send(p1);
                        System.out.println("Enviando paquetes al servidor y preparando para recibir el eco");
                        DatagramPacket p2 = new DatagramPacket(new byte[30], 30);
                        cl.receive(p2);
                        String eco = new String(p2.getData(),0,p2.getLength());
                        System.out.println("\nEco recibido " + eco);
                    }
                }
                else{
                    System.out.println("Enviando paquete completo y preparando para recibir el eco");
                    DatagramPacket p1 = new DatagramPacket(b,b.length,dst,pto);
                    cl.send(p1);
                    DatagramPacket p2 = new DatagramPacket(new byte[65500], 65500);
                    cl.receive(p2);
                    String eco = new String(p2.getData(),0,p2.getLength());
                    System.out.println("\nEco recibido: " + eco);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
