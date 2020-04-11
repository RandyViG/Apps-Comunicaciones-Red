/**
 * @author axel
 */
public class Hilo1 extends Thread{
    String mensaje;
    public Hilo1(String msj){
        this.mensaje=msj;
    } 
    void m1(){
        System.out.println("Dentro del metodo 1..");
    }
    void m2(){
        System.out.println("Dentro del metodo 2..");
    }
    // Cuerpo del hilo
    public void run(){
        System.out.println("El hilo: "+this.mensaje+" esta en ejecucion.. obteniendo sus propiedades");
        System.out.println("Nombre:"+getName()+" Id:"+getId()+ " Prioridad:"+getPriority()+" Grupo:"+getThreadGroup().getName()+" Estado:"+getState());
        //Runtime rt = Runtime.getRuntime();
        //System.out.println("Memoria:"+(rt.freeMemory() / 1024) + "/" + (rt.maxMemory() / 1024) + " kB");
        m1();
        m2();
        System.out.println(""+toString());
        // toString() -> Nombre, prioridad y grupo
    }//run
    
    public static void main(String[] args){
        Hilo1 h1 = new Hilo1("hilo 1");    
        Hilo1 h2 = new Hilo1("hilo 2");
        h1.start();
        h2.start();
    }//main
}