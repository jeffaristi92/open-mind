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
@Table(name = "opm_devolucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmDevolucion.findAll", query = "SELECT o FROM OpmDevolucion o"),
    @NamedQuery(name = "OpmDevolucion.findByNmCodigo", query = "SELECT o FROM OpmDevolucion o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmDevolucion.findByDaFecha", query = "SELECT o FROM OpmDevolucion o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmDevolucion.findByNmCantidad", query = "SELECT o FROM OpmDevolucion o WHERE o.nmCantidad = :nmCantidad")})
public class OpmDevolucion implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "NM_CANTIDAD")
    private int nmCantidad;
    @JoinColumn(name = "NM_DETALLE", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmDetalleVenta nmDetalle;

    public OpmDevolucion() {
    }

    public OpmDevolucion(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmDevolucion(Integer nmCodigo, Date daFecha, int nmCantidad) {
        this.nmCodigo = nmCodigo;
        this.daFecha = daFecha;
        this.nmCantidad = nmCantidad;
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

    public int getNmCantidad() {
        return nmCantidad;
    }

    public void setNmCantidad(int nmCantidad) {
        this.nmCantidad = nmCantidad;
    }

    public OpmDetalleVenta getNmDetalle() {
        return nmDetalle;
    }

    public void setNmDetalle(OpmDetalleVenta nmDetalle) {
        this.nmDetalle = nmDetalle;
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
        if (!(object instanceof OpmDevolucion)) {
            return false;
        }
        OpmDevolucion other = (OpmDevolucion) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmDevolucion[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
