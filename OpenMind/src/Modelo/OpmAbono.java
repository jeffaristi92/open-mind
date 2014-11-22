/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "opm_abono")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmAbono.findAll", query = "SELECT o FROM OpmAbono o"),
    @NamedQuery(name = "OpmAbono.findByNmCodigo", query = "SELECT o FROM OpmAbono o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmAbono.findByDaFecha", query = "SELECT o FROM OpmAbono o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmAbono.findByNmValor", query = "SELECT o FROM OpmAbono o WHERE o.nmValor = :nmValor")})
public class OpmAbono implements Serializable {
    @Basic(optional = false)
    @Column(name = "NM_VALOR")
    private double nmValor;
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
    @JoinColumn(name = "NM_VENTA", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmVenta nmVenta;

    public OpmAbono() {
    }

    public OpmAbono(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmAbono(Integer nmCodigo, Date daFecha, double nmValor) {
        this.nmCodigo = nmCodigo;
        this.daFecha = daFecha;
        this.nmValor = nmValor;
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
        if (!(object instanceof OpmAbono)) {
            return false;
        }
        OpmAbono other = (OpmAbono) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmAbono[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
