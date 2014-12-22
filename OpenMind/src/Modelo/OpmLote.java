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
@Table(name = "opm_lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmLote.findAll", query = "SELECT o FROM OpmLote o"),
    @NamedQuery(name = "OpmLote.findByNmCodigo", query = "SELECT o FROM OpmLote o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmLote.findByNvNumero", query = "SELECT o FROM OpmLote o WHERE o.nvNumero = :nvNumero"),
    @NamedQuery(name = "OpmLote.findByDaFecha", query = "SELECT o FROM OpmLote o WHERE o.daFecha = :daFecha")})
public class OpmLote implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmLote")
    private List<OpmDetalleLote> opmDetalleLoteList;
    @JoinColumn(name = "NM_EMPLEADO", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmUsuario nmEmpleado;

    public OpmLote() {
    }

    public OpmLote(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmLote(Integer nmCodigo, String nvNumero, Date daFecha) {
        this.nmCodigo = nmCodigo;
        this.nvNumero = nvNumero;
        this.daFecha = daFecha;
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

    @XmlTransient
    public List<OpmDetalleLote> getOpmDetalleLoteList() {
        return opmDetalleLoteList;
    }

    public void setOpmDetalleLoteList(List<OpmDetalleLote> opmDetalleLoteList) {
        this.opmDetalleLoteList = opmDetalleLoteList;
    }

    public OpmUsuario getNmEmpleado() {
        return nmEmpleado;
    }

    public void setNmEmpleado(OpmUsuario nmEmpleado) {
        this.nmEmpleado = nmEmpleado;
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
        if (!(object instanceof OpmLote)) {
            return false;
        }
        OpmLote other = (OpmLote) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmLote[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
