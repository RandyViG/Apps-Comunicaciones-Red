import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor {
    public Servidor(int puerto, Producto[] misProductos) {
        this.puerto = puerto;
        this.misProductos = misProductos;
    }// Constructor

    public void online() throws ClassNotFoundException {
        try {
            servidor = new ServerSocket(this.puerto);
            System.out.println("Servidor Activo");
            for (;;) {
                cliente = servidor.accept();
                ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
                Last l = (Last) ois.readObject();
                int accion = l.getAccion();
                if (accion == 1) {
                    mandarCatalogo();
                } else if (accion == 2) {
                    registrarCompra(l);
                }
                ois.close();
                cliente.close();
            } // for
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// online

    private void mandarCatalogo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(misProductos);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch
    }// mandarCatalogo

    private void registrarCompra(Last l) {
        ArrayList<Producto> productoRecibido = l.getProducto();

        actualizarExistencia(productoRecibido);
    }// registrarCompra

    private void actualizarExistencia(ArrayList<Producto> productoRecibido) {
        for(Producto p : productoRecibido) {
            if(p.getID() == 1) {
                misProductos[0].setExistencias(misProductos[0].getExistencias() - p.getCantidad());
            } else if(p.getID() == 2) {
                misProductos[1].setExistencias(misProductos[1].getExistencias() - p.getCantidad());
            } else if(p.getID() == 3) {
                misProductos[2].setExistencias(misProductos[2].getExistencias() - p.getCantidad());
            } else if(p.getID() == 4) {
                misProductos[3].setExistencias(misProductos[3].getExistencias() - p.getCantidad());
            } else if(p.getID() == 5) {
                misProductos[4].setExistencias(misProductos[4].getExistencias() - p.getCantidad());
            } else if(p.getID() == 6) {
                misProductos[5].setExistencias(misProductos[5].getExistencias() - p.getCantidad());
            } else if(p.getID() == 7) {
                misProductos[6].setExistencias(misProductos[6].getExistencias() - p.getCantidad());
            } else if(p.getID() == 8) {
                misProductos[7].setExistencias(misProductos[7].getExistencias() - p.getCantidad());
            } else if(p.getID() == 9) {
                misProductos[8].setExistencias(misProductos[8].getExistencias() - p.getCantidad());
            } else if(p.getID() == 10) {
                misProductos[9].setExistencias(misProductos[9].getExistencias() - p.getCantidad());
            } else if(p.getID() == 11) {
                misProductos[10].setExistencias(misProductos[10].getExistencias() - p.getCantidad());
            } else if(p.getID() == 12) {
                misProductos[11].setExistencias(misProductos[11].getExistencias() - p.getCantidad());
            } else if(p.getID() == 13) {
                misProductos[12].setExistencias(misProductos[12].getExistencias() - p.getCantidad());
            } else if(p.getID() == 14) {
                misProductos[13].setExistencias(misProductos[13].getExistencias() - p.getCantidad());
            } else if(p.getID() == 15) {
                misProductos[14].setExistencias(misProductos[14].getExistencias() - p.getCantidad());
            } else if(p.getID() == 16) {
                misProductos[15].setExistencias(misProductos[15].getExistencias() - p.getCantidad());
            } else if(p.getID() == 17) {
                misProductos[16].setExistencias(misProductos[16].getExistencias() - p.getCantidad());
            } else if(p.getID() == 18) {
                misProductos[17].setExistencias(misProductos[17].getExistencias() - p.getCantidad());
            } else if(p.getID() == 19) {
                misProductos[18].setExistencias(misProductos[18].getExistencias() - p.getCantidad());
            } else if(p.getID() == 20) {
                misProductos[19].setExistencias(misProductos[19].getExistencias() - p.getCantidad());
            }
        }
        
        generarTicket(productoRecibido);

        System.out.println("Productos Actualizados");
        for(Producto p : misProductos)
            System.out.println(p.getNombre() + " : " + p.getExistencias());
    }// actualizarExistencia

    private void generarTicket(ArrayList<Producto> productoRecibido) {
        double precio = 0;
        for (Producto p : productoRecibido)
            precio += p.getPrecio() * p.getCantidad();

        Ticket t = new Ticket(productoRecibido, precio);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(t);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//generarTicket

    private int puerto;
    private ServerSocket servidor;
    private Socket cliente;
    private Producto[] misProductos;
}//Clase