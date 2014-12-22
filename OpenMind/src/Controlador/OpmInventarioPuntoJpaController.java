/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmInventarioPunto;
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
public class OpmInventarioPuntoJpaController implements Serializable {

    public OpmInventarioPuntoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmInventarioPunto opmInventarioPunto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opmInventarioPunto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmInventarioPunto opmInventarioPunto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opmInventarioPunto = em.merge(opmInventarioPunto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmInventarioPunto.getNmCodigo();
                if (findOpmInventarioPunto(id) == null) {
                    throw new NonexistentEntityException("The opmInventarioPunto with id " + id + " no longer exists.");
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
            OpmInventarioPunto opmInventarioPunto;
            try {
                opmInventarioPunto = em.getReference(OpmInventarioPunto.class, id);
                opmInventarioPunto.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmInventarioPunto with id " + id + " no longer exists.", enfe);
            }
            em.remove(opmInventarioPunto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmInventarioPunto> findOpmInventarioPuntoEntities() {
        return findOpmInventarioPuntoEntities(true, -1, -1);
    }

    public List<OpmInventarioPunto> findOpmInventarioPuntoEntities(int maxResults, int firstResult) {
        return findOpmInventarioPuntoEntities(false, maxResults, firstResult);
    }

    private List<OpmInventarioPunto> findOpmInventarioPuntoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmInventarioPunto.class));
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

    public OpmInventarioPunto findOpmInventarioPunto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmInventarioPunto.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmInventarioPuntoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmInventarioPunto> rt = cq.from(OpmInventarioPunto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
