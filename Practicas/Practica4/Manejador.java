import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;

class Manejador implements Runnable{

    protected Socket cl = null;
    protected PrintWriter pw;
    protected BufferedReader br;
    protected BufferedOutputStream bos;
    protected Mime mime;

    public Manejador(Socket cl) throws Exception {
        this.cl = cl;
        this.mime = new Mime();
    }

    public void run() {
        String headers = "HTTP/1.1 200 OK\n" +
            			 "Date: " + new Date() + " \n" +
          				 "Server: webServer/1.0 \n" +
          				 "Content-Type: text/html \n\n";
        try {
            br = new BufferedReader( new InputStreamReader( cl.getInputStream() ) );
		    bos = new BufferedOutputStream( cl.getOutputStream() );
		    pw = new PrintWriter( new OutputStreamWriter( bos ) );
		    String statusLine = br.readLine();
            if ( statusLine == null ){
                pw.print("<html><head><title>Servidor WEB");
                pw.print("</title><body bgcolor=\"#AACCFF\"<br>statusLinea Vacia</br>");
                pw.print("</body></html>");
                pw.flush();
                cl.close();
                System.out.println("Cliente Atendido");
                return;
            }
            else{
                System.out.println("Cliente Conectado desde: " + cl.getInetAddress());
                System.out.println("Por el puerto: " + cl.getPort());
                System.out.println("Datos: " + statusLine + "\r\n\r\n");

                if( statusLine.startsWith("GET") ){
                    if( statusLine.indexOf("?") != -1 ){
	                    String response = getParameters(statusLine, headers);
	                    pw.write( response );
	                    pw.flush();
	                    System.out.println("Respuesta GET: \n" + response);
                    }else{
                        String fileName = getFileName( statusLine );
                        String meta = getMetadata( fileName );
                        pw.print( meta );
                        System.out.println( "Respuesta: " + meta );
                        System.out.println("Archivo: " + fileName);
	                    sendSource( fileName );
                    }
                } 
                else if ( statusLine.startsWith("POST") ){
                    String response = getParameters(statusLine, headers);
                    pw.write(response);
                    pw.flush();
                    System.out.println("Respuesta POST: \n" + response);
                } 
                else if ( statusLine.startsWith("HEAD") ){
                    String fileName = getFileName( statusLine );
                    String response = getMetadata( fileName );
                    pw.print( response );
                }
                else{
                    String error501 = "HTTP/1.1 501 Not Implemented\n" +
                    				  "Date: " + new Date() + " \n" +
		              				  "Server: webServer/1.0 \n" +
		              				  "Content-Type: text/html \n\n";
                    sendSource("501.html");
                    pw.write(error501);
                    pw.flush();
                    System.out.println("Respuesta ERROR 501: \n" + error501);
                }
                System.out.println("Cliente Atendido");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getParameters( String statusLine , String headers ){
        String method = "",request;
        if( statusLine.startsWith("GET") ){
            method = "GET";
        	// Line: GET /?Nombre=&Direccion=&Telefono=&Comentarios= HTTP/1.1
        	StringTokenizer tokens = new StringTokenizer( statusLine , "?" );
            request = tokens.nextToken();
            request = tokens.nextToken();
            tokens = new StringTokenizer(request, " ");
            request = tokens.nextToken();
        }else{
            String[] reqLineas = statusLine.split("\n");
            System.out.println("Tam: " + reqLineas.length);
            int ult = reqLineas.length - 1;
            request = reqLineas[ult];
            System.out.println("Obtener: " +  request );
        }

        StringTokenizer paramsTokens = new StringTokenizer(request, "&");
        String html = headers +
					"<html><head><meta charset='UTF-8'><title>Metodo " + method  + "\n" +
                    "</title></head><body><center><h2>Parametros obtenidos, Método " 
                    + method + "</h2><br>\n" + "<table border='0'><tr><th>Parametro</th><th>Valor</th></tr>";

        while(paramsTokens.hasMoreTokens()) {
        	String parametros = paramsTokens.nextToken();
        	StringTokenizer paramValue = new StringTokenizer( parametros , "=" );
        	String param = "";
            String value = "";
            if ( paramsTokens.hasMoreTokens() )
                param = paramValue.nextToken();
            if ( paramsTokens.hasMoreTokens() )
        	    value = paramValue.nextToken();
        	html = html + "<tr><td><b>" + param + "</b></td><td>" + value + "</td></tr>\n";
        }
        html = html + "</table></center></body></html>";
        return html;
    }

    private String getFileName( String statusLine ){
        String file = statusLine.substring( 5, statusLine.length() );
        String source = file.substring( 0 , file.indexOf(" ") );
        if( source.compareTo(" ") == 0 )
            source = "index.html";
        return source;
    }

    private String getMetadata( String fileName ){
        String responseHead = "";
        try{
            File file = new File( fileName );
        	String statusResponse = "HTTP/1.1 200 OK\n";
        	if( !file.exists() ){
        		fileName= "404.html"; // Recurso no encontrado
        		statusResponse = "HTTP/1.1 404 Not Found\n";
        	}
        	else if( file.isDirectory() ) {
        		fileName = "403.html"; // Recurso privado
        		statusResponse = "HTTP/1.1 403 Forbidden\n";
        	}
    		DataInputStream dis = new DataInputStream( new FileInputStream(fileName) ); 
    		int len = dis.available();
            int index = fileName.indexOf(".");
            String extension = fileName.substring( index + 1 , fileName.length() );

            responseHead = statusResponse + "Date: " + new Date() + " \n" +
		                    "Server: webServer/1.0 \n" +
		                    "Content-Type: " + mime.get(extension) + " \n" + 
                            "Content-Length: " + len + " \n\n";
            dis.close();
        }catch( Exception e ) {
            System.out.println(e.getMessage());
        }
        return responseHead;
    }
    
    private void sendSource( String fileName ){
        try{
            File file = new File( fileName );
            if ( file.exists() && !file.isDirectory() ){
                DataInputStream dis = new DataInputStream( new FileInputStream(fileName) ); 
                int len = dis.available();
                byte [] b = new byte[1024];
	            long send = 0;
	            int n = 0;
	            DataOutputStream dos = new DataOutputStream( this.cl.getOutputStream() );
	            while( send < len ) {
	                n = dis.read(b);
	                dos.write(b, 0, n);
	                dos.flush();
	                send += n;
	            }
                dis.close();
                System.out.println("Archivo enviado");
            }
        }catch( Exception e){
            System.out.println(e.getMessage());
        }
    }
}