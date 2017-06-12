package managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.IntefaceGestion;

@ManagedBean
@SessionScoped
public class registroUsuario {

    String dni;
    String nombre;
    String apellidos;
    String password;
    String email;

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    public registroUsuario() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public String guardarUsuarioBBDD() {
        if (g.darDeAltaUsuarioNuevo(dni, nombre, apellidos, password, email)) {
            return "showAltaCompleto";
        } else {
            return "altaError";
        }
    }

}
