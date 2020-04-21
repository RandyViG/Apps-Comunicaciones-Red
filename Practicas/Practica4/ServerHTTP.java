import java.net.Socket;
import java.net.ServerSocket;
import java.lang.Runnable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerHTTP implements Runnable {
    protected int port;
    protected ServerSocket ss;
    protected boolean stopped = false;
    protected Thread runningThread = null;
    protected ExecutorService pool = Executors.newFixedThreadPool(100);

    public ServerHTTP(int port) {
        this.port = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        startServer();
        while (!stopped()) {
            Socket cl = null;
            try {
                cl = this.ss.accept();
                System.out.println("Conexion aceptada...");
            } catch (IOException e) {
                if (stopped()) {
                    System.out.println("Servidor detenido.");
                    break;
                }
                throw new RuntimeException("Error al aceptar nueva conexion", e);
            }
            try {
                this.pool.execute( new Manejador(cl) );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.pool.shutdown();
        System.out.println("Servidor stopped.") ;
    }

    private synchronized boolean stopped() {
        return this.stopped;
    }

    public synchronized void stop(){
        this.stopped = true;
        try {
            this.ss.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al cerrar el socket del servidor", e);
        }
    }

    private void startServer() {
        try {
            this.ss = new ServerSocket(this.port);
            System.out.println("--------------- Servidor HTTP ---------------");
            System.out.println("Servicio iniciado en el puerto: " + ss.getLocalPort() + "\nEsperando cliente...");
        } catch (IOException e) {
            throw new RuntimeException("No puede iniciar el socket en el puerto: "+ss.getLocalPort(), e);
        }
    }

    public static void main( String[] args ){
        ServerHTTP server = new ServerHTTP( 8080 );
        new Thread(server).start();
    }    
}