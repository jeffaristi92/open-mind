/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_remision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmRemision.findAll", query = "SELECT o FROM OpmRemision o"),
    @NamedQuery(name = "OpmRemision.findByNmCodigo", query = "SELECT o FROM OpmRemision o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmRemision.findByDaFecha", query = "SELECT o FROM OpmRemision o WHERE o.daFecha = :daFecha")})
public class OpmRemision implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmRemision")
    private List<OpmDetalleRemision> opmDetalleRemisionList;
    @JoinColumn(name = "NM_EMPLEADO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmUsuario nmEmpleado;
    @JoinColumn(name = "NM_PUNTO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmPuntoVenta nmPunto;

    public OpmRemision() {
    }

    public OpmRemision(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmRemision(Integer nmCodigo, Date daFecha) {
        this.nmCodigo = nmCodigo;
        this.daFecha = daFecha;
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

    @XmlTransient
    public List<OpmDetalleRemision> getOpmDetalleRemisionList() {
        return opmDetalleRemisionList;
    }

    public void setOpmDetalleRemisionList(List<OpmDetalleRemision> opmDetalleRemisionList) {
        this.opmDetalleRemisionList = opmDetalleRemisionList;
    }

    public OpmUsuario getNmEmpleado() {
        return nmEmpleado;
    }

    public void setNmEmpleado(OpmUsuario nmEmpleado) {
        this.nmEmpleado = nmEmpleado;
    }

    public OpmPuntoVenta getNmPunto() {
        return nmPunto;
    }

    public void setNmPunto(OpmPuntoVenta nmPunto) {
        this.nmPunto = nmPunto;
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
        if (!(object instanceof OpmRemision)) {
            return false;
        }
        OpmRemision other = (OpmRemision) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmRemision[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
