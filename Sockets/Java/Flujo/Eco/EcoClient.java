import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EcoClient{

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
            PrintWriter pw = new PrintWriter ( new OutputStreamWriter( cl.getOutputStream() ) );
            BufferedReader br1 = new BufferedReader ( new InputStreamReader ( cl.getInputStream() ) );
            while( true ){
                System.out.println(" Escribe una cadena, <Enter> para enviar o '\'cerrar para terminar .. ");
                String msj = br.readLine();
                pw.println(msj);
                pw.flush();
                if( msj.compareToIgnoreCase("cerrar") == 0 ){
                    System.out.println("Termina programa");
                    br.close();
                    br1.close();
                    pw.close();
                    cl.close();
                    System.exit(0);
                } else{
                    String eco = br1.readLine();
                    System.out.println("Echo recibido: "+ eco);
                }
            }
        } catch( Exception e ){
            e.printStackTrace();
        }
    }
}