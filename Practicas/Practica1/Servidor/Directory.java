
import java.io.Serializable;
import javax.swing.JTree;

/**
 * @author randy
 */

public class Directory implements Serializable{
    
    private JTree directory;

    public Directory( JTree directory ){
        this.directory = directory;
    }

    public JTree getDirectory(){
        return this.directory;
    }

    public void setDirectory( JTree directory ){
        this.directory = directory;
    }
}
