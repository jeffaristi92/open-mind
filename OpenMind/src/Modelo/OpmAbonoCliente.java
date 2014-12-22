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
@Table(name = "opm_abono_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmAbonoCliente.findAll", query = "SELECT o FROM OpmAbonoCliente o"),
    @NamedQuery(name = "OpmAbonoCliente.findByNmCodigo", query = "SELECT o FROM OpmAbonoCliente o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmAbonoCliente.findByNvNumero", query = "SELECT o FROM OpmAbonoCliente o WHERE o.nvNumero = :nvNumero"),
    @NamedQuery(name = "OpmAbonoCliente.findByDaFecha", query = "SELECT o FROM OpmAbonoCliente o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmAbonoCliente.findByNmValor", query = "SELECT o FROM OpmAbonoCliente o WHERE o.nmValor = :nmValor")})
public class OpmAbonoCliente implements Serializable {
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
    @Column(name = "DA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date daFecha;
    @Basic(optional = false)
    @Column(name = "NM_VALOR")
    private double nmValor;
    @JoinColumn(name = "NM_VENTA", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmVenta nmVenta;

    public OpmAbonoCliente() {
    }

    public OpmAbonoCliente(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmAbonoCliente(Integer nmCodigo, String nvNumero, Date daFecha, double nmValor) {
        this.nmCodigo = nmCodigo;
        this.nvNumero = nvNumero;
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

    public OpmVenta getNmVenta() {
        return nmVenta;
    }

    public void setNmVenta(OpmVenta nmVenta) {
        this.nmVenta = nmVenta;
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
        if (!(object instanceof OpmAbonoCliente)) {
            return false;
        }
        OpmAbonoCliente other = (OpmAbonoCliente) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmAbonoCliente[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
