import java.util.*;
/**
 * Writer.java
 * Este hilo agrega aleatoriamente un elemento a un ArrayList compartido
 */
public class Writer extends Thread {
    private ReadWriteList<Integer> sharedList;
 
    public Writer(ReadWriteList<Integer> sharedList) {
        this.sharedList = sharedList;
    }
 
    public void run() {
        Random random = new Random();
        int number = random.nextInt(100);
        sharedList.add(number);
 
        try {
            Thread.sleep(100);
            System.out.println(getName() + " -> put: " + number);
        } catch (InterruptedException ie ) { ie.printStackTrace(); }
    }
}