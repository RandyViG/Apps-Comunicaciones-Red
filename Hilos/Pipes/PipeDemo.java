import java.io.*;

public class PipeDemo extends Thread{
    PipedOutputStream output;
    
    public PipeDemo(PipedOutputStream out){
        output=out;
    }
    
    public static void main(String[] args){
        try{
            PipedOutputStream pout = new PipedOutputStream();
            PipedInputStream pin = new PipedInputStream(pout);
            PipeDemo pd = new PipeDemo(pout);
            pd.start();
            int input = pin.read();
            while(input !=-1){
                System.out.print((char)input);
                input = pin.read();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        try{
            PrintStream ps = new PrintStream(output);
            ps.println("Mensaje enviado desde otro hilo a traves de una tuberia..");
            ps.flush();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
