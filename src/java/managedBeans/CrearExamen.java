package managedBeans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.IntefaceGestion;
import persistencia.Cursos;


@ManagedBean
@RequestScoped
public class CrearExamen {

    List<Cursos> cursos;

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    String pregunta;
    String[] rp = new String[4];
    String idurso;
    int correcta;

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String[] getRp() {
        return rp;
    }

    public void setRp(String[] rp) {
        this.rp = rp;
    }

    public String getIdurso() {
        return idurso;
    }

    public void setIdurso(String idurso) {
        this.idurso = idurso;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public List<Cursos> getCursos() {
        cursos = g.listarCursos();
        return cursos;
    }

    public void setCursos(List<Cursos> cursos) {
        this.cursos = cursos;
    }

    public CrearExamen() {
    }

    public String insertarPregunta() {
        if (g.insertarPregunta(pregunta, rp, correcta, idurso)) {
            return "showAltaCompleto";
        } else {
            return "error";
        }
    }

    public String insertarPreguntaYSegir() {
        if (g.insertarPregunta(pregunta, rp, correcta, idurso)) {
            return "showCrearExamen";
        } else {
            return "error";
        }
    }
}
