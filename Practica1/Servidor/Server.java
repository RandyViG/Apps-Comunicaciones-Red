
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Server {
    private int pto;
    private ServerSocket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private ZipOutputStream zos;

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
            String save = path + "/" + name;
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

    public void getFile( String file ){
        try{
            Socket cl = s.accept();
            String nombre,path;
            long len;
            File f = new File( file );
            
            if( f.isDirectory() ){
                zos = new ZipOutputStream(new FileOutputStream( f.getName() + ".zip") ); 
                zipDir(f.getAbsolutePath(), zos); 
                File zipFile = new File( f.getName() + ".zip" );
                nombre = zipFile.getName();
                len = zipFile.length();
                path = zipFile.getAbsolutePath();
            }else{
                nombre = f.getName();
                len = f.length();
                path = f.getAbsolutePath();
            }
            System.out.println("Tamaño " + len );
            System.out.println("Ruta " + path );
            this.dos = new DataOutputStream ( cl.getOutputStream() );
            this.dis = new DataInputStream( new FileInputStream(path) );
            dos.writeUTF(nombre);
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
                rec = rec + n;
                porcentage = (int ) ( (rec*100)/len );
                System.out.print("\renviando el "+porcentage+" % del archivo");
            }
            System.out.println(""); 
            dis.close();
            dos.close();
            zos.close();
        }catch( Exception e){
            e.printStackTrace();
        }
    }
    
    public void zipDir(String dir2zip, ZipOutputStream zos) { 
	try {   
            File zipDire = new File(dir2zip); 
	    String[] dirList = zipDire.list(); 
	    byte[] readBuffer = new byte[3000]; 
	    int bytesIn = 0;  
	    for(int i=0; i<dirList.length; i++) { 
	        File f = new File(zipDire, dirList[i]); 
	        if(f.isDirectory()){ 
	            String filePath = f.getPath(); 
	            zipDir(filePath, zos); 
	            continue; 
	        } 
	        FileInputStream fis = new FileInputStream(f); 
	        ZipEntry anEntry = new ZipEntry(f.getPath()); 
	        zos.putNextEntry(anEntry); 
	        while((bytesIn = fis.read(readBuffer)) != -1) { 
	            zos.write(readBuffer, 0, bytesIn); 
	        } 
	        fis.close(); 
	    } 
	}catch(Exception e){ 
            e.printStackTrace();
	} 
    }
}