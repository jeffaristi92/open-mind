/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmFabrica;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmFabricaJpaController implements Serializable {

    public OpmFabricaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmFabrica opmFabrica) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmUsuario nmAdministrador = opmFabrica.getNmAdministrador();
            if (nmAdministrador != null) {
                nmAdministrador = em.getReference(nmAdministrador.getClass(), nmAdministrador.getNmCodigo());
                opmFabrica.setNmAdministrador(nmAdministrador);
            }
            em.persist(opmFabrica);
            if (nmAdministrador != null) {
                nmAdministrador.getOpmFabricaList().add(opmFabrica);
                nmAdministrador = em.merge(nmAdministrador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmFabrica opmFabrica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmFabrica persistentOpmFabrica = em.find(OpmFabrica.class, opmFabrica.getNmCodigo());
            OpmUsuario nmAdministradorOld = persistentOpmFabrica.getNmAdministrador();
            OpmUsuario nmAdministradorNew = opmFabrica.getNmAdministrador();
            if (nmAdministradorNew != null) {
                nmAdministradorNew = em.getReference(nmAdministradorNew.getClass(), nmAdministradorNew.getNmCodigo());
                opmFabrica.setNmAdministrador(nmAdministradorNew);
            }
            opmFabrica = em.merge(opmFabrica);
            if (nmAdministradorOld != null && !nmAdministradorOld.equals(nmAdministradorNew)) {
                nmAdministradorOld.getOpmFabricaList().remove(opmFabrica);
                nmAdministradorOld = em.merge(nmAdministradorOld);
            }
            if (nmAdministradorNew != null && !nmAdministradorNew.equals(nmAdministradorOld)) {
                nmAdministradorNew.getOpmFabricaList().add(opmFabrica);
                nmAdministradorNew = em.merge(nmAdministradorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmFabrica.getNmCodigo();
                if (findOpmFabrica(id) == null) {
                    throw new NonexistentEntityException("The opmFabrica with id " + id + " no longer exists.");
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
            OpmFabrica opmFabrica;
            try {
                opmFabrica = em.getReference(OpmFabrica.class, id);
                opmFabrica.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmFabrica with id " + id + " no longer exists.", enfe);
            }
            OpmUsuario nmAdministrador = opmFabrica.getNmAdministrador();
            if (nmAdministrador != null) {
                nmAdministrador.getOpmFabricaList().remove(opmFabrica);
                nmAdministrador = em.merge(nmAdministrador);
            }
            em.remove(opmFabrica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmFabrica> findOpmFabricaEntities() {
        return findOpmFabricaEntities(true, -1, -1);
    }

    public List<OpmFabrica> findOpmFabricaEntities(int maxResults, int firstResult) {
        return findOpmFabricaEntities(false, maxResults, firstResult);
    }

    private List<OpmFabrica> findOpmFabricaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmFabrica.class));
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

    public OpmFabrica findOpmFabrica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmFabrica.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmFabricaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmFabrica> rt = cq.from(OpmFabrica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
