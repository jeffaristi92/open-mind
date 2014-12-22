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
@Table(name = "opm_tipo_gasto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmTipoGasto.findAll", query = "SELECT o FROM OpmTipoGasto o"),
    @NamedQuery(name = "OpmTipoGasto.findByNmCodigo", query = "SELECT o FROM OpmTipoGasto o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmTipoGasto.findByNvNombre", query = "SELECT o FROM OpmTipoGasto o WHERE o.nvNombre = :nvNombre")})
public class OpmTipoGasto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmTipo")
    private List<OpmGastos> opmGastosList;

    public OpmTipoGasto() {
    }

    public OpmTipoGasto(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmTipoGasto(Integer nmCodigo, String nvNombre) {
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
    public List<OpmGastos> getOpmGastosList() {
        return opmGastosList;
    }

    public void setOpmGastosList(List<OpmGastos> opmGastosList) {
        this.opmGastosList = opmGastosList;
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
        if (!(object instanceof OpmTipoGasto)) {
            return false;
        }
        OpmTipoGasto other = (OpmTipoGasto) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmTipoGasto[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
