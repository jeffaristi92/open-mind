/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmRol;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmUsuario;
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
        if (opmRol.getOpmUsuarioList() == null) {
            opmRol.setOpmUsuarioList(new ArrayList<OpmUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmUsuario> attachedOpmUsuarioList = new ArrayList<OpmUsuario>();
            for (OpmUsuario opmUsuarioListOpmUsuarioToAttach : opmRol.getOpmUsuarioList()) {
                opmUsuarioListOpmUsuarioToAttach = em.getReference(opmUsuarioListOpmUsuarioToAttach.getClass(), opmUsuarioListOpmUsuarioToAttach.getNmCodigo());
                attachedOpmUsuarioList.add(opmUsuarioListOpmUsuarioToAttach);
            }
            opmRol.setOpmUsuarioList(attachedOpmUsuarioList);
            em.persist(opmRol);
            for (OpmUsuario opmUsuarioListOpmUsuario : opmRol.getOpmUsuarioList()) {
                OpmRol oldNmRolOfOpmUsuarioListOpmUsuario = opmUsuarioListOpmUsuario.getNmRol();
                opmUsuarioListOpmUsuario.setNmRol(opmRol);
                opmUsuarioListOpmUsuario = em.merge(opmUsuarioListOpmUsuario);
                if (oldNmRolOfOpmUsuarioListOpmUsuario != null) {
                    oldNmRolOfOpmUsuarioListOpmUsuario.getOpmUsuarioList().remove(opmUsuarioListOpmUsuario);
                    oldNmRolOfOpmUsuarioListOpmUsuario = em.merge(oldNmRolOfOpmUsuarioListOpmUsuario);
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
            List<OpmUsuario> opmUsuarioListOld = persistentOpmRol.getOpmUsuarioList();
            List<OpmUsuario> opmUsuarioListNew = opmRol.getOpmUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (OpmUsuario opmUsuarioListOldOpmUsuario : opmUsuarioListOld) {
                if (!opmUsuarioListNew.contains(opmUsuarioListOldOpmUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmUsuario " + opmUsuarioListOldOpmUsuario + " since its nmRol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmUsuario> attachedOpmUsuarioListNew = new ArrayList<OpmUsuario>();
            for (OpmUsuario opmUsuarioListNewOpmUsuarioToAttach : opmUsuarioListNew) {
                opmUsuarioListNewOpmUsuarioToAttach = em.getReference(opmUsuarioListNewOpmUsuarioToAttach.getClass(), opmUsuarioListNewOpmUsuarioToAttach.getNmCodigo());
                attachedOpmUsuarioListNew.add(opmUsuarioListNewOpmUsuarioToAttach);
            }
            opmUsuarioListNew = attachedOpmUsuarioListNew;
            opmRol.setOpmUsuarioList(opmUsuarioListNew);
            opmRol = em.merge(opmRol);
            for (OpmUsuario opmUsuarioListNewOpmUsuario : opmUsuarioListNew) {
                if (!opmUsuarioListOld.contains(opmUsuarioListNewOpmUsuario)) {
                    OpmRol oldNmRolOfOpmUsuarioListNewOpmUsuario = opmUsuarioListNewOpmUsuario.getNmRol();
                    opmUsuarioListNewOpmUsuario.setNmRol(opmRol);
                    opmUsuarioListNewOpmUsuario = em.merge(opmUsuarioListNewOpmUsuario);
                    if (oldNmRolOfOpmUsuarioListNewOpmUsuario != null && !oldNmRolOfOpmUsuarioListNewOpmUsuario.equals(opmRol)) {
                        oldNmRolOfOpmUsuarioListNewOpmUsuario.getOpmUsuarioList().remove(opmUsuarioListNewOpmUsuario);
                        oldNmRolOfOpmUsuarioListNewOpmUsuario = em.merge(oldNmRolOfOpmUsuarioListNewOpmUsuario);
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
            List<OpmUsuario> opmUsuarioListOrphanCheck = opmRol.getOpmUsuarioList();
            for (OpmUsuario opmUsuarioListOrphanCheckOpmUsuario : opmUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmRol (" + opmRol + ") cannot be destroyed since the OpmUsuario " + opmUsuarioListOrphanCheckOpmUsuario + " in its opmUsuarioList field has a non-nullable nmRol field.");
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
