 /*
 * Fichero: PruebaJTree.java
 * Autor: Chuidiang
 * Fecha: 27/02/07 22:19
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 * Clase de ejemplo sencillo de uso del JTree
 *
 * @author Chuidiang
 */
public class PruebaJTree
{
    /**
     * Ejemplo sencillo de uso de JTree
     *
     * @param args Argumentos de linea de comandos. Se ignoran.
     */
    public static void main(String[] args)
    {
        // Construccion del arbol
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Drive");
        DefaultTreeModel modelo = new DefaultTreeModel(raiz);
        JTree tree = new JTree(raiz);

        // Construccion de los datos del arbol
        DefaultMutableTreeNode docs = new DefaultMutableTreeNode("Docs");
        DefaultMutableTreeNode images = new DefaultMutableTreeNode("Images");
        modelo.insertNodeInto(docs , raiz, 0);
        modelo.insertNodeInto(images , raiz, 1);

        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode("Paola");
        DefaultMutableTreeNode hija = new DefaultMutableTreeNode("Ran");
        DefaultMutableTreeNode hi = new DefaultMutableTreeNode("Tol");
        modelo.insertNodeInto(hijo, docs, 0);
        modelo.insertNodeInto(hija, docs, 1);
        modelo.insertNodeInto(hi, docs, 2);
        
        DefaultMutableTreeNode h = new DefaultMutableTreeNode("Hijo1");
        DefaultMutableTreeNode ha = new DefaultMutableTreeNode("Hijo2");
        modelo.insertNodeInto(h, images, 0);
        modelo.insertNodeInto(ha, images, 1);
        
        // Construccion y visualizacion de la ventana
        JFrame v = new JFrame();
        JScrollPane scroll = new JScrollPane(tree);
        v.getContentPane().add(scroll);
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}