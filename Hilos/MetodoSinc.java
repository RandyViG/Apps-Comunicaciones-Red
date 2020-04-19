/**
 *
 * @author axel
 */
class Contador{
    private int vcuenta;
    public Contador(){
        vcuenta=0;
    }
    
    public Contador(int i){
        vcuenta=i;
    }
    
    public synchronized void incrementa(){
        int cuenta = vcuenta;
        try{
            Thread.sleep(5);
        }catch(InterruptedException ie){}//catch
        cuenta=cuenta+1;
        vcuenta=cuenta;
    }
    
    public synchronized int getCuenta(){
        return vcuenta;
    }
}

public class MetodoSinc implements Runnable {
    Contador cont;
    int incrementos;

    public MetodoSinc(Contador cont, int c){
        this.cont = cont;
        this.incrementos = c;
    }//constructor

    public void run(){
        for(int i=1;i<=incrementos;i++)
            cont.incrementa();
    }
    public static void main(String[] args)throws Exception{
        Contador c = new Contador();
        Runnable r = new MetodoSinc(c,10);
        System.out.println("Comienza la cuenta de hilos..");
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start(); t2.start(); t3.start();
        t1.join(); t2.join(); t3.join();
        System.out.println("El valor de la cuenta es: "+c.getCuenta());
    }

}
