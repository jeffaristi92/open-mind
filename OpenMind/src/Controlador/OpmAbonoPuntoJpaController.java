/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmAbonoPunto;
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
public class OpmAbonoPuntoJpaController implements Serializable {

    public OpmAbonoPuntoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmAbonoPunto opmAbonoPunto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opmAbonoPunto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmAbonoPunto opmAbonoPunto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opmAbonoPunto = em.merge(opmAbonoPunto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmAbonoPunto.getNmCodigo();
                if (findOpmAbonoPunto(id) == null) {
                    throw new NonexistentEntityException("The opmAbonoPunto with id " + id + " no longer exists.");
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
            OpmAbonoPunto opmAbonoPunto;
            try {
                opmAbonoPunto = em.getReference(OpmAbonoPunto.class, id);
                opmAbonoPunto.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmAbonoPunto with id " + id + " no longer exists.", enfe);
            }
            em.remove(opmAbonoPunto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmAbonoPunto> findOpmAbonoPuntoEntities() {
        return findOpmAbonoPuntoEntities(true, -1, -1);
    }

    public List<OpmAbonoPunto> findOpmAbonoPuntoEntities(int maxResults, int firstResult) {
        return findOpmAbonoPuntoEntities(false, maxResults, firstResult);
    }

    private List<OpmAbonoPunto> findOpmAbonoPuntoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmAbonoPunto.class));
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

    public OpmAbonoPunto findOpmAbonoPunto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmAbonoPunto.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmAbonoPuntoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmAbonoPunto> rt = cq.from(OpmAbonoPunto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
