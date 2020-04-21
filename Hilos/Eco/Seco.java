import java.io.*;
import java.net.*;


public class Seco {
    public static void main(String[] args){
        try{
            ServerSocket s = new ServerSocket(9000);
            System.out.println("Servidor listo en el puero "+s.getLocalPort());
            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado..\n");
                Manejador m = new Manejador(cl);
                m.start();
            }//for
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}

class Manejador extends Thread{
    Socket cl;
    public Manejador(Socket cl){
        this.cl = cl;
    }
    public void run(){
        try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String linea="";
            for(;;){
                linea= br.readLine();
                System.out.println("Recibiendo mensaje: "+linea);
                if(linea.indexOf("SALIR")>=0){
                    System.out.println("Cliente se va...");
                    cl.close();
                    break;
                }
                pw.println(linea);
                pw.flush();
                   }//for
        }catch(IOException io){
            io.printStackTrace();
        }//catch
    }//run
}