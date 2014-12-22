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
@Table(name = "opm_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmProducto.findAll", query = "SELECT o FROM OpmProducto o"),
    @NamedQuery(name = "OpmProducto.findByNmCodigo", query = "SELECT o FROM OpmProducto o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmProducto.findByNvNombre", query = "SELECT o FROM OpmProducto o WHERE o.nvNombre = :nvNombre"),
    @NamedQuery(name = "OpmProducto.findByNvDescripcion", query = "SELECT o FROM OpmProducto o WHERE o.nvDescripcion = :nvDescripcion"),
    @NamedQuery(name = "OpmProducto.findByNmCostoPp", query = "SELECT o FROM OpmProducto o WHERE o.nmCostoPp = :nmCostoPp"),
    @NamedQuery(name = "OpmProducto.findByNmValorPv", query = "SELECT o FROM OpmProducto o WHERE o.nmValorPv = :nmValorPv"),
    @NamedQuery(name = "OpmProducto.findByNmValorPm", query = "SELECT o FROM OpmProducto o WHERE o.nmValorPm = :nmValorPm"),
    @NamedQuery(name = "OpmProducto.findByNmValorVs", query = "SELECT o FROM OpmProducto o WHERE o.nmValorVs = :nmValorVs"),
    @NamedQuery(name = "OpmProducto.findByBtActivo", query = "SELECT o FROM OpmProducto o WHERE o.btActivo = :btActivo")})
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
    @Basic(optional = false)
    @Column(name = "NM_COSTO_PP")
    private double nmCostoPp;
    @Basic(optional = false)
    @Column(name = "NM_VALOR_PV")
    private double nmValorPv;
    @Basic(optional = false)
    @Column(name = "NM_VALOR_PM")
    private double nmValorPm;
    @Basic(optional = false)
    @Column(name = "NM_VALOR_VS")
    private double nmValorVs;
    @Basic(optional = false)
    @Column(name = "BT_ACTIVO")
    private boolean btActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmProducto")
    private List<OpmReferenciaProducto> opmReferenciaProductoList;

    public OpmProducto() {
    }

    public OpmProducto(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmProducto(Integer nmCodigo, String nvNombre, String nvDescripcion, double nmCostoPp, double nmValorPv, double nmValorPm, double nmValorVs, boolean btActivo) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
        this.nvDescripcion = nvDescripcion;
        this.nmCostoPp = nmCostoPp;
        this.nmValorPv = nmValorPv;
        this.nmValorPm = nmValorPm;
        this.nmValorVs = nmValorVs;
        this.btActivo = btActivo;
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

    public double getNmCostoPp() {
        return nmCostoPp;
    }

    public void setNmCostoPp(double nmCostoPp) {
        this.nmCostoPp = nmCostoPp;
    }

    public double getNmValorPv() {
        return nmValorPv;
    }

    public void setNmValorPv(double nmValorPv) {
        this.nmValorPv = nmValorPv;
    }

    public double getNmValorPm() {
        return nmValorPm;
    }

    public void setNmValorPm(double nmValorPm) {
        this.nmValorPm = nmValorPm;
    }

    public double getNmValorVs() {
        return nmValorVs;
    }

    public void setNmValorVs(double nmValorVs) {
        this.nmValorVs = nmValorVs;
    }

    public boolean getBtActivo() {
        return btActivo;
    }

    public void setBtActivo(boolean btActivo) {
        this.btActivo = btActivo;
    }

    @XmlTransient
    public List<OpmReferenciaProducto> getOpmReferenciaProductoList() {
        return opmReferenciaProductoList;
    }

    public void setOpmReferenciaProductoList(List<OpmReferenciaProducto> opmReferenciaProductoList) {
        this.opmReferenciaProductoList = opmReferenciaProductoList;
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
        return "Modelo.OpmProducto[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
