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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "opm_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmUsuario.findAll", query = "SELECT o FROM OpmUsuario o"),
    @NamedQuery(name = "OpmUsuario.findByNmCodigo", query = "SELECT o FROM OpmUsuario o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmUsuario.findByNvNombre", query = "SELECT o FROM OpmUsuario o WHERE o.nvNombre = :nvNombre"),
    @NamedQuery(name = "OpmUsuario.findByNvEmail", query = "SELECT o FROM OpmUsuario o WHERE o.nvEmail = :nvEmail"),
    @NamedQuery(name = "OpmUsuario.findByNvTel", query = "SELECT o FROM OpmUsuario o WHERE o.nvTel = :nvTel")})
public class OpmUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;
    @Column(name = "NV_EMAIL")
    private String nvEmail;
    @Column(name = "NV_TEL")
    private String nvTel;
    @JoinColumn(name = "NM_ROL", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmRol nmRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmAdministrador")
    private List<OpmPuntoVenta> opmPuntoVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCliente")
    private List<OpmVenta> opmVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmEmpleado")
    private List<OpmRemision> opmRemisionList;

    public OpmUsuario() {
    }

    public OpmUsuario(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmUsuario(Integer nmCodigo, String nvNombre) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
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

    public String getNvEmail() {
        return nvEmail;
    }

    public void setNvEmail(String nvEmail) {
        this.nvEmail = nvEmail;
    }

    public String getNvTel() {
        return nvTel;
    }

    public void setNvTel(String nvTel) {
        this.nvTel = nvTel;
    }

    public OpmRol getNmRol() {
        return nmRol;
    }

    public void setNmRol(OpmRol nmRol) {
        this.nmRol = nmRol;
    }

    @XmlTransient
    public List<OpmPuntoVenta> getOpmPuntoVentaList() {
        return opmPuntoVentaList;
    }

    public void setOpmPuntoVentaList(List<OpmPuntoVenta> opmPuntoVentaList) {
        this.opmPuntoVentaList = opmPuntoVentaList;
    }

    @XmlTransient
    public List<OpmVenta> getOpmVentaList() {
        return opmVentaList;
    }

    public void setOpmVentaList(List<OpmVenta> opmVentaList) {
        this.opmVentaList = opmVentaList;
    }

    @XmlTransient
    public List<OpmRemision> getOpmRemisionList() {
        return opmRemisionList;
    }

    public void setOpmRemisionList(List<OpmRemision> opmRemisionList) {
        this.opmRemisionList = opmRemisionList;
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
        if (!(object instanceof OpmUsuario)) {
            return false;
        }
        OpmUsuario other = (OpmUsuario) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controlador.OpmUsuario[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
