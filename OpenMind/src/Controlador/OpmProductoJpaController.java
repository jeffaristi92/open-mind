/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmInventario;
import java.util.ArrayList;
import java.util.List;
import Modelo.OpmInventarioPunto;
import Modelo.OpmDetalleRemision;
import Modelo.OpmDetalleTraslado;
import Modelo.OpmDetalleVenta;
import Modelo.OpmProducto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmProductoJpaController implements Serializable {

    public OpmProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmProducto opmProducto) {
        if (opmProducto.getOpmInventarioList() == null) {
            opmProducto.setOpmInventarioList(new ArrayList<OpmInventario>());
        }
        if (opmProducto.getOpmInventarioPuntoList() == null) {
            opmProducto.setOpmInventarioPuntoList(new ArrayList<OpmInventarioPunto>());
        }
        if (opmProducto.getOpmDetalleRemisionList() == null) {
            opmProducto.setOpmDetalleRemisionList(new ArrayList<OpmDetalleRemision>());
        }
        if (opmProducto.getOpmDetalleTrasladoList() == null) {
            opmProducto.setOpmDetalleTrasladoList(new ArrayList<OpmDetalleTraslado>());
        }
        if (opmProducto.getOpmDetalleVentaList() == null) {
            opmProducto.setOpmDetalleVentaList(new ArrayList<OpmDetalleVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmInventario> attachedOpmInventarioList = new ArrayList<OpmInventario>();
            for (OpmInventario opmInventarioListOpmInventarioToAttach : opmProducto.getOpmInventarioList()) {
                opmInventarioListOpmInventarioToAttach = em.getReference(opmInventarioListOpmInventarioToAttach.getClass(), opmInventarioListOpmInventarioToAttach.getNmCodigo());
                attachedOpmInventarioList.add(opmInventarioListOpmInventarioToAttach);
            }
            opmProducto.setOpmInventarioList(attachedOpmInventarioList);
            List<OpmInventarioPunto> attachedOpmInventarioPuntoList = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPuntoToAttach : opmProducto.getOpmInventarioPuntoList()) {
                opmInventarioPuntoListOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoList.add(opmInventarioPuntoListOpmInventarioPuntoToAttach);
            }
            opmProducto.setOpmInventarioPuntoList(attachedOpmInventarioPuntoList);
            List<OpmDetalleRemision> attachedOpmDetalleRemisionList = new ArrayList<OpmDetalleRemision>();
            for (OpmDetalleRemision opmDetalleRemisionListOpmDetalleRemisionToAttach : opmProducto.getOpmDetalleRemisionList()) {
                opmDetalleRemisionListOpmDetalleRemisionToAttach = em.getReference(opmDetalleRemisionListOpmDetalleRemisionToAttach.getClass(), opmDetalleRemisionListOpmDetalleRemisionToAttach.getNmCodigo());
                attachedOpmDetalleRemisionList.add(opmDetalleRemisionListOpmDetalleRemisionToAttach);
            }
            opmProducto.setOpmDetalleRemisionList(attachedOpmDetalleRemisionList);
            List<OpmDetalleTraslado> attachedOpmDetalleTrasladoList = new ArrayList<OpmDetalleTraslado>();
            for (OpmDetalleTraslado opmDetalleTrasladoListOpmDetalleTrasladoToAttach : opmProducto.getOpmDetalleTrasladoList()) {
                opmDetalleTrasladoListOpmDetalleTrasladoToAttach = em.getReference(opmDetalleTrasladoListOpmDetalleTrasladoToAttach.getClass(), opmDetalleTrasladoListOpmDetalleTrasladoToAttach.getNmCodigo());
                attachedOpmDetalleTrasladoList.add(opmDetalleTrasladoListOpmDetalleTrasladoToAttach);
            }
            opmProducto.setOpmDetalleTrasladoList(attachedOpmDetalleTrasladoList);
            List<OpmDetalleVenta> attachedOpmDetalleVentaList = new ArrayList<OpmDetalleVenta>();
            for (OpmDetalleVenta opmDetalleVentaListOpmDetalleVentaToAttach : opmProducto.getOpmDetalleVentaList()) {
                opmDetalleVentaListOpmDetalleVentaToAttach = em.getReference(opmDetalleVentaListOpmDetalleVentaToAttach.getClass(), opmDetalleVentaListOpmDetalleVentaToAttach.getNmCodigo());
                attachedOpmDetalleVentaList.add(opmDetalleVentaListOpmDetalleVentaToAttach);
            }
            opmProducto.setOpmDetalleVentaList(attachedOpmDetalleVentaList);
            em.persist(opmProducto);
            for (OpmInventario opmInventarioListOpmInventario : opmProducto.getOpmInventarioList()) {
                OpmProducto oldNmProductoOfOpmInventarioListOpmInventario = opmInventarioListOpmInventario.getNmProducto();
                opmInventarioListOpmInventario.setNmProducto(opmProducto);
                opmInventarioListOpmInventario = em.merge(opmInventarioListOpmInventario);
                if (oldNmProductoOfOpmInventarioListOpmInventario != null) {
                    oldNmProductoOfOpmInventarioListOpmInventario.getOpmInventarioList().remove(opmInventarioListOpmInventario);
                    oldNmProductoOfOpmInventarioListOpmInventario = em.merge(oldNmProductoOfOpmInventarioListOpmInventario);
                }
            }
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPunto : opmProducto.getOpmInventarioPuntoList()) {
                OpmProducto oldNmProductoOfOpmInventarioPuntoListOpmInventarioPunto = opmInventarioPuntoListOpmInventarioPunto.getNmProducto();
                opmInventarioPuntoListOpmInventarioPunto.setNmProducto(opmProducto);
                opmInventarioPuntoListOpmInventarioPunto = em.merge(opmInventarioPuntoListOpmInventarioPunto);
                if (oldNmProductoOfOpmInventarioPuntoListOpmInventarioPunto != null) {
                    oldNmProductoOfOpmInventarioPuntoListOpmInventarioPunto.getOpmInventarioPuntoList().remove(opmInventarioPuntoListOpmInventarioPunto);
                    oldNmProductoOfOpmInventarioPuntoListOpmInventarioPunto = em.merge(oldNmProductoOfOpmInventarioPuntoListOpmInventarioPunto);
                }
            }
            for (OpmDetalleRemision opmDetalleRemisionListOpmDetalleRemision : opmProducto.getOpmDetalleRemisionList()) {
                OpmProducto oldNmProductoOfOpmDetalleRemisionListOpmDetalleRemision = opmDetalleRemisionListOpmDetalleRemision.getNmProducto();
                opmDetalleRemisionListOpmDetalleRemision.setNmProducto(opmProducto);
                opmDetalleRemisionListOpmDetalleRemision = em.merge(opmDetalleRemisionListOpmDetalleRemision);
                if (oldNmProductoOfOpmDetalleRemisionListOpmDetalleRemision != null) {
                    oldNmProductoOfOpmDetalleRemisionListOpmDetalleRemision.getOpmDetalleRemisionList().remove(opmDetalleRemisionListOpmDetalleRemision);
                    oldNmProductoOfOpmDetalleRemisionListOpmDetalleRemision = em.merge(oldNmProductoOfOpmDetalleRemisionListOpmDetalleRemision);
                }
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListOpmDetalleTraslado : opmProducto.getOpmDetalleTrasladoList()) {
                OpmProducto oldNmProductoOfOpmDetalleTrasladoListOpmDetalleTraslado = opmDetalleTrasladoListOpmDetalleTraslado.getNmProducto();
                opmDetalleTrasladoListOpmDetalleTraslado.setNmProducto(opmProducto);
                opmDetalleTrasladoListOpmDetalleTraslado = em.merge(opmDetalleTrasladoListOpmDetalleTraslado);
                if (oldNmProductoOfOpmDetalleTrasladoListOpmDetalleTraslado != null) {
                    oldNmProductoOfOpmDetalleTrasladoListOpmDetalleTraslado.getOpmDetalleTrasladoList().remove(opmDetalleTrasladoListOpmDetalleTraslado);
                    oldNmProductoOfOpmDetalleTrasladoListOpmDetalleTraslado = em.merge(oldNmProductoOfOpmDetalleTrasladoListOpmDetalleTraslado);
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListOpmDetalleVenta : opmProducto.getOpmDetalleVentaList()) {
                OpmProducto oldNmProductoOfOpmDetalleVentaListOpmDetalleVenta = opmDetalleVentaListOpmDetalleVenta.getNmProducto();
                opmDetalleVentaListOpmDetalleVenta.setNmProducto(opmProducto);
                opmDetalleVentaListOpmDetalleVenta = em.merge(opmDetalleVentaListOpmDetalleVenta);
                if (oldNmProductoOfOpmDetalleVentaListOpmDetalleVenta != null) {
                    oldNmProductoOfOpmDetalleVentaListOpmDetalleVenta.getOpmDetalleVentaList().remove(opmDetalleVentaListOpmDetalleVenta);
                    oldNmProductoOfOpmDetalleVentaListOpmDetalleVenta = em.merge(oldNmProductoOfOpmDetalleVentaListOpmDetalleVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmProducto opmProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmProducto persistentOpmProducto = em.find(OpmProducto.class, opmProducto.getNmCodigo());
            List<OpmInventario> opmInventarioListOld = persistentOpmProducto.getOpmInventarioList();
            List<OpmInventario> opmInventarioListNew = opmProducto.getOpmInventarioList();
            List<OpmInventarioPunto> opmInventarioPuntoListOld = persistentOpmProducto.getOpmInventarioPuntoList();
            List<OpmInventarioPunto> opmInventarioPuntoListNew = opmProducto.getOpmInventarioPuntoList();
            List<OpmDetalleRemision> opmDetalleRemisionListOld = persistentOpmProducto.getOpmDetalleRemisionList();
            List<OpmDetalleRemision> opmDetalleRemisionListNew = opmProducto.getOpmDetalleRemisionList();
            List<OpmDetalleTraslado> opmDetalleTrasladoListOld = persistentOpmProducto.getOpmDetalleTrasladoList();
            List<OpmDetalleTraslado> opmDetalleTrasladoListNew = opmProducto.getOpmDetalleTrasladoList();
            List<OpmDetalleVenta> opmDetalleVentaListOld = persistentOpmProducto.getOpmDetalleVentaList();
            List<OpmDetalleVenta> opmDetalleVentaListNew = opmProducto.getOpmDetalleVentaList();
            List<String> illegalOrphanMessages = null;
            for (OpmInventario opmInventarioListOldOpmInventario : opmInventarioListOld) {
                if (!opmInventarioListNew.contains(opmInventarioListOldOpmInventario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmInventario " + opmInventarioListOldOpmInventario + " since its nmProducto field is not nullable.");
                }
            }
            for (OpmInventarioPunto opmInventarioPuntoListOldOpmInventarioPunto : opmInventarioPuntoListOld) {
                if (!opmInventarioPuntoListNew.contains(opmInventarioPuntoListOldOpmInventarioPunto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmInventarioPunto " + opmInventarioPuntoListOldOpmInventarioPunto + " since its nmProducto field is not nullable.");
                }
            }
            for (OpmDetalleRemision opmDetalleRemisionListOldOpmDetalleRemision : opmDetalleRemisionListOld) {
                if (!opmDetalleRemisionListNew.contains(opmDetalleRemisionListOldOpmDetalleRemision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleRemision " + opmDetalleRemisionListOldOpmDetalleRemision + " since its nmProducto field is not nullable.");
                }
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListOldOpmDetalleTraslado : opmDetalleTrasladoListOld) {
                if (!opmDetalleTrasladoListNew.contains(opmDetalleTrasladoListOldOpmDetalleTraslado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleTraslado " + opmDetalleTrasladoListOldOpmDetalleTraslado + " since its nmProducto field is not nullable.");
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListOldOpmDetalleVenta : opmDetalleVentaListOld) {
                if (!opmDetalleVentaListNew.contains(opmDetalleVentaListOldOpmDetalleVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleVenta " + opmDetalleVentaListOldOpmDetalleVenta + " since its nmProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmInventario> attachedOpmInventarioListNew = new ArrayList<OpmInventario>();
            for (OpmInventario opmInventarioListNewOpmInventarioToAttach : opmInventarioListNew) {
                opmInventarioListNewOpmInventarioToAttach = em.getReference(opmInventarioListNewOpmInventarioToAttach.getClass(), opmInventarioListNewOpmInventarioToAttach.getNmCodigo());
                attachedOpmInventarioListNew.add(opmInventarioListNewOpmInventarioToAttach);
            }
            opmInventarioListNew = attachedOpmInventarioListNew;
            opmProducto.setOpmInventarioList(opmInventarioListNew);
            List<OpmInventarioPunto> attachedOpmInventarioPuntoListNew = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListNewOpmInventarioPuntoToAttach : opmInventarioPuntoListNew) {
                opmInventarioPuntoListNewOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoListNew.add(opmInventarioPuntoListNewOpmInventarioPuntoToAttach);
            }
            opmInventarioPuntoListNew = attachedOpmInventarioPuntoListNew;
            opmProducto.setOpmInventarioPuntoList(opmInventarioPuntoListNew);
            List<OpmDetalleRemision> attachedOpmDetalleRemisionListNew = new ArrayList<OpmDetalleRemision>();
            for (OpmDetalleRemision opmDetalleRemisionListNewOpmDetalleRemisionToAttach : opmDetalleRemisionListNew) {
                opmDetalleRemisionListNewOpmDetalleRemisionToAttach = em.getReference(opmDetalleRemisionListNewOpmDetalleRemisionToAttach.getClass(), opmDetalleRemisionListNewOpmDetalleRemisionToAttach.getNmCodigo());
                attachedOpmDetalleRemisionListNew.add(opmDetalleRemisionListNewOpmDetalleRemisionToAttach);
            }
            opmDetalleRemisionListNew = attachedOpmDetalleRemisionListNew;
            opmProducto.setOpmDetalleRemisionList(opmDetalleRemisionListNew);
            List<OpmDetalleTraslado> attachedOpmDetalleTrasladoListNew = new ArrayList<OpmDetalleTraslado>();
            for (OpmDetalleTraslado opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach : opmDetalleTrasladoListNew) {
                opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach = em.getReference(opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach.getClass(), opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach.getNmCodigo());
                attachedOpmDetalleTrasladoListNew.add(opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach);
            }
            opmDetalleTrasladoListNew = attachedOpmDetalleTrasladoListNew;
            opmProducto.setOpmDetalleTrasladoList(opmDetalleTrasladoListNew);
            List<OpmDetalleVenta> attachedOpmDetalleVentaListNew = new ArrayList<OpmDetalleVenta>();
            for (OpmDetalleVenta opmDetalleVentaListNewOpmDetalleVentaToAttach : opmDetalleVentaListNew) {
                opmDetalleVentaListNewOpmDetalleVentaToAttach = em.getReference(opmDetalleVentaListNewOpmDetalleVentaToAttach.getClass(), opmDetalleVentaListNewOpmDetalleVentaToAttach.getNmCodigo());
                attachedOpmDetalleVentaListNew.add(opmDetalleVentaListNewOpmDetalleVentaToAttach);
            }
            opmDetalleVentaListNew = attachedOpmDetalleVentaListNew;
            opmProducto.setOpmDetalleVentaList(opmDetalleVentaListNew);
            opmProducto = em.merge(opmProducto);
            for (OpmInventario opmInventarioListNewOpmInventario : opmInventarioListNew) {
                if (!opmInventarioListOld.contains(opmInventarioListNewOpmInventario)) {
                    OpmProducto oldNmProductoOfOpmInventarioListNewOpmInventario = opmInventarioListNewOpmInventario.getNmProducto();
                    opmInventarioListNewOpmInventario.setNmProducto(opmProducto);
                    opmInventarioListNewOpmInventario = em.merge(opmInventarioListNewOpmInventario);
                    if (oldNmProductoOfOpmInventarioListNewOpmInventario != null && !oldNmProductoOfOpmInventarioListNewOpmInventario.equals(opmProducto)) {
                        oldNmProductoOfOpmInventarioListNewOpmInventario.getOpmInventarioList().remove(opmInventarioListNewOpmInventario);
                        oldNmProductoOfOpmInventarioListNewOpmInventario = em.merge(oldNmProductoOfOpmInventarioListNewOpmInventario);
                    }
                }
            }
            for (OpmInventarioPunto opmInventarioPuntoListNewOpmInventarioPunto : opmInventarioPuntoListNew) {
                if (!opmInventarioPuntoListOld.contains(opmInventarioPuntoListNewOpmInventarioPunto)) {
                    OpmProducto oldNmProductoOfOpmInventarioPuntoListNewOpmInventarioPunto = opmInventarioPuntoListNewOpmInventarioPunto.getNmProducto();
                    opmInventarioPuntoListNewOpmInventarioPunto.setNmProducto(opmProducto);
                    opmInventarioPuntoListNewOpmInventarioPunto = em.merge(opmInventarioPuntoListNewOpmInventarioPunto);
                    if (oldNmProductoOfOpmInventarioPuntoListNewOpmInventarioPunto != null && !oldNmProductoOfOpmInventarioPuntoListNewOpmInventarioPunto.equals(opmProducto)) {
                        oldNmProductoOfOpmInventarioPuntoListNewOpmInventarioPunto.getOpmInventarioPuntoList().remove(opmInventarioPuntoListNewOpmInventarioPunto);
                        oldNmProductoOfOpmInventarioPuntoListNewOpmInventarioPunto = em.merge(oldNmProductoOfOpmInventarioPuntoListNewOpmInventarioPunto);
                    }
                }
            }
            for (OpmDetalleRemision opmDetalleRemisionListNewOpmDetalleRemision : opmDetalleRemisionListNew) {
                if (!opmDetalleRemisionListOld.contains(opmDetalleRemisionListNewOpmDetalleRemision)) {
                    OpmProducto oldNmProductoOfOpmDetalleRemisionListNewOpmDetalleRemision = opmDetalleRemisionListNewOpmDetalleRemision.getNmProducto();
                    opmDetalleRemisionListNewOpmDetalleRemision.setNmProducto(opmProducto);
                    opmDetalleRemisionListNewOpmDetalleRemision = em.merge(opmDetalleRemisionListNewOpmDetalleRemision);
                    if (oldNmProductoOfOpmDetalleRemisionListNewOpmDetalleRemision != null && !oldNmProductoOfOpmDetalleRemisionListNewOpmDetalleRemision.equals(opmProducto)) {
                        oldNmProductoOfOpmDetalleRemisionListNewOpmDetalleRemision.getOpmDetalleRemisionList().remove(opmDetalleRemisionListNewOpmDetalleRemision);
                        oldNmProductoOfOpmDetalleRemisionListNewOpmDetalleRemision = em.merge(oldNmProductoOfOpmDetalleRemisionListNewOpmDetalleRemision);
                    }
                }
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListNewOpmDetalleTraslado : opmDetalleTrasladoListNew) {
                if (!opmDetalleTrasladoListOld.contains(opmDetalleTrasladoListNewOpmDetalleTraslado)) {
                    OpmProducto oldNmProductoOfOpmDetalleTrasladoListNewOpmDetalleTraslado = opmDetalleTrasladoListNewOpmDetalleTraslado.getNmProducto();
                    opmDetalleTrasladoListNewOpmDetalleTraslado.setNmProducto(opmProducto);
                    opmDetalleTrasladoListNewOpmDetalleTraslado = em.merge(opmDetalleTrasladoListNewOpmDetalleTraslado);
                    if (oldNmProductoOfOpmDetalleTrasladoListNewOpmDetalleTraslado != null && !oldNmProductoOfOpmDetalleTrasladoListNewOpmDetalleTraslado.equals(opmProducto)) {
                        oldNmProductoOfOpmDetalleTrasladoListNewOpmDetalleTraslado.getOpmDetalleTrasladoList().remove(opmDetalleTrasladoListNewOpmDetalleTraslado);
                        oldNmProductoOfOpmDetalleTrasladoListNewOpmDetalleTraslado = em.merge(oldNmProductoOfOpmDetalleTrasladoListNewOpmDetalleTraslado);
                    }
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListNewOpmDetalleVenta : opmDetalleVentaListNew) {
                if (!opmDetalleVentaListOld.contains(opmDetalleVentaListNewOpmDetalleVenta)) {
                    OpmProducto oldNmProductoOfOpmDetalleVentaListNewOpmDetalleVenta = opmDetalleVentaListNewOpmDetalleVenta.getNmProducto();
                    opmDetalleVentaListNewOpmDetalleVenta.setNmProducto(opmProducto);
                    opmDetalleVentaListNewOpmDetalleVenta = em.merge(opmDetalleVentaListNewOpmDetalleVenta);
                    if (oldNmProductoOfOpmDetalleVentaListNewOpmDetalleVenta != null && !oldNmProductoOfOpmDetalleVentaListNewOpmDetalleVenta.equals(opmProducto)) {
                        oldNmProductoOfOpmDetalleVentaListNewOpmDetalleVenta.getOpmDetalleVentaList().remove(opmDetalleVentaListNewOpmDetalleVenta);
                        oldNmProductoOfOpmDetalleVentaListNewOpmDetalleVenta = em.merge(oldNmProductoOfOpmDetalleVentaListNewOpmDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmProducto.getNmCodigo();
                if (findOpmProducto(id) == null) {
                    throw new NonexistentEntityException("The opmProducto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmProducto opmProducto;
            try {
                opmProducto = em.getReference(OpmProducto.class, id);
                opmProducto.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmInventario> opmInventarioListOrphanCheck = opmProducto.getOpmInventarioList();
            for (OpmInventario opmInventarioListOrphanCheckOpmInventario : opmInventarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmProducto (" + opmProducto + ") cannot be destroyed since the OpmInventario " + opmInventarioListOrphanCheckOpmInventario + " in its opmInventarioList field has a non-nullable nmProducto field.");
            }
            List<OpmInventarioPunto> opmInventarioPuntoListOrphanCheck = opmProducto.getOpmInventarioPuntoList();
            for (OpmInventarioPunto opmInventarioPuntoListOrphanCheckOpmInventarioPunto : opmInventarioPuntoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmProducto (" + opmProducto + ") cannot be destroyed since the OpmInventarioPunto " + opmInventarioPuntoListOrphanCheckOpmInventarioPunto + " in its opmInventarioPuntoList field has a non-nullable nmProducto field.");
            }
            List<OpmDetalleRemision> opmDetalleRemisionListOrphanCheck = opmProducto.getOpmDetalleRemisionList();
            for (OpmDetalleRemision opmDetalleRemisionListOrphanCheckOpmDetalleRemision : opmDetalleRemisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmProducto (" + opmProducto + ") cannot be destroyed since the OpmDetalleRemision " + opmDetalleRemisionListOrphanCheckOpmDetalleRemision + " in its opmDetalleRemisionList field has a non-nullable nmProducto field.");
            }
            List<OpmDetalleTraslado> opmDetalleTrasladoListOrphanCheck = opmProducto.getOpmDetalleTrasladoList();
            for (OpmDetalleTraslado opmDetalleTrasladoListOrphanCheckOpmDetalleTraslado : opmDetalleTrasladoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmProducto (" + opmProducto + ") cannot be destroyed since the OpmDetalleTraslado " + opmDetalleTrasladoListOrphanCheckOpmDetalleTraslado + " in its opmDetalleTrasladoList field has a non-nullable nmProducto field.");
            }
            List<OpmDetalleVenta> opmDetalleVentaListOrphanCheck = opmProducto.getOpmDetalleVentaList();
            for (OpmDetalleVenta opmDetalleVentaListOrphanCheckOpmDetalleVenta : opmDetalleVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmProducto (" + opmProducto + ") cannot be destroyed since the OpmDetalleVenta " + opmDetalleVentaListOrphanCheckOpmDetalleVenta + " in its opmDetalleVentaList field has a non-nullable nmProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(opmProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmProducto> findOpmProductoEntities() {
        return findOpmProductoEntities(true, -1, -1);
    }

    public List<OpmProducto> findOpmProductoEntities(int maxResults, int firstResult) {
        return findOpmProductoEntities(false, maxResults, firstResult);
    }

    private List<OpmProducto> findOpmProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmProducto.class));
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

    public OpmProducto findOpmProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmProducto> rt = cq.from(OpmProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
