/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmAbonoCliente;
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
public class OpmAbonoClienteJpaController implements Serializable {

    public OpmAbonoClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmAbonoCliente opmAbonoCliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmVenta nmVenta = opmAbonoCliente.getNmVenta();
            if (nmVenta != null) {
                nmVenta = em.getReference(nmVenta.getClass(), nmVenta.getNmCodigo());
                opmAbonoCliente.setNmVenta(nmVenta);
            }
            em.persist(opmAbonoCliente);
            if (nmVenta != null) {
                nmVenta.getOpmAbonoClienteList().add(opmAbonoCliente);
                nmVenta = em.merge(nmVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmAbonoCliente opmAbonoCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmAbonoCliente persistentOpmAbonoCliente = em.find(OpmAbonoCliente.class, opmAbonoCliente.getNmCodigo());
            OpmVenta nmVentaOld = persistentOpmAbonoCliente.getNmVenta();
            OpmVenta nmVentaNew = opmAbonoCliente.getNmVenta();
            if (nmVentaNew != null) {
                nmVentaNew = em.getReference(nmVentaNew.getClass(), nmVentaNew.getNmCodigo());
                opmAbonoCliente.setNmVenta(nmVentaNew);
            }
            opmAbonoCliente = em.merge(opmAbonoCliente);
            if (nmVentaOld != null && !nmVentaOld.equals(nmVentaNew)) {
                nmVentaOld.getOpmAbonoClienteList().remove(opmAbonoCliente);
                nmVentaOld = em.merge(nmVentaOld);
            }
            if (nmVentaNew != null && !nmVentaNew.equals(nmVentaOld)) {
                nmVentaNew.getOpmAbonoClienteList().add(opmAbonoCliente);
                nmVentaNew = em.merge(nmVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmAbonoCliente.getNmCodigo();
                if (findOpmAbonoCliente(id) == null) {
                    throw new NonexistentEntityException("The opmAbonoCliente with id " + id + " no longer exists.");
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
            OpmAbonoCliente opmAbonoCliente;
            try {
                opmAbonoCliente = em.getReference(OpmAbonoCliente.class, id);
                opmAbonoCliente.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmAbonoCliente with id " + id + " no longer exists.", enfe);
            }
            OpmVenta nmVenta = opmAbonoCliente.getNmVenta();
            if (nmVenta != null) {
                nmVenta.getOpmAbonoClienteList().remove(opmAbonoCliente);
                nmVenta = em.merge(nmVenta);
            }
            em.remove(opmAbonoCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmAbonoCliente> findOpmAbonoClienteEntities() {
        return findOpmAbonoClienteEntities(true, -1, -1);
    }

    public List<OpmAbonoCliente> findOpmAbonoClienteEntities(int maxResults, int firstResult) {
        return findOpmAbonoClienteEntities(false, maxResults, firstResult);
    }

    private List<OpmAbonoCliente> findOpmAbonoClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmAbonoCliente.class));
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

    public OpmAbonoCliente findOpmAbonoCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmAbonoCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmAbonoClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmAbonoCliente> rt = cq.from(OpmAbonoCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
