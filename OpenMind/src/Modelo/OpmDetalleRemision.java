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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_detalle_remision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmDetalleRemision.findAll", query = "SELECT o FROM OpmDetalleRemision o"),
    @NamedQuery(name = "OpmDetalleRemision.findByNmCodigo", query = "SELECT o FROM OpmDetalleRemision o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmDetalleRemision.findByNmCantidad", query = "SELECT o FROM OpmDetalleRemision o WHERE o.nmCantidad = :nmCantidad")})
public class OpmDetalleRemision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NM_CANTIDAD")
    private int nmCantidad;
    @JoinColumn(name = "NM_REMISION", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmRemision nmRemision;
    @JoinColumn(name = "NV_REFERENCIA", referencedColumnName = "NV_CODIGO")
    @ManyToOne(optional = false)
    private OpmReferenciaProducto nvReferencia;

    public OpmDetalleRemision() {
    }

    public OpmDetalleRemision(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmDetalleRemision(Integer nmCodigo, int nmCantidad) {
        this.nmCodigo = nmCodigo;
        this.nmCantidad = nmCantidad;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public int getNmCantidad() {
        return nmCantidad;
    }

    public void setNmCantidad(int nmCantidad) {
        this.nmCantidad = nmCantidad;
    }

    public OpmRemision getNmRemision() {
        return nmRemision;
    }

    public void setNmRemision(OpmRemision nmRemision) {
        this.nmRemision = nmRemision;
    }

    public OpmReferenciaProducto getNvReferencia() {
        return nvReferencia;
    }

    public void setNvReferencia(OpmReferenciaProducto nvReferencia) {
        this.nvReferencia = nvReferencia;
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
        if (!(object instanceof OpmDetalleRemision)) {
            return false;
        }
        OpmDetalleRemision other = (OpmDetalleRemision) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmDetalleRemision[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
