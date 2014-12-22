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
import Modelo.OpmGastos;
import Modelo.OpmTipoGasto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmTipoGastoJpaController implements Serializable {

    public OpmTipoGastoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmTipoGasto opmTipoGasto) {
        if (opmTipoGasto.getOpmGastosList() == null) {
            opmTipoGasto.setOpmGastosList(new ArrayList<OpmGastos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmGastos> attachedOpmGastosList = new ArrayList<OpmGastos>();
            for (OpmGastos opmGastosListOpmGastosToAttach : opmTipoGasto.getOpmGastosList()) {
                opmGastosListOpmGastosToAttach = em.getReference(opmGastosListOpmGastosToAttach.getClass(), opmGastosListOpmGastosToAttach.getNmCodigo());
                attachedOpmGastosList.add(opmGastosListOpmGastosToAttach);
            }
            opmTipoGasto.setOpmGastosList(attachedOpmGastosList);
            em.persist(opmTipoGasto);
            for (OpmGastos opmGastosListOpmGastos : opmTipoGasto.getOpmGastosList()) {
                OpmTipoGasto oldNmTipoOfOpmGastosListOpmGastos = opmGastosListOpmGastos.getNmTipo();
                opmGastosListOpmGastos.setNmTipo(opmTipoGasto);
                opmGastosListOpmGastos = em.merge(opmGastosListOpmGastos);
                if (oldNmTipoOfOpmGastosListOpmGastos != null) {
                    oldNmTipoOfOpmGastosListOpmGastos.getOpmGastosList().remove(opmGastosListOpmGastos);
                    oldNmTipoOfOpmGastosListOpmGastos = em.merge(oldNmTipoOfOpmGastosListOpmGastos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmTipoGasto opmTipoGasto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmTipoGasto persistentOpmTipoGasto = em.find(OpmTipoGasto.class, opmTipoGasto.getNmCodigo());
            List<OpmGastos> opmGastosListOld = persistentOpmTipoGasto.getOpmGastosList();
            List<OpmGastos> opmGastosListNew = opmTipoGasto.getOpmGastosList();
            List<String> illegalOrphanMessages = null;
            for (OpmGastos opmGastosListOldOpmGastos : opmGastosListOld) {
                if (!opmGastosListNew.contains(opmGastosListOldOpmGastos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmGastos " + opmGastosListOldOpmGastos + " since its nmTipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmGastos> attachedOpmGastosListNew = new ArrayList<OpmGastos>();
            for (OpmGastos opmGastosListNewOpmGastosToAttach : opmGastosListNew) {
                opmGastosListNewOpmGastosToAttach = em.getReference(opmGastosListNewOpmGastosToAttach.getClass(), opmGastosListNewOpmGastosToAttach.getNmCodigo());
                attachedOpmGastosListNew.add(opmGastosListNewOpmGastosToAttach);
            }
            opmGastosListNew = attachedOpmGastosListNew;
            opmTipoGasto.setOpmGastosList(opmGastosListNew);
            opmTipoGasto = em.merge(opmTipoGasto);
            for (OpmGastos opmGastosListNewOpmGastos : opmGastosListNew) {
                if (!opmGastosListOld.contains(opmGastosListNewOpmGastos)) {
                    OpmTipoGasto oldNmTipoOfOpmGastosListNewOpmGastos = opmGastosListNewOpmGastos.getNmTipo();
                    opmGastosListNewOpmGastos.setNmTipo(opmTipoGasto);
                    opmGastosListNewOpmGastos = em.merge(opmGastosListNewOpmGastos);
                    if (oldNmTipoOfOpmGastosListNewOpmGastos != null && !oldNmTipoOfOpmGastosListNewOpmGastos.equals(opmTipoGasto)) {
                        oldNmTipoOfOpmGastosListNewOpmGastos.getOpmGastosList().remove(opmGastosListNewOpmGastos);
                        oldNmTipoOfOpmGastosListNewOpmGastos = em.merge(oldNmTipoOfOpmGastosListNewOpmGastos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmTipoGasto.getNmCodigo();
                if (findOpmTipoGasto(id) == null) {
                    throw new NonexistentEntityException("The opmTipoGasto with id " + id + " no longer exists.");
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
            OpmTipoGasto opmTipoGasto;
            try {
                opmTipoGasto = em.getReference(OpmTipoGasto.class, id);
                opmTipoGasto.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmTipoGasto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmGastos> opmGastosListOrphanCheck = opmTipoGasto.getOpmGastosList();
            for (OpmGastos opmGastosListOrphanCheckOpmGastos : opmGastosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmTipoGasto (" + opmTipoGasto + ") cannot be destroyed since the OpmGastos " + opmGastosListOrphanCheckOpmGastos + " in its opmGastosList field has a non-nullable nmTipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(opmTipoGasto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmTipoGasto> findOpmTipoGastoEntities() {
        return findOpmTipoGastoEntities(true, -1, -1);
    }

    public List<OpmTipoGasto> findOpmTipoGastoEntities(int maxResults, int firstResult) {
        return findOpmTipoGastoEntities(false, maxResults, firstResult);
    }

    private List<OpmTipoGasto> findOpmTipoGastoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmTipoGasto.class));
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

    public OpmTipoGasto findOpmTipoGasto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmTipoGasto.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmTipoGastoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmTipoGasto> rt = cq.from(OpmTipoGasto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
