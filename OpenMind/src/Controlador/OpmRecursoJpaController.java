/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.OpmRecurso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmRecursosRol;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmRecursoJpaController implements Serializable {

    public OpmRecursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmRecurso opmRecurso) throws PreexistingEntityException, Exception {
        if (opmRecurso.getOpmRecursosRolList() == null) {
            opmRecurso.setOpmRecursosRolList(new ArrayList<OpmRecursosRol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmRecursosRol> attachedOpmRecursosRolList = new ArrayList<OpmRecursosRol>();
            for (OpmRecursosRol opmRecursosRolListOpmRecursosRolToAttach : opmRecurso.getOpmRecursosRolList()) {
                opmRecursosRolListOpmRecursosRolToAttach = em.getReference(opmRecursosRolListOpmRecursosRolToAttach.getClass(), opmRecursosRolListOpmRecursosRolToAttach.getNmCodigo());
                attachedOpmRecursosRolList.add(opmRecursosRolListOpmRecursosRolToAttach);
            }
            opmRecurso.setOpmRecursosRolList(attachedOpmRecursosRolList);
            em.persist(opmRecurso);
            for (OpmRecursosRol opmRecursosRolListOpmRecursosRol : opmRecurso.getOpmRecursosRolList()) {
                OpmRecurso oldNmRecursoOfOpmRecursosRolListOpmRecursosRol = opmRecursosRolListOpmRecursosRol.getNmRecurso();
                opmRecursosRolListOpmRecursosRol.setNmRecurso(opmRecurso);
                opmRecursosRolListOpmRecursosRol = em.merge(opmRecursosRolListOpmRecursosRol);
                if (oldNmRecursoOfOpmRecursosRolListOpmRecursosRol != null) {
                    oldNmRecursoOfOpmRecursosRolListOpmRecursosRol.getOpmRecursosRolList().remove(opmRecursosRolListOpmRecursosRol);
                    oldNmRecursoOfOpmRecursosRolListOpmRecursosRol = em.merge(oldNmRecursoOfOpmRecursosRolListOpmRecursosRol);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpmRecurso(opmRecurso.getNmCodigo()) != null) {
                throw new PreexistingEntityException("OpmRecurso " + opmRecurso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmRecurso opmRecurso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmRecurso persistentOpmRecurso = em.find(OpmRecurso.class, opmRecurso.getNmCodigo());
            List<OpmRecursosRol> opmRecursosRolListOld = persistentOpmRecurso.getOpmRecursosRolList();
            List<OpmRecursosRol> opmRecursosRolListNew = opmRecurso.getOpmRecursosRolList();
            List<String> illegalOrphanMessages = null;
            for (OpmRecursosRol opmRecursosRolListOldOpmRecursosRol : opmRecursosRolListOld) {
                if (!opmRecursosRolListNew.contains(opmRecursosRolListOldOpmRecursosRol)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmRecursosRol " + opmRecursosRolListOldOpmRecursosRol + " since its nmRecurso field is not nullable.");
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
            opmRecurso.setOpmRecursosRolList(opmRecursosRolListNew);
            opmRecurso = em.merge(opmRecurso);
            for (OpmRecursosRol opmRecursosRolListNewOpmRecursosRol : opmRecursosRolListNew) {
                if (!opmRecursosRolListOld.contains(opmRecursosRolListNewOpmRecursosRol)) {
                    OpmRecurso oldNmRecursoOfOpmRecursosRolListNewOpmRecursosRol = opmRecursosRolListNewOpmRecursosRol.getNmRecurso();
                    opmRecursosRolListNewOpmRecursosRol.setNmRecurso(opmRecurso);
                    opmRecursosRolListNewOpmRecursosRol = em.merge(opmRecursosRolListNewOpmRecursosRol);
                    if (oldNmRecursoOfOpmRecursosRolListNewOpmRecursosRol != null && !oldNmRecursoOfOpmRecursosRolListNewOpmRecursosRol.equals(opmRecurso)) {
                        oldNmRecursoOfOpmRecursosRolListNewOpmRecursosRol.getOpmRecursosRolList().remove(opmRecursosRolListNewOpmRecursosRol);
                        oldNmRecursoOfOpmRecursosRolListNewOpmRecursosRol = em.merge(oldNmRecursoOfOpmRecursosRolListNewOpmRecursosRol);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmRecurso.getNmCodigo();
                if (findOpmRecurso(id) == null) {
                    throw new NonexistentEntityException("The opmRecurso with id " + id + " no longer exists.");
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
            OpmRecurso opmRecurso;
            try {
                opmRecurso = em.getReference(OpmRecurso.class, id);
                opmRecurso.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmRecurso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmRecursosRol> opmRecursosRolListOrphanCheck = opmRecurso.getOpmRecursosRolList();
            for (OpmRecursosRol opmRecursosRolListOrphanCheckOpmRecursosRol : opmRecursosRolListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmRecurso (" + opmRecurso + ") cannot be destroyed since the OpmRecursosRol " + opmRecursosRolListOrphanCheckOpmRecursosRol + " in its opmRecursosRolList field has a non-nullable nmRecurso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(opmRecurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmRecurso> findOpmRecursoEntities() {
        return findOpmRecursoEntities(true, -1, -1);
    }

    public List<OpmRecurso> findOpmRecursoEntities(int maxResults, int firstResult) {
        return findOpmRecursoEntities(false, maxResults, firstResult);
    }

    private List<OpmRecurso> findOpmRecursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmRecurso.class));
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

    public OpmRecurso findOpmRecurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmRecurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmRecursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmRecurso> rt = cq.from(OpmRecurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
