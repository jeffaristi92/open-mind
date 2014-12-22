/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmGastos.findAll", query = "SELECT o FROM OpmGastos o"),
    @NamedQuery(name = "OpmGastos.findByNmCodigo", query = "SELECT o FROM OpmGastos o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmGastos.findByNvNumero", query = "SELECT o FROM OpmGastos o WHERE o.nvNumero = :nvNumero"),
    @NamedQuery(name = "OpmGastos.findByNvDescripcion", query = "SELECT o FROM OpmGastos o WHERE o.nvDescripcion = :nvDescripcion"),
    @NamedQuery(name = "OpmGastos.findByDaFecha", query = "SELECT o FROM OpmGastos o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmGastos.findByNmValor", query = "SELECT o FROM OpmGastos o WHERE o.nmValor = :nmValor")})
public class OpmGastos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NUMERO")
    private String nvNumero;
    @Basic(optional = false)
    @Column(name = "NV_DESCRIPCION")
    private String nvDescripcion;
    @Basic(optional = false)
    @Column(name = "DA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date daFecha;
    @Basic(optional = false)
    @Column(name = "NM_VALOR")
    private double nmValor;
    @JoinColumn(name = "NM_TIPO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmTipoGasto nmTipo;
    @JoinColumn(name = "NM_PUNTO_VENTA", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmPuntoVenta nmPuntoVenta;

    public OpmGastos() {
    }

    public OpmGastos(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmGastos(Integer nmCodigo, String nvNumero, String nvDescripcion, Date daFecha, double nmValor) {
        this.nmCodigo = nmCodigo;
        this.nvNumero = nvNumero;
        this.nvDescripcion = nvDescripcion;
        this.daFecha = daFecha;
        this.nmValor = nmValor;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public String getNvNumero() {
        return nvNumero;
    }

    public void setNvNumero(String nvNumero) {
        this.nvNumero = nvNumero;
    }

    public String getNvDescripcion() {
        return nvDescripcion;
    }

    public void setNvDescripcion(String nvDescripcion) {
        this.nvDescripcion = nvDescripcion;
    }

    public Date getDaFecha() {
        return daFecha;
    }

    public void setDaFecha(Date daFecha) {
        this.daFecha = daFecha;
    }

    public double getNmValor() {
        return nmValor;
    }

    public void setNmValor(double nmValor) {
        this.nmValor = nmValor;
    }

    public OpmTipoGasto getNmTipo() {
        return nmTipo;
    }

    public void setNmTipo(OpmTipoGasto nmTipo) {
        this.nmTipo = nmTipo;
    }

    public OpmPuntoVenta getNmPuntoVenta() {
        return nmPuntoVenta;
    }

    public void setNmPuntoVenta(OpmPuntoVenta nmPuntoVenta) {
        this.nmPuntoVenta = nmPuntoVenta;
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
        if (!(object instanceof OpmGastos)) {
            return false;
        }
        OpmGastos other = (OpmGastos) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmGastos[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
