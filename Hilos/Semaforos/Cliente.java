import java.util.Random;

class Cliente extends Thread {
    private Restaurante r;
    private int clienteID;
    private static final Random random = new Random();
    
    public Cliente(Restaurante r, int customerID) {
        this.r = r;
        this.clienteID = customerID;
    }
    
    public void run() {
        r.getMesa(this.clienteID); // Get a table
        try {
            // Eat for some time. Use number between 1 and 30 seconds
            int eatingTime = random.nextInt(30) + 1 ;
            System.out.println("Cliente #" + this.clienteID +" comera por " + eatingTime +" segundos.");
            Thread.sleep(eatingTime * 1000);
            System.out.println("Cliente #" + this.clienteID +" termino de comer.");
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            r.returnMesa(this.clienteID);
        }
    }
}
