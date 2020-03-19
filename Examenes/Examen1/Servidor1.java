import java.net.*;
import java.io.*;
/**
 *
 * @author FERNANDO
 */
public class Servidor1 {

    public void servidor(){
        try{
            int pto = 5678;
            ServerSocket s = new ServerSocket(pto);
            s.setReuseAddress( true );
            System.out.println("Servidor iniciado en el puerto " + pto + " Esperando archivo");
            for(;;){
                Socket cl = s.accept();
                ObjectOutputStream oos = new ObjectOutputStream( cl.getOutputStream() );
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                int op = dis.readInt();
                switch(op){
                    case 1:
                            String[][] palabras1 = { {"maestro", "alumno", "lapiz", "lapicero", "cuaderno", "crayon", "computadora", "goma",
				"gis", "regla", "compas", "libro", "colores", "tijeras", "pizarron"}, 
                                {"Se_dedica_a_la_enseñanza", "Aquella_persona_que_aprende", "Mina_encapsulado_en_madera", 
                                "Al_contacto_con_papel_dosifica_tinta", "Sirve_para_notas_dibujo_etc", "barra_de_cera_de_color", 
                                "Sirve_para_guardar_y_procesar_informacion", "Sirve_para_borrar", "Barrita_de_arcilla", 
                                "De_forma_plana_y_rectangular", "Traza_curvas", "Hojas_protegidas_con_tapas", "Sus_primarios_son_RGB", 
                                "Te_sirve_para_cortar", "Superficie_de_escritura_reutilizable"}};
                            oos.writeObject(palabras1);
                            
                            break;
                    case 2:
                            String [][] palabras2 = {
                            {"maestro", "alumno", "plumon", "lapicero", "cuaderno", "prefecto", "salon", "goma","escuadra", "regla", "compas", "libro", "colores", "tijeras", "lapicera"},
                            {"traemos", "maulon", "pulmon","copiarle", "educaron", "perfecto", "lonas", "mago", "acuerdas", "glera", "campos", "birlo", "coserlo", "tirajes", "aplicare"}};
                            oos.writeObject(palabras2);
                            break;
                }
                oos.flush();
                oos.close();
                cl.close();
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }    
}
