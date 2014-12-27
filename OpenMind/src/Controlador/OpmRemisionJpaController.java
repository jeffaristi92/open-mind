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
import Modelo.OpmDetalleRemision;
import Modelo.OpmRemision;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmRemisionJpaController implements Serializable {

    public OpmRemisionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmRemision opmRemision) {
        if (opmRemision.getOpmDetalleRemisionList() == null) {
            opmRemision.setOpmDetalleRemisionList(new ArrayList<OpmDetalleRemision>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmDetalleRemision> attachedOpmDetalleRemisionList = new ArrayList<OpmDetalleRemision>();
            for (OpmDetalleRemision opmDetalleRemisionListOpmDetalleRemisionToAttach : opmRemision.getOpmDetalleRemisionList()) {
                opmDetalleRemisionListOpmDetalleRemisionToAttach = em.getReference(opmDetalleRemisionListOpmDetalleRemisionToAttach.getClass(), opmDetalleRemisionListOpmDetalleRemisionToAttach.getNmCodigo());
                attachedOpmDetalleRemisionList.add(opmDetalleRemisionListOpmDetalleRemisionToAttach);
            }
            opmRemision.setOpmDetalleRemisionList(attachedOpmDetalleRemisionList);
            em.persist(opmRemision);
            for (OpmDetalleRemision opmDetalleRemisionListOpmDetalleRemision : opmRemision.getOpmDetalleRemisionList()) {
                OpmRemision oldNmRemisionOfOpmDetalleRemisionListOpmDetalleRemision = opmDetalleRemisionListOpmDetalleRemision.getNmRemision();
                opmDetalleRemisionListOpmDetalleRemision.setNmRemision(opmRemision);
                opmDetalleRemisionListOpmDetalleRemision = em.merge(opmDetalleRemisionListOpmDetalleRemision);
                if (oldNmRemisionOfOpmDetalleRemisionListOpmDetalleRemision != null) {
                    oldNmRemisionOfOpmDetalleRemisionListOpmDetalleRemision.getOpmDetalleRemisionList().remove(opmDetalleRemisionListOpmDetalleRemision);
                    oldNmRemisionOfOpmDetalleRemisionListOpmDetalleRemision = em.merge(oldNmRemisionOfOpmDetalleRemisionListOpmDetalleRemision);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmRemision opmRemision) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmRemision persistentOpmRemision = em.find(OpmRemision.class, opmRemision.getNmCodigo());
            List<OpmDetalleRemision> opmDetalleRemisionListOld = persistentOpmRemision.getOpmDetalleRemisionList();
            List<OpmDetalleRemision> opmDetalleRemisionListNew = opmRemision.getOpmDetalleRemisionList();
            List<String> illegalOrphanMessages = null;
            for (OpmDetalleRemision opmDetalleRemisionListOldOpmDetalleRemision : opmDetalleRemisionListOld) {
                if (!opmDetalleRemisionListNew.contains(opmDetalleRemisionListOldOpmDetalleRemision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleRemision " + opmDetalleRemisionListOldOpmDetalleRemision + " since its nmRemision field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmDetalleRemision> attachedOpmDetalleRemisionListNew = new ArrayList<OpmDetalleRemision>();
            for (OpmDetalleRemision opmDetalleRemisionListNewOpmDetalleRemisionToAttach : opmDetalleRemisionListNew) {
                opmDetalleRemisionListNewOpmDetalleRemisionToAttach = em.getReference(opmDetalleRemisionListNewOpmDetalleRemisionToAttach.getClass(), opmDetalleRemisionListNewOpmDetalleRemisionToAttach.getNmCodigo());
                attachedOpmDetalleRemisionListNew.add(opmDetalleRemisionListNewOpmDetalleRemisionToAttach);
            }
            opmDetalleRemisionListNew = attachedOpmDetalleRemisionListNew;
            opmRemision.setOpmDetalleRemisionList(opmDetalleRemisionListNew);
            opmRemision = em.merge(opmRemision);
            for (OpmDetalleRemision opmDetalleRemisionListNewOpmDetalleRemision : opmDetalleRemisionListNew) {
                if (!opmDetalleRemisionListOld.contains(opmDetalleRemisionListNewOpmDetalleRemision)) {
                    OpmRemision oldNmRemisionOfOpmDetalleRemisionListNewOpmDetalleRemision = opmDetalleRemisionListNewOpmDetalleRemision.getNmRemision();
                    opmDetalleRemisionListNewOpmDetalleRemision.setNmRemision(opmRemision);
                    opmDetalleRemisionListNewOpmDetalleRemision = em.merge(opmDetalleRemisionListNewOpmDetalleRemision);
                    if (oldNmRemisionOfOpmDetalleRemisionListNewOpmDetalleRemision != null && !oldNmRemisionOfOpmDetalleRemisionListNewOpmDetalleRemision.equals(opmRemision)) {
                        oldNmRemisionOfOpmDetalleRemisionListNewOpmDetalleRemision.getOpmDetalleRemisionList().remove(opmDetalleRemisionListNewOpmDetalleRemision);
                        oldNmRemisionOfOpmDetalleRemisionListNewOpmDetalleRemision = em.merge(oldNmRemisionOfOpmDetalleRemisionListNewOpmDetalleRemision);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmRemision.getNmCodigo();
                if (findOpmRemision(id) == null) {
                    throw new NonexistentEntityException("The opmRemision with id " + id + " no longer exists.");
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
            OpmRemision opmRemision;
            try {
                opmRemision = em.getReference(OpmRemision.class, id);
                opmRemision.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmRemision with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmDetalleRemision> opmDetalleRemisionListOrphanCheck = opmRemision.getOpmDetalleRemisionList();
            for (OpmDetalleRemision opmDetalleRemisionListOrphanCheckOpmDetalleRemision : opmDetalleRemisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmRemision (" + opmRemision + ") cannot be destroyed since the OpmDetalleRemision " + opmDetalleRemisionListOrphanCheckOpmDetalleRemision + " in its opmDetalleRemisionList field has a non-nullable nmRemision field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(opmRemision);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmRemision> findOpmRemisionEntities() {
        return findOpmRemisionEntities(true, -1, -1);
    }

    public List<OpmRemision> findOpmRemisionEntities(int maxResults, int firstResult) {
        return findOpmRemisionEntities(false, maxResults, firstResult);
    }

    private List<OpmRemision> findOpmRemisionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmRemision.class));
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

    public OpmRemision findOpmRemision(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmRemision.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmRemisionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmRemision> rt = cq.from(OpmRemision.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
