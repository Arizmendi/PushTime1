package OBJETOS;

import java.util.Date;
import java.util.List;

/**
 * Created by ruben on 6/7/17.
 */

public class Proyecto {
    String Nombre;
    Date fechain ;
    Date fechafin ;
    List<Tarea> tarea;

    public Proyecto() {
    }

    public Proyecto(String nombre, Date fechain, Date fechafin, List<Tarea> tarea) {
        Nombre = nombre;
        this.fechain = fechain;
        this.fechafin = fechafin;
        this.tarea = tarea;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getFechain() {
        return fechain;
    }

    public void setFechain(Date fechain) {
        this.fechain = fechain;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public List<Tarea> getTarea() {
        return tarea;
    }

    public void setTarea(List<Tarea> tarea) {
        this.tarea = tarea;
    }
}
