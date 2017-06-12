/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "matricula")
@NamedQueries({
    @NamedQuery(name = "Matricula.buscarPorIdAlumno", query = "SELECT m FROM Matricula m WHERE m.idalumno = :idalumno"),
    @NamedQuery(name = "Matricula.findEdicion", query = "SELECT m FROM Matricula m WHERE m.idedicion = :idedicion"),
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByIdalumnoscurso", query = "SELECT m FROM Matricula m WHERE m.idalumnoscurso = :idalumnoscurso"),
    @NamedQuery(name = "Matricula.findByNota", query = "SELECT m FROM Matricula m WHERE m.nota = :nota")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalumnoscurso")
    private Integer idalumnoscurso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota")
    private Double nota;
    @JoinColumn(name = "idalumno", referencedColumnName = "dni")
    @ManyToOne
    private Usuarios idalumno;
    @JoinColumn(name = "idedicion", referencedColumnName = "idcursoedicion")
    @ManyToOne
    private Cursosedicion idedicion;

    public Matricula() {
    }

    public Matricula(Integer idalumnoscurso) {
        this.idalumnoscurso = idalumnoscurso;
    }

    public Integer getIdalumnoscurso() {
        return idalumnoscurso;
    }

    public void setIdalumnoscurso(Integer idalumnoscurso) {
        this.idalumnoscurso = idalumnoscurso;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Usuarios getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(Usuarios idalumno) {
        this.idalumno = idalumno;
    }

    public Cursosedicion getIdedicion() {
        return idedicion;
    }

    public void setIdedicion(Cursosedicion idedicion) {
        this.idedicion = idedicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalumnoscurso != null ? idalumnoscurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idalumnoscurso == null && other.idalumnoscurso != null) || (this.idalumnoscurso != null && !this.idalumnoscurso.equals(other.idalumnoscurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Matricula[ idalumnoscurso=" + idalumnoscurso + " ]";
    }
    
}
