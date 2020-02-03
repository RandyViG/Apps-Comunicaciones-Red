import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Recibe{

    public static void main(String [] args){
        try{
            int pto = 5678;
            ServerSocket s= new ServerSocket(pto);
            s.setReuseAddress( true );
            System.out.println("Servidor iniciado en el puerto " + pto + " Esperando archivo");
            for(;;){
                Socket cl = s.accept();
                DataInputStream dis = new DataInputStream( cl.getInputStream() );
                String nombre = dis.readUTF();
                long tam = dis.readLong();
                System.out.println("Preparado para recibir el archivo: " + nombre + "de " + tam  + " bytes desde " + cl.getInetAddress() + ":" + cl.getPort() );
                DataOutputStream dos = new DataOutputStream ( new FileOutputStream(nombre) );
                long rec = 0;
                int n = 0, porcentaje = 0;
                while( rec < tam){
                    byte[] b = new byte[3000]; // En producción el tamaño real de envio solo es de 1500
                    n = dis.read(b);
                    dos.write(b,0,n);
                    dos.flush();
                    rec = rec +n;
                    porcentaje = (int ) ( (rec*100)/tam );
                    System.out.print("\r Recibe el "+porcentaje+" % del archivo");
                }

                File f = new File(""); // Apunta a la dirección donde esta el proyecto 
                String dst = f.getAbsolutePath();
                /*
                File f1 = new File(dst + "\\archivos");
                f1.mkDirs();
                String ruta = f1.getAbsolutePath(); 
                */ 
                System.out.println("Archivo recibido y descargado en la carpeta" + dst);
                dos.close();
                dis.close();
                cl.close();
            }
        } catch( Exception e){
            e.printStackTrace();
        }
    }
}