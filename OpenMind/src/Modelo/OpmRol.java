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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmRol.findAll", query = "SELECT o FROM OpmRol o"),
    @NamedQuery(name = "OpmRol.findByNmCodigo", query = "SELECT o FROM OpmRol o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmRol.findByNvNombre", query = "SELECT o FROM OpmRol o WHERE o.nvNombre = :nvNombre")})
public class OpmRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;

    public OpmRol() {
    }

    public OpmRol(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmRol(Integer nmCodigo, String nvNombre) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public String getNvNombre() {
        return nvNombre;
    }

    public void setNvNombre(String nvNombre) {
        this.nvNombre = nvNombre;
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
        if (!(object instanceof OpmRol)) {
            return false;
        }
        OpmRol other = (OpmRol) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmRol[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
