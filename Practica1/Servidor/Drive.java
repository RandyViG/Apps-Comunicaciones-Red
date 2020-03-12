/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;
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
        
        path = "/home/randy/Documentos/AppsComunicacionRedes/P1/Servidor/Drive";
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
                                driveServer.uploadFile( path + "/" );
                                root = new DefaultMutableTreeNode("Drive");
                                model = new DefaultTreeModel( root );
                                tree = new JTree( root );
                            
                                getPath( file , model , root );
                                directory = new Directory( tree );
                                client = driveServer.getSocket().accept();
                                driveServer.sendDirectory( client , directory );
                            break;
                        case 2: 
                                System.out.println("reciviendo pos");
                                String position = driveServer.receiveFileName(  );
                                String newDirectory = driveServer.receiveFileName(  );
                                String route = path + position;
                                makeDirectory(route , newDirectory);
                                
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
                                file = new File ( fileName );
                                removeFile( file );
                            break;
                        case 4:
                                String fileDownload = driveServer.receiveFileName( );
                                driveServer.getFile( client , fileDownload );
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
        if ( directorys != null){
            for(int i=0; i < directorys.length ; i++ ){
                if ( directorys[i].isDirectory() ){
                    DefaultMutableTreeNode folder = new DefaultMutableTreeNode(directorys[i].getName());
                    model.insertNodeInto(folder , root, i);
                    getPath( directorys[i] , model , folder );
                }
                DefaultMutableTreeNode folder = new DefaultMutableTreeNode(directorys[i].getName());
                model.insertNodeInto(folder , root, i);
                System.out.println( "--> " + directorys[i].getName());
            }
        }else{
            DefaultMutableTreeNode folder = new DefaultMutableTreeNode(f.getName());
            model.insertNodeInto(folder , root, 0);
            return;
        }
            

    }

    public static void makeDirectory( String position , String newDirectory ){
        File pos = new File ( position + "/" + newDirectory );
        System.out.println("Pos: " + position);
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
        File items[] = file.listFiles();
        if( items != null ){
            for(int i=0 ; i<items.length ; i++ ){
                if( items[i].isDirectory() )
                    removeFile( items[i] );
                else
                    items[i].delete();
            }
        } 
        else{
            file.delete();
            return;
        }
    }
}