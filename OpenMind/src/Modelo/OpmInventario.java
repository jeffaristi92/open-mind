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
@Table(name = "opm_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmInventario.findAll", query = "SELECT o FROM OpmInventario o"),
    @NamedQuery(name = "OpmInventario.findByNmCodigo", query = "SELECT o FROM OpmInventario o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmInventario.findByNmCantidad", query = "SELECT o FROM OpmInventario o WHERE o.nmCantidad = :nmCantidad")})
public class OpmInventario implements Serializable {
    @Column(name = "NM_CANTIDAD")
    private Integer nmCantidad;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @JoinColumn(name = "NM_PRODUCTO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmProducto nmProducto;

    public OpmInventario() {
    }

    public OpmInventario(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
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

    public OpmProducto getNmProducto() {
        return nmProducto;
    }

    public void setNmProducto(OpmProducto nmProducto) {
        this.nmProducto = nmProducto;
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
        if (!(object instanceof OpmInventario)) {
            return false;
        }
        OpmInventario other = (OpmInventario) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmInventario[ nmCodigo=" + nmCodigo + " ]";
    }

    public void setNmCantidad(Integer nmCantidad) {
        this.nmCantidad = nmCantidad;
    }    
}
