package managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.IntefaceGestion;
import persistencia.Cursosedicion;
import persistencia.Matricula;

@ManagedBean
@SessionScoped
public class CursosActivos {

    List<Cursosedicion> listaCursosActivos = new ArrayList<>();
    List<Matricula> listaUsuariosCursosActivos = new ArrayList<>();
    String prueba;
    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    boolean mostrar = false;

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public List<Matricula> getListaUsuariosCursosActivos() {
        if (prueba != null && !prueba.equals("")) {
            mostrar = true;
            listaUsuariosCursosActivos = g.listadoAlumnosCursosActivos(prueba);
        } else {
            mostrar = false;
        }
        return listaUsuariosCursosActivos;

    }

    public void setListaUsuariosCursosActivos(List<Matricula> listaUsuariosCursosActivos) {
        this.listaUsuariosCursosActivos = listaUsuariosCursosActivos;
    }

    public List<Cursosedicion> getListaCursosActivos() {
        listaCursosActivos = g.listarCursosActivos();
        return listaCursosActivos;
    }

    public void setListaCursosActivos(ArrayList<Cursosedicion> listaCursosActivos) {
        this.listaCursosActivos = listaCursosActivos;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {

        this.g = g;
    }

    public CursosActivos() {

    }

    public void devolverAlumnos(String idcursoedicion) {

        System.out.println(idcursoedicion);
        listaUsuariosCursosActivos = g.listadoAlumnosCursosActivos(idcursoedicion);
        if (listaUsuariosCursosActivos != null) {
            mostrar = true;
        } else {
            mostrar = false;
        }
    }

    public String devolverAlumnoss(String idcursoedicion) {
        System.out.println(idcursoedicion);
        listaUsuariosCursosActivos = g.listadoAlumnosCursosActivos(idcursoedicion);
        if (listaUsuariosCursosActivos != null) {
            mostrar = true;
        } else {
            mostrar = false;
        }
        return "BajaAlumno";
    }

    public String darDeBajaAlumno(int idalumnocurso) {
        return g.darDeBajaAlumno(idalumnocurso);
    }
}
