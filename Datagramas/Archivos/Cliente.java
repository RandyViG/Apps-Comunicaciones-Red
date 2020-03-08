import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Cliente{

    public static void main(String[] args) {
        int limite = 3000,t;
        try{
            DatagramSocket cl = new DatagramSocket();
            String host = "localhost";
            int pto = 1235;
            InetAddress dst = null;

            try{
                dst = InetAddress.getByName(host);   
            }
            catch(UnknownHostException e){
                System.err.println("Direccion no es valida");
                cl.close();
                System.exit(1);
            }
    
            JFileChooser jf = new JFileChooser();
            int r = jf.showOpenDialog(null);

            if( r == JFileChooser.APPROVE_OPTION){
                File f = jf.getSelectedFile();
                String name = f.getName();
                long len = f.length();
                String path = f.getAbsolutePath();

                byte[] b = name.getBytes();
                DatagramPacket p = new DatagramPacket(b,b.length,dst,pto);
                cl.send(p);

                DatagramPacket p2 = new DatagramPacket(new byte[100], 100);
                cl.receive(p2);
                String response = new String(p2.getData(),0,p2.getLength());
                System.out.println(response);
                
                t = (int) len / limite ;
                if(t % limite != 0)
                    t=(int)(len/limite)+1;
      
                int n, porcentaje;
                long totalSend = 0;
                DataInputStream dis= new DataInputStream(new FileInputStream(path));
                
                for(int i=1; i<=t; i++){
                    ByteArrayOutputStream baos= new ByteArrayOutputStream();
                    ObjectOutputStream oos= new ObjectOutputStream(baos);
                    
                    byte[] aux = new byte[limite];
                    n = dis.read(aux); 
                    Dato d = new Dato(i,aux,t);
                    
                    oos.writeObject(d);
                    oos.flush();
                    byte[] tmp = baos.toByteArray();
                    DatagramPacket dp = new DatagramPacket(tmp,tmp.length,dst, pto);
                    cl.send(dp);

                    totalSend += n;
                    porcentaje = (int ) ( (totalSend * 100) / len );
                    System.out.print("\rSe ha enviado el " + porcentaje + "% del archivo" ); 

                    //Recibiendo confirmación
                    p2 = new DatagramPacket(new byte[100], 100);
                    cl.receive(p2);
                    response = new String(p2.getData(),0,p2.getLength());
                    //System.out.print(response);

                    oos.close();
                    baos.close();
                }
            System.out.println("Cliente finalizado");
            cl.close();    
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
