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
@Table(name = "opm_punto_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmPuntoVenta.findAll", query = "SELECT o FROM OpmPuntoVenta o"),
    @NamedQuery(name = "OpmPuntoVenta.findByNmCodigo", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmPuntoVenta.findByNvDireccion", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nvDireccion = :nvDireccion"),
    @NamedQuery(name = "OpmPuntoVenta.findByNvTel", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nvTel = :nvTel")})
public class OpmPuntoVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_DIRECCION")
    private String nvDireccion;
    @Basic(optional = false)
    @Column(name = "NV_TEL")
    private String nvTel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmOrigen")
    private List<OpmTraslado> opmTrasladoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmPuntoVenta")
    private List<OpmInventarioPunto> opmInventarioPuntoList;
    @JoinColumn(name = "NM_ADMINISTRADOR", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmUsuario nmAdministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmPunto")
    private List<OpmVenta> opmVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmPunto")
    private List<OpmRemision> opmRemisionList;

    public OpmPuntoVenta() {
    }

    public OpmPuntoVenta(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmPuntoVenta(Integer nmCodigo, String nvDireccion, String nvTel) {
        this.nmCodigo = nmCodigo;
        this.nvDireccion = nvDireccion;
        this.nvTel = nvTel;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public String getNvDireccion() {
        return nvDireccion;
    }

    public void setNvDireccion(String nvDireccion) {
        this.nvDireccion = nvDireccion;
    }

    public String getNvTel() {
        return nvTel;
    }

    public void setNvTel(String nvTel) {
        this.nvTel = nvTel;
    }

    @XmlTransient
    public List<OpmTraslado> getOpmTrasladoList() {
        return opmTrasladoList;
    }

    public void setOpmTrasladoList(List<OpmTraslado> opmTrasladoList) {
        this.opmTrasladoList = opmTrasladoList;
    }

    @XmlTransient
    public List<OpmInventarioPunto> getOpmInventarioPuntoList() {
        return opmInventarioPuntoList;
    }

    public void setOpmInventarioPuntoList(List<OpmInventarioPunto> opmInventarioPuntoList) {
        this.opmInventarioPuntoList = opmInventarioPuntoList;
    }

    public OpmUsuario getNmAdministrador() {
        return nmAdministrador;
    }

    public void setNmAdministrador(OpmUsuario nmAdministrador) {
        this.nmAdministrador = nmAdministrador;
    }

    @XmlTransient
    public List<OpmVenta> getOpmVentaList() {
        return opmVentaList;
    }

    public void setOpmVentaList(List<OpmVenta> opmVentaList) {
        this.opmVentaList = opmVentaList;
    }

    @XmlTransient
    public List<OpmRemision> getOpmRemisionList() {
        return opmRemisionList;
    }

    public void setOpmRemisionList(List<OpmRemision> opmRemisionList) {
        this.opmRemisionList = opmRemisionList;
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
        if (!(object instanceof OpmPuntoVenta)) {
            return false;
        }
        OpmPuntoVenta other = (OpmPuntoVenta) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmPuntoVenta[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
