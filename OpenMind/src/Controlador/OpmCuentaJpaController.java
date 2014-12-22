/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmCuenta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmGrupo;
import Modelo.OpmTransaccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmCuentaJpaController implements Serializable {

    public OpmCuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmCuenta opmCuenta) {
        if (opmCuenta.getOpmTransaccionList() == null) {
            opmCuenta.setOpmTransaccionList(new ArrayList<OpmTransaccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmGrupo nmGrupo = opmCuenta.getNmGrupo();
            if (nmGrupo != null) {
                nmGrupo = em.getReference(nmGrupo.getClass(), nmGrupo.getNmCodigo());
                opmCuenta.setNmGrupo(nmGrupo);
            }
            List<OpmTransaccion> attachedOpmTransaccionList = new ArrayList<OpmTransaccion>();
            for (OpmTransaccion opmTransaccionListOpmTransaccionToAttach : opmCuenta.getOpmTransaccionList()) {
                opmTransaccionListOpmTransaccionToAttach = em.getReference(opmTransaccionListOpmTransaccionToAttach.getClass(), opmTransaccionListOpmTransaccionToAttach.getNmCodigo());
                attachedOpmTransaccionList.add(opmTransaccionListOpmTransaccionToAttach);
            }
            opmCuenta.setOpmTransaccionList(attachedOpmTransaccionList);
            em.persist(opmCuenta);
            if (nmGrupo != null) {
                nmGrupo.getOpmCuentaList().add(opmCuenta);
                nmGrupo = em.merge(nmGrupo);
            }
            for (OpmTransaccion opmTransaccionListOpmTransaccion : opmCuenta.getOpmTransaccionList()) {
                OpmCuenta oldNmCuentaOfOpmTransaccionListOpmTransaccion = opmTransaccionListOpmTransaccion.getNmCuenta();
                opmTransaccionListOpmTransaccion.setNmCuenta(opmCuenta);
                opmTransaccionListOpmTransaccion = em.merge(opmTransaccionListOpmTransaccion);
                if (oldNmCuentaOfOpmTransaccionListOpmTransaccion != null) {
                    oldNmCuentaOfOpmTransaccionListOpmTransaccion.getOpmTransaccionList().remove(opmTransaccionListOpmTransaccion);
                    oldNmCuentaOfOpmTransaccionListOpmTransaccion = em.merge(oldNmCuentaOfOpmTransaccionListOpmTransaccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmCuenta opmCuenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmCuenta persistentOpmCuenta = em.find(OpmCuenta.class, opmCuenta.getNmCodigo());
            OpmGrupo nmGrupoOld = persistentOpmCuenta.getNmGrupo();
            OpmGrupo nmGrupoNew = opmCuenta.getNmGrupo();
            List<OpmTransaccion> opmTransaccionListOld = persistentOpmCuenta.getOpmTransaccionList();
            List<OpmTransaccion> opmTransaccionListNew = opmCuenta.getOpmTransaccionList();
            List<String> illegalOrphanMessages = null;
            for (OpmTransaccion opmTransaccionListOldOpmTransaccion : opmTransaccionListOld) {
                if (!opmTransaccionListNew.contains(opmTransaccionListOldOpmTransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmTransaccion " + opmTransaccionListOldOpmTransaccion + " since its nmCuenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmGrupoNew != null) {
                nmGrupoNew = em.getReference(nmGrupoNew.getClass(), nmGrupoNew.getNmCodigo());
                opmCuenta.setNmGrupo(nmGrupoNew);
            }
            List<OpmTransaccion> attachedOpmTransaccionListNew = new ArrayList<OpmTransaccion>();
            for (OpmTransaccion opmTransaccionListNewOpmTransaccionToAttach : opmTransaccionListNew) {
                opmTransaccionListNewOpmTransaccionToAttach = em.getReference(opmTransaccionListNewOpmTransaccionToAttach.getClass(), opmTransaccionListNewOpmTransaccionToAttach.getNmCodigo());
                attachedOpmTransaccionListNew.add(opmTransaccionListNewOpmTransaccionToAttach);
            }
            opmTransaccionListNew = attachedOpmTransaccionListNew;
            opmCuenta.setOpmTransaccionList(opmTransaccionListNew);
            opmCuenta = em.merge(opmCuenta);
            if (nmGrupoOld != null && !nmGrupoOld.equals(nmGrupoNew)) {
                nmGrupoOld.getOpmCuentaList().remove(opmCuenta);
                nmGrupoOld = em.merge(nmGrupoOld);
            }
            if (nmGrupoNew != null && !nmGrupoNew.equals(nmGrupoOld)) {
                nmGrupoNew.getOpmCuentaList().add(opmCuenta);
                nmGrupoNew = em.merge(nmGrupoNew);
            }
            for (OpmTransaccion opmTransaccionListNewOpmTransaccion : opmTransaccionListNew) {
                if (!opmTransaccionListOld.contains(opmTransaccionListNewOpmTransaccion)) {
                    OpmCuenta oldNmCuentaOfOpmTransaccionListNewOpmTransaccion = opmTransaccionListNewOpmTransaccion.getNmCuenta();
                    opmTransaccionListNewOpmTransaccion.setNmCuenta(opmCuenta);
                    opmTransaccionListNewOpmTransaccion = em.merge(opmTransaccionListNewOpmTransaccion);
                    if (oldNmCuentaOfOpmTransaccionListNewOpmTransaccion != null && !oldNmCuentaOfOpmTransaccionListNewOpmTransaccion.equals(opmCuenta)) {
                        oldNmCuentaOfOpmTransaccionListNewOpmTransaccion.getOpmTransaccionList().remove(opmTransaccionListNewOpmTransaccion);
                        oldNmCuentaOfOpmTransaccionListNewOpmTransaccion = em.merge(oldNmCuentaOfOpmTransaccionListNewOpmTransaccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmCuenta.getNmCodigo();
                if (findOpmCuenta(id) == null) {
                    throw new NonexistentEntityException("The opmCuenta with id " + id + " no longer exists.");
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
            OpmCuenta opmCuenta;
            try {
                opmCuenta = em.getReference(OpmCuenta.class, id);
                opmCuenta.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmCuenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmTransaccion> opmTransaccionListOrphanCheck = opmCuenta.getOpmTransaccionList();
            for (OpmTransaccion opmTransaccionListOrphanCheckOpmTransaccion : opmTransaccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmCuenta (" + opmCuenta + ") cannot be destroyed since the OpmTransaccion " + opmTransaccionListOrphanCheckOpmTransaccion + " in its opmTransaccionList field has a non-nullable nmCuenta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmGrupo nmGrupo = opmCuenta.getNmGrupo();
            if (nmGrupo != null) {
                nmGrupo.getOpmCuentaList().remove(opmCuenta);
                nmGrupo = em.merge(nmGrupo);
            }
            em.remove(opmCuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmCuenta> findOpmCuentaEntities() {
        return findOpmCuentaEntities(true, -1, -1);
    }

    public List<OpmCuenta> findOpmCuentaEntities(int maxResults, int firstResult) {
        return findOpmCuentaEntities(false, maxResults, firstResult);
    }

    private List<OpmCuenta> findOpmCuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmCuenta.class));
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

    public OpmCuenta findOpmCuenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmCuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmCuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmCuenta> rt = cq.from(OpmCuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
