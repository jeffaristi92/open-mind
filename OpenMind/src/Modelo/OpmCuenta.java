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
@Table(name = "opm_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmCuenta.findAll", query = "SELECT o FROM OpmCuenta o"),
    @NamedQuery(name = "OpmCuenta.findByNmCodigo", query = "SELECT o FROM OpmCuenta o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmCuenta.findByNvNombre", query = "SELECT o FROM OpmCuenta o WHERE o.nvNombre = :nvNombre")})
public class OpmCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;
    @JoinColumn(name = "NM_GRUPO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmGrupo nmGrupo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCuenta")
    private List<OpmTransaccion> opmTransaccionList;

    public OpmCuenta() {
    }

    public OpmCuenta(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmCuenta(Integer nmCodigo, String nvNombre) {
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

    public OpmGrupo getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(OpmGrupo nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    @XmlTransient
    public List<OpmTransaccion> getOpmTransaccionList() {
        return opmTransaccionList;
    }

    public void setOpmTransaccionList(List<OpmTransaccion> opmTransaccionList) {
        this.opmTransaccionList = opmTransaccionList;
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
        if (!(object instanceof OpmCuenta)) {
            return false;
        }
        OpmCuenta other = (OpmCuenta) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmCuenta[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
