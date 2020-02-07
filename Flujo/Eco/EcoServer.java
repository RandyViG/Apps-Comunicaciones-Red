import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EcoServer{

    public static void main( String[] args){
        try{
            ServerSocket s = new ServerSocket(1234);
            //s.setReuseAddreess(true);
            System.out.println("Servidor inicializado en el puerto " + s.getLocalPort() + " \nEsperando Cliente...");

            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde " + cl.getInetAddress() + ": " + cl.getPort() );
                PrintWriter pw = new PrintWriter( new OutputStreamWriter( cl.getOutputStream() ) );
                BufferedReader br = new BufferedReader( new InputStreamReader( cl.getInputStream() ) );
                while( true ){
                    String msj = br.readLine();
                    if( msj.compareToIgnoreCase("cerrar") == 0 )
                        break;
                    System.out.println("Mensaje recibido: " + msj );
                    pw.println ("El mensaje se recibio exitosamente");
                    pw.flush();
                }
                System.out.println("Cerrando conexión");
                br.close();
                pw.close();
                cl.close();
            }
        } catch( Exception e){
            e.printStackTrace();
        }
    }

}