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
@Table(name = "opm_detalle_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmDetalleVenta.findAll", query = "SELECT o FROM OpmDetalleVenta o"),
    @NamedQuery(name = "OpmDetalleVenta.findByNmCodigo", query = "SELECT o FROM OpmDetalleVenta o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmDetalleVenta.findByNmCantidad", query = "SELECT o FROM OpmDetalleVenta o WHERE o.nmCantidad = :nmCantidad"),
    @NamedQuery(name = "OpmDetalleVenta.findByNmPrecio", query = "SELECT o FROM OpmDetalleVenta o WHERE o.nmPrecio = :nmPrecio")})
public class OpmDetalleVenta implements Serializable {
    @Basic(optional = false)
    @Column(name = "NM_PRECIO")
    private double nmPrecio;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NM_CANTIDAD")
    private int nmCantidad;
    @JoinColumn(name = "NM_VENTA", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmVenta nmVenta;
    @JoinColumn(name = "NM_PRODUCTO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmProducto nmProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmDetalle")
    private List<OpmDevolucion> opmDevolucionList;

    public OpmDetalleVenta() {
    }

    public OpmDetalleVenta(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmDetalleVenta(Integer nmCodigo, int nmCantidad, int nmPrecio) {
        this.nmCodigo = nmCodigo;
        this.nmCantidad = nmCantidad;
        this.nmPrecio = nmPrecio;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public int getNmCantidad() {
        return nmCantidad;
    }

    public void setNmCantidad(int nmCantidad) {
        this.nmCantidad = nmCantidad;
    }

    public double getNmPrecio() {
        return nmPrecio;
    }

    public void setNmPrecio(int nmPrecio) {
        this.nmPrecio = nmPrecio;
    }

    public OpmVenta getNmVenta() {
        return nmVenta;
    }

    public void setNmVenta(OpmVenta nmVenta) {
        this.nmVenta = nmVenta;
    }

    public OpmProducto getNmProducto() {
        return nmProducto;
    }

    public void setNmProducto(OpmProducto nmProducto) {
        this.nmProducto = nmProducto;
    }

    @XmlTransient
    public List<OpmDevolucion> getOpmDevolucionList() {
        return opmDevolucionList;
    }

    public void setOpmDevolucionList(List<OpmDevolucion> opmDevolucionList) {
        this.opmDevolucionList = opmDevolucionList;
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
        if (!(object instanceof OpmDetalleVenta)) {
            return false;
        }
        OpmDetalleVenta other = (OpmDetalleVenta) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmDetalleVenta[ nmCodigo=" + nmCodigo + " ]";
    }

    public void setNmPrecio(double nmPrecio) {
        this.nmPrecio = nmPrecio;
    }
}
