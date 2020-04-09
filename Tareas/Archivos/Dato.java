import java.io.Serializable;

public class Dato implements Serializable{
    private int numberPackage;
    private byte[] data;
    private int totalPackage;
  
    public Dato(int numberPackage, byte[] data, int totalPackage){
        this.numberPackage = numberPackage;
        this.data = data;
        this.totalPackage = totalPackage;   
    }

    public void setNumberPackage( int n ){
        this.numberPackage = n;
    }

    public int getNumberPackage(){
        return this.numberPackage;
    }

    public void setData( byte[] d ){
        this.data = d;
    }

    public byte [] getData(){
        return this.data;
    }

    public void setTotalPackage( int t){
        this.totalPackage = t;
    }

    public int getTotalPackage(){
        return this.totalPackage;
    }
}
