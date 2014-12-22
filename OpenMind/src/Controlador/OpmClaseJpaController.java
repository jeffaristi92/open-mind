/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmClase;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmGrupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmClaseJpaController implements Serializable {

    public OpmClaseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmClase opmClase) {
        if (opmClase.getOpmGrupoList() == null) {
            opmClase.setOpmGrupoList(new ArrayList<OpmGrupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmGrupo> attachedOpmGrupoList = new ArrayList<OpmGrupo>();
            for (OpmGrupo opmGrupoListOpmGrupoToAttach : opmClase.getOpmGrupoList()) {
                opmGrupoListOpmGrupoToAttach = em.getReference(opmGrupoListOpmGrupoToAttach.getClass(), opmGrupoListOpmGrupoToAttach.getNmCodigo());
                attachedOpmGrupoList.add(opmGrupoListOpmGrupoToAttach);
            }
            opmClase.setOpmGrupoList(attachedOpmGrupoList);
            em.persist(opmClase);
            for (OpmGrupo opmGrupoListOpmGrupo : opmClase.getOpmGrupoList()) {
                OpmClase oldNmClaseOfOpmGrupoListOpmGrupo = opmGrupoListOpmGrupo.getNmClase();
                opmGrupoListOpmGrupo.setNmClase(opmClase);
                opmGrupoListOpmGrupo = em.merge(opmGrupoListOpmGrupo);
                if (oldNmClaseOfOpmGrupoListOpmGrupo != null) {
                    oldNmClaseOfOpmGrupoListOpmGrupo.getOpmGrupoList().remove(opmGrupoListOpmGrupo);
                    oldNmClaseOfOpmGrupoListOpmGrupo = em.merge(oldNmClaseOfOpmGrupoListOpmGrupo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmClase opmClase) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmClase persistentOpmClase = em.find(OpmClase.class, opmClase.getNmCodigo());
            List<OpmGrupo> opmGrupoListOld = persistentOpmClase.getOpmGrupoList();
            List<OpmGrupo> opmGrupoListNew = opmClase.getOpmGrupoList();
            List<String> illegalOrphanMessages = null;
            for (OpmGrupo opmGrupoListOldOpmGrupo : opmGrupoListOld) {
                if (!opmGrupoListNew.contains(opmGrupoListOldOpmGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmGrupo " + opmGrupoListOldOpmGrupo + " since its nmClase field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmGrupo> attachedOpmGrupoListNew = new ArrayList<OpmGrupo>();
            for (OpmGrupo opmGrupoListNewOpmGrupoToAttach : opmGrupoListNew) {
                opmGrupoListNewOpmGrupoToAttach = em.getReference(opmGrupoListNewOpmGrupoToAttach.getClass(), opmGrupoListNewOpmGrupoToAttach.getNmCodigo());
                attachedOpmGrupoListNew.add(opmGrupoListNewOpmGrupoToAttach);
            }
            opmGrupoListNew = attachedOpmGrupoListNew;
            opmClase.setOpmGrupoList(opmGrupoListNew);
            opmClase = em.merge(opmClase);
            for (OpmGrupo opmGrupoListNewOpmGrupo : opmGrupoListNew) {
                if (!opmGrupoListOld.contains(opmGrupoListNewOpmGrupo)) {
                    OpmClase oldNmClaseOfOpmGrupoListNewOpmGrupo = opmGrupoListNewOpmGrupo.getNmClase();
                    opmGrupoListNewOpmGrupo.setNmClase(opmClase);
                    opmGrupoListNewOpmGrupo = em.merge(opmGrupoListNewOpmGrupo);
                    if (oldNmClaseOfOpmGrupoListNewOpmGrupo != null && !oldNmClaseOfOpmGrupoListNewOpmGrupo.equals(opmClase)) {
                        oldNmClaseOfOpmGrupoListNewOpmGrupo.getOpmGrupoList().remove(opmGrupoListNewOpmGrupo);
                        oldNmClaseOfOpmGrupoListNewOpmGrupo = em.merge(oldNmClaseOfOpmGrupoListNewOpmGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmClase.getNmCodigo();
                if (findOpmClase(id) == null) {
                    throw new NonexistentEntityException("The opmClase with id " + id + " no longer exists.");
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
            OpmClase opmClase;
            try {
                opmClase = em.getReference(OpmClase.class, id);
                opmClase.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmClase with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmGrupo> opmGrupoListOrphanCheck = opmClase.getOpmGrupoList();
            for (OpmGrupo opmGrupoListOrphanCheckOpmGrupo : opmGrupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmClase (" + opmClase + ") cannot be destroyed since the OpmGrupo " + opmGrupoListOrphanCheckOpmGrupo + " in its opmGrupoList field has a non-nullable nmClase field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(opmClase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmClase> findOpmClaseEntities() {
        return findOpmClaseEntities(true, -1, -1);
    }

    public List<OpmClase> findOpmClaseEntities(int maxResults, int firstResult) {
        return findOpmClaseEntities(false, maxResults, firstResult);
    }

    private List<OpmClase> findOpmClaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmClase.class));
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

    public OpmClase findOpmClase(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmClase.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmClaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmClase> rt = cq.from(OpmClase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
