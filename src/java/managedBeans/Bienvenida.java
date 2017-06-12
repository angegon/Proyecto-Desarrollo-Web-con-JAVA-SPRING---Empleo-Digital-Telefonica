package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.IntefaceGestion;
import persistencia.Usuarios;

@ManagedBean
@SessionScoped
public class Bienvenida {

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;
    Usuarios us;

    public Bienvenida() {
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public Usuarios getUs() {
        return us;
    }

    public void setUs(Usuarios us) {
        us = g.obtenerNombre();
        this.us = us;
    }
}
