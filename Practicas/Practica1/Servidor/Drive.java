
import java.io.File;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class Drive{ 
    
    static File file;
    static JTree tree;
    static int option;
    static long lenFile;
    static Socket client; 
    static String path, fileName;
    static Directory directory;
    static Server driveServer;
    static DefaultTreeModel model;
    static DefaultMutableTreeNode root;
    static DataInputStream dis;
    static DataOutputStream dos;

    public static void main( String[] args ){
        
        path = "DriveFiles";
        root = new DefaultMutableTreeNode("Drive");
        model = new DefaultTreeModel( root );
        tree = new JTree( root );
        int closeClient;
        driveServer = new Server( 1239 );
        try{
            driveServer.startConnection();
            while(true){
                closeClient = 0;
                file = new File( path );
                getPath( file , model , root );
                directory = new Directory( tree );
                client = driveServer.getSocket().accept();
                driveServer.sendDirectory( client , directory );
                System.out.println("Directorio Enviado");
                client.close();
                for( ; ; ) {
                    root =  null;
                    model = null;
                    tree = null;
                    client = driveServer.getSocket().accept();
                    option = driveServer.receiveAction( client );

                    switch( option ){
                        case 1:
                                String dir = driveServer.receiveFileName();
                                driveServer.uploadFile( path + dir );
                                root = new DefaultMutableTreeNode("Drive");
                                model = new DefaultTreeModel( root );
                                tree = new JTree( root );
                            
                                getPath( file , model , root );
                                directory = new Directory( tree );
                                client = driveServer.getSocket().accept();
                                driveServer.sendDirectory( client , directory );
                            break;
                        case 2: 
                                String newDirectory = driveServer.receiveFileName(  );
                                makeDirectory(newDirectory);    
                                
                                root = new DefaultMutableTreeNode("Drive");
                                model = new DefaultTreeModel( root );
                                tree = new JTree( root );    
                                getPath( file , model , root );
                                directory = new Directory( tree );
                                client = driveServer.getSocket().accept();
                                driveServer.sendDirectory( client , directory );
                            break;
                        case 3:
                                fileName = driveServer.receiveFileName( );
                                String pathFile = path + '/' + fileName;
                                File fileDelete = new File ( pathFile );
                                removeFile( fileDelete );
                                
                                root = new DefaultMutableTreeNode("Drive");
                                model = new DefaultTreeModel( root );
                                tree = new JTree( root );    
                                getPath( file , model , root );
                                directory = new Directory( tree );
                                client = driveServer.getSocket().accept();
                                driveServer.sendDirectory( client , directory );
                            break;
                        case 4:
                                System.out.println("Descargar");
                                String fileDownload = driveServer.receiveFileName( );
                                String foundFile = path + "/" + fileDownload;
                                driveServer.getFile( foundFile );
                              
                                root = new DefaultMutableTreeNode("Drive");
                                model = new DefaultTreeModel( root );
                                tree = new JTree( root );    
                                getPath( file , model , root );
                                directory = new Directory( tree );
                                client = driveServer.getSocket().accept();
                                driveServer.sendDirectory( client , directory );
                            break;
                        case 5:
                                client.close();
                                closeClient=1;
                            break;
                    }
                    
                    if( closeClient == 1)
                    break;
                } 
            }  
        }catch(Exception e ){
            e.printStackTrace();
        }
        
    }

    public static void getPath( File f , DefaultTreeModel model , DefaultMutableTreeNode root ){
        File directorys[] = f.listFiles();
        for( int i=0; i < directorys.length ; i++ ){
            if ( directorys[i].isDirectory() ){
                DefaultMutableTreeNode folder = new DefaultMutableTreeNode(directorys[i].getName());
                model.insertNodeInto(folder , root , i);
                System.out.println( "Dir --> " + directorys[i].getName());
                getPath( directorys[i] , model , folder );
            }
            else{
                DefaultMutableTreeNode folder = new DefaultMutableTreeNode(directorys[i].getName());
                model.insertNodeInto(folder , root, i);
                System.out.println( "Archivo --> " + directorys[i].getName());
            }
        }
    }

    public static void makeDirectory( String newDirectory ){
        File pos = new File ( path + "/" + newDirectory );
        System.out.println("Pos: " + path);
        System.out.println("Carpeta: " + newDirectory);
        System.out.println("Path: " + pos.getAbsolutePath());
        
        if(!pos.exists()) {
            try {
                if (pos.mkdirs())
                    System.out.println("Carpeta creada");
                else 
                    System.out.println("No se creo la carpeta");
            }catch(SecurityException se) { 
                se.printStackTrace(); 
            }
        }
        else
            System.out.println("El directorio ya existe");
    }

    public static void removeFile( File file  ){
        System.out.println(file.getAbsolutePath());
        if ( file.exists() ){
            System.out.println("Archivo Encontrado");
            if ( file.isDirectory() ){
                System.out.println("Es carpeta");
                File items[] = file.listFiles();
                if( items.length < 0 ){
                    for(int i=0 ; i<items.length ; i++ ){
                        if( items[i].isDirectory() )
                            removeFile( items[i] );
                        else{
                             items[i].delete();
                            System.out.println("Archivo: " + file.getPath() + " borrado");
                        }
                           
                    }
                } 
                else{
                    file.delete();
                    System.out.println("Carpeta: " + file.getPath() + " borrado");
                }
            }
            else{
                file.delete();
                System.out.println("Archivo: " + file.getPath() + " borrado");
            }         
        }
        System.out.println("Archivo No existe");
    }
}