/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmDetalleLote;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmLote;
import Modelo.OpmProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmDetalleLoteJpaController implements Serializable {

    public OpmDetalleLoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmDetalleLote opmDetalleLote) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmLote nmLote = opmDetalleLote.getNmLote();
            if (nmLote != null) {
                nmLote = em.getReference(nmLote.getClass(), nmLote.getNmCodigo());
                opmDetalleLote.setNmLote(nmLote);
            }
            OpmProducto nmProducto = opmDetalleLote.getNmProducto();
            if (nmProducto != null) {
                nmProducto = em.getReference(nmProducto.getClass(), nmProducto.getNmCodigo());
                opmDetalleLote.setNmProducto(nmProducto);
            }
            em.persist(opmDetalleLote);
            if (nmLote != null) {
                nmLote.getOpmDetalleLoteList().add(opmDetalleLote);
                nmLote = em.merge(nmLote);
            }
            if (nmProducto != null) {
                nmProducto.getOpmDetalleLoteList().add(opmDetalleLote);
                nmProducto = em.merge(nmProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmDetalleLote opmDetalleLote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmDetalleLote persistentOpmDetalleLote = em.find(OpmDetalleLote.class, opmDetalleLote.getNmCodigo());
            OpmLote nmLoteOld = persistentOpmDetalleLote.getNmLote();
            OpmLote nmLoteNew = opmDetalleLote.getNmLote();
            OpmProducto nmProductoOld = persistentOpmDetalleLote.getNmProducto();
            OpmProducto nmProductoNew = opmDetalleLote.getNmProducto();
            if (nmLoteNew != null) {
                nmLoteNew = em.getReference(nmLoteNew.getClass(), nmLoteNew.getNmCodigo());
                opmDetalleLote.setNmLote(nmLoteNew);
            }
            if (nmProductoNew != null) {
                nmProductoNew = em.getReference(nmProductoNew.getClass(), nmProductoNew.getNmCodigo());
                opmDetalleLote.setNmProducto(nmProductoNew);
            }
            opmDetalleLote = em.merge(opmDetalleLote);
            if (nmLoteOld != null && !nmLoteOld.equals(nmLoteNew)) {
                nmLoteOld.getOpmDetalleLoteList().remove(opmDetalleLote);
                nmLoteOld = em.merge(nmLoteOld);
            }
            if (nmLoteNew != null && !nmLoteNew.equals(nmLoteOld)) {
                nmLoteNew.getOpmDetalleLoteList().add(opmDetalleLote);
                nmLoteNew = em.merge(nmLoteNew);
            }
            if (nmProductoOld != null && !nmProductoOld.equals(nmProductoNew)) {
                nmProductoOld.getOpmDetalleLoteList().remove(opmDetalleLote);
                nmProductoOld = em.merge(nmProductoOld);
            }
            if (nmProductoNew != null && !nmProductoNew.equals(nmProductoOld)) {
                nmProductoNew.getOpmDetalleLoteList().add(opmDetalleLote);
                nmProductoNew = em.merge(nmProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmDetalleLote.getNmCodigo();
                if (findOpmDetalleLote(id) == null) {
                    throw new NonexistentEntityException("The opmDetalleLote with id " + id + " no longer exists.");
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
            OpmDetalleLote opmDetalleLote;
            try {
                opmDetalleLote = em.getReference(OpmDetalleLote.class, id);
                opmDetalleLote.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmDetalleLote with id " + id + " no longer exists.", enfe);
            }
            OpmLote nmLote = opmDetalleLote.getNmLote();
            if (nmLote != null) {
                nmLote.getOpmDetalleLoteList().remove(opmDetalleLote);
                nmLote = em.merge(nmLote);
            }
            OpmProducto nmProducto = opmDetalleLote.getNmProducto();
            if (nmProducto != null) {
                nmProducto.getOpmDetalleLoteList().remove(opmDetalleLote);
                nmProducto = em.merge(nmProducto);
            }
            em.remove(opmDetalleLote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmDetalleLote> findOpmDetalleLoteEntities() {
        return findOpmDetalleLoteEntities(true, -1, -1);
    }

    public List<OpmDetalleLote> findOpmDetalleLoteEntities(int maxResults, int firstResult) {
        return findOpmDetalleLoteEntities(false, maxResults, firstResult);
    }

    private List<OpmDetalleLote> findOpmDetalleLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmDetalleLote.class));
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

    public OpmDetalleLote findOpmDetalleLote(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmDetalleLote.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmDetalleLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmDetalleLote> rt = cq.from(OpmDetalleLote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
