
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
/**
 *
 * @author randy
 */
public class Client {
    private int pto;
    private Socket cl;
    private JFileChooser jf;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectInputStream ois;
    private String host;
    
    public Client( String host , int pto){
        this.host = host;
        this.pto = pto;
    }
    
    public Directory receiveDirectory(){
        Directory d = new Directory(null);
        try{          
            this.cl = new Socket(this.host,this.pto);
            this.ois = new ObjectInputStream( cl.getInputStream() );
            Directory dir = (Directory)ois.readObject();
            this.ois.close();
            this.cl.close();
            return dir;
            
        }catch( Exception e){
            e.printStackTrace();
        }
        return d;
    }
    
    public void sendAction( int opt ){
        try{
            this.cl = new Socket( this.host , this.pto  );
            this.dos = new DataOutputStream( this.cl.getOutputStream() );
            this.dos.writeInt(opt);       
            this.dos.close();
            cl.close();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
    
    public void uploadFile( ){
        try{
            this.cl = new Socket( this.host , this.pto  );
            this.jf = new JFileChooser();
            int r = jf.showOpenDialog( null );
            if ( r == JFileChooser.APPROVE_OPTION ){
                String nombre,ruta;
                long tam;
                File f = jf.getSelectedFile();
                if( f.isDirectory() ){
                    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream( "./" + f.getName() + ".zip ") ); 
                    zipDir(f.getAbsolutePath(), zos); 
                    File zipFile = new File( "./" + f.getName() + ".zip " );
                    nombre = zipFile.getName();
                    tam = zipFile.length();
                    ruta = zipFile.getAbsolutePath();
                    zos.close(); 
                }else{
                    nombre = f.getName();
                    tam = f.length();
                    ruta = f.getAbsolutePath();
                }
                    
                this.dos = new DataOutputStream( cl.getOutputStream() );
                this.dis = new DataInputStream( new FileInputStream(ruta) );
                this.dos.writeUTF(nombre);
                this.dos.flush();
                this.dos.writeLong(tam);
                this.dos.flush();
                int n, porcentaje;
                long env = 0;
                while( env < tam){
                    byte[] b = new byte[3000];
                    n = this.dis.read(b);
                    this.dos.write(b,0,n);
                    this.dos.flush();
                    env = env + n;
                    porcentaje = (int ) ( (env*100)/tam );
                    System.out.print("\rSe ha enviado el " + porcentaje + "% del archivo: " +ruta );
                }
                this.dis.close();
                this.dos.close();
                this.cl.close();
            }
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
    
    public void zipDir(String dir2zip, ZipOutputStream zos) { 
	try {   
            File zipDire = new File(dir2zip); 
	    String[] dirList = zipDire.list(); 
	    byte[] readBuffer = new byte[2156]; 
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

	} 
    }
    
    public void sendDir( String dir ){
        try{
            this.cl = new Socket( this.host , this.pto  );
            this.dos = new DataOutputStream( cl.getOutputStream() );
            this.dos.writeUTF(dir);
            this.dos.flush();
            this.dos.close();
            this.cl.close();
        }catch( Exception e ){
            e.printStackTrace();
        }
    }
    
    public void reciveFile(){
        try{
            this.cl = new Socket( this.host , this.pto  );
            DataInputStream dis = new DataInputStream( cl.getInputStream() );
            String nombre = dis.readUTF();
            long tam = dis.readLong();
            System.out.println("Preparado para recibir el archivo: " + nombre + "de " + tam  + " bytes desde " + cl.getInetAddress() + ":" + cl.getPort() );
            DataOutputStream dos = new DataOutputStream ( new FileOutputStream(nombre) );
            long rec = 0;
            int n = 0, porcentaje = 0;
            while( rec < tam){
                byte[] b = new byte[3000]; 
                n = dis.read(b);
                dos.write(b,0,n);
                dos.flush();
                rec = rec +n;
                porcentaje = (int ) ( (rec*100)/tam );
                System.out.print("\rRecibe el "+porcentaje+" % del archivo");
            }
            File f = new File(""); 
            String dst = f.getAbsolutePath();
            System.out.println("Archivo recibido y descargado en la carpeta" + dst);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}