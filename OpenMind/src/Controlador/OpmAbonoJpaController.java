/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmAbono;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmAbonoJpaController implements Serializable {

    public OpmAbonoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmAbono opmAbono) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmVenta nmVenta = opmAbono.getNmVenta();
            if (nmVenta != null) {
                nmVenta = em.getReference(nmVenta.getClass(), nmVenta.getNmCodigo());
                opmAbono.setNmVenta(nmVenta);
            }
            em.persist(opmAbono);
            if (nmVenta != null) {
                nmVenta.getOpmAbonoList().add(opmAbono);
                nmVenta = em.merge(nmVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmAbono opmAbono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmAbono persistentOpmAbono = em.find(OpmAbono.class, opmAbono.getNmCodigo());
            OpmVenta nmVentaOld = persistentOpmAbono.getNmVenta();
            OpmVenta nmVentaNew = opmAbono.getNmVenta();
            if (nmVentaNew != null) {
                nmVentaNew = em.getReference(nmVentaNew.getClass(), nmVentaNew.getNmCodigo());
                opmAbono.setNmVenta(nmVentaNew);
            }
            opmAbono = em.merge(opmAbono);
            if (nmVentaOld != null && !nmVentaOld.equals(nmVentaNew)) {
                nmVentaOld.getOpmAbonoList().remove(opmAbono);
                nmVentaOld = em.merge(nmVentaOld);
            }
            if (nmVentaNew != null && !nmVentaNew.equals(nmVentaOld)) {
                nmVentaNew.getOpmAbonoList().add(opmAbono);
                nmVentaNew = em.merge(nmVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmAbono.getNmCodigo();
                if (findOpmAbono(id) == null) {
                    throw new NonexistentEntityException("The opmAbono with id " + id + " no longer exists.");
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
            OpmAbono opmAbono;
            try {
                opmAbono = em.getReference(OpmAbono.class, id);
                opmAbono.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmAbono with id " + id + " no longer exists.", enfe);
            }
            OpmVenta nmVenta = opmAbono.getNmVenta();
            if (nmVenta != null) {
                nmVenta.getOpmAbonoList().remove(opmAbono);
                nmVenta = em.merge(nmVenta);
            }
            em.remove(opmAbono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmAbono> findOpmAbonoEntities() {
        return findOpmAbonoEntities(true, -1, -1);
    }

    public List<OpmAbono> findOpmAbonoEntities(int maxResults, int firstResult) {
        return findOpmAbonoEntities(false, maxResults, firstResult);
    }

    private List<OpmAbono> findOpmAbonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmAbono.class));
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

    public OpmAbono findOpmAbono(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmAbono.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmAbonoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmAbono> rt = cq.from(OpmAbono.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
