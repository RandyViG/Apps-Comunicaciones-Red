import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CHME{

    public static void main( String[] args ){
        try{
            BufferedReader br = new BufferedReader ( new InputStreamReader( System.in ));
            int pto = 1234;
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
            System.out.println("Conexión con el servidor establecida, recibiendo mensaje");
            PrintWriter pw = new PrintWriter( new OutputStreamWriter( cl.getOutputStream() ) );
            BufferedReader br1 = new BufferedReader ( new InputStreamReader( cl.getInputStream() ) );
            String msj = br1.readLine();
            System.out.println("Mensaje recibido: "+ msj + "\nDevolviendo saludo");
            pw.println("Saludo devuelto");
            pw.flush();
            System.out.println("Terminando Aplicación");
            br.close();
            br1.close();
            pw.close();
            cl.close();
        } catch( Exception e ){
            e.printStackTrace();
        }
    }
}