
package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.mail.MessagingException;
import modelo.IntefaceGestion;


@Named
@ManagedBean
@RequestScoped
public class Email {
    String para, asunto, mensaje;
    @ManagedProperty ("#{gestion}")
    IntefaceGestion g;
    
    public Email() {
    }

    public Email(String para, String asunto, String mensaje) {
        this.para = para;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public String enviar() {
        boolean prueba = g.enviar(para, asunto, mensaje);
        if (prueba){           
            
            return "indexAlumno";
        }else{
            return "error";
        }
    }

}