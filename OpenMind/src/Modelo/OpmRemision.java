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
@Table(name = "opm_remision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmRemision.findAll", query = "SELECT o FROM OpmRemision o"),
    @NamedQuery(name = "OpmRemision.findByNmCodigo", query = "SELECT o FROM OpmRemision o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmRemision.findByNvNumero", query = "SELECT o FROM OpmRemision o WHERE o.nvNumero = :nvNumero"),
    @NamedQuery(name = "OpmRemision.findByDaFecha", query = "SELECT o FROM OpmRemision o WHERE o.daFecha = :daFecha"),
    @NamedQuery(name = "OpmRemision.findByNmEmpleado", query = "SELECT o FROM OpmRemision o WHERE o.nmEmpleado = :nmEmpleado"),
    @NamedQuery(name = "OpmRemision.findByNmPunto", query = "SELECT o FROM OpmRemision o WHERE o.nmPunto = :nmPunto")})
public class OpmRemision implements Serializable {
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
    @Column(name = "NM_EMPLEADO")
    private int nmEmpleado;
    @Basic(optional = false)
    @Column(name = "NM_PUNTO")
    private int nmPunto;

    public OpmRemision() {
    }

    public OpmRemision(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmRemision(Integer nmCodigo, String nvNumero, Date daFecha, int nmEmpleado, int nmPunto) {
        this.nmCodigo = nmCodigo;
        this.nvNumero = nvNumero;
        this.daFecha = daFecha;
        this.nmEmpleado = nmEmpleado;
        this.nmPunto = nmPunto;
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

    public int getNmEmpleado() {
        return nmEmpleado;
    }

    public void setNmEmpleado(int nmEmpleado) {
        this.nmEmpleado = nmEmpleado;
    }

    public int getNmPunto() {
        return nmPunto;
    }

    public void setNmPunto(int nmPunto) {
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
        return "Modelo.OpmRemision[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
