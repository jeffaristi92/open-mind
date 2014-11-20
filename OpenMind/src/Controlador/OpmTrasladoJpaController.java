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
import Modelo.OpmPuntoVenta;
import Modelo.OpmDetalleTraslado;
import Modelo.OpmTraslado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmTrasladoJpaController implements Serializable {

    public OpmTrasladoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmTraslado opmTraslado) {
        if (opmTraslado.getOpmDetalleTrasladoList() == null) {
            opmTraslado.setOpmDetalleTrasladoList(new ArrayList<OpmDetalleTraslado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmPuntoVenta nmOrigen = opmTraslado.getNmOrigen();
            if (nmOrigen != null) {
                nmOrigen = em.getReference(nmOrigen.getClass(), nmOrigen.getNmCodigo());
                opmTraslado.setNmOrigen(nmOrigen);
            }
            List<OpmDetalleTraslado> attachedOpmDetalleTrasladoList = new ArrayList<OpmDetalleTraslado>();
            for (OpmDetalleTraslado opmDetalleTrasladoListOpmDetalleTrasladoToAttach : opmTraslado.getOpmDetalleTrasladoList()) {
                opmDetalleTrasladoListOpmDetalleTrasladoToAttach = em.getReference(opmDetalleTrasladoListOpmDetalleTrasladoToAttach.getClass(), opmDetalleTrasladoListOpmDetalleTrasladoToAttach.getNmCodigo());
                attachedOpmDetalleTrasladoList.add(opmDetalleTrasladoListOpmDetalleTrasladoToAttach);
            }
            opmTraslado.setOpmDetalleTrasladoList(attachedOpmDetalleTrasladoList);
            em.persist(opmTraslado);
            if (nmOrigen != null) {
                nmOrigen.getOpmTrasladoList().add(opmTraslado);
                nmOrigen = em.merge(nmOrigen);
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListOpmDetalleTraslado : opmTraslado.getOpmDetalleTrasladoList()) {
                OpmTraslado oldNmTrasladoOfOpmDetalleTrasladoListOpmDetalleTraslado = opmDetalleTrasladoListOpmDetalleTraslado.getNmTraslado();
                opmDetalleTrasladoListOpmDetalleTraslado.setNmTraslado(opmTraslado);
                opmDetalleTrasladoListOpmDetalleTraslado = em.merge(opmDetalleTrasladoListOpmDetalleTraslado);
                if (oldNmTrasladoOfOpmDetalleTrasladoListOpmDetalleTraslado != null) {
                    oldNmTrasladoOfOpmDetalleTrasladoListOpmDetalleTraslado.getOpmDetalleTrasladoList().remove(opmDetalleTrasladoListOpmDetalleTraslado);
                    oldNmTrasladoOfOpmDetalleTrasladoListOpmDetalleTraslado = em.merge(oldNmTrasladoOfOpmDetalleTrasladoListOpmDetalleTraslado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmTraslado opmTraslado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmTraslado persistentOpmTraslado = em.find(OpmTraslado.class, opmTraslado.getNmCodigo());
            OpmPuntoVenta nmOrigenOld = persistentOpmTraslado.getNmOrigen();
            OpmPuntoVenta nmOrigenNew = opmTraslado.getNmOrigen();
            List<OpmDetalleTraslado> opmDetalleTrasladoListOld = persistentOpmTraslado.getOpmDetalleTrasladoList();
            List<OpmDetalleTraslado> opmDetalleTrasladoListNew = opmTraslado.getOpmDetalleTrasladoList();
            List<String> illegalOrphanMessages = null;
            for (OpmDetalleTraslado opmDetalleTrasladoListOldOpmDetalleTraslado : opmDetalleTrasladoListOld) {
                if (!opmDetalleTrasladoListNew.contains(opmDetalleTrasladoListOldOpmDetalleTraslado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleTraslado " + opmDetalleTrasladoListOldOpmDetalleTraslado + " since its nmTraslado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmOrigenNew != null) {
                nmOrigenNew = em.getReference(nmOrigenNew.getClass(), nmOrigenNew.getNmCodigo());
                opmTraslado.setNmOrigen(nmOrigenNew);
            }
            List<OpmDetalleTraslado> attachedOpmDetalleTrasladoListNew = new ArrayList<OpmDetalleTraslado>();
            for (OpmDetalleTraslado opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach : opmDetalleTrasladoListNew) {
                opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach = em.getReference(opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach.getClass(), opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach.getNmCodigo());
                attachedOpmDetalleTrasladoListNew.add(opmDetalleTrasladoListNewOpmDetalleTrasladoToAttach);
            }
            opmDetalleTrasladoListNew = attachedOpmDetalleTrasladoListNew;
            opmTraslado.setOpmDetalleTrasladoList(opmDetalleTrasladoListNew);
            opmTraslado = em.merge(opmTraslado);
            if (nmOrigenOld != null && !nmOrigenOld.equals(nmOrigenNew)) {
                nmOrigenOld.getOpmTrasladoList().remove(opmTraslado);
                nmOrigenOld = em.merge(nmOrigenOld);
            }
            if (nmOrigenNew != null && !nmOrigenNew.equals(nmOrigenOld)) {
                nmOrigenNew.getOpmTrasladoList().add(opmTraslado);
                nmOrigenNew = em.merge(nmOrigenNew);
            }
            for (OpmDetalleTraslado opmDetalleTrasladoListNewOpmDetalleTraslado : opmDetalleTrasladoListNew) {
                if (!opmDetalleTrasladoListOld.contains(opmDetalleTrasladoListNewOpmDetalleTraslado)) {
                    OpmTraslado oldNmTrasladoOfOpmDetalleTrasladoListNewOpmDetalleTraslado = opmDetalleTrasladoListNewOpmDetalleTraslado.getNmTraslado();
                    opmDetalleTrasladoListNewOpmDetalleTraslado.setNmTraslado(opmTraslado);
                    opmDetalleTrasladoListNewOpmDetalleTraslado = em.merge(opmDetalleTrasladoListNewOpmDetalleTraslado);
                    if (oldNmTrasladoOfOpmDetalleTrasladoListNewOpmDetalleTraslado != null && !oldNmTrasladoOfOpmDetalleTrasladoListNewOpmDetalleTraslado.equals(opmTraslado)) {
                        oldNmTrasladoOfOpmDetalleTrasladoListNewOpmDetalleTraslado.getOpmDetalleTrasladoList().remove(opmDetalleTrasladoListNewOpmDetalleTraslado);
                        oldNmTrasladoOfOpmDetalleTrasladoListNewOpmDetalleTraslado = em.merge(oldNmTrasladoOfOpmDetalleTrasladoListNewOpmDetalleTraslado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmTraslado.getNmCodigo();
                if (findOpmTraslado(id) == null) {
                    throw new NonexistentEntityException("The opmTraslado with id " + id + " no longer exists.");
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
            OpmTraslado opmTraslado;
            try {
                opmTraslado = em.getReference(OpmTraslado.class, id);
                opmTraslado.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmTraslado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmDetalleTraslado> opmDetalleTrasladoListOrphanCheck = opmTraslado.getOpmDetalleTrasladoList();
            for (OpmDetalleTraslado opmDetalleTrasladoListOrphanCheckOpmDetalleTraslado : opmDetalleTrasladoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmTraslado (" + opmTraslado + ") cannot be destroyed since the OpmDetalleTraslado " + opmDetalleTrasladoListOrphanCheckOpmDetalleTraslado + " in its opmDetalleTrasladoList field has a non-nullable nmTraslado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmPuntoVenta nmOrigen = opmTraslado.getNmOrigen();
            if (nmOrigen != null) {
                nmOrigen.getOpmTrasladoList().remove(opmTraslado);
                nmOrigen = em.merge(nmOrigen);
            }
            em.remove(opmTraslado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmTraslado> findOpmTrasladoEntities() {
        return findOpmTrasladoEntities(true, -1, -1);
    }

    public List<OpmTraslado> findOpmTrasladoEntities(int maxResults, int firstResult) {
        return findOpmTrasladoEntities(false, maxResults, firstResult);
    }

    private List<OpmTraslado> findOpmTrasladoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmTraslado.class));
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

    public OpmTraslado findOpmTraslado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmTraslado.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmTrasladoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmTraslado> rt = cq.from(OpmTraslado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
