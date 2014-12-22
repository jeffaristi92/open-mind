/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_recursos_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmRecursosRol.findAll", query = "SELECT o FROM OpmRecursosRol o"),
    @NamedQuery(name = "OpmRecursosRol.findByNmCodigo", query = "SELECT o FROM OpmRecursosRol o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmRecursosRol.findByNmRol", query = "SELECT o FROM OpmRecursosRol o WHERE o.nmRol = :nmRol")})
public class OpmRecursosRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @JoinColumn(name = "NM_ROL", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmRol nmRol;
    @JoinColumn(name = "NM_RECURSO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmRecurso nmRecurso;

    public OpmRecursosRol() {
    }

    public OpmRecursosRol(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmRol getNmRol() {
        return nmRol;
    }

    public void setNmRol(OpmRol nmRol) {
        this.nmRol = nmRol;
    }

    public OpmRecurso getNmRecurso() {
        return nmRecurso;
    }

    public void setNmRecurso(OpmRecurso nmRecurso) {
        this.nmRecurso = nmRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nmCodigo != null ? nmCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpmRecursosRol)) {
            return false;
        }
        OpmRecursosRol other = (OpmRecursosRol) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmRecursosRol[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
