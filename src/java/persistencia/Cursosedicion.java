/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "cursosedicion")
@NamedQueries({
    @NamedQuery(name = "Cursosedicion.buscarEdiciones", query = "SELECT c FROM Cursosedicion c WHERE c.idcursoedicion  LIKE  :idcursoedicion"),
    @NamedQuery(name = "Cursosedicion.contarEdiciones", query= "SELECT  COUNT(c.idcursoedicion) FROM Cursosedicion c WHERE c.idcurso = :idcurso"),
    @NamedQuery(name = "Cursosedicion.findAll", query = "SELECT c FROM Cursosedicion c"),
    @NamedQuery(name = "Cursosedicion.findByIdcursoedicion", query = "SELECT c FROM Cursosedicion c WHERE c.idcursoedicion = :idcursoedicion"),
    @NamedQuery(name = "Cursosedicion.findByNombre", query = "SELECT c FROM Cursosedicion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cursosedicion.findByFechainicio", query = "SELECT c FROM Cursosedicion c WHERE c.fechainicio = :fechainicio"),
    @NamedQuery(name = "Cursosedicion.findByFechafin", query = "SELECT c FROM Cursosedicion c WHERE c.fechafin = :fechafin")})
public class Cursosedicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcursoedicion")
    private String idcursoedicion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @OneToMany(mappedBy = "idedicion")
    private Collection<Matricula> matriculaCollection;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne
    private Cursos idcurso;
    @JoinColumn(name = "idprofesor", referencedColumnName = "dni")
    @ManyToOne
    private Usuarios idprofesor;

    public Cursosedicion() {
    }

    public Cursosedicion(String idcursoedicion) {
        this.idcursoedicion = idcursoedicion;
    }

    public String getIdcursoedicion() {
        return idcursoedicion;
    }

    public void setIdcursoedicion(String idcursoedicion) {
        this.idcursoedicion = idcursoedicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Collection<Matricula> getMatriculaCollection() {
        return matriculaCollection;
    }

    public void setMatriculaCollection(Collection<Matricula> matriculaCollection) {
        this.matriculaCollection = matriculaCollection;
    }

    public Cursos getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Cursos idcurso) {
        this.idcurso = idcurso;
    }

    public Usuarios getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Usuarios idprofesor) {
        this.idprofesor = idprofesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcursoedicion != null ? idcursoedicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursosedicion)) {
            return false;
        }
        Cursosedicion other = (Cursosedicion) object;
        if ((this.idcursoedicion == null && other.idcursoedicion != null) || (this.idcursoedicion != null && !this.idcursoedicion.equals(other.idcursoedicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Cursosedicion[ idcursoedicion=" + idcursoedicion + " ]";
    }
    
}
