/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmRol;
import Modelo.OpmRecurso;
import Modelo.OpmRecursosRol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmRecursosRolJpaController implements Serializable {

    public OpmRecursosRolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmRecursosRol opmRecursosRol) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmRol nmRol = opmRecursosRol.getNmRol();
            if (nmRol != null) {
                nmRol = em.getReference(nmRol.getClass(), nmRol.getNmCodigo());
                opmRecursosRol.setNmRol(nmRol);
            }
            OpmRecurso nmRecurso = opmRecursosRol.getNmRecurso();
            if (nmRecurso != null) {
                nmRecurso = em.getReference(nmRecurso.getClass(), nmRecurso.getNmCodigo());
                opmRecursosRol.setNmRecurso(nmRecurso);
            }
            em.persist(opmRecursosRol);
            if (nmRol != null) {
                nmRol.getOpmRecursosRolList().add(opmRecursosRol);
                nmRol = em.merge(nmRol);
            }
            if (nmRecurso != null) {
                nmRecurso.getOpmRecursosRolList().add(opmRecursosRol);
                nmRecurso = em.merge(nmRecurso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmRecursosRol opmRecursosRol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmRecursosRol persistentOpmRecursosRol = em.find(OpmRecursosRol.class, opmRecursosRol.getNmCodigo());
            OpmRol nmRolOld = persistentOpmRecursosRol.getNmRol();
            OpmRol nmRolNew = opmRecursosRol.getNmRol();
            OpmRecurso nmRecursoOld = persistentOpmRecursosRol.getNmRecurso();
            OpmRecurso nmRecursoNew = opmRecursosRol.getNmRecurso();
            if (nmRolNew != null) {
                nmRolNew = em.getReference(nmRolNew.getClass(), nmRolNew.getNmCodigo());
                opmRecursosRol.setNmRol(nmRolNew);
            }
            if (nmRecursoNew != null) {
                nmRecursoNew = em.getReference(nmRecursoNew.getClass(), nmRecursoNew.getNmCodigo());
                opmRecursosRol.setNmRecurso(nmRecursoNew);
            }
            opmRecursosRol = em.merge(opmRecursosRol);
            if (nmRolOld != null && !nmRolOld.equals(nmRolNew)) {
                nmRolOld.getOpmRecursosRolList().remove(opmRecursosRol);
                nmRolOld = em.merge(nmRolOld);
            }
            if (nmRolNew != null && !nmRolNew.equals(nmRolOld)) {
                nmRolNew.getOpmRecursosRolList().add(opmRecursosRol);
                nmRolNew = em.merge(nmRolNew);
            }
            if (nmRecursoOld != null && !nmRecursoOld.equals(nmRecursoNew)) {
                nmRecursoOld.getOpmRecursosRolList().remove(opmRecursosRol);
                nmRecursoOld = em.merge(nmRecursoOld);
            }
            if (nmRecursoNew != null && !nmRecursoNew.equals(nmRecursoOld)) {
                nmRecursoNew.getOpmRecursosRolList().add(opmRecursosRol);
                nmRecursoNew = em.merge(nmRecursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmRecursosRol.getNmCodigo();
                if (findOpmRecursosRol(id) == null) {
                    throw new NonexistentEntityException("The opmRecursosRol with id " + id + " no longer exists.");
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
            OpmRecursosRol opmRecursosRol;
            try {
                opmRecursosRol = em.getReference(OpmRecursosRol.class, id);
                opmRecursosRol.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmRecursosRol with id " + id + " no longer exists.", enfe);
            }
            OpmRol nmRol = opmRecursosRol.getNmRol();
            if (nmRol != null) {
                nmRol.getOpmRecursosRolList().remove(opmRecursosRol);
                nmRol = em.merge(nmRol);
            }
            OpmRecurso nmRecurso = opmRecursosRol.getNmRecurso();
            if (nmRecurso != null) {
                nmRecurso.getOpmRecursosRolList().remove(opmRecursosRol);
                nmRecurso = em.merge(nmRecurso);
            }
            em.remove(opmRecursosRol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmRecursosRol> findOpmRecursosRolEntities() {
        return findOpmRecursosRolEntities(true, -1, -1);
    }

    public List<OpmRecursosRol> findOpmRecursosRolEntities(int maxResults, int firstResult) {
        return findOpmRecursosRolEntities(false, maxResults, firstResult);
    }

    private List<OpmRecursosRol> findOpmRecursosRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmRecursosRol.class));
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

    public OpmRecursosRol findOpmRecursosRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmRecursosRol.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmRecursosRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmRecursosRol> rt = cq.from(OpmRecursosRol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
