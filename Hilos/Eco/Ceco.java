import java.net.*;
import java.io.*;

public class Ceco {
    public static void main(String[] args){
        try{
            InetAddress srv = InetAddress.getByName("127.0.0.1");
            Socket cl = new Socket(srv,9000);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            String linea="";
            System.out.println("Escribe mensajes <ENTER> para enviar, <SALIR> para terminar\n");
            for(;;){
                linea= br2.readLine();
                pw.println(linea);
                pw.flush();
                if(linea.indexOf("SALIR")>=0){
                    System.out.println("Adios...");
                    cl.close();
                    System.exit(0);
                }//if
                String eco=br.readLine();
                System.out.println("ECO: "+eco);
                
            }//for
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}
