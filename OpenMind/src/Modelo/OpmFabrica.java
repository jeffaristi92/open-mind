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
@Table(name = "opm_fabrica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpmFabrica.findAll", query = "SELECT o FROM OpmFabrica o"),
    @NamedQuery(name = "OpmFabrica.findByNmCodigo", query = "SELECT o FROM OpmFabrica o WHERE o.nmCodigo = :nmCodigo"),
    @NamedQuery(name = "OpmFabrica.findByNvNombre", query = "SELECT o FROM OpmFabrica o WHERE o.nvNombre = :nvNombre"),
    @NamedQuery(name = "OpmFabrica.findByNvTel", query = "SELECT o FROM OpmFabrica o WHERE o.nvTel = :nvTel"),
    @NamedQuery(name = "OpmFabrica.findByNvDireccion", query = "SELECT o FROM OpmFabrica o WHERE o.nvDireccion = :nvDireccion")})
public class OpmFabrica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NM_CODIGO")
    private Integer nmCodigo;
    @Basic(optional = false)
    @Column(name = "NV_NOMBRE")
    private String nvNombre;
    @Basic(optional = false)
    @Column(name = "NV_TEL")
    private String nvTel;
    @Basic(optional = false)
    @Column(name = "NV_DIRECCION")
    private String nvDireccion;
    @JoinColumn(name = "NM_ADMINISTRADOR", referencedColumnName = "NM_CODIGO")
    @ManyToOne(optional = false)
    private OpmUsuario nmAdministrador;

    public OpmFabrica() {
    }

    public OpmFabrica(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmFabrica(Integer nmCodigo, String nvNombre, String nvTel, String nvDireccion) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
        this.nvTel = nvTel;
        this.nvDireccion = nvDireccion;
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

    public String getNvTel() {
        return nvTel;
    }

    public void setNvTel(String nvTel) {
        this.nvTel = nvTel;
    }

    public String getNvDireccion() {
        return nvDireccion;
    }

    public void setNvDireccion(String nvDireccion) {
        this.nvDireccion = nvDireccion;
    }

    public OpmUsuario getNmAdministrador() {
        return nmAdministrador;
    }

    public void setNmAdministrador(OpmUsuario nmAdministrador) {
        this.nmAdministrador = nmAdministrador;
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
        if (!(object instanceof OpmFabrica)) {
            return false;
        }
        OpmFabrica other = (OpmFabrica) object;
        if ((this.nmCodigo == null && other.nmCodigo != null) || (this.nmCodigo != null && !this.nmCodigo.equals(other.nmCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.OpmFabrica[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
