
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@Named(value = "cerrarSesion")
@RequestScoped
public class CerrarSesion {

    public CerrarSesion() {
    }

    public String cerrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "login.xhtml";

    }
}
