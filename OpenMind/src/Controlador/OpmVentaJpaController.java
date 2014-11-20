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
import Modelo.OpmUsuario;
import Modelo.OpmPuntoVenta;
import Modelo.OpmAbono;
import java.util.ArrayList;
import java.util.List;
import Modelo.OpmDetalleVenta;
import Modelo.OpmVenta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmVentaJpaController implements Serializable {

    public OpmVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmVenta opmVenta) {
        if (opmVenta.getOpmAbonoList() == null) {
            opmVenta.setOpmAbonoList(new ArrayList<OpmAbono>());
        }
        if (opmVenta.getOpmDetalleVentaList() == null) {
            opmVenta.setOpmDetalleVentaList(new ArrayList<OpmDetalleVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmUsuario nmCliente = opmVenta.getNmCliente();
            if (nmCliente != null) {
                nmCliente = em.getReference(nmCliente.getClass(), nmCliente.getNmCodigo());
                opmVenta.setNmCliente(nmCliente);
            }
            OpmPuntoVenta nmPunto = opmVenta.getNmPunto();
            if (nmPunto != null) {
                nmPunto = em.getReference(nmPunto.getClass(), nmPunto.getNmCodigo());
                opmVenta.setNmPunto(nmPunto);
            }
            List<OpmAbono> attachedOpmAbonoList = new ArrayList<OpmAbono>();
            for (OpmAbono opmAbonoListOpmAbonoToAttach : opmVenta.getOpmAbonoList()) {
                opmAbonoListOpmAbonoToAttach = em.getReference(opmAbonoListOpmAbonoToAttach.getClass(), opmAbonoListOpmAbonoToAttach.getNmCodigo());
                attachedOpmAbonoList.add(opmAbonoListOpmAbonoToAttach);
            }
            opmVenta.setOpmAbonoList(attachedOpmAbonoList);
            List<OpmDetalleVenta> attachedOpmDetalleVentaList = new ArrayList<OpmDetalleVenta>();
            for (OpmDetalleVenta opmDetalleVentaListOpmDetalleVentaToAttach : opmVenta.getOpmDetalleVentaList()) {
                opmDetalleVentaListOpmDetalleVentaToAttach = em.getReference(opmDetalleVentaListOpmDetalleVentaToAttach.getClass(), opmDetalleVentaListOpmDetalleVentaToAttach.getNmCodigo());
                attachedOpmDetalleVentaList.add(opmDetalleVentaListOpmDetalleVentaToAttach);
            }
            opmVenta.setOpmDetalleVentaList(attachedOpmDetalleVentaList);
            em.persist(opmVenta);
            if (nmCliente != null) {
                nmCliente.getOpmVentaList().add(opmVenta);
                nmCliente = em.merge(nmCliente);
            }
            if (nmPunto != null) {
                nmPunto.getOpmVentaList().add(opmVenta);
                nmPunto = em.merge(nmPunto);
            }
            for (OpmAbono opmAbonoListOpmAbono : opmVenta.getOpmAbonoList()) {
                OpmVenta oldNmVentaOfOpmAbonoListOpmAbono = opmAbonoListOpmAbono.getNmVenta();
                opmAbonoListOpmAbono.setNmVenta(opmVenta);
                opmAbonoListOpmAbono = em.merge(opmAbonoListOpmAbono);
                if (oldNmVentaOfOpmAbonoListOpmAbono != null) {
                    oldNmVentaOfOpmAbonoListOpmAbono.getOpmAbonoList().remove(opmAbonoListOpmAbono);
                    oldNmVentaOfOpmAbonoListOpmAbono = em.merge(oldNmVentaOfOpmAbonoListOpmAbono);
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListOpmDetalleVenta : opmVenta.getOpmDetalleVentaList()) {
                OpmVenta oldNmVentaOfOpmDetalleVentaListOpmDetalleVenta = opmDetalleVentaListOpmDetalleVenta.getNmVenta();
                opmDetalleVentaListOpmDetalleVenta.setNmVenta(opmVenta);
                opmDetalleVentaListOpmDetalleVenta = em.merge(opmDetalleVentaListOpmDetalleVenta);
                if (oldNmVentaOfOpmDetalleVentaListOpmDetalleVenta != null) {
                    oldNmVentaOfOpmDetalleVentaListOpmDetalleVenta.getOpmDetalleVentaList().remove(opmDetalleVentaListOpmDetalleVenta);
                    oldNmVentaOfOpmDetalleVentaListOpmDetalleVenta = em.merge(oldNmVentaOfOpmDetalleVentaListOpmDetalleVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmVenta opmVenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmVenta persistentOpmVenta = em.find(OpmVenta.class, opmVenta.getNmCodigo());
            OpmUsuario nmClienteOld = persistentOpmVenta.getNmCliente();
            OpmUsuario nmClienteNew = opmVenta.getNmCliente();
            OpmPuntoVenta nmPuntoOld = persistentOpmVenta.getNmPunto();
            OpmPuntoVenta nmPuntoNew = opmVenta.getNmPunto();
            List<OpmAbono> opmAbonoListOld = persistentOpmVenta.getOpmAbonoList();
            List<OpmAbono> opmAbonoListNew = opmVenta.getOpmAbonoList();
            List<OpmDetalleVenta> opmDetalleVentaListOld = persistentOpmVenta.getOpmDetalleVentaList();
            List<OpmDetalleVenta> opmDetalleVentaListNew = opmVenta.getOpmDetalleVentaList();
            List<String> illegalOrphanMessages = null;
            for (OpmAbono opmAbonoListOldOpmAbono : opmAbonoListOld) {
                if (!opmAbonoListNew.contains(opmAbonoListOldOpmAbono)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmAbono " + opmAbonoListOldOpmAbono + " since its nmVenta field is not nullable.");
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListOldOpmDetalleVenta : opmDetalleVentaListOld) {
                if (!opmDetalleVentaListNew.contains(opmDetalleVentaListOldOpmDetalleVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleVenta " + opmDetalleVentaListOldOpmDetalleVenta + " since its nmVenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmClienteNew != null) {
                nmClienteNew = em.getReference(nmClienteNew.getClass(), nmClienteNew.getNmCodigo());
                opmVenta.setNmCliente(nmClienteNew);
            }
            if (nmPuntoNew != null) {
                nmPuntoNew = em.getReference(nmPuntoNew.getClass(), nmPuntoNew.getNmCodigo());
                opmVenta.setNmPunto(nmPuntoNew);
            }
            List<OpmAbono> attachedOpmAbonoListNew = new ArrayList<OpmAbono>();
            for (OpmAbono opmAbonoListNewOpmAbonoToAttach : opmAbonoListNew) {
                opmAbonoListNewOpmAbonoToAttach = em.getReference(opmAbonoListNewOpmAbonoToAttach.getClass(), opmAbonoListNewOpmAbonoToAttach.getNmCodigo());
                attachedOpmAbonoListNew.add(opmAbonoListNewOpmAbonoToAttach);
            }
            opmAbonoListNew = attachedOpmAbonoListNew;
            opmVenta.setOpmAbonoList(opmAbonoListNew);
            List<OpmDetalleVenta> attachedOpmDetalleVentaListNew = new ArrayList<OpmDetalleVenta>();
            for (OpmDetalleVenta opmDetalleVentaListNewOpmDetalleVentaToAttach : opmDetalleVentaListNew) {
                opmDetalleVentaListNewOpmDetalleVentaToAttach = em.getReference(opmDetalleVentaListNewOpmDetalleVentaToAttach.getClass(), opmDetalleVentaListNewOpmDetalleVentaToAttach.getNmCodigo());
                attachedOpmDetalleVentaListNew.add(opmDetalleVentaListNewOpmDetalleVentaToAttach);
            }
            opmDetalleVentaListNew = attachedOpmDetalleVentaListNew;
            opmVenta.setOpmDetalleVentaList(opmDetalleVentaListNew);
            opmVenta = em.merge(opmVenta);
            if (nmClienteOld != null && !nmClienteOld.equals(nmClienteNew)) {
                nmClienteOld.getOpmVentaList().remove(opmVenta);
                nmClienteOld = em.merge(nmClienteOld);
            }
            if (nmClienteNew != null && !nmClienteNew.equals(nmClienteOld)) {
                nmClienteNew.getOpmVentaList().add(opmVenta);
                nmClienteNew = em.merge(nmClienteNew);
            }
            if (nmPuntoOld != null && !nmPuntoOld.equals(nmPuntoNew)) {
                nmPuntoOld.getOpmVentaList().remove(opmVenta);
                nmPuntoOld = em.merge(nmPuntoOld);
            }
            if (nmPuntoNew != null && !nmPuntoNew.equals(nmPuntoOld)) {
                nmPuntoNew.getOpmVentaList().add(opmVenta);
                nmPuntoNew = em.merge(nmPuntoNew);
            }
            for (OpmAbono opmAbonoListNewOpmAbono : opmAbonoListNew) {
                if (!opmAbonoListOld.contains(opmAbonoListNewOpmAbono)) {
                    OpmVenta oldNmVentaOfOpmAbonoListNewOpmAbono = opmAbonoListNewOpmAbono.getNmVenta();
                    opmAbonoListNewOpmAbono.setNmVenta(opmVenta);
                    opmAbonoListNewOpmAbono = em.merge(opmAbonoListNewOpmAbono);
                    if (oldNmVentaOfOpmAbonoListNewOpmAbono != null && !oldNmVentaOfOpmAbonoListNewOpmAbono.equals(opmVenta)) {
                        oldNmVentaOfOpmAbonoListNewOpmAbono.getOpmAbonoList().remove(opmAbonoListNewOpmAbono);
                        oldNmVentaOfOpmAbonoListNewOpmAbono = em.merge(oldNmVentaOfOpmAbonoListNewOpmAbono);
                    }
                }
            }
            for (OpmDetalleVenta opmDetalleVentaListNewOpmDetalleVenta : opmDetalleVentaListNew) {
                if (!opmDetalleVentaListOld.contains(opmDetalleVentaListNewOpmDetalleVenta)) {
                    OpmVenta oldNmVentaOfOpmDetalleVentaListNewOpmDetalleVenta = opmDetalleVentaListNewOpmDetalleVenta.getNmVenta();
                    opmDetalleVentaListNewOpmDetalleVenta.setNmVenta(opmVenta);
                    opmDetalleVentaListNewOpmDetalleVenta = em.merge(opmDetalleVentaListNewOpmDetalleVenta);
                    if (oldNmVentaOfOpmDetalleVentaListNewOpmDetalleVenta != null && !oldNmVentaOfOpmDetalleVentaListNewOpmDetalleVenta.equals(opmVenta)) {
                        oldNmVentaOfOpmDetalleVentaListNewOpmDetalleVenta.getOpmDetalleVentaList().remove(opmDetalleVentaListNewOpmDetalleVenta);
                        oldNmVentaOfOpmDetalleVentaListNewOpmDetalleVenta = em.merge(oldNmVentaOfOpmDetalleVentaListNewOpmDetalleVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmVenta.getNmCodigo();
                if (findOpmVenta(id) == null) {
                    throw new NonexistentEntityException("The opmVenta with id " + id + " no longer exists.");
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
            OpmVenta opmVenta;
            try {
                opmVenta = em.getReference(OpmVenta.class, id);
                opmVenta.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmVenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmAbono> opmAbonoListOrphanCheck = opmVenta.getOpmAbonoList();
            for (OpmAbono opmAbonoListOrphanCheckOpmAbono : opmAbonoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmVenta (" + opmVenta + ") cannot be destroyed since the OpmAbono " + opmAbonoListOrphanCheckOpmAbono + " in its opmAbonoList field has a non-nullable nmVenta field.");
            }
            List<OpmDetalleVenta> opmDetalleVentaListOrphanCheck = opmVenta.getOpmDetalleVentaList();
            for (OpmDetalleVenta opmDetalleVentaListOrphanCheckOpmDetalleVenta : opmDetalleVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmVenta (" + opmVenta + ") cannot be destroyed since the OpmDetalleVenta " + opmDetalleVentaListOrphanCheckOpmDetalleVenta + " in its opmDetalleVentaList field has a non-nullable nmVenta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmUsuario nmCliente = opmVenta.getNmCliente();
            if (nmCliente != null) {
                nmCliente.getOpmVentaList().remove(opmVenta);
                nmCliente = em.merge(nmCliente);
            }
            OpmPuntoVenta nmPunto = opmVenta.getNmPunto();
            if (nmPunto != null) {
                nmPunto.getOpmVentaList().remove(opmVenta);
                nmPunto = em.merge(nmPunto);
            }
            em.remove(opmVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmVenta> findOpmVentaEntities() {
        return findOpmVentaEntities(true, -1, -1);
    }

    public List<OpmVenta> findOpmVentaEntities(int maxResults, int firstResult) {
        return findOpmVentaEntities(false, maxResults, firstResult);
    }

    private List<OpmVenta> findOpmVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmVenta.class));
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

    public OpmVenta findOpmVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmVenta> rt = cq.from(OpmVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
