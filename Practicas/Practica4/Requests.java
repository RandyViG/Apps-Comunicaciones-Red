import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;

class Requests implements Runnable{

    protected Socket cl = null;
    protected PrintWriter pw;
	protected BufferedOutputStream bos;
    protected DataInputStream dis;
    protected DataOutputStream dos;
    protected Mime mime;
    protected String headers;

    public Requests(Socket cl) throws Exception {
        this.cl = cl;
        mime = new Mime();
        headers =   "HTTP/1.1 200 OK \r\n" +
                    "Server: webServer/1.0 \n" +
                    "Content-Type: text/html \n"+
                    "Date: " + new Date() + " \n\r\n";
                    //"Connection: Keep-Alive \n\r\n";
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream( cl.getInputStream() );
            dos = new DataOutputStream( cl.getOutputStream() );
            bos=new BufferedOutputStream( cl.getOutputStream() );
			pw=new PrintWriter( new OutputStreamWriter(bos) );
            
            @Deprecated
            String statusLine = dis.readLine();
            if ( statusLine == null ){
                String empty = "<html><head><title>Servidor WEB</title><body> <br>statusLinea Vacia</br></body></html>";
                dos.writeUTF( empty );
                dos.flush();
                dos.close();
                dis.close();
                cl.close();
                System.out.println("Cliente Atendido");
                return;
            }
            else{
                System.out.println("\n**************************************************");
                System.out.println("Cliente Conectado desde: " + cl.getInetAddress());
                System.out.println("Por el puerto: " + cl.getPort());
                System.out.println("Petición: \n" + statusLine + "\r\n\r\n");

                if( statusLine.startsWith("GET") ){
                    if( statusLine.indexOf("?") != -1 ){
	                    String response = getParameters( statusLine );
	                    //dos.writeUTF( response );
                        //dos.flush();
                        pw.write(response);
                        pw.flush();
	                    System.out.println("Respuesta GET: \n" + response);
                    }else{
                        String fileName = getFileName( 5 , statusLine );
                        fileName = getMetadata( fileName );
                        System.out.println("Archivo: " + fileName);
	                    sendSource( fileName );
                    }
                } 
                else if ( statusLine.startsWith("POST") ){
                    int lenLine = dis.available();
                    byte []lineS = new byte[ lenLine ];
                    dis.read(lineS, 0, lenLine);
                    String line = new String(lineS, 0, lenLine);
                    String response = getParameters( line );
                    dos.writeUTF(response);
                    dos.flush();
                    System.out.println("Respuesta POST: \n" + response);
                } 
                else if ( statusLine.startsWith("HEAD") ){
                    String fileName = getFileName( 6 , statusLine );
                    System.out.println("Respuesta HEAD:");
                    fileName = getMetadata( fileName );
                }
                else if( statusLine.startsWith("DELETE") ){
                    String fileName = getFileName( 8 , statusLine );
                    deleteSource(fileName);
                }
                else{
                    String error501 =   "HTTP/1.1 501 Not Implemented \r\n" +
		              				    "Server: webServer/1.0 \n" +
                                        "Content-Type: text/html \n" +
                                        "Date: " + new Date() + " \n\r\n";
                    dos.writeUTF( error501 );
                    dos.flush();
                    sendSource("error/501.html");
                    System.out.println("Respuesta ERROR 501: \n" + error501);
                }
                System.out.println("Cliente Atendido");
                pw.flush();
                pw.close();
                dos.flush();
                dos.close();
                dis.close();
                cl.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getParameters( String statusLine){
        String method = "",line;
                           
        if( statusLine.startsWith("GET") ){
            method = "GET";
        	// Line: GET /?Nombre=&Direccion=&Telefono=&Comentarios= HTTP/1.1
        	StringTokenizer tokens = new StringTokenizer( statusLine , "?" );
            line = tokens.nextToken();
            line = tokens.nextToken();
            tokens = new StringTokenizer(line, " ");
            line = tokens.nextToken();
        }else{
            method= "POST";
            String[] reqLineas = statusLine.split("\n");
            System.out.println("Tam: " + reqLineas.length);
            int ult = reqLineas.length - 1;
            line = reqLineas[ult];
            System.out.println("Obtener: " +  line );
        }

        StringTokenizer tokens = new StringTokenizer(line, "&");
        String html = headers +
					"<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Metodo " + method +
                    "</title></head><body><center><h2>Parametros obtenidos, Método " 
                    + method + "</h2><br>\n" + "<table border='0'><tr><th>Parametro</th><th>Valor</th></tr>";

        while(tokens.hasMoreTokens()) {
        	String parameters = tokens.nextToken();
        	StringTokenizer paramValue = new StringTokenizer( parameters , "=" );
        	String param = "";
            String value = "";
            if ( paramValue.hasMoreTokens() )
                param = paramValue.nextToken();
            if ( paramValue.hasMoreTokens() )
        	    value = paramValue.nextToken();
        	html = html + "<tr><td><b>" + param + "</b></td><td>" + value + "</td></tr>\n";
        }
        html = html + "</table></center></body></html>";
        return html;
    }

    private String getFileName( int start , String statusLine ){
        String file = statusLine.substring( start , statusLine.length() );
        String source = file.substring( 0 , file.indexOf(" ") );
        if( source.compareTo(" ") == 0 )
            source = "index.html";
        return source;
    }

    private String getMetadata( String fileName ){
        String responseHead = "";
        try{
            File file = new File( fileName );
        	String statusResponse = "HTTP/1.1 200 OK \r\n";
        	if( !file.exists() ){
        		fileName= "error/404.html"; // Recurso no encontrado
        		statusResponse = "HTTP/1.1 404 Not Found \r\n";
        	}
        	else if( file.isDirectory() ) {
        		fileName = "error/403.html"; // Recurso privado
        		statusResponse = "HTTP/1.1 403 Forbidden \r\n";
        	}
    		DataInputStream d = new DataInputStream( new FileInputStream(fileName) ); 
    		int len = d.available();
            int index = fileName.indexOf(".");
            String extension = fileName.substring( index + 1 , fileName.length() );

            responseHead = statusResponse + 
		                    "Server: webServer/1.0 \n" +
                            "Content-Type: " + mime.get(extension) + " \n" +
                            "Date: " + new Date() + " \n" + 
                            "Content-Length: " + len + " \n\r\n";
            d.close();
            dos.writeUTF( responseHead );
        }catch( Exception e ) {
            System.out.println(e.getMessage());
        }
        System.out.println( responseHead );
        return fileName;
    }
    
    private void sendSource( String fileName ){
        try{
            File file = new File( fileName );
            if ( file.exists() && !file.isDirectory() ){
                DataInputStream f = new DataInputStream( new FileInputStream(fileName) ); 
                int len = f.available();
                byte [] b = new byte[1024];
	            long send = 0;
	            int n = 0;
	            while( send < len ) {
	                n = f.read(b);
                    dos.write(b, 0, n);
                    dos.flush();
	                send += n;
                }
                System.out.println("Archivo enviado");
                f.close();
            }
        }catch( Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void deleteSource( String fileName ){
        File f = new File( fileName );
        try{
            if( f.exists() ){
                String[] name = fileName.split("/");
                if( f.delete() ){
                    System.out.println( "Archivo: " + fileName + " eliminado");
                    String delete = headers +
                                      "<html><head><meta charset='UTF-8'><title>202 Succesuful</title></head>" +
                                      "<body><h1>202 Recurso eliminado exitosamente.</h1>" +
                                      "<p>El recurso " + name[1] + " ha sido eliminado permanentemente del servidor.</p>" + 
                                      "</body></html>";
                    dos.writeUTF(delete);
                    dos.flush();
                    System.out.println("Respuesta DELETE: \n" + delete);
                }
                else{
                    System.out.println( "El archivo: " + fileName + " no puede ser eliminado");
                    String error =  "HTTP/1.1  500 Internal server error \r\n" +
                                    "Server: webServer/1.0 \n" +
                                    "Content-Type: text/html \n" +
                                    "Date: " + new Date() + " \n\r\n" +
                                    "<html><head><meta charset='UTF-8'><title>Server Error</title></head>" +
                                    "<body><h1>500 Internal server error</h1>" +
                                    "<p>El recurso: " + name[1] + " no se puede borrar.</p>" +
                                    "</body></html>";
                    dos.writeUTF(error);
                    dos.flush();
                    System.out.println("Respuesta DELETE: \n" + error);
                }
            }
            else{
                String error = "HTTP/1.1 404 Not Found \r\n" +
                                "Server: webServer/1.0 \n" +
                                "Content-Type: text/html \n" +
                                "Date: " + new Date() + " \n\r\n" +
                                "<html><head><meta charset='UTF-8'><title>404 Not found</title></head>" +
                                "<body><h1>404 Not found</h1>" +
                                "<p>El recurso: " + fileName + " no se encontro.</p>" +
                                "</body></html>";
                dos.writeUTF(error);
                dos.flush();
                System.out.println("Respuesta DELETE: \n" + error);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        System.out.println( "El archivo: " + fileName + " no existe");
    }
}