import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;

public class Envia{

    public static void main( String[] args ){
        try{
            BufferedReader br = new BufferedReader ( new InputStreamReader( System.in ));
            int pto = 5678;
            InetAddress dist = null;
            while( true ){
                System.out.println("Escribe la dirección o nombre cifrado del servidor: ");
                String host = br.readLine();
                try{
                    dist = InetAddress.getByName(host);
                } catch( UnknownHostException u ){
                    System.err.println("Dirección no valida");
                    continue;
                }
                if ( dist != null )
                    break;
            }
            Socket cl = new Socket( dist,pto );
            System.out.println("Conexión con el servidor Estabelicda, mostando caja de selección");
            JFileChooser jf = new JFileChooser();
            int r = jf.showOpenDialog( null );
            //jf.requestFocus();
            if ( r == JFileChooser.APPROVE_OPTION ){
                File f = jf.getSelectedFile();
                String nombre = f.getName();
                long tam = f.length();
                String ruta = f.getAbsolutePath();
                DataOutputStream dos = new DataOutputStream( cl.getOutputStream());
                DataInputStream dis = new DataInputStream( new FileInputStream(ruta) );
                dos.writeUTF(nombre);
                dos.flush();
                dos.writeLong(tam);
                dos.flush();
                int n, porcentaje;
                long env = 0;
                while( env < tam){
                    byte[] b = new byte[3000];
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    env = env + n;
                    porcentaje = (int ) ( (env*100)/tam );
                    System.out.print("\r Se ha enviado el " + porcentaje + "% del archivo: " +ruta );
                }
                System.out.println("Archivo enviado");
                dis.close();
                dos.close();
                cl.close();
                br.close(); 
            }
        } catch( Exception e ){
            e.printStackTrace();
        }
    }
}