/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_inventario_punto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmInventarioPunto.findAll", query = "SELECT o FROM OpmInventarioPunto o"),
    @NamedQuery(name = "OpmInventarioPunto.findByNmCodigo", query = "SELECT o FROM OpmInventarioPunto o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmInventarioPunto.findByNmPuntoVenta", query = "SELECT o FROM OpmInventarioPunto o WHERE o.nmPuntoVenta = :nmPuntoVenta"),
    @NamedQuery(name = "OpmInventarioPunto.findByNvReferencia", query = "SELECT o FROM OpmInventarioPunto o WHERE o.nvReferencia = :nvReferencia"),
    @NamedQuery(name = "OpmInventarioPunto.findByNmCantidad", query = "SELECT o FROM OpmInventarioPunto o WHERE o.nmCantidad = :nmCantidad")})
public class OpmInventarioPunto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NM_PUNTO_VENTA")
    private int nmPuntoVenta;
    @Basic(optional = false)
    @Column(name = "NV_REFERENCIA")
    private String nvReferencia;
    @Basic(optional = false)
    @Column(name = "NM_CANTIDAD")
    private int nmCantidad;

    public OpmInventarioPunto() {
    }

    public OpmInventarioPunto(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmInventarioPunto(Integer nmCodigo, int nmPuntoVenta, String nvReferencia, int nmCantidad) {
        this.nmCodigo = nmCodigo;
        this.nmPuntoVenta = nmPuntoVenta;
        this.nvReferencia = nvReferencia;
        this.nmCantidad = nmCantidad;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public int getNmPuntoVenta() {
        return nmPuntoVenta;
    }

    public void setNmPuntoVenta(int nmPuntoVenta) {
        this.nmPuntoVenta = nmPuntoVenta;
    }

    public String getNvReferencia() {
        return nvReferencia;
    }

    public void setNvReferencia(String nvReferencia) {
        this.nvReferencia = nvReferencia;
    }

    public int getNmCantidad() {
        return nmCantidad;
    }

    public void setNmCantidad(int nmCantidad) {
        this.nmCantidad = nmCantidad;
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
        if (!(object instanceof OpmInventarioPunto)) {
            return false;
        }
        OpmInventarioPunto other = (OpmInventarioPunto) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmInventarioPunto[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
