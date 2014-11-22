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
@Table(name = "opm_detalle_lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmDetalleLote.findAll", query = "SELECT o FROM OpmDetalleLote o"),
    @NamedQuery(name = "OpmDetalleLote.findByNmCodigo", query = "SELECT o FROM OpmDetalleLote o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmDetalleLote.findByNmLote", query = "SELECT o FROM OpmDetalleLote o WHERE o.nmLote = :nmLote"),
    @NamedQuery(name = "OpmDetalleLote.findByNmProducto", query = "SELECT o FROM OpmDetalleLote o WHERE o.nmProducto = :nmProducto"),
    @NamedQuery(name = "OpmDetalleLote.findByNmCantidad", query = "SELECT o FROM OpmDetalleLote o WHERE o.nmCantidad = :nmCantidad")})
public class OpmDetalleLote implements Serializable {
    @JoinColumn(name = "NM_PRODUCTO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmProducto nmProducto;
    @JoinColumn(name = "NM_LOTE", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmLote nmLote;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NM_CANTIDAD")
    private int nmCantidad;

    public OpmDetalleLote() {
    }

    public OpmDetalleLote(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmDetalleLote(Integer nmCodigo, OpmLote nmLote, OpmProducto nmProducto, int nmCantidad) {
        this.nmCodigo = nmCodigo;
        this.nmLote = nmLote;
        this.nmProducto = nmProducto;
        this.nmCantidad = nmCantidad;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmLote getNmLote() {
        return nmLote;
    }

    public void setNmLote(OpmLote nmLote) {
        this.nmLote = nmLote;
    }

    public OpmProducto getNmProducto() {
        return nmProducto;
    }

    public void setNmProducto(OpmProducto nmProducto) {
        this.nmProducto = nmProducto;
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
        if (!(object instanceof OpmDetalleLote)) {
            return false;
        }
        OpmDetalleLote other = (OpmDetalleLote) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmDetalleLote[ nmCodigo=" + nmCodigo + " ]";
    }    
}
