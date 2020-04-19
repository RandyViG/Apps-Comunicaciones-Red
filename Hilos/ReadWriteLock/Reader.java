import java.util.*;
 
/**
 * Reader.java
 * Este hilo lee aleatoriamente un elemento a un ArrayList compartido
 */
public class Reader extends Thread {
    private ReadWriteList<Integer> sharedList;
 
    public Reader(ReadWriteList<Integer> sharedList) {
        this.sharedList = sharedList;
    }
 
    public void run() {
        Random random = new Random();
        int index = random.nextInt(sharedList.size());
        Integer number = sharedList.get(index);
 
        System.out.println(getName() + " -> get: " + number);
 
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie ){ 
            ie.printStackTrace(); 
        }
 
    }
}