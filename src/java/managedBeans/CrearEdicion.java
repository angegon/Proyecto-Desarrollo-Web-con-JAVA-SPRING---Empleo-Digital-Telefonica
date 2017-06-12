package managedBeans;

import java.util.ArrayList;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import modelo.IntefaceGestion;
import java.util.Date;
import persistencia.Cursos;
import persistencia.Usuarios;

@ManagedBean
@RequestScoped
public class CrearEdicion {

    Date fIni;
    Date fFin;
    String idCurso;
    String idProfesor;
    ArrayList<Cursos> listaCursos = new ArrayList<>();
    ArrayList<Usuarios> listaProfesores = new ArrayList<>();

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    public Date getfIni() {
        return fIni;
    }

    public void setfIni(Date fIni) {
        this.fIni = fIni;
    }

    public Date getfFin() {
        return fFin;
    }

    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }

    public List<Usuarios> getListaProfesores() {

        return listaProfesores;
    }

    public void setListaProfesores(ArrayList<Usuarios> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public IntefaceGestion getG() {

        return g;
    }

    public List<Cursos> getListaCursos() {

        return listaCursos;
    }

    public void setListaCursos(ArrayList<Cursos> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public void setG(IntefaceGestion g) {
        listaProfesores = (ArrayList<Usuarios>) g.listarProfesores();
        listaCursos = (ArrayList<Cursos>) g.listarCursos();
        this.g = g;

    }

    public String nuevaEdicion() {

        if (g.introEdicion(fIni, fFin, idCurso, idProfesor)) {
            return "showAltaCompleto";

        } else {
            return "error";
        }

    }

    public CrearEdicion() {

    }

}
