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
@Table(name = "opm_punto_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmPuntoVenta.findAll", query = "SELECT o FROM OpmPuntoVenta o"),
    @NamedQuery(name = "OpmPuntoVenta.findByNmCodigo", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmPuntoVenta.findByNvNombre", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nvNombre = :nvNombre"),
    @NamedQuery(name = "OpmPuntoVenta.findByNvDireccion", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nvDireccion = :nvDireccion"),
    @NamedQuery(name = "OpmPuntoVenta.findByNvTel", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nvTel = :nvTel"),
    @NamedQuery(name = "OpmPuntoVenta.findByNmAdministrador", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nmAdministrador = :nmAdministrador"),
    @NamedQuery(name = "OpmPuntoVenta.findByNmFabrica", query = "SELECT o FROM OpmPuntoVenta o WHERE o.nmFabrica = :nmFabrica")})
public class OpmPuntoVenta implements Serializable {
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
    @Column(name = "NV_DIRECCION")
    private String nvDireccion;
    @Basic(optional = false)
    @Column(name = "NV_TEL")
    private String nvTel;
    @Basic(optional = false)
    @Column(name = "NM_ADMINISTRADOR")
    private int nmAdministrador;
    @Basic(optional = false)
    @Column(name = "NM_FABRICA")
    private int nmFabrica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmOrigen")
    private List<OpmTraslado> opmTrasladoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmPuntoVenta")
    private List<OpmGastos> opmGastosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmPunto")
    private List<OpmVenta> opmVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmPuntoVenta")
    private List<OpmTransaccion> opmTransaccionList;

    public OpmPuntoVenta() {
    }

    public OpmPuntoVenta(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmPuntoVenta(Integer nmCodigo, String nvNombre, String nvDireccion, String nvTel, int nmAdministrador, int nmFabrica) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
        this.nvDireccion = nvDireccion;
        this.nvTel = nvTel;
        this.nmAdministrador = nmAdministrador;
        this.nmFabrica = nmFabrica;
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

    public int getNmAdministrador() {
        return nmAdministrador;
    }

    public void setNmAdministrador(int nmAdministrador) {
        this.nmAdministrador = nmAdministrador;
    }

    public int getNmFabrica() {
        return nmFabrica;
    }

    public void setNmFabrica(int nmFabrica) {
        this.nmFabrica = nmFabrica;
    }

    @XmlTransient
    public List<OpmTraslado> getOpmTrasladoList() {
        return opmTrasladoList;
    }

    public void setOpmTrasladoList(List<OpmTraslado> opmTrasladoList) {
        this.opmTrasladoList = opmTrasladoList;
    }

    @XmlTransient
    public List<OpmGastos> getOpmGastosList() {
        return opmGastosList;
    }

    public void setOpmGastosList(List<OpmGastos> opmGastosList) {
        this.opmGastosList = opmGastosList;
    }

    @XmlTransient
    public List<OpmVenta> getOpmVentaList() {
        return opmVentaList;
    }

    public void setOpmVentaList(List<OpmVenta> opmVentaList) {
        this.opmVentaList = opmVentaList;
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
        return "Modelo.OpmPuntoVenta[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
