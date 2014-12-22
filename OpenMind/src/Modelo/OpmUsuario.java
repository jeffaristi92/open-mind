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
    @NamedQuery(name = "OpmUsuario.findByNvTel", query = "SELECT o FROM OpmUsuario o WHERE o.nvTel = :nvTel"),
    @NamedQuery(name = "OpmUsuario.findByNmRol", query = "SELECT o FROM OpmUsuario o WHERE o.nmRol = :nmRol"),
    @NamedQuery(name = "OpmUsuario.findByNvPass", query = "SELECT o FROM OpmUsuario o WHERE o.nvPass = :nvPass"),
    @NamedQuery(name = "OpmUsuario.findByBtActivo", query = "SELECT o FROM OpmUsuario o WHERE o.btActivo = :btActivo"),
    @NamedQuery(name = "OpmUsuario.findByNmFabrica", query = "SELECT o FROM OpmUsuario o WHERE o.nmFabrica = :nmFabrica")})
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
    @Basic(optional = false)
    @Column(name = "NM_ROL")
    private int nmRol;
    @Column(name = "NV_PASS")
    private String nvPass;
    @Basic(optional = false)
    @Column(name = "BT_ACTIVO")
    private boolean btActivo;
    @Column(name = "NM_FABRICA")
    private Integer nmFabrica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmAdministrador")
    private List<OpmFabrica> opmFabricaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCliente")
    private List<OpmVenta> opmVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmUsuario")
    private List<OpmTransaccion> opmTransaccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmEmpleado")
    private List<OpmLote> opmLoteList;

    public OpmUsuario() {
    }

    public OpmUsuario(Integer nmCodigo) {
        this.nmCodigo = nmCodigo;
    }

    public OpmUsuario(Integer nmCodigo, String nvNombre, int nmRol, boolean btActivo) {
        this.nmCodigo = nmCodigo;
        this.nvNombre = nvNombre;
        this.nmRol = nmRol;
        this.btActivo = btActivo;
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

    public int getNmRol() {
        return nmRol;
    }

    public void setNmRol(int nmRol) {
        this.nmRol = nmRol;
    }

    public String getNvPass() {
        return nvPass;
    }

    public void setNvPass(String nvPass) {
        this.nvPass = nvPass;
    }

    public boolean getBtActivo() {
        return btActivo;
    }

    public void setBtActivo(boolean btActivo) {
        this.btActivo = btActivo;
    }

    public Integer getNmFabrica() {
        return nmFabrica;
    }

    public void setNmFabrica(Integer nmFabrica) {
        this.nmFabrica = nmFabrica;
    }

    @XmlTransient
    public List<OpmFabrica> getOpmFabricaList() {
        return opmFabricaList;
    }

    public void setOpmFabricaList(List<OpmFabrica> opmFabricaList) {
        this.opmFabricaList = opmFabricaList;
    }

    @XmlTransient
    public List<OpmVenta> getOpmVentaList() {
        return opmVentaList;
    }

    public void setOpmVentaList(List<OpmVenta> opmVentaList) {
        this.opmVentaList = opmVentaList;
    }

    @XmlTransient
    public List<OpmTransaccion> getOpmTransaccionList() {
        return opmTransaccionList;
    }

    public void setOpmTransaccionList(List<OpmTransaccion> opmTransaccionList) {
        this.opmTransaccionList = opmTransaccionList;
    }

    @XmlTransient
    public List<OpmLote> getOpmLoteList() {
        return opmLoteList;
    }

    public void setOpmLoteList(List<OpmLote> opmLoteList) {
        this.opmLoteList = opmLoteList;
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
        return "Modelo.OpmUsuario[ nmCodigo=" + nmCodigo + " ]";
    }
    
}
