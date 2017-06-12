package managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.IntefaceGestion;
import persistencia.Cursos;
import persistencia.Matricula;
import persistencia.Preguntas;
import persistencia.Usuarios;

@ManagedBean
@SessionScoped
public class RealizarExamen {

    boolean respuesta;
    ArrayList respuestass = new ArrayList<>();
    Usuarios us;
    List<Preguntas> listarPreguntas;
    double nota;

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    Cursos idcurso;

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        respuestass.add(respuesta);
        this.respuesta = respuesta;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public List<Preguntas> getListarPreguntas() {
        listarPreguntas = g.listarPreguntas(idcurso);
        return listarPreguntas;
    }

    public void setListarPreguntas(List<Preguntas> listarPreguntas) {
        this.listarPreguntas = listarPreguntas;
    }

    public String cargarExamen(Cursos idcurso) {
        this.idcurso = idcurso;
        listarPreguntas = g.listarPreguntas(idcurso);

        return "showExamen";
    }

    public RealizarExamen() {
    }

    public String corregir() {

        int cont = 0;
        nota = 0.0;

        for (int i = 0; i < respuestass.size(); i++) {
            if (respuestass.get(i).equals(true)) {
                cont += 1;
            }
        }
        nota = (cont / (double) respuestass.size()) * 100;
        System.out.println(nota);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        us = (Usuarios) session.getAttribute("usuario");
        //obtiene las matriculas de un alumno en los cursos
        List<Matricula> matriculasAlumno = g.listarCursosAlumno(us);
        String ir = "showExamenYaRealizado";
        for (Matricula m : matriculasAlumno) {
            if (m.getIdalumno().getDni().equals(us.getDni())) {

                if (g.insertarNota(m.getIdalumnoscurso(), nota)) {
                    ir = "showAlumno";

                }
            }
        }
        return ir;
    }
}
