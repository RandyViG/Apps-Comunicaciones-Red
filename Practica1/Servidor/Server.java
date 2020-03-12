/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    private int pto;
    private ServerSocket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public Server( int pto ){
        this.pto = pto;
    }

    public ServerSocket getSocket(){
        return this.s;
    }

    public void startConnection( ){
        try{
            this.s = new ServerSocket( this.pto );
            System.out.println("Servidor inicializado en el puerto " + s.getLocalPort() + " \nEsperando Cliente...");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void endConnection( ){
        try{
            System.out.println("Cerrando servidor en el puerto " + s.getLocalPort() + " \nEsperando Cliente...");
            this.s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendDirectory( Socket cl , Directory archivo ){
        try{
            this.oos = new ObjectOutputStream( cl.getOutputStream() );
            this.oos.writeObject( archivo );
            this.oos.flush();
            this.oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int receiveAction( Socket cl ){
        try{
            this.dis = new DataInputStream( cl.getInputStream() );
            int opt = dis.readInt();
            dis.close();
            return opt;  
        }catch(Exception e){
            e.printStackTrace();
        } 
        return -1;
    }  

    public String uploadFile( String path ){
        try{
            Socket cl = s.accept();
            this.dis = new DataInputStream( cl.getInputStream() );
            String name = dis.readUTF();
            long len = dis.readLong();
            System.out.println("Preparado para recibir el archivo: " + name + "de " + len  + " bytes desde " + cl.getInetAddress() + ":" + cl.getPort() );
            String save = path + name;
            this.dos = new DataOutputStream ( new FileOutputStream(save) );
            long rec = 0;
            int n = 0, porcentage = 0;
            while( rec < len){
                byte[] b = new byte[3000];
                n = dis.read(b);
                dos.write(b,0,n);
                dos.flush();
                rec = rec +n;
                porcentage = (int ) ( (rec*100)/len );
                System.out.print("\r Recibe el "+porcentage+" % del archivo");
            }
            dos.close();
            dis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public String receiveFileName( ){
        try{
            Socket cl = s.accept();
            this.dis = new DataInputStream( cl.getInputStream() );
            String name = dis.readUTF();
            dis.close();
            cl.close();
            return name;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public void getFile( Socket cl , String file ){
        try{
            File f = new File( file );
            String fileDownload = f.getName();
            long len = f.length();
            String path = f.getAbsolutePath();
            this.dos = new DataOutputStream ( cl.getOutputStream() );
            this.dis = new DataInputStream( new FileInputStream(path) );
            dos.writeUTF(fileDownload);
            dos.flush();
            dos.writeLong(len);
            dos.flush();
            long rec = 0;
            int n = 0, porcentage = 0;
            while( rec < len ){
                byte[] b = new byte[3000];
                n = dis.read(b);
                dos.write(b,0,n);
                dos.flush();
                rec = rec +n;
                porcentage = (int ) ( (rec*100)/len );
                System.out.print("\r enviando el "+porcentage+" % del archivo");
            }
            dis.close();
            dos.close();
        }catch( Exception e){
            e.printStackTrace();
        }
    }   
}