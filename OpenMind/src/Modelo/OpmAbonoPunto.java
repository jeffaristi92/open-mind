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
@Table(name = "opm_abono_punto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmAbonoPunto.findAll", query = "SELECT o FROM OpmAbonoPunto o"),
    @NamedQuery(name = "OpmAbonoPunto.findByNmCodigo", query = "SELECT o FROM OpmAbonoPunto o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmAbonoPunto.findByNvNumero", query = "SELECT o FROM OpmAbonoPunto o WHERE o.nvNumero = :nvNumero"),
    @NamedQuery(name = "OpmAbonoPunto.findByNmRemision", query = "SELECT o FROM OpmAbonoPunto o WHERE o.nmRemision = :nmRemision"),
    @NamedQuery(name = "OpmAbonoPunto.findByDaFecha", query = "SELECT o FROM OpmAbonoPunto o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmAbonoPunto.findByNmValor", query = "SELECT o FROM OpmAbonoPunto o WHERE o.nmValor = :nmValor")})
public class OpmAbonoPunto implements Serializable {
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
    @Column(name = "NM_REMISION")
    private int nmRemision;
    @Basic(optional = false)
    @Column(name = "DA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date daFecha;
    @Basic(optional = false)
    @Column(name = "NM_VALOR")
    private double nmValor;

    public OpmAbonoPunto() {
    }

    public OpmAbonoPunto(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmAbonoPunto(Integer nmCodigo, String nvNumero, int nmRemision, Date daFecha, double nmValor) {
        this.nmCodigo = nmCodigo;
        this.nvNumero = nvNumero;
        this.nmRemision = nmRemision;
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

    public int getNmRemision() {
        return nmRemision;
    }

    public void setNmRemision(int nmRemision) {
        this.nmRemision = nmRemision;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nmCodigo != null ? nmCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpmAbonoPunto)) {
            return false;
        }
        OpmAbonoPunto other = (OpmAbonoPunto) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmAbonoPunto[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
