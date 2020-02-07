import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SHMF{

    public static void main( String[] args){
        try{
            ServerSocket s = new ServerSocket(1234);
            //s.setReuseAddreess(true);
            System.out.println("Servidor inicializado en el puerto " + s.getLocalPort() + " \nEsperando Cliente...");

            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde " + cl.getInetAddress() + ": " + cl.getPort() + " enviando mensaje");
                PrintWriter pw = new PrintWriter( new OutputStreamWriter( cl.getOutputStream() ) );
                pw.println ("Hola mundo con un socket de flujo bloqueante");
                pw.flush();
                System.out.println("Mensaje enviado... preparado para recibir un mensaje");
                BufferedReader br = new BufferedReader( new InputStreamReader( cl.getInputStream() ) );
                String msj = br.readLine();
                System.out.println("Mensaje recibido " + msj + "\nCerrando conexión");
                br.close();
                pw.close();
                cl.close();
            }
        } catch( Exception e){
            e.printStackTrace();
        }
    }

}