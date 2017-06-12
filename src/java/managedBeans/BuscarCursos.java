package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.IntefaceGestion;
import persistencia.Cursosedicion;

@ManagedBean
@SessionScoped
public class BuscarCursos implements Serializable {

    String curso;
    List<Cursosedicion> cursos;
    boolean mostrar = false;

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public List<Cursosedicion> getCursos() {
        return cursos;
    }

    public void setCursos(List<Cursosedicion> cursos) {
        this.cursos = cursos;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    /**
     * Creates a new instance of BuscarCursos
     */
    public BuscarCursos() {
    }

    public void obtenerCursos() throws IOException {

        cursos = g.listarCursosBuscados(curso);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        mostrar = true;
    }

}
