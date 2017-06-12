/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "cursos")
@NamedQueries({
    @NamedQuery(name = "Cursos.findAll", query = "SELECT c FROM Cursos c"),
    @NamedQuery(name = "Cursos.findByIdcurso", query = "SELECT c FROM Cursos c WHERE c.idcurso = :idcurso"),
    @NamedQuery(name = "Cursos.findByNombre", query = "SELECT c FROM Cursos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cursos.findByDescripcion", query = "SELECT c FROM Cursos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Cursos.findByContenido", query = "SELECT c FROM Cursos c WHERE c.contenido = :contenido")})
public class Cursos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcurso")
    private String idcurso;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "contenido")
    private String contenido;
    @OneToMany(mappedBy = "idcurso")
    private Collection<Preguntas> preguntasCollection;
    @OneToMany(mappedBy = "idcurso")
    private Collection<Cursosedicion> cursosedicionCollection;

    public Cursos() {
    }

    public Cursos(String idcurso) {
        this.idcurso = idcurso;
    }

    public String getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(String idcurso) {
        this.idcurso = idcurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Collection<Preguntas> getPreguntasCollection() {
        return preguntasCollection;
    }

    public void setPreguntasCollection(Collection<Preguntas> preguntasCollection) {
        this.preguntasCollection = preguntasCollection;
    }

    public Collection<Cursosedicion> getCursosedicionCollection() {
        return cursosedicionCollection;
    }

    public void setCursosedicionCollection(Collection<Cursosedicion> cursosedicionCollection) {
        this.cursosedicionCollection = cursosedicionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcurso != null ? idcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursos)) {
            return false;
        }
        Cursos other = (Cursos) object;
        if ((this.idcurso == null && other.idcurso != null) || (this.idcurso != null && !this.idcurso.equals(other.idcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Cursos[ idcurso=" + idcurso + " ]";
    }
    
}
