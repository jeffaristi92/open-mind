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
@Table(name = "opm_referencia_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmReferenciaProducto.findAll", query = "SELECT o FROM OpmReferenciaProducto o"),
    @NamedQuery(name = "OpmReferenciaProducto.findByNvCodigo", query = "SELECT o FROM OpmReferenciaProducto o WHERE o.nvCodigo = :nvCodigo"),
    @NamedQuery(name = "OpmReferenciaProducto.findByNvTalla", query = "SELECT o FROM OpmReferenciaProducto o WHERE o.nvTalla = :nvTalla"),
    @NamedQuery(name = "OpmReferenciaProducto.findByNvColor", query = "SELECT o FROM OpmReferenciaProducto o WHERE o.nvColor = :nvColor"),
    @NamedQuery(name = "OpmReferenciaProducto.findByNvGenero", query = "SELECT o FROM OpmReferenciaProducto o WHERE o.nvGenero = :nvGenero")})
public class OpmReferenciaProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NV_CODIGO")
    private String nvCodigo;
    @Column(name = "NV_TALLA")
    private String nvTalla;
    @Column(name = "NV_COLOR")
    private String nvColor;
    @Column(name = "NV_GENERO")
    private Character nvGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvReferencia")
    private List<OpmInventarioPunto> opmInventarioPuntoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvReferencia")
    private List<OpmDetalleLote> opmDetalleLoteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvReferencia")
    private List<OpmDetalleVenta> opmDetalleVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvReferencia")
    private List<OpmInventario> opmInventarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvReferencia")
    private List<OpmDetalleRemision> opmDetalleRemisionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nvReferencia")
    private List<OpmDetalleTraslado> opmDetalleTrasladoList;
    @JoinColumn(name = "NM_PRODUCTO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmProducto nmProducto;

    public OpmReferenciaProducto() {
    }

    public OpmReferenciaProducto(String nvCodigo) {
        this.nvCodigo = nvCodigo;
    }

    public String getNvCodigo() {
        return nvCodigo;
    }

    public void setNvCodigo(String nvCodigo) {
        this.nvCodigo = nvCodigo;
    }

    public String getNvTalla() {
        return nvTalla;
    }

    public void setNvTalla(String nvTalla) {
        this.nvTalla = nvTalla;
    }

    public String getNvColor() {
        return nvColor;
    }

    public void setNvColor(String nvColor) {
        this.nvColor = nvColor;
    }

    public Character getNvGenero() {
        return nvGenero;
    }

    public void setNvGenero(Character nvGenero) {
        this.nvGenero = nvGenero;
    }

    @XmlTransient
    public List<OpmInventarioPunto> getOpmInventarioPuntoList() {
        return opmInventarioPuntoList;
    }

    public void setOpmInventarioPuntoList(List<OpmInventarioPunto> opmInventarioPuntoList) {
        this.opmInventarioPuntoList = opmInventarioPuntoList;
    }

    @XmlTransient
    public List<OpmDetalleLote> getOpmDetalleLoteList() {
        return opmDetalleLoteList;
    }

    public void setOpmDetalleLoteList(List<OpmDetalleLote> opmDetalleLoteList) {
        this.opmDetalleLoteList = opmDetalleLoteList;
    }

    @XmlTransient
    public List<OpmDetalleVenta> getOpmDetalleVentaList() {
        return opmDetalleVentaList;
    }

    public void setOpmDetalleVentaList(List<OpmDetalleVenta> opmDetalleVentaList) {
        this.opmDetalleVentaList = opmDetalleVentaList;
    }

    @XmlTransient
    public List<OpmInventario> getOpmInventarioList() {
        return opmInventarioList;
    }

    public void setOpmInventarioList(List<OpmInventario> opmInventarioList) {
        this.opmInventarioList = opmInventarioList;
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

    public OpmProducto getNmProducto() {
        return nmProducto;
    }

    public void setNmProducto(OpmProducto nmProducto) {
        this.nmProducto = nmProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nvCodigo != null ? nvCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpmReferenciaProducto)) {
            return false;
        }
        OpmReferenciaProducto other = (OpmReferenciaProducto) object;
        if ((this.nvCodigo == null && other.nvCodigo != null) || (this.nvCodigo != null && !this.nvCodigo.equals(other.nvCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmReferenciaProducto[ nvCodigo=" + nvCodigo + " ]";
    }
    
}
