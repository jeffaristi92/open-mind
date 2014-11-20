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
import Modelo.OpmRol;
import Modelo.OpmPuntoVenta;
import java.util.ArrayList;
import java.util.List;
import Modelo.OpmVenta;
import Modelo.OpmRemision;
import Modelo.OpmUsuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmUsuarioJpaController implements Serializable {

    public OpmUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmUsuario opmUsuario) {
        if (opmUsuario.getOpmPuntoVentaList() == null) {
            opmUsuario.setOpmPuntoVentaList(new ArrayList<OpmPuntoVenta>());
        }
        if (opmUsuario.getOpmVentaList() == null) {
            opmUsuario.setOpmVentaList(new ArrayList<OpmVenta>());
        }
        if (opmUsuario.getOpmRemisionList() == null) {
            opmUsuario.setOpmRemisionList(new ArrayList<OpmRemision>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmRol nmRol = opmUsuario.getNmRol();
            if (nmRol != null) {
                nmRol = em.getReference(nmRol.getClass(), nmRol.getNmCodigo());
                opmUsuario.setNmRol(nmRol);
            }
            List<OpmPuntoVenta> attachedOpmPuntoVentaList = new ArrayList<OpmPuntoVenta>();
            for (OpmPuntoVenta opmPuntoVentaListOpmPuntoVentaToAttach : opmUsuario.getOpmPuntoVentaList()) {
                opmPuntoVentaListOpmPuntoVentaToAttach = em.getReference(opmPuntoVentaListOpmPuntoVentaToAttach.getClass(), opmPuntoVentaListOpmPuntoVentaToAttach.getNmCodigo());
                attachedOpmPuntoVentaList.add(opmPuntoVentaListOpmPuntoVentaToAttach);
            }
            opmUsuario.setOpmPuntoVentaList(attachedOpmPuntoVentaList);
            List<OpmVenta> attachedOpmVentaList = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListOpmVentaToAttach : opmUsuario.getOpmVentaList()) {
                opmVentaListOpmVentaToAttach = em.getReference(opmVentaListOpmVentaToAttach.getClass(), opmVentaListOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaList.add(opmVentaListOpmVentaToAttach);
            }
            opmUsuario.setOpmVentaList(attachedOpmVentaList);
            List<OpmRemision> attachedOpmRemisionList = new ArrayList<OpmRemision>();
            for (OpmRemision opmRemisionListOpmRemisionToAttach : opmUsuario.getOpmRemisionList()) {
                opmRemisionListOpmRemisionToAttach = em.getReference(opmRemisionListOpmRemisionToAttach.getClass(), opmRemisionListOpmRemisionToAttach.getNmCodigo());
                attachedOpmRemisionList.add(opmRemisionListOpmRemisionToAttach);
            }
            opmUsuario.setOpmRemisionList(attachedOpmRemisionList);
            em.persist(opmUsuario);
            if (nmRol != null) {
                nmRol.getOpmUsuarioList().add(opmUsuario);
                nmRol = em.merge(nmRol);
            }
            for (OpmPuntoVenta opmPuntoVentaListOpmPuntoVenta : opmUsuario.getOpmPuntoVentaList()) {
                OpmUsuario oldNmAdministradorOfOpmPuntoVentaListOpmPuntoVenta = opmPuntoVentaListOpmPuntoVenta.getNmAdministrador();
                opmPuntoVentaListOpmPuntoVenta.setNmAdministrador(opmUsuario);
                opmPuntoVentaListOpmPuntoVenta = em.merge(opmPuntoVentaListOpmPuntoVenta);
                if (oldNmAdministradorOfOpmPuntoVentaListOpmPuntoVenta != null) {
                    oldNmAdministradorOfOpmPuntoVentaListOpmPuntoVenta.getOpmPuntoVentaList().remove(opmPuntoVentaListOpmPuntoVenta);
                    oldNmAdministradorOfOpmPuntoVentaListOpmPuntoVenta = em.merge(oldNmAdministradorOfOpmPuntoVentaListOpmPuntoVenta);
                }
            }
            for (OpmVenta opmVentaListOpmVenta : opmUsuario.getOpmVentaList()) {
                OpmUsuario oldNmClienteOfOpmVentaListOpmVenta = opmVentaListOpmVenta.getNmCliente();
                opmVentaListOpmVenta.setNmCliente(opmUsuario);
                opmVentaListOpmVenta = em.merge(opmVentaListOpmVenta);
                if (oldNmClienteOfOpmVentaListOpmVenta != null) {
                    oldNmClienteOfOpmVentaListOpmVenta.getOpmVentaList().remove(opmVentaListOpmVenta);
                    oldNmClienteOfOpmVentaListOpmVenta = em.merge(oldNmClienteOfOpmVentaListOpmVenta);
                }
            }
            for (OpmRemision opmRemisionListOpmRemision : opmUsuario.getOpmRemisionList()) {
                OpmUsuario oldNmEmpleadoOfOpmRemisionListOpmRemision = opmRemisionListOpmRemision.getNmEmpleado();
                opmRemisionListOpmRemision.setNmEmpleado(opmUsuario);
                opmRemisionListOpmRemision = em.merge(opmRemisionListOpmRemision);
                if (oldNmEmpleadoOfOpmRemisionListOpmRemision != null) {
                    oldNmEmpleadoOfOpmRemisionListOpmRemision.getOpmRemisionList().remove(opmRemisionListOpmRemision);
                    oldNmEmpleadoOfOpmRemisionListOpmRemision = em.merge(oldNmEmpleadoOfOpmRemisionListOpmRemision);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmUsuario opmUsuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmUsuario persistentOpmUsuario = em.find(OpmUsuario.class, opmUsuario.getNmCodigo());
            OpmRol nmRolOld = persistentOpmUsuario.getNmRol();
            OpmRol nmRolNew = opmUsuario.getNmRol();
            List<OpmPuntoVenta> opmPuntoVentaListOld = persistentOpmUsuario.getOpmPuntoVentaList();
            List<OpmPuntoVenta> opmPuntoVentaListNew = opmUsuario.getOpmPuntoVentaList();
            List<OpmVenta> opmVentaListOld = persistentOpmUsuario.getOpmVentaList();
            List<OpmVenta> opmVentaListNew = opmUsuario.getOpmVentaList();
            List<OpmRemision> opmRemisionListOld = persistentOpmUsuario.getOpmRemisionList();
            List<OpmRemision> opmRemisionListNew = opmUsuario.getOpmRemisionList();
            List<String> illegalOrphanMessages = null;
            for (OpmPuntoVenta opmPuntoVentaListOldOpmPuntoVenta : opmPuntoVentaListOld) {
                if (!opmPuntoVentaListNew.contains(opmPuntoVentaListOldOpmPuntoVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmPuntoVenta " + opmPuntoVentaListOldOpmPuntoVenta + " since its nmAdministrador field is not nullable.");
                }
            }
            for (OpmVenta opmVentaListOldOpmVenta : opmVentaListOld) {
                if (!opmVentaListNew.contains(opmVentaListOldOpmVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmVenta " + opmVentaListOldOpmVenta + " since its nmCliente field is not nullable.");
                }
            }
            for (OpmRemision opmRemisionListOldOpmRemision : opmRemisionListOld) {
                if (!opmRemisionListNew.contains(opmRemisionListOldOpmRemision)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmRemision " + opmRemisionListOldOpmRemision + " since its nmEmpleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmRolNew != null) {
                nmRolNew = em.getReference(nmRolNew.getClass(), nmRolNew.getNmCodigo());
                opmUsuario.setNmRol(nmRolNew);
            }
            List<OpmPuntoVenta> attachedOpmPuntoVentaListNew = new ArrayList<OpmPuntoVenta>();
            for (OpmPuntoVenta opmPuntoVentaListNewOpmPuntoVentaToAttach : opmPuntoVentaListNew) {
                opmPuntoVentaListNewOpmPuntoVentaToAttach = em.getReference(opmPuntoVentaListNewOpmPuntoVentaToAttach.getClass(), opmPuntoVentaListNewOpmPuntoVentaToAttach.getNmCodigo());
                attachedOpmPuntoVentaListNew.add(opmPuntoVentaListNewOpmPuntoVentaToAttach);
            }
            opmPuntoVentaListNew = attachedOpmPuntoVentaListNew;
            opmUsuario.setOpmPuntoVentaList(opmPuntoVentaListNew);
            List<OpmVenta> attachedOpmVentaListNew = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListNewOpmVentaToAttach : opmVentaListNew) {
                opmVentaListNewOpmVentaToAttach = em.getReference(opmVentaListNewOpmVentaToAttach.getClass(), opmVentaListNewOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaListNew.add(opmVentaListNewOpmVentaToAttach);
            }
            opmVentaListNew = attachedOpmVentaListNew;
            opmUsuario.setOpmVentaList(opmVentaListNew);
            List<OpmRemision> attachedOpmRemisionListNew = new ArrayList<OpmRemision>();
            for (OpmRemision opmRemisionListNewOpmRemisionToAttach : opmRemisionListNew) {
                opmRemisionListNewOpmRemisionToAttach = em.getReference(opmRemisionListNewOpmRemisionToAttach.getClass(), opmRemisionListNewOpmRemisionToAttach.getNmCodigo());
                attachedOpmRemisionListNew.add(opmRemisionListNewOpmRemisionToAttach);
            }
            opmRemisionListNew = attachedOpmRemisionListNew;
            opmUsuario.setOpmRemisionList(opmRemisionListNew);
            opmUsuario = em.merge(opmUsuario);
            if (nmRolOld != null && !nmRolOld.equals(nmRolNew)) {
                nmRolOld.getOpmUsuarioList().remove(opmUsuario);
                nmRolOld = em.merge(nmRolOld);
            }
            if (nmRolNew != null && !nmRolNew.equals(nmRolOld)) {
                nmRolNew.getOpmUsuarioList().add(opmUsuario);
                nmRolNew = em.merge(nmRolNew);
            }
            for (OpmPuntoVenta opmPuntoVentaListNewOpmPuntoVenta : opmPuntoVentaListNew) {
                if (!opmPuntoVentaListOld.contains(opmPuntoVentaListNewOpmPuntoVenta)) {
                    OpmUsuario oldNmAdministradorOfOpmPuntoVentaListNewOpmPuntoVenta = opmPuntoVentaListNewOpmPuntoVenta.getNmAdministrador();
                    opmPuntoVentaListNewOpmPuntoVenta.setNmAdministrador(opmUsuario);
                    opmPuntoVentaListNewOpmPuntoVenta = em.merge(opmPuntoVentaListNewOpmPuntoVenta);
                    if (oldNmAdministradorOfOpmPuntoVentaListNewOpmPuntoVenta != null && !oldNmAdministradorOfOpmPuntoVentaListNewOpmPuntoVenta.equals(opmUsuario)) {
                        oldNmAdministradorOfOpmPuntoVentaListNewOpmPuntoVenta.getOpmPuntoVentaList().remove(opmPuntoVentaListNewOpmPuntoVenta);
                        oldNmAdministradorOfOpmPuntoVentaListNewOpmPuntoVenta = em.merge(oldNmAdministradorOfOpmPuntoVentaListNewOpmPuntoVenta);
                    }
                }
            }
            for (OpmVenta opmVentaListNewOpmVenta : opmVentaListNew) {
                if (!opmVentaListOld.contains(opmVentaListNewOpmVenta)) {
                    OpmUsuario oldNmClienteOfOpmVentaListNewOpmVenta = opmVentaListNewOpmVenta.getNmCliente();
                    opmVentaListNewOpmVenta.setNmCliente(opmUsuario);
                    opmVentaListNewOpmVenta = em.merge(opmVentaListNewOpmVenta);
                    if (oldNmClienteOfOpmVentaListNewOpmVenta != null && !oldNmClienteOfOpmVentaListNewOpmVenta.equals(opmUsuario)) {
                        oldNmClienteOfOpmVentaListNewOpmVenta.getOpmVentaList().remove(opmVentaListNewOpmVenta);
                        oldNmClienteOfOpmVentaListNewOpmVenta = em.merge(oldNmClienteOfOpmVentaListNewOpmVenta);
                    }
                }
            }
            for (OpmRemision opmRemisionListNewOpmRemision : opmRemisionListNew) {
                if (!opmRemisionListOld.contains(opmRemisionListNewOpmRemision)) {
                    OpmUsuario oldNmEmpleadoOfOpmRemisionListNewOpmRemision = opmRemisionListNewOpmRemision.getNmEmpleado();
                    opmRemisionListNewOpmRemision.setNmEmpleado(opmUsuario);
                    opmRemisionListNewOpmRemision = em.merge(opmRemisionListNewOpmRemision);
                    if (oldNmEmpleadoOfOpmRemisionListNewOpmRemision != null && !oldNmEmpleadoOfOpmRemisionListNewOpmRemision.equals(opmUsuario)) {
                        oldNmEmpleadoOfOpmRemisionListNewOpmRemision.getOpmRemisionList().remove(opmRemisionListNewOpmRemision);
                        oldNmEmpleadoOfOpmRemisionListNewOpmRemision = em.merge(oldNmEmpleadoOfOpmRemisionListNewOpmRemision);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmUsuario.getNmCodigo();
                if (findOpmUsuario(id) == null) {
                    throw new NonexistentEntityException("The opmUsuario with id " + id + " no longer exists.");
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
            OpmUsuario opmUsuario;
            try {
                opmUsuario = em.getReference(OpmUsuario.class, id);
                opmUsuario.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmUsuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmPuntoVenta> opmPuntoVentaListOrphanCheck = opmUsuario.getOpmPuntoVentaList();
            for (OpmPuntoVenta opmPuntoVentaListOrphanCheckOpmPuntoVenta : opmPuntoVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmUsuario (" + opmUsuario + ") cannot be destroyed since the OpmPuntoVenta " + opmPuntoVentaListOrphanCheckOpmPuntoVenta + " in its opmPuntoVentaList field has a non-nullable nmAdministrador field.");
            }
            List<OpmVenta> opmVentaListOrphanCheck = opmUsuario.getOpmVentaList();
            for (OpmVenta opmVentaListOrphanCheckOpmVenta : opmVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmUsuario (" + opmUsuario + ") cannot be destroyed since the OpmVenta " + opmVentaListOrphanCheckOpmVenta + " in its opmVentaList field has a non-nullable nmCliente field.");
            }
            List<OpmRemision> opmRemisionListOrphanCheck = opmUsuario.getOpmRemisionList();
            for (OpmRemision opmRemisionListOrphanCheckOpmRemision : opmRemisionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmUsuario (" + opmUsuario + ") cannot be destroyed since the OpmRemision " + opmRemisionListOrphanCheckOpmRemision + " in its opmRemisionList field has a non-nullable nmEmpleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmRol nmRol = opmUsuario.getNmRol();
            if (nmRol != null) {
                nmRol.getOpmUsuarioList().remove(opmUsuario);
                nmRol = em.merge(nmRol);
            }
            em.remove(opmUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmUsuario> findOpmUsuarioEntities() {
        return findOpmUsuarioEntities(true, -1, -1);
    }

    public List<OpmUsuario> findOpmUsuarioEntities(int maxResults, int firstResult) {
        return findOpmUsuarioEntities(false, maxResults, firstResult);
    }

    private List<OpmUsuario> findOpmUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmUsuario.class));
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

    public OpmUsuario findOpmUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmUsuario> rt = cq.from(OpmUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
