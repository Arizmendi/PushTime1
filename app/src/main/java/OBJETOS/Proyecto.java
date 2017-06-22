package OBJETOS;

import java.util.Date;
import java.util.List;

/**
 * Created by ruben on 6/7/17.
 */

public class Proyecto {
    String Nombre;
    String fechain;
    String fechafin;




    public Proyecto(String nombre, String fechain, String fechafin) {
        Nombre = nombre;
        this.fechain = fechain;
        this.fechafin = fechafin;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFechain() {
        return fechain;
    }

    public void setFechain(String fechain) {
        this.fechain = fechain;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }


}
