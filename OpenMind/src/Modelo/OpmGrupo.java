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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "opm_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmGrupo.findAll", query = "SELECT o FROM OpmGrupo o"),
    @NamedQuery(name = "OpmGrupo.findByNmCodigo", query = "SELECT o FROM OpmGrupo o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmGrupo.findByNvNombre", query = "SELECT o FROM OpmGrupo o WHERE o.nvNombre = :nvNombre")})
public class OpmGrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmGrupo")
    private List<OpmCuenta> opmCuentaList;
    @JoinColumn(name = "NM_CLASE", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmClase nmClase;

    public OpmGrupo() {
    }

    public OpmGrupo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmGrupo(Integer nmCodigo, String nvNombre) {
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

    @XmlTransient
    public List<OpmCuenta> getOpmCuentaList() {
        return opmCuentaList;
    }

    public void setOpmCuentaList(List<OpmCuenta> opmCuentaList) {
        this.opmCuentaList = opmCuentaList;
    }

    public OpmClase getNmClase() {
        return nmClase;
    }

    public void setNmClase(OpmClase nmClase) {
        this.nmClase = nmClase;
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
        if (!(object instanceof OpmGrupo)) {
            return false;
        }
        OpmGrupo other = (OpmGrupo) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmGrupo[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
