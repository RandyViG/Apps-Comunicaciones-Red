import java.io.*;
import java.util.Random;

class Productor extends Thread{
    private DataOutputStream dos;
    private Random rand = new Random();
    
    public Productor(OutputStream os){
        dos = new DataOutputStream(os);
    }
    
    public void run(){
        while(true){
            try{
                double num = rand.nextDouble();
                dos.writeDouble(num);
                dos.flush();
                sleep(Math.abs(rand.nextInt()%1000));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

class Consumidor extends Thread{
    private double prom_ant=0;
    private DataInputStream dis;
    
    public Consumidor(InputStream is){
        dis = new DataInputStream(is);
    }
    
    public void run(){
        for(;;){
            try{
                double prom = dis.readDouble();
                if(Math.abs(prom-prom_ant)>0.01){
                    System.out.println("El promedio actual es: "+prom);
                    prom_ant=prom;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

class Filtro extends Thread{
    private DataInputStream dis;
    private DataOutputStream dos;
    private double total=0;
    private int cuenta=0;
    
    public Filtro(InputStream is, OutputStream os){
        dis = new DataInputStream(is);
        dos = new DataOutputStream(os);
    }
    
    public void run(){
        for(;;){
            try{
                double x = dis.readDouble();
                total+=x;
                cuenta++;
                if(cuenta !=0){
                    dos.writeDouble(total/cuenta);
                    dos.flush();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
public class PipeTest {
    public static void main(String[] args){
        try{
            PipedOutputStream pout1 = new PipedOutputStream();
            PipedInputStream pin1 = new PipedInputStream(pout1);
            PipedOutputStream pout2 = new PipedOutputStream();
            PipedInputStream pin2 = new PipedInputStream(pout2);
            Productor prod = new Productor(pout1);
            Filtro f = new Filtro(pin1,pout2);
            Consumidor cons = new Consumidor(pin2);
            prod.start(); f.start(); cons.start();
        }catch(IOException io){
            io.printStackTrace();
        }
    }
}
