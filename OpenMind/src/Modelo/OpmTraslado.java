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
@Table(name = "opm_traslado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmTraslado.findAll", query = "SELECT o FROM OpmTraslado o"),
    @NamedQuery(name = "OpmTraslado.findByNmCodigo", query = "SELECT o FROM OpmTraslado o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmTraslado.findByNvNumero", query = "SELECT o FROM OpmTraslado o WHERE o.nvNumero = :nvNumero"),
    @NamedQuery(name = "OpmTraslado.findByNmDestino", query = "SELECT o FROM OpmTraslado o WHERE o.nmDestino = :nmDestino"),
    @NamedQuery(name = "OpmTraslado.findByDaFecha", query = "SELECT o FROM OpmTraslado o WHERE o.daFecha = :daFecha")})
public class OpmTraslado implements Serializable {
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
    @Column(name = "NM_DESTINO")
    private int nmDestino;
    @Basic(optional = false)
    @Column(name = "DA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date daFecha;
    @JoinColumn(name = "NM_ORIGEN", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmPuntoVenta nmOrigen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmTraslado")
    private List<OpmDetalleTraslado> opmDetalleTrasladoList;

    public OpmTraslado() {
    }

    public OpmTraslado(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmTraslado(Integer nmCodigo, String nvNumero, int nmDestino, Date daFecha) {
        this.nmCodigo = nmCodigo;
        this.nvNumero = nvNumero;
        this.nmDestino = nmDestino;
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

    public int getNmDestino() {
        return nmDestino;
    }

    public void setNmDestino(int nmDestino) {
        this.nmDestino = nmDestino;
    }

    public Date getDaFecha() {
        return daFecha;
    }

    public void setDaFecha(Date daFecha) {
        this.daFecha = daFecha;
    }

    public OpmPuntoVenta getNmOrigen() {
        return nmOrigen;
    }

    public void setNmOrigen(OpmPuntoVenta nmOrigen) {
        this.nmOrigen = nmOrigen;
    }

    @XmlTransient
    public List<OpmDetalleTraslado> getOpmDetalleTrasladoList() {
        return opmDetalleTrasladoList;
    }

    public void setOpmDetalleTrasladoList(List<OpmDetalleTraslado> opmDetalleTrasladoList) {
        this.opmDetalleTrasladoList = opmDetalleTrasladoList;
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
        if (!(object instanceof OpmTraslado)) {
            return false;
        }
        OpmTraslado other = (OpmTraslado) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmTraslado[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
