/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmProducto;
import Modelo.OpmInventarioPunto;
import java.util.ArrayList;
import java.util.List;
import Modelo.OpmDetalleLote;
import Modelo.OpmDetalleVenta;
import Modelo.OpmInventario;
import Modelo.OpmDetalleRemision;
import Modelo.OpmDetalleTraslado;
import Modelo.OpmReferenciaProducto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmReferenciaProductoJpaController implements Serializable {

    public OpmReferenciaProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmReferenciaProducto opmReferenciaProducto) throws PreexistingEntityException, Exception {
        if (opmReferenciaProducto.getOpmInventarioPuntoList() == null) {
            opmReferenciaProducto.setOpmInventarioPuntoList(new ArrayList<OpmInventarioPunto>());
        }
        if (opmReferenciaProducto.getOpmDetalleLoteList() == null) {
            opmReferenciaProducto.setOpmDetalleLoteList(new ArrayList<OpmDetalleLote>());
        }
        if (opmReferenciaProducto.getOpmDetalleVentaList() == null) {
            opmReferenciaProducto.setOpmDetalleVentaList(new ArrayList<OpmDetalleVenta>());
        }
        if (opmReferenciaProducto.getOpmInventarioList() == null) {
            opmReferenciaProducto.setOpmInventarioList(new ArrayList<OpmInventario>());
        }
        if (opmReferenciaProducto.getOpmDetalleRemisionList() == null) {
            opmReferenciaProducto.setOpmDetalleRemisionList(new ArrayList<OpmDetalleRemision>());
        }
        if (opmReferenciaProducto.getOpmDetalleTrasladoList() == null) {
            opmReferenciaProducto.setOpmDetalleTrasladoList(new ArrayList<OpmDetalleTraslado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmProducto nmProducto = opmReferenciaProducto.getNmProducto();
            if (nmProducto != null) {
                nmProducto = em.getReference(nmProducto.getClass(), nmProducto.getNmCodigo());
                opmReferenciaProducto.setNmProducto(nmProducto);
            }
            List<OpmInventarioPunto> attachedOpmInventarioPuntoList = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPuntoToAttach : opmReferenciaProducto.getOpmInventarioPuntoList()) {
                opmInventarioPuntoListOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoList.add(opmInventarioPuntoListOpmInventarioPuntoToAttach);
            }
            opmReferenciaProducto.setOpmInventarioPuntoList(attachedOpmInventarioPuntoList);
            List<OpmDetalleLote> attachedOpmDetalleLoteList = new ArrayList<OpmDetalleLote>();
            for (OpmDetalleLote opmDetalleLoteListOpmDetalleLoteToAttach : opmReferenciaProducto.getOpmDetalleLoteList()) {
                opmDetalleLoteListOpmDetalleLoteToAttach = em.getReference(opmDetalleLoteListOpmDetalleLoteToAttach.getClass(), opmDetalleLoteListOpmDetalleLoteToAttach.getNmCodigo());
                attachedOpmDetalleLoteList.add(opmDetalleLoteListOpmDetalleLoteToAttach);
            }
            opmReferenciaProducto.setOpmDetalleLoteList(attachedOpmDetalleLoteList);
            List<OpmDetalleVenta> attachedOpmDetalleVentaList = new ArrayList<OpmDetalleVenta>();
            for (OpmDetalleVenta opmDetalleVentaListOpmDetalleVentaToAttach : opmReferenciaProducto.getOpmDetalleVentaList()) {
                opmDetalleVentaListOpmDetalleVentaToAttach = em.getReference(opmDetalleVentaListOpmDetalleVentaToAttach.getClass(), opmDetalleVentaListOpmDetalleVentaToAttach.getNmCodigo());
                attachedOpmDetalleVentaList.add(opmDetalleVentaListOpmDetalleVentaToAttach);
            }
            opmReferenciaProducto.setOpmDetalleVentaList(attachedOpmDetalleVentaList);
            List<OpmInventario> attachedOpmInventarioList = new ArrayList<OpmInventario>();
            for (OpmInventario opmInventarioListOpmInventarioToAttach : opmReferenciaProducto.getOpmInventarioList()) {
                opmInventarioListOpmInventarioToAttach = em.getReference(opmInventarioListOpmInventarioToAttach.getClass(), opmInventarioListOpmInventarioToAttach.getNmCodigo());
                attachedOpmInventarioList.add(opmInventarioListOpmInventarioToAttach);
            }
            opmReferenciaProducto.setOpmInventarioList(attachedOpmInventarioList);
            List<OpmDetalleRemision> attachedOpmDetalleRemisionList = new ArrayList<OpmDetalleRemision>();
            for (OpmDetalleRemision opmDetalleRemisionListOpmDetalleRemisionToAttach : opmReferenciaProducto.getOpmDetalleRemisionList()) {
                opmDetalleRemisionListOpmDetalleRemisionToAttach = em.getReference(opmDetalleRemisionListOpmDetalleRemisionToAttach.getClass(), opmDetalleRemisionListOpmDetalleRemisionToAttach.getNmCodigo());
                attachedOpmDetalleRemisionList.add(opmDetalleRemisionListOpmDetalleRemisionToAttach);
            }
            opmReferenciaProducto.setOpmDetalleRemisionList(attachedOpmDetalleRemisionList);
            List<OpmDetalleTraslado> attachedOpmDetalleTrasladoList = new ArrayList<OpmDetalleTraslado>();
            for (OpmDetalleTraslado opmDetalleTrasladoListOpmDetalleTrasladoToAttach : opmReferenciaProducto.getOpmDetalleTrasladoList()) {
                opmDetalleTrasladoListOpmDetalleTrasladoToAttach = em.getReference(opmDetalleTrasladoListOpmDetalleTrasladoToAttach.getClass(), opmDetalleTrasladoListOpmDetalleTrasladoToAttach.getNmCodigo());
                attachedOpmDetalleTrasladoList.add(opmDetalleTrasladoListOpmDetalleTrasladoToAttach);
            }
            opmReferenciaProducto.setOpmDetalleTrasladoList(attachedOpmDetalleTrasladoList);
            em.persist(opmReferenciaProducto);
            if (nmProducto != null) {
                nmProducto.getOpmReferenciaProductoList().add(opmReferenciaProducto);
                nmProducto = em.merge(nmProducto);
            }
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPunto : opmReferenciaProducto.getOpmInventarioPuntoList()) {
                OpmReferenciaProducto oldNvReferenciaOfOpmInventarioPuntoListOpmInventarioPunto = opmInventarioPuntoListOpmInventarioPunto.getNvReferencia();
                opmInventarioPuntoListOpmInventarioPunto.setNvReferencia(opmReferenciaProducto);
                opmInventarioPuntoListOpmInventarioPunto = em.merge(opmInventarioPuntoListOpmInventarioPunto);
                if (oldNvReferenciaOfOpmInventarioPuntoListOpmInventarioPunto != null) {
                    oldNvReferenciaOfOpmInventarioPuntoListOpmInventarioPunto.getOpmInventarioPuntoList().remove(opmInventarioPuntoListOpmInventarioPunto);
                    oldNvReferenciaOfOpmInventarioPuntoListOpmInventarioPunto = em.merge(oldNvReferenciaOfOpmInventarioPuntoListOpmInventarioPunto);
                }
            }
            for (OpmDetalleLote opmDetalleLoteListOpmDetalleLote : opmReferenciaProducto.getOpmDetalleLoteList()) {
                OpmReferenciaProducto oldNvReferenciaOfOpmDetalleLoteListOpmDetalleLote = opmDetalleLoteListOpmDetalleLote.getNvReferencia();
                opmDetalleLoteListOpmDetalleLote.setNvReferencia(opmReferenciaProducto);
                opmDetalleLoteListOpmDetalleLote = em.merge(opmDetalleLoteListOpmDetalleLote);
                if (oldNvReferenciaOfOpmDetalleLoteListOpmDetalleLote != null) {
                    oldNvReferenciaOfOpmDetalleLoteListOpmDetalleLote.getOpmDetalleLoteList().remove(opmDetalleLoteListOpmDetalleLote);
                    oldNvReferenciaOfOpmDetalleLoteListOpmDetalleLote = em.merge(oldNvReferenciaOfOpmDetalleLoteListOpmDetalleLote);
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListOpmDetalleVenta : opmReferenciaProducto.getOpmDetalleVentaList()) {
                OpmReferenciaProducto oldNvReferenciaOfOpmDetalleVentaListOpmDetalleVenta = opmDetalleVentaListOpmDetalleVenta.getNvReferencia();
                opmDetalleVentaListOpmDetalleVenta.setNvReferencia(opmReferenciaProducto);
                opmDetalleVentaListOpmDetalleVenta = em.merge(opmDetalleVentaListOpmDetalleVenta);
                if (oldNvReferenciaOfOpmDetalleVentaListOpmDetalleVenta != null) {
                    oldNvReferenciaOfOpmDetalleVentaListOpmDetalleVenta.getOpmDetalleVentaList().remove(opmDetalleVentaListOpmDetalleVenta);
                    oldNvReferenciaOfOpmDetalleVentaListOpmDetalleVenta = em.merge(oldNvReferenciaOfOpmDetalleVentaListOpmDetalleVenta);
                }
            }
            for (OpmInventario opmInventarioListOpmInventario : opmReferenciaProducto.getOpmInventarioList()) {
                OpmReferenciaProducto oldNvReferenciaOfOpmInventarioListOpmInventario = opmInventarioListOpmInventario.getNvReferencia();
                opmInventarioListOpmInventario.setNvReferencia(opmReferenciaProducto);
                opmInventarioListOpmInventario = em.merge(opmInventarioListOpmInventario);
                if (oldNvReferenciaOfOpmInventarioListOpmInventario != null) {
                    oldNvReferenciaOfOpmInventarioListOpmInventario.getOpmInventarioList().remove(opmInventarioListOpmInventario);
                    oldNvReferenciaOfOpmInventarioListOpmInventario = em.merge(oldNvReferenciaOfOpmInventarioListOpmInventario);
                }
            }
            for (OpmDetalleRemision opmDetalleRemisionListOpmDetalleRemision : opmReferenciaProducto.getOpmDetalleRemisionList()) {
                OpmReferenciaProducto oldNvReferenciaOfOpmDetalleRemisionListOpmDetalleRemision = opmDetalleRemisionListOpmDetalleRemision.getNvReferencia();
                opmDetalleRemisionListOpmDetalleRemision.setNvReferencia(opmReferenciaProducto);
                opmDetalleRemisionListOpmDetalleRemision = em.merge(opmDetalleRemisionListOpmDetalleRemision);
                if (oldNvReferenciaOfOpmDetalleRemisionListOpmDetalleRemision != null) {
                    oldNvReferenciaOfOpmDetalleRemisionListOpmDetalleRemision.getOpmDetalleRemisionList().remove(opmDetalleRemisionListOpmDetalleRemision);
                    oldNvReferenciaOfOpmDetalleRemisionListOpmDetalleRemision = em.merge(oldNvReferenciaOfOpmDetalleRemisionListOpmDetalleRemision);
                }
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListOpmDetalleTraslado : opmReferenciaProducto.getOpmDetalleTrasladoList()) {
                OpmReferenciaProducto oldNvReferenciaOfOpmDetalleTrasladoListOpmDetalleTraslado = opmDetalleTrasladoListOpmDetalleTraslado.getNvReferencia();
                opmDetalleTrasladoListOpmDetalleTraslado.setNvReferencia(opmReferenciaProducto);
                opmDetalleTrasladoListOpmDetalleTraslado = em.merge(opmDetalleTrasladoListOpmDetalleTraslado);
                if (oldNvReferenciaOfOpmDetalleTrasladoListOpmDetalleTraslado != null) {
                    oldNvReferenciaOfOpmDetalleTrasladoListOpmDetalleTraslado.getOpmDetalleTrasladoList().remove(opmDetalleTrasladoListOpmDetalleTraslado);
                    oldNvReferenciaOfOpmDetalleTrasladoListOpmDetalleTraslado = em.merge(oldNvReferenciaOfOpmDetalleTrasladoListOpmDetalleTraslado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpmReferenciaProducto(opmReferenciaProducto.getNvCodigo()) != null) {
                throw new PreexistingEntityException("OpmReferenciaProducto " + opmReferenciaProducto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmReferenciaProducto opmReferenciaProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmReferenciaProducto persistentOpmReferenciaProducto = em.find(OpmReferenciaProducto.class, opmReferenciaProducto.getNvCodigo());
            OpmProducto nmProductoOld = persistentOpmReferenciaProducto.getNmProducto();
            OpmProducto nmProductoNew = opmReferenciaProducto.getNmProducto();
            List<OpmInventarioPunto> opmInventarioPuntoListOld = persistentOpmReferenciaProducto.getOpmInventarioPuntoList();
            List<OpmInventarioPunto> opmInventarioPuntoListNew = opmReferenciaProducto.getOpmInventarioPuntoList();
            List<OpmDetalleLote> opmDetalleLoteListOld = persistentOpmReferenciaProducto.getOpmDetalleLoteList();
            List<OpmDetalleLote> opmDetalleLoteListNew = opmReferenciaProducto.getOpmDetalleLoteList();
            List<OpmDetalleVenta> opmDetalleVentaListOld = persistentOpmReferenciaProducto.getOpmDetalleVentaList();
            List<OpmDetalleVenta> opmDetalleVentaListNew = opmReferenciaProducto.getOpmDetalleVentaList();
            List<OpmInventario> opmInventarioListOld = persistentOpmReferenciaProducto.getOpmInventarioList();
            List<OpmInventario> opmInventarioListNew = opmReferenciaProducto.getOpmInventarioList();
            List<OpmDetalleRemision> opmDetalleRemisionListOld = persistentOpmReferenciaProducto.getOpmDetalleRemisionList();
            List<OpmDetalleRemision> opmDetalleRemisionListNew = opmReferenciaProducto.getOpmDetalleRemisionList();
            List<OpmDetalleTraslado> opmDetalleTrasladoListOld = persistentOpmReferenciaProducto.getOpmDetalleTrasladoList();
            List<OpmDetalleTraslado> opmDetalleTrasladoListNew = opmReferenciaProducto.getOpmDetalleTrasladoList();
            List<String> illegalOrphanMessages = null;
            for (OpmInventarioPunto opmInventarioPuntoListOldOpmInventarioPunto : opmInventarioPuntoListOld) {
                if (!opmInventarioPuntoListNew.contains(opmInventarioPuntoListOldOpmInventarioPunto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmInventarioPunto " + opmInventarioPuntoListOldOpmInventarioPunto + " since its nvReferencia field is not nullable.");
                }
            }
            for (OpmDetalleLote opmDetalleLoteListOldOpmDetalleLote : opmDetalleLoteListOld) {
                if (!opmDetalleLoteListNew.contains(opmDetalleLoteListOldOpmDetalleLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleLote " + opmDetalleLoteListOldOpmDetalleLote + " since its nvReferencia field is not nullable.");
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListOldOpmDetalleVenta : opmDetalleVentaListOld) {
                if (!opmDetalleVentaListNew.contains(opmDetalleVentaListOldOpmDetalleVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleVenta " + opmDetalleVentaListOldOpmDetalleVenta + " since its nvReferencia field is not nullable.");
                }
            }
            for (OpmInventario opmInventarioListOldOpmInventario : opmInventarioListOld) {
                if (!opmInventarioListNew.contains(opmInventarioListOldOpmInventario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmInventario " + opmInventarioListOldOpmInventario + " since its nvReferencia field is not nullable.");
                }
            }
            for (OpmDetalleRemision opmDetalleRemisionListOldOpmDetalleRemision : opmDetalleRemisionListOld) {
                if (!opmDetalleRemisionListNew.contains(opmDetalleRemisionListOldOpmDetalleRemision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleRemision " + opmDetalleRemisionListOldOpmDetalleRemision + " since its nvReferencia field is not nullable.");
                }
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListOldOpmDetalleTraslado : opmDetalleTrasladoListOld) {
                if (!opmDetalleTrasladoListNew.contains(opmDetalleTrasladoListOldOpmDetalleTraslado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleTraslado " + opmDetalleTrasladoListOldOpmDetalleTraslado + " since its nvReferencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmProductoNew != null) {
                nmProductoNew = em.getReference(nmProductoNew.getClass(), nmProductoNew.getNmCodigo());
                opmReferenciaProducto.setNmProducto(nmProductoNew);
            }
            List<OpmInventarioPunto> attachedOpmInventarioPuntoListNew = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListNewOpmInventarioPuntoToAttach : opmInventarioPuntoListNew) {
                opmInventarioPuntoListNewOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoListNew.add(opmInventarioPuntoListNewOpmInventarioPuntoToAttach);
            }
            opmInventarioPuntoListNew = attachedOpmInventarioPuntoListNew;
            opmReferenciaProducto.setOpmInventarioPuntoList(opmInventarioPuntoListNew);
            List<OpmDetalleLote> attachedOpmDetalleLoteListNew = new ArrayList<OpmDetalleLote>();
            for (OpmDetalleLote opmDetalleLoteListNewOpmDetalleLoteToAttach : opmDetalleLoteListNew) {
                opmDetalleLoteListNewOpmDetalleLoteToAttach = em.getReference(opmDetalleLoteListNewOpmDetalleLoteToAttach.getClass(), opmDetalleLoteListNewOpmDetalleLoteToAttach.getNmCodigo());
                attachedOpmDetalleLoteListNew.add(opmDetalleLoteListNewOpmDetalleLoteToAttach);
            }
            opmDetalleLoteListNew = attachedOpmDetalleLoteListNew;
            opmReferenciaProducto.setOpmDetalleLoteList(opmDetalleLoteListNew);
            List<OpmDetalleVenta> attachedOpmDetalleVentaListNew = new ArrayList<OpmDetalleVenta>();
            for (OpmDetalleVenta opmDetalleVentaListNewOpmDetalleVentaToAttach : opmDetalleVentaListNew) {
                opmDetalleVentaListNewOpmDetalleVentaToAttach = em.getReference(opmDetalleVentaListNewOpmDetalleVentaToAttach.getClass(), opmDetalleVentaListNewOpmDetalleVentaToAttach.getNmCodigo());
                attachedOpmDetalleVentaListNew.add(opmDetalleVentaListNewOpmDetalleVentaToAttach);
            }
            opmDetalleVentaListNew = attachedOpmDetalleVentaListNew;
            opmReferenciaProducto.setOpmDetalleVentaList(opmDetalleVentaListNew);
            List<OpmInventario> attachedOpmInventarioListNew = new ArrayList<OpmInventario>();
            for (OpmInventario opmInventarioListNewOpmInventarioToAttach : opmInventarioListNew) {
                opmInventarioListNewOpmInventarioToAttach = em.getReference(opmInventarioListNewOpmInventarioToAttach.getClass(), opmInventarioListNewOpmInventarioToAttach.getNmCodigo());
                attachedOpmInventarioListNew.add(opmInventarioListNewOpmInventarioToAttach);
            }
            opmInventarioListNew = attachedOpmInventarioListNew;
            opmReferenciaProducto.setOpmInventarioList(opmInventarioListNew);
            List<OpmDetalleRemision> attachedOpmDetalleRemisionListNew = new ArrayList<OpmDetalleRemision>();
            for (OpmDetalleRemision opmDetalleRemisionListNewOpmDetalleRemisionToAttach : opmDetalleRemisionListNew) {
                opmDetalleRemisionListNewOpmDetalleRemisionToAttach = em.getReference(opmDetalleRemisionListNewOpmDetalleRemisionToAttach.getClass(), opmDetalleRemisionListNewOpmDetalleRemisionToAttach.getNmCodigo());
                attachedOpmDetalleRemisionListNew.add(opmDetalleRemisionListNewOpmDetalleRemisionToAttach);
            }
            opmDetalleRemisionListNew = attachedOpmDetalleRemisionListNew;
            opmReferenciaProducto.setOpmDetalleRemisionList(opmDetalleRemisionListNew);
            List<OpmDetalleTraslado> attachedOpmDetalleTrasladoListNew = new ArrayList<OpmDetalleTraslado>();
            for (OpmDetalleTraslado opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach : opmDetalleTrasladoListNew) {
                opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach = em.getReference(opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach.getClass(), opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach.getNmCodigo());
                attachedOpmDetalleTrasladoListNew.add(opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach);
            }
            opmDetalleTrasladoListNew = attachedOpmDetalleTrasladoListNew;
            opmReferenciaProducto.setOpmDetalleTrasladoList(opmDetalleTrasladoListNew);
            opmReferenciaProducto = em.merge(opmReferenciaProducto);
            if (nmProductoOld != null && !nmProductoOld.equals(nmProductoNew)) {
                nmProductoOld.getOpmReferenciaProductoList().remove(opmReferenciaProducto);
                nmProductoOld = em.merge(nmProductoOld);
            }
            if (nmProductoNew != null && !nmProductoNew.equals(nmProductoOld)) {
                nmProductoNew.getOpmReferenciaProductoList().add(opmReferenciaProducto);
                nmProductoNew = em.merge(nmProductoNew);
            }
            for (OpmInventarioPunto opmInventarioPuntoListNewOpmInventarioPunto : opmInventarioPuntoListNew) {
                if (!opmInventarioPuntoListOld.contains(opmInventarioPuntoListNewOpmInventarioPunto)) {
                    OpmReferenciaProducto oldNvReferenciaOfOpmInventarioPuntoListNewOpmInventarioPunto = opmInventarioPuntoListNewOpmInventarioPunto.getNvReferencia();
                    opmInventarioPuntoListNewOpmInventarioPunto.setNvReferencia(opmReferenciaProducto);
                    opmInventarioPuntoListNewOpmInventarioPunto = em.merge(opmInventarioPuntoListNewOpmInventarioPunto);
                    if (oldNvReferenciaOfOpmInventarioPuntoListNewOpmInventarioPunto != null && !oldNvReferenciaOfOpmInventarioPuntoListNewOpmInventarioPunto.equals(opmReferenciaProducto)) {
                        oldNvReferenciaOfOpmInventarioPuntoListNewOpmInventarioPunto.getOpmInventarioPuntoList().remove(opmInventarioPuntoListNewOpmInventarioPunto);
                        oldNvReferenciaOfOpmInventarioPuntoListNewOpmInventarioPunto = em.merge(oldNvReferenciaOfOpmInventarioPuntoListNewOpmInventarioPunto);
                    }
                }
            }
            for (OpmDetalleLote opmDetalleLoteListNewOpmDetalleLote : opmDetalleLoteListNew) {
                if (!opmDetalleLoteListOld.contains(opmDetalleLoteListNewOpmDetalleLote)) {
                    OpmReferenciaProducto oldNvReferenciaOfOpmDetalleLoteListNewOpmDetalleLote = opmDetalleLoteListNewOpmDetalleLote.getNvReferencia();
                    opmDetalleLoteListNewOpmDetalleLote.setNvReferencia(opmReferenciaProducto);
                    opmDetalleLoteListNewOpmDetalleLote = em.merge(opmDetalleLoteListNewOpmDetalleLote);
                    if (oldNvReferenciaOfOpmDetalleLoteListNewOpmDetalleLote != null && !oldNvReferenciaOfOpmDetalleLoteListNewOpmDetalleLote.equals(opmReferenciaProducto)) {
                        oldNvReferenciaOfOpmDetalleLoteListNewOpmDetalleLote.getOpmDetalleLoteList().remove(opmDetalleLoteListNewOpmDetalleLote);
                        oldNvReferenciaOfOpmDetalleLoteListNewOpmDetalleLote = em.merge(oldNvReferenciaOfOpmDetalleLoteListNewOpmDetalleLote);
                    }
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListNewOpmDetalleVenta : opmDetalleVentaListNew) {
                if (!opmDetalleVentaListOld.contains(opmDetalleVentaListNewOpmDetalleVenta)) {
                    OpmReferenciaProducto oldNvReferenciaOfOpmDetalleVentaListNewOpmDetalleVenta = opmDetalleVentaListNewOpmDetalleVenta.getNvReferencia();
                    opmDetalleVentaListNewOpmDetalleVenta.setNvReferencia(opmReferenciaProducto);
                    opmDetalleVentaListNewOpmDetalleVenta = em.merge(opmDetalleVentaListNewOpmDetalleVenta);
                    if (oldNvReferenciaOfOpmDetalleVentaListNewOpmDetalleVenta != null && !oldNvReferenciaOfOpmDetalleVentaListNewOpmDetalleVenta.equals(opmReferenciaProducto)) {
                        oldNvReferenciaOfOpmDetalleVentaListNewOpmDetalleVenta.getOpmDetalleVentaList().remove(opmDetalleVentaListNewOpmDetalleVenta);
                        oldNvReferenciaOfOpmDetalleVentaListNewOpmDetalleVenta = em.merge(oldNvReferenciaOfOpmDetalleVentaListNewOpmDetalleVenta);
                    }
                }
            }
            for (OpmInventario opmInventarioListNewOpmInventario : opmInventarioListNew) {
                if (!opmInventarioListOld.contains(opmInventarioListNewOpmInventario)) {
                    OpmReferenciaProducto oldNvReferenciaOfOpmInventarioListNewOpmInventario = opmInventarioListNewOpmInventario.getNvReferencia();
                    opmInventarioListNewOpmInventario.setNvReferencia(opmReferenciaProducto);
                    opmInventarioListNewOpmInventario = em.merge(opmInventarioListNewOpmInventario);
                    if (oldNvReferenciaOfOpmInventarioListNewOpmInventario != null && !oldNvReferenciaOfOpmInventarioListNewOpmInventario.equals(opmReferenciaProducto)) {
                        oldNvReferenciaOfOpmInventarioListNewOpmInventario.getOpmInventarioList().remove(opmInventarioListNewOpmInventario);
                        oldNvReferenciaOfOpmInventarioListNewOpmInventario = em.merge(oldNvReferenciaOfOpmInventarioListNewOpmInventario);
                    }
                }
            }
            for (OpmDetalleRemision opmDetalleRemisionListNewOpmDetalleRemision : opmDetalleRemisionListNew) {
                if (!opmDetalleRemisionListOld.contains(opmDetalleRemisionListNewOpmDetalleRemision)) {
                    OpmReferenciaProducto oldNvReferenciaOfOpmDetalleRemisionListNewOpmDetalleRemision = opmDetalleRemisionListNewOpmDetalleRemision.getNvReferencia();
                    opmDetalleRemisionListNewOpmDetalleRemision.setNvReferencia(opmReferenciaProducto);
                    opmDetalleRemisionListNewOpmDetalleRemision = em.merge(opmDetalleRemisionListNewOpmDetalleRemision);
                    if (oldNvReferenciaOfOpmDetalleRemisionListNewOpmDetalleRemision != null && !oldNvReferenciaOfOpmDetalleRemisionListNewOpmDetalleRemision.equals(opmReferenciaProducto)) {
                        oldNvReferenciaOfOpmDetalleRemisionListNewOpmDetalleRemision.getOpmDetalleRemisionList().remove(opmDetalleRemisionListNewOpmDetalleRemision);
                        oldNvReferenciaOfOpmDetalleRemisionListNewOpmDetalleRemision = em.merge(oldNvReferenciaOfOpmDetalleRemisionListNewOpmDetalleRemision);
                    }
                }
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListNewOpmDetalleTraslado : opmDetalleTrasladoListNew) {
                if (!opmDetalleTrasladoListOld.contains(opmDetalleTrasladoListNewOpmDetalleTraslado)) {
                    OpmReferenciaProducto oldNvReferenciaOfOpmDetalleTrasladoListNewOpmDetalleTraslado = opmDetalleTrasladoListNewOpmDetalleTraslado.getNvReferencia();
                    opmDetalleTrasladoListNewOpmDetalleTraslado.setNvReferencia(opmReferenciaProducto);
                    opmDetalleTrasladoListNewOpmDetalleTraslado = em.merge(opmDetalleTrasladoListNewOpmDetalleTraslado);
                    if (oldNvReferenciaOfOpmDetalleTrasladoListNewOpmDetalleTraslado != null && !oldNvReferenciaOfOpmDetalleTrasladoListNewOpmDetalleTraslado.equals(opmReferenciaProducto)) {
                        oldNvReferenciaOfOpmDetalleTrasladoListNewOpmDetalleTraslado.getOpmDetalleTrasladoList().remove(opmDetalleTrasladoListNewOpmDetalleTraslado);
                        oldNvReferenciaOfOpmDetalleTrasladoListNewOpmDetalleTraslado = em.merge(oldNvReferenciaOfOpmDetalleTrasladoListNewOpmDetalleTraslado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = opmReferenciaProducto.getNvCodigo();
                if (findOpmReferenciaProducto(id) == null) {
                    throw new NonexistentEntityException("The opmReferenciaProducto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmReferenciaProducto opmReferenciaProducto;
            try {
                opmReferenciaProducto = em.getReference(OpmReferenciaProducto.class, id);
                opmReferenciaProducto.getNvCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmReferenciaProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmInventarioPunto> opmInventarioPuntoListOrphanCheck = opmReferenciaProducto.getOpmInventarioPuntoList();
            for (OpmInventarioPunto opmInventarioPuntoListOrphanCheckOpmInventarioPunto : opmInventarioPuntoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmReferenciaProducto (" + opmReferenciaProducto + ") cannot be destroyed since the OpmInventarioPunto " + opmInventarioPuntoListOrphanCheckOpmInventarioPunto + " in its opmInventarioPuntoList field has a non-nullable nvReferencia field.");
            }
            List<OpmDetalleLote> opmDetalleLoteListOrphanCheck = opmReferenciaProducto.getOpmDetalleLoteList();
            for (OpmDetalleLote opmDetalleLoteListOrphanCheckOpmDetalleLote : opmDetalleLoteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmReferenciaProducto (" + opmReferenciaProducto + ") cannot be destroyed since the OpmDetalleLote " + opmDetalleLoteListOrphanCheckOpmDetalleLote + " in its opmDetalleLoteList field has a non-nullable nvReferencia field.");
            }
            List<OpmDetalleVenta> opmDetalleVentaListOrphanCheck = opmReferenciaProducto.getOpmDetalleVentaList();
            for (OpmDetalleVenta opmDetalleVentaListOrphanCheckOpmDetalleVenta : opmDetalleVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmReferenciaProducto (" + opmReferenciaProducto + ") cannot be destroyed since the OpmDetalleVenta " + opmDetalleVentaListOrphanCheckOpmDetalleVenta + " in its opmDetalleVentaList field has a non-nullable nvReferencia field.");
            }
            List<OpmInventario> opmInventarioListOrphanCheck = opmReferenciaProducto.getOpmInventarioList();
            for (OpmInventario opmInventarioListOrphanCheckOpmInventario : opmInventarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmReferenciaProducto (" + opmReferenciaProducto + ") cannot be destroyed since the OpmInventario " + opmInventarioListOrphanCheckOpmInventario + " in its opmInventarioList field has a non-nullable nvReferencia field.");
            }
            List<OpmDetalleRemision> opmDetalleRemisionListOrphanCheck = opmReferenciaProducto.getOpmDetalleRemisionList();
            for (OpmDetalleRemision opmDetalleRemisionListOrphanCheckOpmDetalleRemision : opmDetalleRemisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmReferenciaProducto (" + opmReferenciaProducto + ") cannot be destroyed since the OpmDetalleRemision " + opmDetalleRemisionListOrphanCheckOpmDetalleRemision + " in its opmDetalleRemisionList field has a non-nullable nvReferencia field.");
            }
            List<OpmDetalleTraslado> opmDetalleTrasladoListOrphanCheck = opmReferenciaProducto.getOpmDetalleTrasladoList();
            for (OpmDetalleTraslado opmDetalleTrasladoListOrphanCheckOpmDetalleTraslado : opmDetalleTrasladoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmReferenciaProducto (" + opmReferenciaProducto + ") cannot be destroyed since the OpmDetalleTraslado " + opmDetalleTrasladoListOrphanCheckOpmDetalleTraslado + " in its opmDetalleTrasladoList field has a non-nullable nvReferencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmProducto nmProducto = opmReferenciaProducto.getNmProducto();
            if (nmProducto != null) {
                nmProducto.getOpmReferenciaProductoList().remove(opmReferenciaProducto);
                nmProducto = em.merge(nmProducto);
            }
            em.remove(opmReferenciaProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmReferenciaProducto> findOpmReferenciaProductoEntities() {
        return findOpmReferenciaProductoEntities(true, -1, -1);
    }

    public List<OpmReferenciaProducto> findOpmReferenciaProductoEntities(int maxResults, int firstResult) {
        return findOpmReferenciaProductoEntities(false, maxResults, firstResult);
    }

    private List<OpmReferenciaProducto> findOpmReferenciaProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmReferenciaProducto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public OpmReferenciaProducto findOpmReferenciaProducto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmReferenciaProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmReferenciaProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmReferenciaProducto> rt = cq.from(OpmReferenciaProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
