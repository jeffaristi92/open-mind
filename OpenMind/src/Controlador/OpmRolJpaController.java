/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmRecursosRol;
import Modelo.OpmRol;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmRolJpaController implements Serializable {

    public OpmRolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmRol opmRol) {
        if (opmRol.getOpmRecursosRolList() == null) {
            opmRol.setOpmRecursosRolList(new ArrayList<OpmRecursosRol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmRecursosRol> attachedOpmRecursosRolList = new ArrayList<OpmRecursosRol>();
            for (OpmRecursosRol opmRecursosRolListOpmRecursosRolToAttach : opmRol.getOpmRecursosRolList()) {
                opmRecursosRolListOpmRecursosRolToAttach = em.getReference(opmRecursosRolListOpmRecursosRolToAttach.getClass(), opmRecursosRolListOpmRecursosRolToAttach.getNmCodigo());
                attachedOpmRecursosRolList.add(opmRecursosRolListOpmRecursosRolToAttach);
            }
            opmRol.setOpmRecursosRolList(attachedOpmRecursosRolList);
            em.persist(opmRol);
            for (OpmRecursosRol opmRecursosRolListOpmRecursosRol : opmRol.getOpmRecursosRolList()) {
                OpmRol oldNmRolOfOpmRecursosRolListOpmRecursosRol = opmRecursosRolListOpmRecursosRol.getNmRol();
                opmRecursosRolListOpmRecursosRol.setNmRol(opmRol);
                opmRecursosRolListOpmRecursosRol = em.merge(opmRecursosRolListOpmRecursosRol);
                if (oldNmRolOfOpmRecursosRolListOpmRecursosRol != null) {
                    oldNmRolOfOpmRecursosRolListOpmRecursosRol.getOpmRecursosRolList().remove(opmRecursosRolListOpmRecursosRol);
                    oldNmRolOfOpmRecursosRolListOpmRecursosRol = em.merge(oldNmRolOfOpmRecursosRolListOpmRecursosRol);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmRol opmRol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmRol persistentOpmRol = em.find(OpmRol.class, opmRol.getNmCodigo());
            List<OpmRecursosRol> opmRecursosRolListOld = persistentOpmRol.getOpmRecursosRolList();
            List<OpmRecursosRol> opmRecursosRolListNew = opmRol.getOpmRecursosRolList();
            List<String> illegalOrphanMessages = null;
            for (OpmRecursosRol opmRecursosRolListOldOpmRecursosRol : opmRecursosRolListOld) {
                if (!opmRecursosRolListNew.contains(opmRecursosRolListOldOpmRecursosRol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmRecursosRol " + opmRecursosRolListOldOpmRecursosRol + " since its nmRol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmRecursosRol> attachedOpmRecursosRolListNew = new ArrayList<OpmRecursosRol>();
            for (OpmRecursosRol opmRecursosRolListNewOpmRecursosRolToAttach : opmRecursosRolListNew) {
                opmRecursosRolListNewOpmRecursosRolToAttach = em.getReference(opmRecursosRolListNewOpmRecursosRolToAttach.getClass(), opmRecursosRolListNewOpmRecursosRolToAttach.getNmCodigo());
                attachedOpmRecursosRolListNew.add(opmRecursosRolListNewOpmRecursosRolToAttach);
            }
            opmRecursosRolListNew = attachedOpmRecursosRolListNew;
            opmRol.setOpmRecursosRolList(opmRecursosRolListNew);
            opmRol = em.merge(opmRol);
            for (OpmRecursosRol opmRecursosRolListNewOpmRecursosRol : opmRecursosRolListNew) {
                if (!opmRecursosRolListOld.contains(opmRecursosRolListNewOpmRecursosRol)) {
                    OpmRol oldNmRolOfOpmRecursosRolListNewOpmRecursosRol = opmRecursosRolListNewOpmRecursosRol.getNmRol();
                    opmRecursosRolListNewOpmRecursosRol.setNmRol(opmRol);
                    opmRecursosRolListNewOpmRecursosRol = em.merge(opmRecursosRolListNewOpmRecursosRol);
                    if (oldNmRolOfOpmRecursosRolListNewOpmRecursosRol != null && !oldNmRolOfOpmRecursosRolListNewOpmRecursosRol.equals(opmRol)) {
                        oldNmRolOfOpmRecursosRolListNewOpmRecursosRol.getOpmRecursosRolList().remove(opmRecursosRolListNewOpmRecursosRol);
                        oldNmRolOfOpmRecursosRolListNewOpmRecursosRol = em.merge(oldNmRolOfOpmRecursosRolListNewOpmRecursosRol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmRol.getNmCodigo();
                if (findOpmRol(id) == null) {
                    throw new NonexistentEntityException("The opmRol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmRol opmRol;
            try {
                opmRol = em.getReference(OpmRol.class, id);
                opmRol.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmRol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmRecursosRol> opmRecursosRolListOrphanCheck = opmRol.getOpmRecursosRolList();
            for (OpmRecursosRol opmRecursosRolListOrphanCheckOpmRecursosRol : opmRecursosRolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmRol (" + opmRol + ") cannot be destroyed since the OpmRecursosRol " + opmRecursosRolListOrphanCheckOpmRecursosRol + " in its opmRecursosRolList field has a non-nullable nmRol field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(opmRol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmRol> findOpmRolEntities() {
        return findOpmRolEntities(true, -1, -1);
    }

    public List<OpmRol> findOpmRolEntities(int maxResults, int firstResult) {
        return findOpmRolEntities(false, maxResults, firstResult);
    }

    private List<OpmRol> findOpmRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmRol.class));
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

    public OpmRol findOpmRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmRol.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmRol> rt = cq.from(OpmRol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
