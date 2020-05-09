import java.io.Serializable;

public class Dato implements Serializable{
    int v1;
    float v2;
    String v3;
    //transient String v3; No se serializa por lo tanto llega el valor por defecto del constructor 
    // primitivo 0
    // objeto null
    public Dato( int v1, float v2, String v3){
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    int getV1(){
        return this.v1;
    }

    float getV2(){
        return this.v2;
    }

    String getV3(){
        return this.v3;
    }
}