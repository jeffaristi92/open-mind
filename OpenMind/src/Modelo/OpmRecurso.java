/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ARISTIZABAL
 */
@Entity
@Table(name = "opm_recurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmRecurso.findAll", query = "SELECT o FROM OpmRecurso o"),
    @NamedQuery(name = "OpmRecurso.findByNmCodigo", query = "SELECT o FROM OpmRecurso o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmRecurso.findByNvNombre", query = "SELECT o FROM OpmRecurso o WHERE o.nvNombre = :nvNombre"),
    @NamedQuery(name = "OpmRecurso.findByNvDescripcion", query = "SELECT o FROM OpmRecurso o WHERE o.nvDescripcion = :nvDescripcion"),
    @NamedQuery(name = "OpmRecurso.findByNmOrden", query = "SELECT o FROM OpmRecurso o WHERE o.nmOrden = :nmOrden")})
public class OpmRecurso implements Serializable {
    @Column(name = "NM_PADRE")
    private Integer nmPadre;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;
    @Column(name = "NV_DESCRIPCION")
    private String nvDescripcion;
    @Basic(optional = false)
    @Column(name = "NM_ORDEN")
    private int nmOrden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmRecurso")
    private List<OpmRecursosRol> opmRecursosRolList;

    public OpmRecurso() {
    }

    public OpmRecurso(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmRecurso(Integer nmCodigo, String nvNombre, int nmOrden) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
        this.nmOrden = nmOrden;
    }

    public Integer getNmCodigo() {
        return nmCodigo;
    }

    public void setNmCodigo(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public String getNvNombre() {
        return nvNombre;
    }

    public void setNvNombre(String nvNombre) {
        this.nvNombre = nvNombre;
    }

    public String getNvDescripcion() {
        return nvDescripcion;
    }

    public void setNvDescripcion(String nvDescripcion) {
        this.nvDescripcion = nvDescripcion;
    }

    public int getNmOrden() {
        return nmOrden;
    }

    public void setNmOrden(int nmOrden) {
        this.nmOrden = nmOrden;
    }

    @XmlTransient
    public List<OpmRecursosRol> getOpmRecursosRolList() {
        return opmRecursosRolList;
    }

    public void setOpmRecursosRolList(List<OpmRecursosRol> opmRecursosRolList) {
        this.opmRecursosRolList = opmRecursosRolList;
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
        if (!(object instanceof OpmRecurso)) {
            return false;
        }
        OpmRecurso other = (OpmRecurso) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmRecurso[ nmCodigo=" + nmCodigo + " ]";
    }

    public Integer getNmPadre() {
        return nmPadre;
    }

    public void setNmPadre(Integer nmPadre) {
        this.nmPadre = nmPadre;
    }
    
}
