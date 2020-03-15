import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class Comprimir {
 
	public static void main(String[] args) {
		String directorioZip = "Servidor/Drive";
		try{ 
    		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("./curDir.zip")); 
    		zipDir(directorioZip, zos); 
    		zos.close(); 
		} 
		catch(Exception e) { 
		} 
	}

	public static void zipDir(String dir2zip, ZipOutputStream zos) { 
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
}