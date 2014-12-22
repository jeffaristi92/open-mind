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
@Table(name = "opm_transaccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmTransaccion.findAll", query = "SELECT o FROM OpmTransaccion o"),
    @NamedQuery(name = "OpmTransaccion.findByNmCodigo", query = "SELECT o FROM OpmTransaccion o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmTransaccion.findByBtFlujo", query = "SELECT o FROM OpmTransaccion o WHERE o.btFlujo = :btFlujo"),
    @NamedQuery(name = "OpmTransaccion.findByNmValor", query = "SELECT o FROM OpmTransaccion o WHERE o.nmValor = :nmValor"),
    @NamedQuery(name = "OpmTransaccion.findByDaFecha", query = "SELECT o FROM OpmTransaccion o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmTransaccion.findByNvNumero", query = "SELECT o FROM OpmTransaccion o WHERE o.nvNumero = :nvNumero"),
    @NamedQuery(name = "OpmTransaccion.findByNvTabla", query = "SELECT o FROM OpmTransaccion o WHERE o.nvTabla = :nvTabla")})
public class OpmTransaccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "BT_FLUJO")
    private boolean btFlujo;
    @Basic(optional = false)
    @Column(name = "NM_VALOR")
    private double nmValor;
    @Basic(optional = false)
    @Column(name = "DA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date daFecha;
    @Basic(optional = false)
    @Column(name = "NV_NUMERO")
    private String nvNumero;
    @Basic(optional = false)
    @Column(name = "NV_TABLA")
    private String nvTabla;
    @JoinColumn(name = "NM_USUARIO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmUsuario nmUsuario;
    @JoinColumn(name = "NM_PUNTO_VENTA", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmPuntoVenta nmPuntoVenta;
    @JoinColumn(name = "NM_CUENTA", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmCuenta nmCuenta;

    public OpmTransaccion() {
    }

    public OpmTransaccion(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmTransaccion(Integer nmCodigo, boolean btFlujo, double nmValor, Date daFecha, String nvNumero, String nvTabla) {
        this.nmCodigo = nmCodigo;
        this.btFlujo = btFlujo;
        this.nmValor = nmValor;
        this.daFecha = daFecha;
        this.nvNumero = nvNumero;
        this.nvTabla = nvTabla;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public boolean getBtFlujo() {
        return btFlujo;
    }

    public void setBtFlujo(boolean btFlujo) {
        this.btFlujo = btFlujo;
    }

    public double getNmValor() {
        return nmValor;
    }

    public void setNmValor(double nmValor) {
        this.nmValor = nmValor;
    }

    public Date getDaFecha() {
        return daFecha;
    }

    public void setDaFecha(Date daFecha) {
        this.daFecha = daFecha;
    }

    public String getNvNumero() {
        return nvNumero;
    }

    public void setNvNumero(String nvNumero) {
        this.nvNumero = nvNumero;
    }

    public String getNvTabla() {
        return nvTabla;
    }

    public void setNvTabla(String nvTabla) {
        this.nvTabla = nvTabla;
    }

    public OpmUsuario getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(OpmUsuario nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public OpmPuntoVenta getNmPuntoVenta() {
        return nmPuntoVenta;
    }

    public void setNmPuntoVenta(OpmPuntoVenta nmPuntoVenta) {
        this.nmPuntoVenta = nmPuntoVenta;
    }

    public OpmCuenta getNmCuenta() {
        return nmCuenta;
    }

    public void setNmCuenta(OpmCuenta nmCuenta) {
        this.nmCuenta = nmCuenta;
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
        if (!(object instanceof OpmTransaccion)) {
            return false;
        }
        OpmTransaccion other = (OpmTransaccion) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmTransaccion[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
