import java.io.*;
import java.net.*;
import java.util.Vector;
 
public class MulticastClient2 extends Thread{
 	public static final String MCAST_ADDR  = "230.0.0.1"; //dir clase D valida, grupo al que nos vamos a unir
 	public static final int MCAST_PORT = 9013;//puerto multicast
 	public static final int DGRAM_BUF_LEN=512; //tamaño del buffer

	public void run(){
   		InetAddress group =null;
    	try{
    		group = InetAddress.getByName(MCAST_ADDR);//intenta resolver la direccion
    	}catch(UnknownHostException e){
    		e.printStackTrace();
    		System.exit(1);
		}
    	Vector d = new Vector();
		boolean salta=true;	
    	try{
    		MulticastSocket socket = new MulticastSocket(MCAST_PORT); //socket tipo multicast
    		socket.joinGroup(group);//se une al grupo
			int cd=0;
    		while(salta){
    			byte[] buf = new byte[DGRAM_BUF_LEN];//crea arreglo de bytes 
    			DatagramPacket recv = new DatagramPacket(buf,buf.length);//crea el datagram packet a recibir
    			socket.receive(recv);// ya se tiene el datagram packet
    			System.out.println("Host remoto: "+recv.getAddress()); 
    			System.out.println("Puerto: "+ recv.getPort());
    			byte [] data = recv.getData(); //aqui no se entienden los datos
    			System.out.println("Datos recibidos: " + new String(data)); // se guarda en un vector los datos recibidos
    			//d.addElement(new String(recv.getData()));		
				//if(++cd==5)
				//salta=false;
			} //se convierten los datos///
			//System.out.println("Vector"); 
			//for(Object a:d){
				// System.out.println((String)a);
			//}
    	}catch(IOException e){
    		e.printStackTrace();
    		System.exit(2);
    	}

	}
 	    
    public static void main(String[] args) {
		try{
			MulticastClient2 mc2 = new MulticastClient2();
			mc2.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
