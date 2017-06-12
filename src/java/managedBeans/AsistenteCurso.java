package managedBeans;

import java.io.Serializable;
import java.sql.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.IntefaceGestion;
import org.primefaces.model.UploadedFile;
import persistencia.Cursos;

@ManagedBean
@SessionScoped
public class AsistenteCurso implements Serializable {

    String nombre;
    String descripcion;
    Date fechainicio;
    Date fechafin;

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public AsistenteCurso() {

    }
}
