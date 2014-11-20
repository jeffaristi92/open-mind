/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmInventario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmInventarioJpaController implements Serializable {

    public OpmInventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmInventario opmInventario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmProducto nmProducto = opmInventario.getNmProducto();
            if (nmProducto != null) {
                nmProducto = em.getReference(nmProducto.getClass(), nmProducto.getNmCodigo());
                opmInventario.setNmProducto(nmProducto);
            }
            em.persist(opmInventario);
            if (nmProducto != null) {
                nmProducto.getOpmInventarioList().add(opmInventario);
                nmProducto = em.merge(nmProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmInventario opmInventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmInventario persistentOpmInventario = em.find(OpmInventario.class, opmInventario.getNmCodigo());
            OpmProducto nmProductoOld = persistentOpmInventario.getNmProducto();
            OpmProducto nmProductoNew = opmInventario.getNmProducto();
            if (nmProductoNew != null) {
                nmProductoNew = em.getReference(nmProductoNew.getClass(), nmProductoNew.getNmCodigo());
                opmInventario.setNmProducto(nmProductoNew);
            }
            opmInventario = em.merge(opmInventario);
            if (nmProductoOld != null && !nmProductoOld.equals(nmProductoNew)) {
                nmProductoOld.getOpmInventarioList().remove(opmInventario);
                nmProductoOld = em.merge(nmProductoOld);
            }
            if (nmProductoNew != null && !nmProductoNew.equals(nmProductoOld)) {
                nmProductoNew.getOpmInventarioList().add(opmInventario);
                nmProductoNew = em.merge(nmProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmInventario.getNmCodigo();
                if (findOpmInventario(id) == null) {
                    throw new NonexistentEntityException("The opmInventario with id " + id + " no longer exists.");
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
            OpmInventario opmInventario;
            try {
                opmInventario = em.getReference(OpmInventario.class, id);
                opmInventario.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmInventario with id " + id + " no longer exists.", enfe);
            }
            OpmProducto nmProducto = opmInventario.getNmProducto();
            if (nmProducto != null) {
                nmProducto.getOpmInventarioList().remove(opmInventario);
                nmProducto = em.merge(nmProducto);
            }
            em.remove(opmInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmInventario> findOpmInventarioEntities() {
        return findOpmInventarioEntities(true, -1, -1);
    }

    public List<OpmInventario> findOpmInventarioEntities(int maxResults, int firstResult) {
        return findOpmInventarioEntities(false, maxResults, firstResult);
    }

    private List<OpmInventario> findOpmInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmInventario.class));
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

    public OpmInventario findOpmInventario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmInventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmInventario> rt = cq.from(OpmInventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
