import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

public class Servidor{
    
    public static void main(String[] args){
        try{
            Dato[] Datos;
            DatagramSocket s = new DatagramSocket(1235);
            s.setReuseAddress(true);
            System.out.println("Servidor iniciado \nEsperando paquetes ...");
            for(;;){

                DatagramPacket p = new DatagramPacket(new byte[65535],65535);
                s.receive(p);
                String name = new String( p.getData(),0,p.getLength() );

                System.out.println("Archivo: " + name);
                System.out.println("Datagrama recibido desde: "+p.getAddress() );

                String response = "Nombre recibido.";
                byte[] aux = response.getBytes();
                DatagramPacket p1 = new DatagramPacket(aux,aux.length,p.getAddress(),p.getPort());
                s.send(p1);

                DatagramPacket info1= new DatagramPacket(new byte[3100],3100);
                s.receive(info1);
                ObjectInputStream ois1=new ObjectInputStream( new ByteArrayInputStream(info1.getData()));
                Dato d1 =(Dato)ois1.readObject();
            
                int t = d1.getTotalPackage();
                Datos = new Dato[t];
                Datos[ ( d1.getNumberPackage() )-1 ] = d1;
                
                response = "Paquete numero: " + d1.getNumberPackage() + " recibido";
                aux = response.getBytes();
                p1 = new DatagramPacket(aux,aux.length,p.getAddress(),p.getPort());
                s.send(p1);

                for(int i=1 ; i<t ; i++) {
                    DatagramPacket info= new DatagramPacket(new byte[3100],3100);
                    s.receive(info);
                    ObjectInputStream ois=new ObjectInputStream( new ByteArrayInputStream(info.getData()));
                    Dato d =(Dato)ois.readObject();
                    int porcentage = (int)(d.getNumberPackage()*100)/t ;
                    System.out.print("\rSe ha recibido el " + porcentage + "% del archivo");
                    Datos[ (d.getNumberPackage())-1 ] = d;

                    response = "Paquete numero: " + d1.getNumberPackage() + " recibido";
                    aux = response.getBytes();
                    p1 = new DatagramPacket(aux,aux.length,p.getAddress(),p.getPort());
                    s.send(p1);
                    ois.close();
                }
                
                DataOutputStream dos= new DataOutputStream(new FileOutputStream(name));
                
                for(int j=0; j<Datos.length; j++){
                    dos.write(Datos[j].getData(),0,3000);
                    dos.flush();
                }
                dos.close();
                ois1.close();
                System.out.println("\nArchivo recibido...");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }   
}