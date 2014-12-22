/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmDetalleRemision;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmDetalleRemisionJpaController implements Serializable {

    public OpmDetalleRemisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmDetalleRemision opmDetalleRemision) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opmDetalleRemision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmDetalleRemision opmDetalleRemision) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opmDetalleRemision = em.merge(opmDetalleRemision);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmDetalleRemision.getNmCodigo();
                if (findOpmDetalleRemision(id) == null) {
                    throw new NonexistentEntityException("The opmDetalleRemision with id " + id + " no longer exists.");
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
            OpmDetalleRemision opmDetalleRemision;
            try {
                opmDetalleRemision = em.getReference(OpmDetalleRemision.class, id);
                opmDetalleRemision.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmDetalleRemision with id " + id + " no longer exists.", enfe);
            }
            em.remove(opmDetalleRemision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmDetalleRemision> findOpmDetalleRemisionEntities() {
        return findOpmDetalleRemisionEntities(true, -1, -1);
    }

    public List<OpmDetalleRemision> findOpmDetalleRemisionEntities(int maxResults, int firstResult) {
        return findOpmDetalleRemisionEntities(false, maxResults, firstResult);
    }

    private List<OpmDetalleRemision> findOpmDetalleRemisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmDetalleRemision.class));
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

    public OpmDetalleRemision findOpmDetalleRemision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmDetalleRemision.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmDetalleRemisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmDetalleRemision> rt = cq.from(OpmDetalleRemision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
