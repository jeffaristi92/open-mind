/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmVenta.findAll", query = "SELECT o FROM OpmVenta o"),
    @NamedQuery(name = "OpmVenta.findByNmCodigo", query = "SELECT o FROM OpmVenta o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmVenta.findByDaFecha", query = "SELECT o FROM OpmVenta o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmVenta.findByNmFlete", query = "SELECT o FROM OpmVenta o WHERE o.nmFlete = :nmFlete")})
public class OpmVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "DA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date daFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "NM_FLETE")
    private BigDecimal nmFlete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmVenta")
    private List<OpmAbono> opmAbonoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmVenta")
    private List<OpmDetalleVenta> opmDetalleVentaList;
    @JoinColumn(name = "NM_CLIENTE", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmUsuario nmCliente;
    @JoinColumn(name = "NM_PUNTO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmPuntoVenta nmPunto;

    public OpmVenta() {
    }

    public OpmVenta(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmVenta(Integer nmCodigo, Date daFecha, BigDecimal nmFlete) {
        this.nmCodigo = nmCodigo;
        this.daFecha = daFecha;
        this.nmFlete = nmFlete;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public Date getDaFecha() {
        return daFecha;
    }

    public void setDaFecha(Date daFecha) {
        this.daFecha = daFecha;
    }

    public BigDecimal getNmFlete() {
        return nmFlete;
    }

    public void setNmFlete(BigDecimal nmFlete) {
        this.nmFlete = nmFlete;
    }

    @XmlTransient
    public List<OpmAbono> getOpmAbonoList() {
        return opmAbonoList;
    }

    public void setOpmAbonoList(List<OpmAbono> opmAbonoList) {
        this.opmAbonoList = opmAbonoList;
    }

    @XmlTransient
    public List<OpmDetalleVenta> getOpmDetalleVentaList() {
        return opmDetalleVentaList;
    }

    public void setOpmDetalleVentaList(List<OpmDetalleVenta> opmDetalleVentaList) {
        this.opmDetalleVentaList = opmDetalleVentaList;
    }

    public OpmUsuario getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(OpmUsuario nmCliente) {
        this.nmCliente = nmCliente;
    }

    public OpmPuntoVenta getNmPunto() {
        return nmPunto;
    }

    public void setNmPunto(OpmPuntoVenta nmPunto) {
        this.nmPunto = nmPunto;
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
        if (!(object instanceof OpmVenta)) {
            return false;
        }
        OpmVenta other = (OpmVenta) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmVenta[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
