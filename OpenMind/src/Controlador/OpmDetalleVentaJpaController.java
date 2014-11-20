/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmDetalleVenta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmVenta;
import Modelo.OpmProducto;
import Modelo.OpmDevolucion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmDetalleVentaJpaController implements Serializable {

    public OpmDetalleVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmDetalleVenta opmDetalleVenta) {
        if (opmDetalleVenta.getOpmDevolucionList() == null) {
            opmDetalleVenta.setOpmDevolucionList(new ArrayList<OpmDevolucion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmVenta nmVenta = opmDetalleVenta.getNmVenta();
            if (nmVenta != null) {
                nmVenta = em.getReference(nmVenta.getClass(), nmVenta.getNmCodigo());
                opmDetalleVenta.setNmVenta(nmVenta);
            }
            OpmProducto nmProducto = opmDetalleVenta.getNmProducto();
            if (nmProducto != null) {
                nmProducto = em.getReference(nmProducto.getClass(), nmProducto.getNmCodigo());
                opmDetalleVenta.setNmProducto(nmProducto);
            }
            List<OpmDevolucion> attachedOpmDevolucionList = new ArrayList<OpmDevolucion>();
            for (OpmDevolucion opmDevolucionListOpmDevolucionToAttach : opmDetalleVenta.getOpmDevolucionList()) {
                opmDevolucionListOpmDevolucionToAttach = em.getReference(opmDevolucionListOpmDevolucionToAttach.getClass(), opmDevolucionListOpmDevolucionToAttach.getNmCodigo());
                attachedOpmDevolucionList.add(opmDevolucionListOpmDevolucionToAttach);
            }
            opmDetalleVenta.setOpmDevolucionList(attachedOpmDevolucionList);
            em.persist(opmDetalleVenta);
            if (nmVenta != null) {
                nmVenta.getOpmDetalleVentaList().add(opmDetalleVenta);
                nmVenta = em.merge(nmVenta);
            }
            if (nmProducto != null) {
                nmProducto.getOpmDetalleVentaList().add(opmDetalleVenta);
                nmProducto = em.merge(nmProducto);
            }
            for (OpmDevolucion opmDevolucionListOpmDevolucion : opmDetalleVenta.getOpmDevolucionList()) {
                OpmDetalleVenta oldNmDetalleOfOpmDevolucionListOpmDevolucion = opmDevolucionListOpmDevolucion.getNmDetalle();
                opmDevolucionListOpmDevolucion.setNmDetalle(opmDetalleVenta);
                opmDevolucionListOpmDevolucion = em.merge(opmDevolucionListOpmDevolucion);
                if (oldNmDetalleOfOpmDevolucionListOpmDevolucion != null) {
                    oldNmDetalleOfOpmDevolucionListOpmDevolucion.getOpmDevolucionList().remove(opmDevolucionListOpmDevolucion);
                    oldNmDetalleOfOpmDevolucionListOpmDevolucion = em.merge(oldNmDetalleOfOpmDevolucionListOpmDevolucion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmDetalleVenta opmDetalleVenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmDetalleVenta persistentOpmDetalleVenta = em.find(OpmDetalleVenta.class, opmDetalleVenta.getNmCodigo());
            OpmVenta nmVentaOld = persistentOpmDetalleVenta.getNmVenta();
            OpmVenta nmVentaNew = opmDetalleVenta.getNmVenta();
            OpmProducto nmProductoOld = persistentOpmDetalleVenta.getNmProducto();
            OpmProducto nmProductoNew = opmDetalleVenta.getNmProducto();
            List<OpmDevolucion> opmDevolucionListOld = persistentOpmDetalleVenta.getOpmDevolucionList();
            List<OpmDevolucion> opmDevolucionListNew = opmDetalleVenta.getOpmDevolucionList();
            List<String> illegalOrphanMessages = null;
            for (OpmDevolucion opmDevolucionListOldOpmDevolucion : opmDevolucionListOld) {
                if (!opmDevolucionListNew.contains(opmDevolucionListOldOpmDevolucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDevolucion " + opmDevolucionListOldOpmDevolucion + " since its nmDetalle field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmVentaNew != null) {
                nmVentaNew = em.getReference(nmVentaNew.getClass(), nmVentaNew.getNmCodigo());
                opmDetalleVenta.setNmVenta(nmVentaNew);
            }
            if (nmProductoNew != null) {
                nmProductoNew = em.getReference(nmProductoNew.getClass(), nmProductoNew.getNmCodigo());
                opmDetalleVenta.setNmProducto(nmProductoNew);
            }
            List<OpmDevolucion> attachedOpmDevolucionListNew = new ArrayList<OpmDevolucion>();
            for (OpmDevolucion opmDevolucionListNewOpmDevolucionToAttach : opmDevolucionListNew) {
                opmDevolucionListNewOpmDevolucionToAttach = em.getReference(opmDevolucionListNewOpmDevolucionToAttach.getClass(), opmDevolucionListNewOpmDevolucionToAttach.getNmCodigo());
                attachedOpmDevolucionListNew.add(opmDevolucionListNewOpmDevolucionToAttach);
            }
            opmDevolucionListNew = attachedOpmDevolucionListNew;
            opmDetalleVenta.setOpmDevolucionList(opmDevolucionListNew);
            opmDetalleVenta = em.merge(opmDetalleVenta);
            if (nmVentaOld != null && !nmVentaOld.equals(nmVentaNew)) {
                nmVentaOld.getOpmDetalleVentaList().remove(opmDetalleVenta);
                nmVentaOld = em.merge(nmVentaOld);
            }
            if (nmVentaNew != null && !nmVentaNew.equals(nmVentaOld)) {
                nmVentaNew.getOpmDetalleVentaList().add(opmDetalleVenta);
                nmVentaNew = em.merge(nmVentaNew);
            }
            if (nmProductoOld != null && !nmProductoOld.equals(nmProductoNew)) {
                nmProductoOld.getOpmDetalleVentaList().remove(opmDetalleVenta);
                nmProductoOld = em.merge(nmProductoOld);
            }
            if (nmProductoNew != null && !nmProductoNew.equals(nmProductoOld)) {
                nmProductoNew.getOpmDetalleVentaList().add(opmDetalleVenta);
                nmProductoNew = em.merge(nmProductoNew);
            }
            for (OpmDevolucion opmDevolucionListNewOpmDevolucion : opmDevolucionListNew) {
                if (!opmDevolucionListOld.contains(opmDevolucionListNewOpmDevolucion)) {
                    OpmDetalleVenta oldNmDetalleOfOpmDevolucionListNewOpmDevolucion = opmDevolucionListNewOpmDevolucion.getNmDetalle();
                    opmDevolucionListNewOpmDevolucion.setNmDetalle(opmDetalleVenta);
                    opmDevolucionListNewOpmDevolucion = em.merge(opmDevolucionListNewOpmDevolucion);
                    if (oldNmDetalleOfOpmDevolucionListNewOpmDevolucion != null && !oldNmDetalleOfOpmDevolucionListNewOpmDevolucion.equals(opmDetalleVenta)) {
                        oldNmDetalleOfOpmDevolucionListNewOpmDevolucion.getOpmDevolucionList().remove(opmDevolucionListNewOpmDevolucion);
                        oldNmDetalleOfOpmDevolucionListNewOpmDevolucion = em.merge(oldNmDetalleOfOpmDevolucionListNewOpmDevolucion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmDetalleVenta.getNmCodigo();
                if (findOpmDetalleVenta(id) == null) {
                    throw new NonexistentEntityException("The opmDetalleVenta with id " + id + " no longer exists.");
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
            OpmDetalleVenta opmDetalleVenta;
            try {
                opmDetalleVenta = em.getReference(OpmDetalleVenta.class, id);
                opmDetalleVenta.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmDetalleVenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmDevolucion> opmDevolucionListOrphanCheck = opmDetalleVenta.getOpmDevolucionList();
            for (OpmDevolucion opmDevolucionListOrphanCheckOpmDevolucion : opmDevolucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmDetalleVenta (" + opmDetalleVenta + ") cannot be destroyed since the OpmDevolucion " + opmDevolucionListOrphanCheckOpmDevolucion + " in its opmDevolucionList field has a non-nullable nmDetalle field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmVenta nmVenta = opmDetalleVenta.getNmVenta();
            if (nmVenta != null) {
                nmVenta.getOpmDetalleVentaList().remove(opmDetalleVenta);
                nmVenta = em.merge(nmVenta);
            }
            OpmProducto nmProducto = opmDetalleVenta.getNmProducto();
            if (nmProducto != null) {
                nmProducto.getOpmDetalleVentaList().remove(opmDetalleVenta);
                nmProducto = em.merge(nmProducto);
            }
            em.remove(opmDetalleVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmDetalleVenta> findOpmDetalleVentaEntities() {
        return findOpmDetalleVentaEntities(true, -1, -1);
    }

    public List<OpmDetalleVenta> findOpmDetalleVentaEntities(int maxResults, int firstResult) {
        return findOpmDetalleVentaEntities(false, maxResults, firstResult);
    }

    private List<OpmDetalleVenta> findOpmDetalleVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmDetalleVenta.class));
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

    public OpmDetalleVenta findOpmDetalleVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmDetalleVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmDetalleVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmDetalleVenta> rt = cq.from(OpmDetalleVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
