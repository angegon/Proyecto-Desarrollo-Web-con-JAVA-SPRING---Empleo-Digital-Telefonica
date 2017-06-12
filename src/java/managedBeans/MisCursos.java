package managedBeans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.IntefaceGestion;
import persistencia.Matricula;
import persistencia.Usuarios;

@ManagedBean
@SessionScoped
public class MisCursos {

    List<Matricula> ma;
    Matricula curso;
    @ManagedProperty("#{gestion}")
    IntefaceGestion g;
    String urlPdf;
    Date fechaHoy = null;

    public Date getFechaHoy() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaHoy = sdf.parse(sdf.format(new Date()));

        } catch (ParseException ex) {
            Logger.getLogger(MisCursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public Matricula getCurso() {
        return curso;
    }

    public void setCurso(Matricula curso) {
        this.curso = curso;
    }

    public List<Matricula> getMa() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Usuarios us = (Usuarios) session.getAttribute("usuario");
        ma = g.listarCursosAlumno(us);
        return ma;
    }

    public void setMa(List<Matricula> ma) {
        this.ma = ma;
    }

    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public String obtenerDatosCurso(Matricula mt) {
        System.out.println(mt);
        curso = mt;
        return "showFichaCursos.xhtml";
    }

    public MisCursos() {

    }
}
