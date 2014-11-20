/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmDetalleTraslado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmTraslado;
import Modelo.OpmProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmDetalleTrasladoJpaController implements Serializable {

    public OpmDetalleTrasladoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmDetalleTraslado opmDetalleTraslado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmTraslado nmTraslado = opmDetalleTraslado.getNmTraslado();
            if (nmTraslado != null) {
                nmTraslado = em.getReference(nmTraslado.getClass(), nmTraslado.getNmCodigo());
                opmDetalleTraslado.setNmTraslado(nmTraslado);
            }
            OpmProducto nmProducto = opmDetalleTraslado.getNmProducto();
            if (nmProducto != null) {
                nmProducto = em.getReference(nmProducto.getClass(), nmProducto.getNmCodigo());
                opmDetalleTraslado.setNmProducto(nmProducto);
            }
            em.persist(opmDetalleTraslado);
            if (nmTraslado != null) {
                nmTraslado.getOpmDetalleTrasladoList().add(opmDetalleTraslado);
                nmTraslado = em.merge(nmTraslado);
            }
            if (nmProducto != null) {
                nmProducto.getOpmDetalleTrasladoList().add(opmDetalleTraslado);
                nmProducto = em.merge(nmProducto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmDetalleTraslado opmDetalleTraslado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmDetalleTraslado persistentOpmDetalleTraslado = em.find(OpmDetalleTraslado.class, opmDetalleTraslado.getNmCodigo());
            OpmTraslado nmTrasladoOld = persistentOpmDetalleTraslado.getNmTraslado();
            OpmTraslado nmTrasladoNew = opmDetalleTraslado.getNmTraslado();
            OpmProducto nmProductoOld = persistentOpmDetalleTraslado.getNmProducto();
            OpmProducto nmProductoNew = opmDetalleTraslado.getNmProducto();
            if (nmTrasladoNew != null) {
                nmTrasladoNew = em.getReference(nmTrasladoNew.getClass(), nmTrasladoNew.getNmCodigo());
                opmDetalleTraslado.setNmTraslado(nmTrasladoNew);
            }
            if (nmProductoNew != null) {
                nmProductoNew = em.getReference(nmProductoNew.getClass(), nmProductoNew.getNmCodigo());
                opmDetalleTraslado.setNmProducto(nmProductoNew);
            }
            opmDetalleTraslado = em.merge(opmDetalleTraslado);
            if (nmTrasladoOld != null && !nmTrasladoOld.equals(nmTrasladoNew)) {
                nmTrasladoOld.getOpmDetalleTrasladoList().remove(opmDetalleTraslado);
                nmTrasladoOld = em.merge(nmTrasladoOld);
            }
            if (nmTrasladoNew != null && !nmTrasladoNew.equals(nmTrasladoOld)) {
                nmTrasladoNew.getOpmDetalleTrasladoList().add(opmDetalleTraslado);
                nmTrasladoNew = em.merge(nmTrasladoNew);
            }
            if (nmProductoOld != null && !nmProductoOld.equals(nmProductoNew)) {
                nmProductoOld.getOpmDetalleTrasladoList().remove(opmDetalleTraslado);
                nmProductoOld = em.merge(nmProductoOld);
            }
            if (nmProductoNew != null && !nmProductoNew.equals(nmProductoOld)) {
                nmProductoNew.getOpmDetalleTrasladoList().add(opmDetalleTraslado);
                nmProductoNew = em.merge(nmProductoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmDetalleTraslado.getNmCodigo();
                if (findOpmDetalleTraslado(id) == null) {
                    throw new NonexistentEntityException("The opmDetalleTraslado with id " + id + " no longer exists.");
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
            OpmDetalleTraslado opmDetalleTraslado;
            try {
                opmDetalleTraslado = em.getReference(OpmDetalleTraslado.class, id);
                opmDetalleTraslado.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmDetalleTraslado with id " + id + " no longer exists.", enfe);
            }
            OpmTraslado nmTraslado = opmDetalleTraslado.getNmTraslado();
            if (nmTraslado != null) {
                nmTraslado.getOpmDetalleTrasladoList().remove(opmDetalleTraslado);
                nmTraslado = em.merge(nmTraslado);
            }
            OpmProducto nmProducto = opmDetalleTraslado.getNmProducto();
            if (nmProducto != null) {
                nmProducto.getOpmDetalleTrasladoList().remove(opmDetalleTraslado);
                nmProducto = em.merge(nmProducto);
            }
            em.remove(opmDetalleTraslado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmDetalleTraslado> findOpmDetalleTrasladoEntities() {
        return findOpmDetalleTrasladoEntities(true, -1, -1);
    }

    public List<OpmDetalleTraslado> findOpmDetalleTrasladoEntities(int maxResults, int firstResult) {
        return findOpmDetalleTrasladoEntities(false, maxResults, firstResult);
    }

    private List<OpmDetalleTraslado> findOpmDetalleTrasladoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmDetalleTraslado.class));
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

    public OpmDetalleTraslado findOpmDetalleTraslado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmDetalleTraslado.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmDetalleTrasladoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmDetalleTraslado> rt = cq.from(OpmDetalleTraslado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
