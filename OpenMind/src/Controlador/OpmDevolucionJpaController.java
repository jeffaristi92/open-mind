/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmDetalleVenta;
import Modelo.OpmDevolucion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmDevolucionJpaController implements Serializable {

    public OpmDevolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmDevolucion opmDevolucion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmDetalleVenta nmDetalle = opmDevolucion.getNmDetalle();
            if (nmDetalle != null) {
                nmDetalle = em.getReference(nmDetalle.getClass(), nmDetalle.getNmCodigo());
                opmDevolucion.setNmDetalle(nmDetalle);
            }
            em.persist(opmDevolucion);
            if (nmDetalle != null) {
                nmDetalle.getOpmDevolucionList().add(opmDevolucion);
                nmDetalle = em.merge(nmDetalle);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmDevolucion opmDevolucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmDevolucion persistentOpmDevolucion = em.find(OpmDevolucion.class, opmDevolucion.getNmCodigo());
            OpmDetalleVenta nmDetalleOld = persistentOpmDevolucion.getNmDetalle();
            OpmDetalleVenta nmDetalleNew = opmDevolucion.getNmDetalle();
            if (nmDetalleNew != null) {
                nmDetalleNew = em.getReference(nmDetalleNew.getClass(), nmDetalleNew.getNmCodigo());
                opmDevolucion.setNmDetalle(nmDetalleNew);
            }
            opmDevolucion = em.merge(opmDevolucion);
            if (nmDetalleOld != null && !nmDetalleOld.equals(nmDetalleNew)) {
                nmDetalleOld.getOpmDevolucionList().remove(opmDevolucion);
                nmDetalleOld = em.merge(nmDetalleOld);
            }
            if (nmDetalleNew != null && !nmDetalleNew.equals(nmDetalleOld)) {
                nmDetalleNew.getOpmDevolucionList().add(opmDevolucion);
                nmDetalleNew = em.merge(nmDetalleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmDevolucion.getNmCodigo();
                if (findOpmDevolucion(id) == null) {
                    throw new NonexistentEntityException("The opmDevolucion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmDevolucion opmDevolucion;
            try {
                opmDevolucion = em.getReference(OpmDevolucion.class, id);
                opmDevolucion.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmDevolucion with id " + id + " no longer exists.", enfe);
            }
            OpmDetalleVenta nmDetalle = opmDevolucion.getNmDetalle();
            if (nmDetalle != null) {
                nmDetalle.getOpmDevolucionList().remove(opmDevolucion);
                nmDetalle = em.merge(nmDetalle);
            }
            em.remove(opmDevolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmDevolucion> findOpmDevolucionEntities() {
        return findOpmDevolucionEntities(true, -1, -1);
    }

    public List<OpmDevolucion> findOpmDevolucionEntities(int maxResults, int firstResult) {
        return findOpmDevolucionEntities(false, maxResults, firstResult);
    }

    private List<OpmDevolucion> findOpmDevolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmDevolucion.class));
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

    public OpmDevolucion findOpmDevolucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmDevolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmDevolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmDevolucion> rt = cq.from(OpmDevolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
