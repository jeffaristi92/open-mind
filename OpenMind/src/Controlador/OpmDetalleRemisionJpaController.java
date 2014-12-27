/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmDetalleRemision;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmRemision;
import Modelo.OpmReferenciaProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
            OpmRemision nmRemision = opmDetalleRemision.getNmRemision();
            if (nmRemision != null) {
                nmRemision = em.getReference(nmRemision.getClass(), nmRemision.getNmCodigo());
                opmDetalleRemision.setNmRemision(nmRemision);
            }
            OpmReferenciaProducto nvReferencia = opmDetalleRemision.getNvReferencia();
            if (nvReferencia != null) {
                nvReferencia = em.getReference(nvReferencia.getClass(), nvReferencia.getNvCodigo());
                opmDetalleRemision.setNvReferencia(nvReferencia);
            }
            em.persist(opmDetalleRemision);
            if (nmRemision != null) {
                nmRemision.getOpmDetalleRemisionList().add(opmDetalleRemision);
                nmRemision = em.merge(nmRemision);
            }
            if (nvReferencia != null) {
                nvReferencia.getOpmDetalleRemisionList().add(opmDetalleRemision);
                nvReferencia = em.merge(nvReferencia);
            }
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
            OpmDetalleRemision persistentOpmDetalleRemision = em.find(OpmDetalleRemision.class, opmDetalleRemision.getNmCodigo());
            OpmRemision nmRemisionOld = persistentOpmDetalleRemision.getNmRemision();
            OpmRemision nmRemisionNew = opmDetalleRemision.getNmRemision();
            OpmReferenciaProducto nvReferenciaOld = persistentOpmDetalleRemision.getNvReferencia();
            OpmReferenciaProducto nvReferenciaNew = opmDetalleRemision.getNvReferencia();
            if (nmRemisionNew != null) {
                nmRemisionNew = em.getReference(nmRemisionNew.getClass(), nmRemisionNew.getNmCodigo());
                opmDetalleRemision.setNmRemision(nmRemisionNew);
            }
            if (nvReferenciaNew != null) {
                nvReferenciaNew = em.getReference(nvReferenciaNew.getClass(), nvReferenciaNew.getNvCodigo());
                opmDetalleRemision.setNvReferencia(nvReferenciaNew);
            }
            opmDetalleRemision = em.merge(opmDetalleRemision);
            if (nmRemisionOld != null && !nmRemisionOld.equals(nmRemisionNew)) {
                nmRemisionOld.getOpmDetalleRemisionList().remove(opmDetalleRemision);
                nmRemisionOld = em.merge(nmRemisionOld);
            }
            if (nmRemisionNew != null && !nmRemisionNew.equals(nmRemisionOld)) {
                nmRemisionNew.getOpmDetalleRemisionList().add(opmDetalleRemision);
                nmRemisionNew = em.merge(nmRemisionNew);
            }
            if (nvReferenciaOld != null && !nvReferenciaOld.equals(nvReferenciaNew)) {
                nvReferenciaOld.getOpmDetalleRemisionList().remove(opmDetalleRemision);
                nvReferenciaOld = em.merge(nvReferenciaOld);
            }
            if (nvReferenciaNew != null && !nvReferenciaNew.equals(nvReferenciaOld)) {
                nvReferenciaNew.getOpmDetalleRemisionList().add(opmDetalleRemision);
                nvReferenciaNew = em.merge(nvReferenciaNew);
            }
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
            OpmRemision nmRemision = opmDetalleRemision.getNmRemision();
            if (nmRemision != null) {
                nmRemision.getOpmDetalleRemisionList().remove(opmDetalleRemision);
                nmRemision = em.merge(nmRemision);
            }
            OpmReferenciaProducto nvReferencia = opmDetalleRemision.getNvReferencia();
            if (nvReferencia != null) {
                nvReferencia.getOpmDetalleRemisionList().remove(opmDetalleRemision);
                nvReferencia = em.merge(nvReferencia);
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
