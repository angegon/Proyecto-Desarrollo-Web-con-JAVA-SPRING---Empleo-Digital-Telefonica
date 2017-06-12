package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import persistencia.Usuarios;

@ManagedBean
@SessionScoped
public class ObtenerDatosSesion {

    Usuarios us;

    public Usuarios getUs() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        us = (Usuarios) session.getAttribute("usuario");
        return us;
    }

    public String cerrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "login.xhtml";

    }

    public void setUs(Usuarios us) {
        this.us = us;
    }

    public ObtenerDatosSesion() {
    }

}
