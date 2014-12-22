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
import Modelo.OpmFabrica;
import java.util.ArrayList;
import java.util.List;
import Modelo.OpmVenta;
import Modelo.OpmTransaccion;
import Modelo.OpmLote;
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
        if (opmUsuario.getOpmFabricaList() == null) {
            opmUsuario.setOpmFabricaList(new ArrayList<OpmFabrica>());
        }
        if (opmUsuario.getOpmVentaList() == null) {
            opmUsuario.setOpmVentaList(new ArrayList<OpmVenta>());
        }
        if (opmUsuario.getOpmTransaccionList() == null) {
            opmUsuario.setOpmTransaccionList(new ArrayList<OpmTransaccion>());
        }
        if (opmUsuario.getOpmLoteList() == null) {
            opmUsuario.setOpmLoteList(new ArrayList<OpmLote>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmFabrica> attachedOpmFabricaList = new ArrayList<OpmFabrica>();
            for (OpmFabrica opmFabricaListOpmFabricaToAttach : opmUsuario.getOpmFabricaList()) {
                opmFabricaListOpmFabricaToAttach = em.getReference(opmFabricaListOpmFabricaToAttach.getClass(), opmFabricaListOpmFabricaToAttach.getNmCodigo());
                attachedOpmFabricaList.add(opmFabricaListOpmFabricaToAttach);
            }
            opmUsuario.setOpmFabricaList(attachedOpmFabricaList);
            List<OpmVenta> attachedOpmVentaList = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListOpmVentaToAttach : opmUsuario.getOpmVentaList()) {
                opmVentaListOpmVentaToAttach = em.getReference(opmVentaListOpmVentaToAttach.getClass(), opmVentaListOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaList.add(opmVentaListOpmVentaToAttach);
            }
            opmUsuario.setOpmVentaList(attachedOpmVentaList);
            List<OpmTransaccion> attachedOpmTransaccionList = new ArrayList<OpmTransaccion>();
            for (OpmTransaccion opmTransaccionListOpmTransaccionToAttach : opmUsuario.getOpmTransaccionList()) {
                opmTransaccionListOpmTransaccionToAttach = em.getReference(opmTransaccionListOpmTransaccionToAttach.getClass(), opmTransaccionListOpmTransaccionToAttach.getNmCodigo());
                attachedOpmTransaccionList.add(opmTransaccionListOpmTransaccionToAttach);
            }
            opmUsuario.setOpmTransaccionList(attachedOpmTransaccionList);
            List<OpmLote> attachedOpmLoteList = new ArrayList<OpmLote>();
            for (OpmLote opmLoteListOpmLoteToAttach : opmUsuario.getOpmLoteList()) {
                opmLoteListOpmLoteToAttach = em.getReference(opmLoteListOpmLoteToAttach.getClass(), opmLoteListOpmLoteToAttach.getNmCodigo());
                attachedOpmLoteList.add(opmLoteListOpmLoteToAttach);
            }
            opmUsuario.setOpmLoteList(attachedOpmLoteList);
            em.persist(opmUsuario);
            for (OpmFabrica opmFabricaListOpmFabrica : opmUsuario.getOpmFabricaList()) {
                OpmUsuario oldNmAdministradorOfOpmFabricaListOpmFabrica = opmFabricaListOpmFabrica.getNmAdministrador();
                opmFabricaListOpmFabrica.setNmAdministrador(opmUsuario);
                opmFabricaListOpmFabrica = em.merge(opmFabricaListOpmFabrica);
                if (oldNmAdministradorOfOpmFabricaListOpmFabrica != null) {
                    oldNmAdministradorOfOpmFabricaListOpmFabrica.getOpmFabricaList().remove(opmFabricaListOpmFabrica);
                    oldNmAdministradorOfOpmFabricaListOpmFabrica = em.merge(oldNmAdministradorOfOpmFabricaListOpmFabrica);
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
            for (OpmTransaccion opmTransaccionListOpmTransaccion : opmUsuario.getOpmTransaccionList()) {
                OpmUsuario oldNmUsuarioOfOpmTransaccionListOpmTransaccion = opmTransaccionListOpmTransaccion.getNmUsuario();
                opmTransaccionListOpmTransaccion.setNmUsuario(opmUsuario);
                opmTransaccionListOpmTransaccion = em.merge(opmTransaccionListOpmTransaccion);
                if (oldNmUsuarioOfOpmTransaccionListOpmTransaccion != null) {
                    oldNmUsuarioOfOpmTransaccionListOpmTransaccion.getOpmTransaccionList().remove(opmTransaccionListOpmTransaccion);
                    oldNmUsuarioOfOpmTransaccionListOpmTransaccion = em.merge(oldNmUsuarioOfOpmTransaccionListOpmTransaccion);
                }
            }
            for (OpmLote opmLoteListOpmLote : opmUsuario.getOpmLoteList()) {
                OpmUsuario oldNmEmpleadoOfOpmLoteListOpmLote = opmLoteListOpmLote.getNmEmpleado();
                opmLoteListOpmLote.setNmEmpleado(opmUsuario);
                opmLoteListOpmLote = em.merge(opmLoteListOpmLote);
                if (oldNmEmpleadoOfOpmLoteListOpmLote != null) {
                    oldNmEmpleadoOfOpmLoteListOpmLote.getOpmLoteList().remove(opmLoteListOpmLote);
                    oldNmEmpleadoOfOpmLoteListOpmLote = em.merge(oldNmEmpleadoOfOpmLoteListOpmLote);
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
            List<OpmFabrica> opmFabricaListOld = persistentOpmUsuario.getOpmFabricaList();
            List<OpmFabrica> opmFabricaListNew = opmUsuario.getOpmFabricaList();
            List<OpmVenta> opmVentaListOld = persistentOpmUsuario.getOpmVentaList();
            List<OpmVenta> opmVentaListNew = opmUsuario.getOpmVentaList();
            List<OpmTransaccion> opmTransaccionListOld = persistentOpmUsuario.getOpmTransaccionList();
            List<OpmTransaccion> opmTransaccionListNew = opmUsuario.getOpmTransaccionList();
            List<OpmLote> opmLoteListOld = persistentOpmUsuario.getOpmLoteList();
            List<OpmLote> opmLoteListNew = opmUsuario.getOpmLoteList();
            List<String> illegalOrphanMessages = null;
            for (OpmFabrica opmFabricaListOldOpmFabrica : opmFabricaListOld) {
                if (!opmFabricaListNew.contains(opmFabricaListOldOpmFabrica)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmFabrica " + opmFabricaListOldOpmFabrica + " since its nmAdministrador field is not nullable.");
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
            for (OpmTransaccion opmTransaccionListOldOpmTransaccion : opmTransaccionListOld) {
                if (!opmTransaccionListNew.contains(opmTransaccionListOldOpmTransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmTransaccion " + opmTransaccionListOldOpmTransaccion + " since its nmUsuario field is not nullable.");
                }
            }
            for (OpmLote opmLoteListOldOpmLote : opmLoteListOld) {
                if (!opmLoteListNew.contains(opmLoteListOldOpmLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmLote " + opmLoteListOldOpmLote + " since its nmEmpleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmFabrica> attachedOpmFabricaListNew = new ArrayList<OpmFabrica>();
            for (OpmFabrica opmFabricaListNewOpmFabricaToAttach : opmFabricaListNew) {
                opmFabricaListNewOpmFabricaToAttach = em.getReference(opmFabricaListNewOpmFabricaToAttach.getClass(), opmFabricaListNewOpmFabricaToAttach.getNmCodigo());
                attachedOpmFabricaListNew.add(opmFabricaListNewOpmFabricaToAttach);
            }
            opmFabricaListNew = attachedOpmFabricaListNew;
            opmUsuario.setOpmFabricaList(opmFabricaListNew);
            List<OpmVenta> attachedOpmVentaListNew = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListNewOpmVentaToAttach : opmVentaListNew) {
                opmVentaListNewOpmVentaToAttach = em.getReference(opmVentaListNewOpmVentaToAttach.getClass(), opmVentaListNewOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaListNew.add(opmVentaListNewOpmVentaToAttach);
            }
            opmVentaListNew = attachedOpmVentaListNew;
            opmUsuario.setOpmVentaList(opmVentaListNew);
            List<OpmTransaccion> attachedOpmTransaccionListNew = new ArrayList<OpmTransaccion>();
            for (OpmTransaccion opmTransaccionListNewOpmTransaccionToAttach : opmTransaccionListNew) {
                opmTransaccionListNewOpmTransaccionToAttach = em.getReference(opmTransaccionListNewOpmTransaccionToAttach.getClass(), opmTransaccionListNewOpmTransaccionToAttach.getNmCodigo());
                attachedOpmTransaccionListNew.add(opmTransaccionListNewOpmTransaccionToAttach);
            }
            opmTransaccionListNew = attachedOpmTransaccionListNew;
            opmUsuario.setOpmTransaccionList(opmTransaccionListNew);
            List<OpmLote> attachedOpmLoteListNew = new ArrayList<OpmLote>();
            for (OpmLote opmLoteListNewOpmLoteToAttach : opmLoteListNew) {
                opmLoteListNewOpmLoteToAttach = em.getReference(opmLoteListNewOpmLoteToAttach.getClass(), opmLoteListNewOpmLoteToAttach.getNmCodigo());
                attachedOpmLoteListNew.add(opmLoteListNewOpmLoteToAttach);
            }
            opmLoteListNew = attachedOpmLoteListNew;
            opmUsuario.setOpmLoteList(opmLoteListNew);
            opmUsuario = em.merge(opmUsuario);
            for (OpmFabrica opmFabricaListNewOpmFabrica : opmFabricaListNew) {
                if (!opmFabricaListOld.contains(opmFabricaListNewOpmFabrica)) {
                    OpmUsuario oldNmAdministradorOfOpmFabricaListNewOpmFabrica = opmFabricaListNewOpmFabrica.getNmAdministrador();
                    opmFabricaListNewOpmFabrica.setNmAdministrador(opmUsuario);
                    opmFabricaListNewOpmFabrica = em.merge(opmFabricaListNewOpmFabrica);
                    if (oldNmAdministradorOfOpmFabricaListNewOpmFabrica != null && !oldNmAdministradorOfOpmFabricaListNewOpmFabrica.equals(opmUsuario)) {
                        oldNmAdministradorOfOpmFabricaListNewOpmFabrica.getOpmFabricaList().remove(opmFabricaListNewOpmFabrica);
                        oldNmAdministradorOfOpmFabricaListNewOpmFabrica = em.merge(oldNmAdministradorOfOpmFabricaListNewOpmFabrica);
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
            for (OpmTransaccion opmTransaccionListNewOpmTransaccion : opmTransaccionListNew) {
                if (!opmTransaccionListOld.contains(opmTransaccionListNewOpmTransaccion)) {
                    OpmUsuario oldNmUsuarioOfOpmTransaccionListNewOpmTransaccion = opmTransaccionListNewOpmTransaccion.getNmUsuario();
                    opmTransaccionListNewOpmTransaccion.setNmUsuario(opmUsuario);
                    opmTransaccionListNewOpmTransaccion = em.merge(opmTransaccionListNewOpmTransaccion);
                    if (oldNmUsuarioOfOpmTransaccionListNewOpmTransaccion != null && !oldNmUsuarioOfOpmTransaccionListNewOpmTransaccion.equals(opmUsuario)) {
                        oldNmUsuarioOfOpmTransaccionListNewOpmTransaccion.getOpmTransaccionList().remove(opmTransaccionListNewOpmTransaccion);
                        oldNmUsuarioOfOpmTransaccionListNewOpmTransaccion = em.merge(oldNmUsuarioOfOpmTransaccionListNewOpmTransaccion);
                    }
                }
            }
            for (OpmLote opmLoteListNewOpmLote : opmLoteListNew) {
                if (!opmLoteListOld.contains(opmLoteListNewOpmLote)) {
                    OpmUsuario oldNmEmpleadoOfOpmLoteListNewOpmLote = opmLoteListNewOpmLote.getNmEmpleado();
                    opmLoteListNewOpmLote.setNmEmpleado(opmUsuario);
                    opmLoteListNewOpmLote = em.merge(opmLoteListNewOpmLote);
                    if (oldNmEmpleadoOfOpmLoteListNewOpmLote != null && !oldNmEmpleadoOfOpmLoteListNewOpmLote.equals(opmUsuario)) {
                        oldNmEmpleadoOfOpmLoteListNewOpmLote.getOpmLoteList().remove(opmLoteListNewOpmLote);
                        oldNmEmpleadoOfOpmLoteListNewOpmLote = em.merge(oldNmEmpleadoOfOpmLoteListNewOpmLote);
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
            List<OpmFabrica> opmFabricaListOrphanCheck = opmUsuario.getOpmFabricaList();
            for (OpmFabrica opmFabricaListOrphanCheckOpmFabrica : opmFabricaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmUsuario (" + opmUsuario + ") cannot be destroyed since the OpmFabrica " + opmFabricaListOrphanCheckOpmFabrica + " in its opmFabricaList field has a non-nullable nmAdministrador field.");
            }
            List<OpmVenta> opmVentaListOrphanCheck = opmUsuario.getOpmVentaList();
            for (OpmVenta opmVentaListOrphanCheckOpmVenta : opmVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmUsuario (" + opmUsuario + ") cannot be destroyed since the OpmVenta " + opmVentaListOrphanCheckOpmVenta + " in its opmVentaList field has a non-nullable nmCliente field.");
            }
            List<OpmTransaccion> opmTransaccionListOrphanCheck = opmUsuario.getOpmTransaccionList();
            for (OpmTransaccion opmTransaccionListOrphanCheckOpmTransaccion : opmTransaccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmUsuario (" + opmUsuario + ") cannot be destroyed since the OpmTransaccion " + opmTransaccionListOrphanCheckOpmTransaccion + " in its opmTransaccionList field has a non-nullable nmUsuario field.");
            }
            List<OpmLote> opmLoteListOrphanCheck = opmUsuario.getOpmLoteList();
            for (OpmLote opmLoteListOrphanCheckOpmLote : opmLoteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmUsuario (" + opmUsuario + ") cannot be destroyed since the OpmLote " + opmLoteListOrphanCheckOpmLote + " in its opmLoteList field has a non-nullable nmEmpleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
