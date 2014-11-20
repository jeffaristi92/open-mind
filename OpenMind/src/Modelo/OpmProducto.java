/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "opm_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmProducto.findAll", query = "SELECT o FROM OpmProducto o"),
    @NamedQuery(name = "OpmProducto.findByNmCodigo", query = "SELECT o FROM OpmProducto o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmProducto.findByNvNombre", query = "SELECT o FROM OpmProducto o WHERE o.nvNombre = :nvNombre"),
    @NamedQuery(name = "OpmProducto.findByNvDescripcion", query = "SELECT o FROM OpmProducto o WHERE o.nvDescripcion = :nvDescripcion"),
    @NamedQuery(name = "OpmProducto.findByNmCosto", query = "SELECT o FROM OpmProducto o WHERE o.nmCosto = :nmCosto"),
    @NamedQuery(name = "OpmProducto.findByNmValor", query = "SELECT o FROM OpmProducto o WHERE o.nmValor = :nmValor")})
public class OpmProducto implements Serializable {
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
    @Column(name = "NV_DESCRIPCION")
    private String nvDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "NM_COSTO")
    private BigDecimal nmCosto;
    @Basic(optional = false)
    @Column(name = "NM_VALOR")
    private int nmValor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmProducto")
    private List<OpmInventario> opmInventarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmProducto")
    private List<OpmInventarioPunto> opmInventarioPuntoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmProducto")
    private List<OpmDetalleRemision> opmDetalleRemisionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmProducto")
    private List<OpmDetalleTraslado> opmDetalleTrasladoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmProducto")
    private List<OpmDetalleVenta> opmDetalleVentaList;

    public OpmProducto() {
    }

    public OpmProducto(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmProducto(Integer nmCodigo, String nvNombre, String nvDescripcion, BigDecimal nmCosto, int nmValor) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
        this.nvDescripcion = nvDescripcion;
        this.nmCosto = nmCosto;
        this.nmValor = nmValor;
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

    public String getNvDescripcion() {
        return nvDescripcion;
    }

    public void setNvDescripcion(String nvDescripcion) {
        this.nvDescripcion = nvDescripcion;
    }

    public BigDecimal getNmCosto() {
        return nmCosto;
    }

    public void setNmCosto(BigDecimal nmCosto) {
        this.nmCosto = nmCosto;
    }

    public int getNmValor() {
        return nmValor;
    }

    public void setNmValor(int nmValor) {
        this.nmValor = nmValor;
    }

    @XmlTransient
    public List<OpmInventario> getOpmInventarioList() {
        return opmInventarioList;
    }

    public void setOpmInventarioList(List<OpmInventario> opmInventarioList) {
        this.opmInventarioList = opmInventarioList;
    }

    @XmlTransient
    public List<OpmInventarioPunto> getOpmInventarioPuntoList() {
        return opmInventarioPuntoList;
    }

    public void setOpmInventarioPuntoList(List<OpmInventarioPunto> opmInventarioPuntoList) {
        this.opmInventarioPuntoList = opmInventarioPuntoList;
    }

    @XmlTransient
    public List<OpmDetalleRemision> getOpmDetalleRemisionList() {
        return opmDetalleRemisionList;
    }

    public void setOpmDetalleRemisionList(List<OpmDetalleRemision> opmDetalleRemisionList) {
        this.opmDetalleRemisionList = opmDetalleRemisionList;
    }

    @XmlTransient
    public List<OpmDetalleTraslado> getOpmDetalleTrasladoList() {
        return opmDetalleTrasladoList;
    }

    public void setOpmDetalleTrasladoList(List<OpmDetalleTraslado> opmDetalleTrasladoList) {
        this.opmDetalleTrasladoList = opmDetalleTrasladoList;
    }

    @XmlTransient
    public List<OpmDetalleVenta> getOpmDetalleVentaList() {
        return opmDetalleVentaList;
    }

    public void setOpmDetalleVentaList(List<OpmDetalleVenta> opmDetalleVentaList) {
        this.opmDetalleVentaList = opmDetalleVentaList;
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
        if (!(object instanceof OpmProducto)) {
            return false;
        }
        OpmProducto other = (OpmProducto) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmProducto[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
