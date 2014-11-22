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
import Modelo.OpmDetalleLote;
import Modelo.OpmLote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmLoteJpaController implements Serializable {

    public OpmLoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmLote opmLote) {
        if (opmLote.getOpmDetalleLoteList() == null) {
            opmLote.setOpmDetalleLoteList(new ArrayList<OpmDetalleLote>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmUsuario nmEmpleado = opmLote.getNmEmpleado();
            if (nmEmpleado != null) {
                nmEmpleado = em.getReference(nmEmpleado.getClass(), nmEmpleado.getNmCodigo());
                opmLote.setNmEmpleado(nmEmpleado);
            }
            List<OpmDetalleLote> attachedOpmDetalleLoteList = new ArrayList<OpmDetalleLote>();
            for (OpmDetalleLote opmDetalleLoteListOpmDetalleLoteToAttach : opmLote.getOpmDetalleLoteList()) {
                opmDetalleLoteListOpmDetalleLoteToAttach = em.getReference(opmDetalleLoteListOpmDetalleLoteToAttach.getClass(), opmDetalleLoteListOpmDetalleLoteToAttach.getNmCodigo());
                attachedOpmDetalleLoteList.add(opmDetalleLoteListOpmDetalleLoteToAttach);
            }
            opmLote.setOpmDetalleLoteList(attachedOpmDetalleLoteList);
            em.persist(opmLote);
            if (nmEmpleado != null) {
                nmEmpleado.getOpmLoteList().add(opmLote);
                nmEmpleado = em.merge(nmEmpleado);
            }
            for (OpmDetalleLote opmDetalleLoteListOpmDetalleLote : opmLote.getOpmDetalleLoteList()) {
                OpmLote oldNmLoteOfOpmDetalleLoteListOpmDetalleLote = opmDetalleLoteListOpmDetalleLote.getNmLote();
                opmDetalleLoteListOpmDetalleLote.setNmLote(opmLote);
                opmDetalleLoteListOpmDetalleLote = em.merge(opmDetalleLoteListOpmDetalleLote);
                if (oldNmLoteOfOpmDetalleLoteListOpmDetalleLote != null) {
                    oldNmLoteOfOpmDetalleLoteListOpmDetalleLote.getOpmDetalleLoteList().remove(opmDetalleLoteListOpmDetalleLote);
                    oldNmLoteOfOpmDetalleLoteListOpmDetalleLote = em.merge(oldNmLoteOfOpmDetalleLoteListOpmDetalleLote);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmLote opmLote) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmLote persistentOpmLote = em.find(OpmLote.class, opmLote.getNmCodigo());
            OpmUsuario nmEmpleadoOld = persistentOpmLote.getNmEmpleado();
            OpmUsuario nmEmpleadoNew = opmLote.getNmEmpleado();
            List<OpmDetalleLote> opmDetalleLoteListOld = persistentOpmLote.getOpmDetalleLoteList();
            List<OpmDetalleLote> opmDetalleLoteListNew = opmLote.getOpmDetalleLoteList();
            List<String> illegalOrphanMessages = null;
            for (OpmDetalleLote opmDetalleLoteListOldOpmDetalleLote : opmDetalleLoteListOld) {
                if (!opmDetalleLoteListNew.contains(opmDetalleLoteListOldOpmDetalleLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmDetalleLote " + opmDetalleLoteListOldOpmDetalleLote + " since its nmLote field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nmEmpleadoNew != null) {
                nmEmpleadoNew = em.getReference(nmEmpleadoNew.getClass(), nmEmpleadoNew.getNmCodigo());
                opmLote.setNmEmpleado(nmEmpleadoNew);
            }
            List<OpmDetalleLote> attachedOpmDetalleLoteListNew = new ArrayList<OpmDetalleLote>();
            for (OpmDetalleLote opmDetalleLoteListNewOpmDetalleLoteToAttach : opmDetalleLoteListNew) {
                opmDetalleLoteListNewOpmDetalleLoteToAttach = em.getReference(opmDetalleLoteListNewOpmDetalleLoteToAttach.getClass(), opmDetalleLoteListNewOpmDetalleLoteToAttach.getNmCodigo());
                attachedOpmDetalleLoteListNew.add(opmDetalleLoteListNewOpmDetalleLoteToAttach);
            }
            opmDetalleLoteListNew = attachedOpmDetalleLoteListNew;
            opmLote.setOpmDetalleLoteList(opmDetalleLoteListNew);
            opmLote = em.merge(opmLote);
            if (nmEmpleadoOld != null && !nmEmpleadoOld.equals(nmEmpleadoNew)) {
                nmEmpleadoOld.getOpmLoteList().remove(opmLote);
                nmEmpleadoOld = em.merge(nmEmpleadoOld);
            }
            if (nmEmpleadoNew != null && !nmEmpleadoNew.equals(nmEmpleadoOld)) {
                nmEmpleadoNew.getOpmLoteList().add(opmLote);
                nmEmpleadoNew = em.merge(nmEmpleadoNew);
            }
            for (OpmDetalleLote opmDetalleLoteListNewOpmDetalleLote : opmDetalleLoteListNew) {
                if (!opmDetalleLoteListOld.contains(opmDetalleLoteListNewOpmDetalleLote)) {
                    OpmLote oldNmLoteOfOpmDetalleLoteListNewOpmDetalleLote = opmDetalleLoteListNewOpmDetalleLote.getNmLote();
                    opmDetalleLoteListNewOpmDetalleLote.setNmLote(opmLote);
                    opmDetalleLoteListNewOpmDetalleLote = em.merge(opmDetalleLoteListNewOpmDetalleLote);
                    if (oldNmLoteOfOpmDetalleLoteListNewOpmDetalleLote != null && !oldNmLoteOfOpmDetalleLoteListNewOpmDetalleLote.equals(opmLote)) {
                        oldNmLoteOfOpmDetalleLoteListNewOpmDetalleLote.getOpmDetalleLoteList().remove(opmDetalleLoteListNewOpmDetalleLote);
                        oldNmLoteOfOpmDetalleLoteListNewOpmDetalleLote = em.merge(oldNmLoteOfOpmDetalleLoteListNewOpmDetalleLote);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmLote.getNmCodigo();
                if (findOpmLote(id) == null) {
                    throw new NonexistentEntityException("The opmLote with id " + id + " no longer exists.");
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
            OpmLote opmLote;
            try {
                opmLote = em.getReference(OpmLote.class, id);
                opmLote.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmLote with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OpmDetalleLote> opmDetalleLoteListOrphanCheck = opmLote.getOpmDetalleLoteList();
            for (OpmDetalleLote opmDetalleLoteListOrphanCheckOpmDetalleLote : opmDetalleLoteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmLote (" + opmLote + ") cannot be destroyed since the OpmDetalleLote " + opmDetalleLoteListOrphanCheckOpmDetalleLote + " in its opmDetalleLoteList field has a non-nullable nmLote field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            OpmUsuario nmEmpleado = opmLote.getNmEmpleado();
            if (nmEmpleado != null) {
                nmEmpleado.getOpmLoteList().remove(opmLote);
                nmEmpleado = em.merge(nmEmpleado);
            }
            em.remove(opmLote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmLote> findOpmLoteEntities() {
        return findOpmLoteEntities(true, -1, -1);
    }

    public List<OpmLote> findOpmLoteEntities(int maxResults, int firstResult) {
        return findOpmLoteEntities(false, maxResults, firstResult);
    }

    private List<OpmLote> findOpmLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmLote.class));
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

    public OpmLote findOpmLote(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmLote.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmLote> rt = cq.from(OpmLote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
