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
@Table(name = "respuestas")
@NamedQueries({
    @NamedQuery(name = "Respuestas.findAll", query = "SELECT r FROM Respuestas r"),
    @NamedQuery(name = "Respuestas.findByIdrespuestas", query = "SELECT r FROM Respuestas r WHERE r.idrespuestas = :idrespuestas"),
    @NamedQuery(name = "Respuestas.findByTextorespuesta", query = "SELECT r FROM Respuestas r WHERE r.textorespuesta = :textorespuesta"),
    @NamedQuery(name = "Respuestas.findByCorrecta", query = "SELECT r FROM Respuestas r WHERE r.correcta = :correcta")})
public class Respuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrespuestas")
    private Integer idrespuestas;
    @Column(name = "textorespuesta")
    private String textorespuesta;
    @Column(name = "correcta")
    private Boolean correcta;
    @JoinColumn(name = "idpregunta", referencedColumnName = "idpregunta")
    @ManyToOne
    private Preguntas idpregunta;

    public Respuestas() {
    }

    public Respuestas(Integer idrespuestas) {
        this.idrespuestas = idrespuestas;
    }

    public Integer getIdrespuestas() {
        return idrespuestas;
    }

    public void setIdrespuestas(Integer idrespuestas) {
        this.idrespuestas = idrespuestas;
    }

    public String getTextorespuesta() {
        return textorespuesta;
    }

    public void setTextorespuesta(String textorespuesta) {
        this.textorespuesta = textorespuesta;
    }

    public Boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Boolean correcta) {
        this.correcta = correcta;
    }

    public Preguntas getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(Preguntas idpregunta) {
        this.idpregunta = idpregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrespuestas != null ? idrespuestas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestas)) {
            return false;
        }
        Respuestas other = (Respuestas) object;
        if ((this.idrespuestas == null && other.idrespuestas != null) || (this.idrespuestas != null && !this.idrespuestas.equals(other.idrespuestas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Respuestas[ idrespuestas=" + idrespuestas + " ]";
    }
    
}
