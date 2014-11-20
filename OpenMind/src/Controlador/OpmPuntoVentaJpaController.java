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
import Modelo.OpmTraslado;
import java.util.ArrayList;
import java.util.List;
import Modelo.OpmInventarioPunto;
import Modelo.OpmPuntoVenta;
import Modelo.OpmVenta;
import Modelo.OpmRemision;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmPuntoVentaJpaController implements Serializable {

    public OpmPuntoVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmPuntoVenta opmPuntoVenta) {
        if (opmPuntoVenta.getOpmTrasladoList() == null) {
            opmPuntoVenta.setOpmTrasladoList(new ArrayList<OpmTraslado>());
        }
        if (opmPuntoVenta.getOpmInventarioPuntoList() == null) {
            opmPuntoVenta.setOpmInventarioPuntoList(new ArrayList<OpmInventarioPunto>());
        }
        if (opmPuntoVenta.getOpmVentaList() == null) {
            opmPuntoVenta.setOpmVentaList(new ArrayList<OpmVenta>());
        }
        if (opmPuntoVenta.getOpmRemisionList() == null) {
            opmPuntoVenta.setOpmRemisionList(new ArrayList<OpmRemision>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmUsuario nmAdministrador = opmPuntoVenta.getNmAdministrador();
            if (nmAdministrador != null) {
                nmAdministrador = em.getReference(nmAdministrador.getClass(), nmAdministrador.getNmCodigo());
                opmPuntoVenta.setNmAdministrador(nmAdministrador);
            }
            List<OpmTraslado> attachedOpmTrasladoList = new ArrayList<OpmTraslado>();
            for (OpmTraslado opmTrasladoListOpmTrasladoToAttach : opmPuntoVenta.getOpmTrasladoList()) {
                opmTrasladoListOpmTrasladoToAttach = em.getReference(opmTrasladoListOpmTrasladoToAttach.getClass(), opmTrasladoListOpmTrasladoToAttach.getNmCodigo());
                attachedOpmTrasladoList.add(opmTrasladoListOpmTrasladoToAttach);
            }
            opmPuntoVenta.setOpmTrasladoList(attachedOpmTrasladoList);
            List<OpmInventarioPunto> attachedOpmInventarioPuntoList = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPuntoToAttach : opmPuntoVenta.getOpmInventarioPuntoList()) {
                opmInventarioPuntoListOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoList.add(opmInventarioPuntoListOpmInventarioPuntoToAttach);
            }
            opmPuntoVenta.setOpmInventarioPuntoList(attachedOpmInventarioPuntoList);
            List<OpmVenta> attachedOpmVentaList = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListOpmVentaToAttach : opmPuntoVenta.getOpmVentaList()) {
                opmVentaListOpmVentaToAttach = em.getReference(opmVentaListOpmVentaToAttach.getClass(), opmVentaListOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaList.add(opmVentaListOpmVentaToAttach);
            }
            opmPuntoVenta.setOpmVentaList(attachedOpmVentaList);
            List<OpmRemision> attachedOpmRemisionList = new ArrayList<OpmRemision>();
            for (OpmRemision opmRemisionListOpmRemisionToAttach : opmPuntoVenta.getOpmRemisionList()) {
                opmRemisionListOpmRemisionToAttach = em.getReference(opmRemisionListOpmRemisionToAttach.getClass(), opmRemisionListOpmRemisionToAttach.getNmCodigo());
                attachedOpmRemisionList.add(opmRemisionListOpmRemisionToAttach);
            }
            opmPuntoVenta.setOpmRemisionList(attachedOpmRemisionList);
            em.persist(opmPuntoVenta);
            if (nmAdministrador != null) {
                nmAdministrador.getOpmPuntoVentaList().add(opmPuntoVenta);
                nmAdministrador = em.merge(nmAdministrador);
            }
            for (OpmTraslado opmTrasladoListOpmTraslado : opmPuntoVenta.getOpmTrasladoList()) {
                OpmPuntoVenta oldNmOrigenOfOpmTrasladoListOpmTraslado = opmTrasladoListOpmTraslado.getNmOrigen();
                opmTrasladoListOpmTraslado.setNmOrigen(opmPuntoVenta);
                opmTrasladoListOpmTraslado = em.merge(opmTrasladoListOpmTraslado);
                if (oldNmOrigenOfOpmTrasladoListOpmTraslado != null) {
                    oldNmOrigenOfOpmTrasladoListOpmTraslado.getOpmTrasladoList().remove(opmTrasladoListOpmTraslado);
                    oldNmOrigenOfOpmTrasladoListOpmTraslado = em.merge(oldNmOrigenOfOpmTrasladoListOpmTraslado);
                }
            }
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPunto : opmPuntoVenta.getOpmInventarioPuntoList()) {
                OpmPuntoVenta oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto = opmInventarioPuntoListOpmInventarioPunto.getNmPuntoVenta();
                opmInventarioPuntoListOpmInventarioPunto.setNmPuntoVenta(opmPuntoVenta);
                opmInventarioPuntoListOpmInventarioPunto = em.merge(opmInventarioPuntoListOpmInventarioPunto);
                if (oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto != null) {
                    oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto.getOpmInventarioPuntoList().remove(opmInventarioPuntoListOpmInventarioPunto);
                    oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto = em.merge(oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto);
                }
            }
            for (OpmVenta opmVentaListOpmVenta : opmPuntoVenta.getOpmVentaList()) {
                OpmPuntoVenta oldNmPuntoOfOpmVentaListOpmVenta = opmVentaListOpmVenta.getNmPunto();
                opmVentaListOpmVenta.setNmPunto(opmPuntoVenta);
                opmVentaListOpmVenta = em.merge(opmVentaListOpmVenta);
                if (oldNmPuntoOfOpmVentaListOpmVenta != null) {
                    oldNmPuntoOfOpmVentaListOpmVenta.getOpmVentaList().remove(opmVentaListOpmVenta);
                    oldNmPuntoOfOpmVentaListOpmVenta = em.merge(oldNmPuntoOfOpmVentaListOpmVenta);
                }
            }
            for (OpmRemision opmRemisionListOpmRemision : opmPuntoVenta.getOpmRemisionList()) {
                OpmPuntoVenta oldNmPuntoOfOpmRemisionListOpmRemision = opmRemisionListOpmRemision.getNmPunto();
                opmRemisionListOpmRemision.setNmPunto(opmPuntoVenta);
                opmRemisionListOpmRemision = em.merge(opmRemisionListOpmRemision);
                if (oldNmPuntoOfOpmRemisionListOpmRemision != null) {
                    oldNmPuntoOfOpmRemisionListOpmRemision.getOpmRemisionList().remove(opmRemisionListOpmRemision);
                    oldNmPuntoOfOpmRemisionListOpmRemision = em.merge(oldNmPuntoOfOpmRemisionListOpmRemision);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmPuntoVenta opmPuntoVenta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmPuntoVenta persistentOpmPuntoVenta = em.find(OpmPuntoVenta.class, opmPuntoVenta.getNmCodigo());
            OpmUsuario nmAdministradorOld = persistentOpmPuntoVenta.getNmAdministrador();
            OpmUsuario nmAdministradorNew = opmPuntoVenta.getNmAdministrador();
            List<OpmTraslado> opmTrasladoListOld = persistentOpmPuntoVenta.getOpmTrasladoList();
            List<OpmTraslado> opmTrasladoListNew = opmPuntoVenta.getOpmTrasladoList();
            List<OpmInventarioPunto> opmInventarioPuntoListOld = persistentOpmPuntoVenta.getOpmInventarioPuntoList();
            List<OpmInventarioPunto> opmInventarioPuntoListNew = opmPuntoVenta.getOpmInventarioPuntoList();
            List<OpmVenta> opmVentaListOld = persistentOpmPuntoVenta.getOpmVentaList();
            List<OpmVenta> opmVentaListNew = opmPuntoVenta.getOpmVentaList();
            List<OpmRemision> opmRemisionListOld = persistentOpmPuntoVenta.getOpmRemisionList();
            List<OpmRemision> opmRemisionListNew = opmPuntoVenta.getOpmRemisionList();
            List<String> illegalOrphanMessages = null;
            for (OpmTraslado opmTrasladoListOldOpmTraslado : opmTrasladoListOld) {
                if (!opmTrasladoListNew.contains(opmTrasladoListOldOpmTraslado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmTraslado " + opmTrasladoListOldOpmTraslado + " since its nmOrigen field is not nullable.");
                }
            }
            for (OpmInventarioPunto opmInventarioPuntoListOldOpmInventarioPunto : opmInventarioPuntoListOld) {
                if (!opmInventarioPuntoListNew.contains(opmInventarioPuntoListOldOpmInventarioPunto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmInventarioPunto " + opmInventarioPuntoListOldOpmInventarioPunto + " since its nmPuntoVenta field is not nullable.");
                }
            }
            for (OpmVenta opmVentaListOldOpmVenta : opmVentaListOld) {
                if (!opmVentaListNew.contains(opmVentaListOldOpmVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmVenta " + opmVentaListOldOpmVenta + " since its nmPunto field is not nullable.");
                }
            }
            for (OpmRemision opmRemisionListOldOpmRemision : opmRemisionListOld) {
                if (!opmRemisionListNew.contains(opmRemisionListOldOpmRemision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmRemision " + opmRemisionListOldOpmRemision + " since its nmPunto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmAdministradorNew != null) {
                nmAdministradorNew = em.getReference(nmAdministradorNew.getClass(), nmAdministradorNew.getNmCodigo());
                opmPuntoVenta.setNmAdministrador(nmAdministradorNew);
            }
            List<OpmTraslado> attachedOpmTrasladoListNew = new ArrayList<OpmTraslado>();
            for (OpmTraslado opmTrasladoListNewOpmTrasladoToAttach : opmTrasladoListNew) {
                opmTrasladoListNewOpmTrasladoToAttach = em.getReference(opmTrasladoListNewOpmTrasladoToAttach.getClass(), opmTrasladoListNewOpmTrasladoToAttach.getNmCodigo());
                attachedOpmTrasladoListNew.add(opmTrasladoListNewOpmTrasladoToAttach);
            }
            opmTrasladoListNew = attachedOpmTrasladoListNew;
            opmPuntoVenta.setOpmTrasladoList(opmTrasladoListNew);
            List<OpmInventarioPunto> attachedOpmInventarioPuntoListNew = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListNewOpmInventarioPuntoToAttach : opmInventarioPuntoListNew) {
                opmInventarioPuntoListNewOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoListNew.add(opmInventarioPuntoListNewOpmInventarioPuntoToAttach);
            }
            opmInventarioPuntoListNew = attachedOpmInventarioPuntoListNew;
            opmPuntoVenta.setOpmInventarioPuntoList(opmInventarioPuntoListNew);
            List<OpmVenta> attachedOpmVentaListNew = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListNewOpmVentaToAttach : opmVentaListNew) {
                opmVentaListNewOpmVentaToAttach = em.getReference(opmVentaListNewOpmVentaToAttach.getClass(), opmVentaListNewOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaListNew.add(opmVentaListNewOpmVentaToAttach);
            }
            opmVentaListNew = attachedOpmVentaListNew;
            opmPuntoVenta.setOpmVentaList(opmVentaListNew);
            List<OpmRemision> attachedOpmRemisionListNew = new ArrayList<OpmRemision>();
            for (OpmRemision opmRemisionListNewOpmRemisionToAttach : opmRemisionListNew) {
                opmRemisionListNewOpmRemisionToAttach = em.getReference(opmRemisionListNewOpmRemisionToAttach.getClass(), opmRemisionListNewOpmRemisionToAttach.getNmCodigo());
                attachedOpmRemisionListNew.add(opmRemisionListNewOpmRemisionToAttach);
            }
            opmRemisionListNew = attachedOpmRemisionListNew;
            opmPuntoVenta.setOpmRemisionList(opmRemisionListNew);
            opmPuntoVenta = em.merge(opmPuntoVenta);
            if (nmAdministradorOld != null && !nmAdministradorOld.equals(nmAdministradorNew)) {
                nmAdministradorOld.getOpmPuntoVentaList().remove(opmPuntoVenta);
                nmAdministradorOld = em.merge(nmAdministradorOld);
            }
            if (nmAdministradorNew != null && !nmAdministradorNew.equals(nmAdministradorOld)) {
                nmAdministradorNew.getOpmPuntoVentaList().add(opmPuntoVenta);
                nmAdministradorNew = em.merge(nmAdministradorNew);
            }
            for (OpmTraslado opmTrasladoListNewOpmTraslado : opmTrasladoListNew) {
                if (!opmTrasladoListOld.contains(opmTrasladoListNewOpmTraslado)) {
                    OpmPuntoVenta oldNmOrigenOfOpmTrasladoListNewOpmTraslado = opmTrasladoListNewOpmTraslado.getNmOrigen();
                    opmTrasladoListNewOpmTraslado.setNmOrigen(opmPuntoVenta);
                    opmTrasladoListNewOpmTraslado = em.merge(opmTrasladoListNewOpmTraslado);
                    if (oldNmOrigenOfOpmTrasladoListNewOpmTraslado != null && !oldNmOrigenOfOpmTrasladoListNewOpmTraslado.equals(opmPuntoVenta)) {
                        oldNmOrigenOfOpmTrasladoListNewOpmTraslado.getOpmTrasladoList().remove(opmTrasladoListNewOpmTraslado);
                        oldNmOrigenOfOpmTrasladoListNewOpmTraslado = em.merge(oldNmOrigenOfOpmTrasladoListNewOpmTraslado);
                    }
                }
            }
            for (OpmInventarioPunto opmInventarioPuntoListNewOpmInventarioPunto : opmInventarioPuntoListNew) {
                if (!opmInventarioPuntoListOld.contains(opmInventarioPuntoListNewOpmInventarioPunto)) {
                    OpmPuntoVenta oldNmPuntoVentaOfOpmInventarioPuntoListNewOpmInventarioPunto = opmInventarioPuntoListNewOpmInventarioPunto.getNmPuntoVenta();
                    opmInventarioPuntoListNewOpmInventarioPunto.setNmPuntoVenta(opmPuntoVenta);
                    opmInventarioPuntoListNewOpmInventarioPunto = em.merge(opmInventarioPuntoListNewOpmInventarioPunto);
                    if (oldNmPuntoVentaOfOpmInventarioPuntoListNewOpmInventarioPunto != null && !oldNmPuntoVentaOfOpmInventarioPuntoListNewOpmInventarioPunto.equals(opmPuntoVenta)) {
                        oldNmPuntoVentaOfOpmInventarioPuntoListNewOpmInventarioPunto.getOpmInventarioPuntoList().remove(opmInventarioPuntoListNewOpmInventarioPunto);
                        oldNmPuntoVentaOfOpmInventarioPuntoListNewOpmInventarioPunto = em.merge(oldNmPuntoVentaOfOpmInventarioPuntoListNewOpmInventarioPunto);
                    }
                }
            }
            for (OpmVenta opmVentaListNewOpmVenta : opmVentaListNew) {
                if (!opmVentaListOld.contains(opmVentaListNewOpmVenta)) {
                    OpmPuntoVenta oldNmPuntoOfOpmVentaListNewOpmVenta = opmVentaListNewOpmVenta.getNmPunto();
                    opmVentaListNewOpmVenta.setNmPunto(opmPuntoVenta);
                    opmVentaListNewOpmVenta = em.merge(opmVentaListNewOpmVenta);
                    if (oldNmPuntoOfOpmVentaListNewOpmVenta != null && !oldNmPuntoOfOpmVentaListNewOpmVenta.equals(opmPuntoVenta)) {
                        oldNmPuntoOfOpmVentaListNewOpmVenta.getOpmVentaList().remove(opmVentaListNewOpmVenta);
                        oldNmPuntoOfOpmVentaListNewOpmVenta = em.merge(oldNmPuntoOfOpmVentaListNewOpmVenta);
                    }
                }
            }
            for (OpmRemision opmRemisionListNewOpmRemision : opmRemisionListNew) {
                if (!opmRemisionListOld.contains(opmRemisionListNewOpmRemision)) {
                    OpmPuntoVenta oldNmPuntoOfOpmRemisionListNewOpmRemision = opmRemisionListNewOpmRemision.getNmPunto();
                    opmRemisionListNewOpmRemision.setNmPunto(opmPuntoVenta);
                    opmRemisionListNewOpmRemision = em.merge(opmRemisionListNewOpmRemision);
                    if (oldNmPuntoOfOpmRemisionListNewOpmRemision != null && !oldNmPuntoOfOpmRemisionListNewOpmRemision.equals(opmPuntoVenta)) {
                        oldNmPuntoOfOpmRemisionListNewOpmRemision.getOpmRemisionList().remove(opmRemisionListNewOpmRemision);
                        oldNmPuntoOfOpmRemisionListNewOpmRemision = em.merge(oldNmPuntoOfOpmRemisionListNewOpmRemision);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmPuntoVenta.getNmCodigo();
                if (findOpmPuntoVenta(id) == null) {
                    throw new NonexistentEntityException("The opmPuntoVenta with id " + id + " no longer exists.");
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
            OpmPuntoVenta opmPuntoVenta;
            try {
                opmPuntoVenta = em.getReference(OpmPuntoVenta.class, id);
                opmPuntoVenta.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmPuntoVenta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmTraslado> opmTrasladoListOrphanCheck = opmPuntoVenta.getOpmTrasladoList();
            for (OpmTraslado opmTrasladoListOrphanCheckOpmTraslado : opmTrasladoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmTraslado " + opmTrasladoListOrphanCheckOpmTraslado + " in its opmTrasladoList field has a non-nullable nmOrigen field.");
            }
            List<OpmInventarioPunto> opmInventarioPuntoListOrphanCheck = opmPuntoVenta.getOpmInventarioPuntoList();
            for (OpmInventarioPunto opmInventarioPuntoListOrphanCheckOpmInventarioPunto : opmInventarioPuntoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmInventarioPunto " + opmInventarioPuntoListOrphanCheckOpmInventarioPunto + " in its opmInventarioPuntoList field has a non-nullable nmPuntoVenta field.");
            }
            List<OpmVenta> opmVentaListOrphanCheck = opmPuntoVenta.getOpmVentaList();
            for (OpmVenta opmVentaListOrphanCheckOpmVenta : opmVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmVenta " + opmVentaListOrphanCheckOpmVenta + " in its opmVentaList field has a non-nullable nmPunto field.");
            }
            List<OpmRemision> opmRemisionListOrphanCheck = opmPuntoVenta.getOpmRemisionList();
            for (OpmRemision opmRemisionListOrphanCheckOpmRemision : opmRemisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmRemision " + opmRemisionListOrphanCheckOpmRemision + " in its opmRemisionList field has a non-nullable nmPunto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmUsuario nmAdministrador = opmPuntoVenta.getNmAdministrador();
            if (nmAdministrador != null) {
                nmAdministrador.getOpmPuntoVentaList().remove(opmPuntoVenta);
                nmAdministrador = em.merge(nmAdministrador);
            }
            em.remove(opmPuntoVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmPuntoVenta> findOpmPuntoVentaEntities() {
        return findOpmPuntoVentaEntities(true, -1, -1);
    }

    public List<OpmPuntoVenta> findOpmPuntoVentaEntities(int maxResults, int firstResult) {
        return findOpmPuntoVentaEntities(false, maxResults, firstResult);
    }

    private List<OpmPuntoVenta> findOpmPuntoVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmPuntoVenta.class));
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

    public OpmPuntoVenta findOpmPuntoVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmPuntoVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmPuntoVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmPuntoVenta> rt = cq.from(OpmPuntoVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
