/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmGastos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmTipoGasto;
import Modelo.OpmPuntoVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmGastosJpaController implements Serializable {

    public OpmGastosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmGastos opmGastos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmTipoGasto nmTipo = opmGastos.getNmTipo();
            if (nmTipo != null) {
                nmTipo = em.getReference(nmTipo.getClass(), nmTipo.getNmCodigo());
                opmGastos.setNmTipo(nmTipo);
            }
            OpmPuntoVenta nmPuntoVenta = opmGastos.getNmPuntoVenta();
            if (nmPuntoVenta != null) {
                nmPuntoVenta = em.getReference(nmPuntoVenta.getClass(), nmPuntoVenta.getNmCodigo());
                opmGastos.setNmPuntoVenta(nmPuntoVenta);
            }
            em.persist(opmGastos);
            if (nmTipo != null) {
                nmTipo.getOpmGastosList().add(opmGastos);
                nmTipo = em.merge(nmTipo);
            }
            if (nmPuntoVenta != null) {
                nmPuntoVenta.getOpmGastosList().add(opmGastos);
                nmPuntoVenta = em.merge(nmPuntoVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmGastos opmGastos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmGastos persistentOpmGastos = em.find(OpmGastos.class, opmGastos.getNmCodigo());
            OpmTipoGasto nmTipoOld = persistentOpmGastos.getNmTipo();
            OpmTipoGasto nmTipoNew = opmGastos.getNmTipo();
            OpmPuntoVenta nmPuntoVentaOld = persistentOpmGastos.getNmPuntoVenta();
            OpmPuntoVenta nmPuntoVentaNew = opmGastos.getNmPuntoVenta();
            if (nmTipoNew != null) {
                nmTipoNew = em.getReference(nmTipoNew.getClass(), nmTipoNew.getNmCodigo());
                opmGastos.setNmTipo(nmTipoNew);
            }
            if (nmPuntoVentaNew != null) {
                nmPuntoVentaNew = em.getReference(nmPuntoVentaNew.getClass(), nmPuntoVentaNew.getNmCodigo());
                opmGastos.setNmPuntoVenta(nmPuntoVentaNew);
            }
            opmGastos = em.merge(opmGastos);
            if (nmTipoOld != null && !nmTipoOld.equals(nmTipoNew)) {
                nmTipoOld.getOpmGastosList().remove(opmGastos);
                nmTipoOld = em.merge(nmTipoOld);
            }
            if (nmTipoNew != null && !nmTipoNew.equals(nmTipoOld)) {
                nmTipoNew.getOpmGastosList().add(opmGastos);
                nmTipoNew = em.merge(nmTipoNew);
            }
            if (nmPuntoVentaOld != null && !nmPuntoVentaOld.equals(nmPuntoVentaNew)) {
                nmPuntoVentaOld.getOpmGastosList().remove(opmGastos);
                nmPuntoVentaOld = em.merge(nmPuntoVentaOld);
            }
            if (nmPuntoVentaNew != null && !nmPuntoVentaNew.equals(nmPuntoVentaOld)) {
                nmPuntoVentaNew.getOpmGastosList().add(opmGastos);
                nmPuntoVentaNew = em.merge(nmPuntoVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmGastos.getNmCodigo();
                if (findOpmGastos(id) == null) {
                    throw new NonexistentEntityException("The opmGastos with id " + id + " no longer exists.");
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
            OpmGastos opmGastos;
            try {
                opmGastos = em.getReference(OpmGastos.class, id);
                opmGastos.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmGastos with id " + id + " no longer exists.", enfe);
            }
            OpmTipoGasto nmTipo = opmGastos.getNmTipo();
            if (nmTipo != null) {
                nmTipo.getOpmGastosList().remove(opmGastos);
                nmTipo = em.merge(nmTipo);
            }
            OpmPuntoVenta nmPuntoVenta = opmGastos.getNmPuntoVenta();
            if (nmPuntoVenta != null) {
                nmPuntoVenta.getOpmGastosList().remove(opmGastos);
                nmPuntoVenta = em.merge(nmPuntoVenta);
            }
            em.remove(opmGastos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmGastos> findOpmGastosEntities() {
        return findOpmGastosEntities(true, -1, -1);
    }

    public List<OpmGastos> findOpmGastosEntities(int maxResults, int firstResult) {
        return findOpmGastosEntities(false, maxResults, firstResult);
    }

    private List<OpmGastos> findOpmGastosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmGastos.class));
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

    public OpmGastos findOpmGastos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmGastos.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmGastosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmGastos> rt = cq.from(OpmGastos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
