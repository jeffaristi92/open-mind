/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmClase.findAll", query = "SELECT o FROM OpmClase o"),
    @NamedQuery(name = "OpmClase.findByNmCodigo", query = "SELECT o FROM OpmClase o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmClase.findByNvNombre", query = "SELECT o FROM OpmClase o WHERE o.nvNombre = :nvNombre"),
    @NamedQuery(name = "OpmClase.findByNmTipo", query = "SELECT o FROM OpmClase o WHERE o.nmTipo = :nmTipo")})
public class OpmClase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;
    @Basic(optional = false)
    @Column(name = "NM_TIPO")
    private int nmTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmClase")
    private List<OpmGrupo> opmGrupoList;

    public OpmClase() {
    }

    public OpmClase(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmClase(Integer nmCodigo, String nvNombre, int nmTipo) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
        this.nmTipo = nmTipo;
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

    public int getNmTipo() {
        return nmTipo;
    }

    public void setNmTipo(int nmTipo) {
        this.nmTipo = nmTipo;
    }

    @XmlTransient
    public List<OpmGrupo> getOpmGrupoList() {
        return opmGrupoList;
    }

    public void setOpmGrupoList(List<OpmGrupo> opmGrupoList) {
        this.opmGrupoList = opmGrupoList;
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
        if (!(object instanceof OpmClase)) {
            return false;
        }
        OpmClase other = (OpmClase) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmClase[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
