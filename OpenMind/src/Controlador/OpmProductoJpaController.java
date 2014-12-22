/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Modelo.OpmProducto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmReferenciaProducto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmProductoJpaController implements Serializable {

    public OpmProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmProducto opmProducto) {
        if (opmProducto.getOpmReferenciaProductoList() == null) {
            opmProducto.setOpmReferenciaProductoList(new ArrayList<OpmReferenciaProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmReferenciaProducto> attachedOpmReferenciaProductoList = new ArrayList<OpmReferenciaProducto>();
            for (OpmReferenciaProducto opmReferenciaProductoListOpmReferenciaProductoToAttach : opmProducto.getOpmReferenciaProductoList()) {
                opmReferenciaProductoListOpmReferenciaProductoToAttach = em.getReference(opmReferenciaProductoListOpmReferenciaProductoToAttach.getClass(), opmReferenciaProductoListOpmReferenciaProductoToAttach.getNvCodigo());
                attachedOpmReferenciaProductoList.add(opmReferenciaProductoListOpmReferenciaProductoToAttach);
            }
            opmProducto.setOpmReferenciaProductoList(attachedOpmReferenciaProductoList);
            em.persist(opmProducto);
            for (OpmReferenciaProducto opmReferenciaProductoListOpmReferenciaProducto : opmProducto.getOpmReferenciaProductoList()) {
                OpmProducto oldNmProductoOfOpmReferenciaProductoListOpmReferenciaProducto = opmReferenciaProductoListOpmReferenciaProducto.getNmProducto();
                opmReferenciaProductoListOpmReferenciaProducto.setNmProducto(opmProducto);
                opmReferenciaProductoListOpmReferenciaProducto = em.merge(opmReferenciaProductoListOpmReferenciaProducto);
                if (oldNmProductoOfOpmReferenciaProductoListOpmReferenciaProducto != null) {
                    oldNmProductoOfOpmReferenciaProductoListOpmReferenciaProducto.getOpmReferenciaProductoList().remove(opmReferenciaProductoListOpmReferenciaProducto);
                    oldNmProductoOfOpmReferenciaProductoListOpmReferenciaProducto = em.merge(oldNmProductoOfOpmReferenciaProductoListOpmReferenciaProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmProducto opmProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmProducto persistentOpmProducto = em.find(OpmProducto.class, opmProducto.getNmCodigo());
            List<OpmReferenciaProducto> opmReferenciaProductoListOld = persistentOpmProducto.getOpmReferenciaProductoList();
            List<OpmReferenciaProducto> opmReferenciaProductoListNew = opmProducto.getOpmReferenciaProductoList();
            List<String> illegalOrphanMessages = null;
            for (OpmReferenciaProducto opmReferenciaProductoListOldOpmReferenciaProducto : opmReferenciaProductoListOld) {
                if (!opmReferenciaProductoListNew.contains(opmReferenciaProductoListOldOpmReferenciaProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmReferenciaProducto " + opmReferenciaProductoListOldOpmReferenciaProducto + " since its nmProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmReferenciaProducto> attachedOpmReferenciaProductoListNew = new ArrayList<OpmReferenciaProducto>();
            for (OpmReferenciaProducto opmReferenciaProductoListNewOpmReferenciaProductoToAttach : opmReferenciaProductoListNew) {
                opmReferenciaProductoListNewOpmReferenciaProductoToAttach = em.getReference(opmReferenciaProductoListNewOpmReferenciaProductoToAttach.getClass(), opmReferenciaProductoListNewOpmReferenciaProductoToAttach.getNvCodigo());
                attachedOpmReferenciaProductoListNew.add(opmReferenciaProductoListNewOpmReferenciaProductoToAttach);
            }
            opmReferenciaProductoListNew = attachedOpmReferenciaProductoListNew;
            opmProducto.setOpmReferenciaProductoList(opmReferenciaProductoListNew);
            opmProducto = em.merge(opmProducto);
            for (OpmReferenciaProducto opmReferenciaProductoListNewOpmReferenciaProducto : opmReferenciaProductoListNew) {
                if (!opmReferenciaProductoListOld.contains(opmReferenciaProductoListNewOpmReferenciaProducto)) {
                    OpmProducto oldNmProductoOfOpmReferenciaProductoListNewOpmReferenciaProducto = opmReferenciaProductoListNewOpmReferenciaProducto.getNmProducto();
                    opmReferenciaProductoListNewOpmReferenciaProducto.setNmProducto(opmProducto);
                    opmReferenciaProductoListNewOpmReferenciaProducto = em.merge(opmReferenciaProductoListNewOpmReferenciaProducto);
                    if (oldNmProductoOfOpmReferenciaProductoListNewOpmReferenciaProducto != null && !oldNmProductoOfOpmReferenciaProductoListNewOpmReferenciaProducto.equals(opmProducto)) {
                        oldNmProductoOfOpmReferenciaProductoListNewOpmReferenciaProducto.getOpmReferenciaProductoList().remove(opmReferenciaProductoListNewOpmReferenciaProducto);
                        oldNmProductoOfOpmReferenciaProductoListNewOpmReferenciaProducto = em.merge(oldNmProductoOfOpmReferenciaProductoListNewOpmReferenciaProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmProducto.getNmCodigo();
                if (findOpmProducto(id) == null) {
                    throw new NonexistentEntityException("The opmProducto with id " + id + " no longer exists.");
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
            OpmProducto opmProducto;
            try {
                opmProducto = em.getReference(OpmProducto.class, id);
                opmProducto.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmReferenciaProducto> opmReferenciaProductoListOrphanCheck = opmProducto.getOpmReferenciaProductoList();
            for (OpmReferenciaProducto opmReferenciaProductoListOrphanCheckOpmReferenciaProducto : opmReferenciaProductoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmProducto (" + opmProducto + ") cannot be destroyed since the OpmReferenciaProducto " + opmReferenciaProductoListOrphanCheckOpmReferenciaProducto + " in its opmReferenciaProductoList field has a non-nullable nmProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(opmProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmProducto> findOpmProductoEntities() {
        return findOpmProductoEntities(true, -1, -1);
    }

    public List<OpmProducto> findOpmProductoEntities(int maxResults, int firstResult) {
        return findOpmProductoEntities(false, maxResults, firstResult);
    }

    private List<OpmProducto> findOpmProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmProducto.class));
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

    public OpmProducto findOpmProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmProducto> rt = cq.from(OpmProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
