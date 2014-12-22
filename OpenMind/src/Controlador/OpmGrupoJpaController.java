/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmClase;
import Modelo.OpmCuenta;
import Modelo.OpmGrupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmGrupoJpaController implements Serializable {

    public OpmGrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmGrupo opmGrupo) throws PreexistingEntityException, Exception {
        if (opmGrupo.getOpmCuentaList() == null) {
            opmGrupo.setOpmCuentaList(new ArrayList<OpmCuenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmClase nmClase = opmGrupo.getNmClase();
            if (nmClase != null) {
                nmClase = em.getReference(nmClase.getClass(), nmClase.getNmCodigo());
                opmGrupo.setNmClase(nmClase);
            }
            List<OpmCuenta> attachedOpmCuentaList = new ArrayList<OpmCuenta>();
            for (OpmCuenta opmCuentaListOpmCuentaToAttach : opmGrupo.getOpmCuentaList()) {
                opmCuentaListOpmCuentaToAttach = em.getReference(opmCuentaListOpmCuentaToAttach.getClass(), opmCuentaListOpmCuentaToAttach.getNmCodigo());
                attachedOpmCuentaList.add(opmCuentaListOpmCuentaToAttach);
            }
            opmGrupo.setOpmCuentaList(attachedOpmCuentaList);
            em.persist(opmGrupo);
            if (nmClase != null) {
                nmClase.getOpmGrupoList().add(opmGrupo);
                nmClase = em.merge(nmClase);
            }
            for (OpmCuenta opmCuentaListOpmCuenta : opmGrupo.getOpmCuentaList()) {
                OpmGrupo oldNmGrupoOfOpmCuentaListOpmCuenta = opmCuentaListOpmCuenta.getNmGrupo();
                opmCuentaListOpmCuenta.setNmGrupo(opmGrupo);
                opmCuentaListOpmCuenta = em.merge(opmCuentaListOpmCuenta);
                if (oldNmGrupoOfOpmCuentaListOpmCuenta != null) {
                    oldNmGrupoOfOpmCuentaListOpmCuenta.getOpmCuentaList().remove(opmCuentaListOpmCuenta);
                    oldNmGrupoOfOpmCuentaListOpmCuenta = em.merge(oldNmGrupoOfOpmCuentaListOpmCuenta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpmGrupo(opmGrupo.getNmCodigo()) != null) {
                throw new PreexistingEntityException("OpmGrupo " + opmGrupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmGrupo opmGrupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmGrupo persistentOpmGrupo = em.find(OpmGrupo.class, opmGrupo.getNmCodigo());
            OpmClase nmClaseOld = persistentOpmGrupo.getNmClase();
            OpmClase nmClaseNew = opmGrupo.getNmClase();
            List<OpmCuenta> opmCuentaListOld = persistentOpmGrupo.getOpmCuentaList();
            List<OpmCuenta> opmCuentaListNew = opmGrupo.getOpmCuentaList();
            List<String> illegalOrphanMessages = null;
            for (OpmCuenta opmCuentaListOldOpmCuenta : opmCuentaListOld) {
                if (!opmCuentaListNew.contains(opmCuentaListOldOpmCuenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmCuenta " + opmCuentaListOldOpmCuenta + " since its nmGrupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmClaseNew != null) {
                nmClaseNew = em.getReference(nmClaseNew.getClass(), nmClaseNew.getNmCodigo());
                opmGrupo.setNmClase(nmClaseNew);
            }
            List<OpmCuenta> attachedOpmCuentaListNew = new ArrayList<OpmCuenta>();
            for (OpmCuenta opmCuentaListNewOpmCuentaToAttach : opmCuentaListNew) {
                opmCuentaListNewOpmCuentaToAttach = em.getReference(opmCuentaListNewOpmCuentaToAttach.getClass(), opmCuentaListNewOpmCuentaToAttach.getNmCodigo());
                attachedOpmCuentaListNew.add(opmCuentaListNewOpmCuentaToAttach);
            }
            opmCuentaListNew = attachedOpmCuentaListNew;
            opmGrupo.setOpmCuentaList(opmCuentaListNew);
            opmGrupo = em.merge(opmGrupo);
            if (nmClaseOld != null && !nmClaseOld.equals(nmClaseNew)) {
                nmClaseOld.getOpmGrupoList().remove(opmGrupo);
                nmClaseOld = em.merge(nmClaseOld);
            }
            if (nmClaseNew != null && !nmClaseNew.equals(nmClaseOld)) {
                nmClaseNew.getOpmGrupoList().add(opmGrupo);
                nmClaseNew = em.merge(nmClaseNew);
            }
            for (OpmCuenta opmCuentaListNewOpmCuenta : opmCuentaListNew) {
                if (!opmCuentaListOld.contains(opmCuentaListNewOpmCuenta)) {
                    OpmGrupo oldNmGrupoOfOpmCuentaListNewOpmCuenta = opmCuentaListNewOpmCuenta.getNmGrupo();
                    opmCuentaListNewOpmCuenta.setNmGrupo(opmGrupo);
                    opmCuentaListNewOpmCuenta = em.merge(opmCuentaListNewOpmCuenta);
                    if (oldNmGrupoOfOpmCuentaListNewOpmCuenta != null && !oldNmGrupoOfOpmCuentaListNewOpmCuenta.equals(opmGrupo)) {
                        oldNmGrupoOfOpmCuentaListNewOpmCuenta.getOpmCuentaList().remove(opmCuentaListNewOpmCuenta);
                        oldNmGrupoOfOpmCuentaListNewOpmCuenta = em.merge(oldNmGrupoOfOpmCuentaListNewOpmCuenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmGrupo.getNmCodigo();
                if (findOpmGrupo(id) == null) {
                    throw new NonexistentEntityException("The opmGrupo with id " + id + " no longer exists.");
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
            OpmGrupo opmGrupo;
            try {
                opmGrupo = em.getReference(OpmGrupo.class, id);
                opmGrupo.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmGrupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmCuenta> opmCuentaListOrphanCheck = opmGrupo.getOpmCuentaList();
            for (OpmCuenta opmCuentaListOrphanCheckOpmCuenta : opmCuentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmGrupo (" + opmGrupo + ") cannot be destroyed since the OpmCuenta " + opmCuentaListOrphanCheckOpmCuenta + " in its opmCuentaList field has a non-nullable nmGrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmClase nmClase = opmGrupo.getNmClase();
            if (nmClase != null) {
                nmClase.getOpmGrupoList().remove(opmGrupo);
                nmClase = em.merge(nmClase);
            }
            em.remove(opmGrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmGrupo> findOpmGrupoEntities() {
        return findOpmGrupoEntities(true, -1, -1);
    }

    public List<OpmGrupo> findOpmGrupoEntities(int maxResults, int firstResult) {
        return findOpmGrupoEntities(false, maxResults, firstResult);
    }

    private List<OpmGrupo> findOpmGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmGrupo.class));
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

    public OpmGrupo findOpmGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmGrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmGrupo> rt = cq.from(OpmGrupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
