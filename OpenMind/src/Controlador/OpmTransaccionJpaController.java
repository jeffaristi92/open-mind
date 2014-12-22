/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.OpmUsuario;
import Modelo.OpmPuntoVenta;
import Modelo.OpmCuenta;
import Modelo.OpmTransaccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ARISTIZABAL
 */
public class OpmTransaccionJpaController implements Serializable {

    public OpmTransaccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OpmTransaccion opmTransaccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmUsuario nmUsuario = opmTransaccion.getNmUsuario();
            if (nmUsuario != null) {
                nmUsuario = em.getReference(nmUsuario.getClass(), nmUsuario.getNmCodigo());
                opmTransaccion.setNmUsuario(nmUsuario);
            }
            OpmPuntoVenta nmPuntoVenta = opmTransaccion.getNmPuntoVenta();
            if (nmPuntoVenta != null) {
                nmPuntoVenta = em.getReference(nmPuntoVenta.getClass(), nmPuntoVenta.getNmCodigo());
                opmTransaccion.setNmPuntoVenta(nmPuntoVenta);
            }
            OpmCuenta nmCuenta = opmTransaccion.getNmCuenta();
            if (nmCuenta != null) {
                nmCuenta = em.getReference(nmCuenta.getClass(), nmCuenta.getNmCodigo());
                opmTransaccion.setNmCuenta(nmCuenta);
            }
            em.persist(opmTransaccion);
            if (nmUsuario != null) {
                nmUsuario.getOpmTransaccionList().add(opmTransaccion);
                nmUsuario = em.merge(nmUsuario);
            }
            if (nmPuntoVenta != null) {
                nmPuntoVenta.getOpmTransaccionList().add(opmTransaccion);
                nmPuntoVenta = em.merge(nmPuntoVenta);
            }
            if (nmCuenta != null) {
                nmCuenta.getOpmTransaccionList().add(opmTransaccion);
                nmCuenta = em.merge(nmCuenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OpmTransaccion opmTransaccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmTransaccion persistentOpmTransaccion = em.find(OpmTransaccion.class, opmTransaccion.getNmCodigo());
            OpmUsuario nmUsuarioOld = persistentOpmTransaccion.getNmUsuario();
            OpmUsuario nmUsuarioNew = opmTransaccion.getNmUsuario();
            OpmPuntoVenta nmPuntoVentaOld = persistentOpmTransaccion.getNmPuntoVenta();
            OpmPuntoVenta nmPuntoVentaNew = opmTransaccion.getNmPuntoVenta();
            OpmCuenta nmCuentaOld = persistentOpmTransaccion.getNmCuenta();
            OpmCuenta nmCuentaNew = opmTransaccion.getNmCuenta();
            if (nmUsuarioNew != null) {
                nmUsuarioNew = em.getReference(nmUsuarioNew.getClass(), nmUsuarioNew.getNmCodigo());
                opmTransaccion.setNmUsuario(nmUsuarioNew);
            }
            if (nmPuntoVentaNew != null) {
                nmPuntoVentaNew = em.getReference(nmPuntoVentaNew.getClass(), nmPuntoVentaNew.getNmCodigo());
                opmTransaccion.setNmPuntoVenta(nmPuntoVentaNew);
            }
            if (nmCuentaNew != null) {
                nmCuentaNew = em.getReference(nmCuentaNew.getClass(), nmCuentaNew.getNmCodigo());
                opmTransaccion.setNmCuenta(nmCuentaNew);
            }
            opmTransaccion = em.merge(opmTransaccion);
            if (nmUsuarioOld != null && !nmUsuarioOld.equals(nmUsuarioNew)) {
                nmUsuarioOld.getOpmTransaccionList().remove(opmTransaccion);
                nmUsuarioOld = em.merge(nmUsuarioOld);
            }
            if (nmUsuarioNew != null && !nmUsuarioNew.equals(nmUsuarioOld)) {
                nmUsuarioNew.getOpmTransaccionList().add(opmTransaccion);
                nmUsuarioNew = em.merge(nmUsuarioNew);
            }
            if (nmPuntoVentaOld != null && !nmPuntoVentaOld.equals(nmPuntoVentaNew)) {
                nmPuntoVentaOld.getOpmTransaccionList().remove(opmTransaccion);
                nmPuntoVentaOld = em.merge(nmPuntoVentaOld);
            }
            if (nmPuntoVentaNew != null && !nmPuntoVentaNew.equals(nmPuntoVentaOld)) {
                nmPuntoVentaNew.getOpmTransaccionList().add(opmTransaccion);
                nmPuntoVentaNew = em.merge(nmPuntoVentaNew);
            }
            if (nmCuentaOld != null && !nmCuentaOld.equals(nmCuentaNew)) {
                nmCuentaOld.getOpmTransaccionList().remove(opmTransaccion);
                nmCuentaOld = em.merge(nmCuentaOld);
            }
            if (nmCuentaNew != null && !nmCuentaNew.equals(nmCuentaOld)) {
                nmCuentaNew.getOpmTransaccionList().add(opmTransaccion);
                nmCuentaNew = em.merge(nmCuentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = opmTransaccion.getNmCodigo();
                if (findOpmTransaccion(id) == null) {
                    throw new NonexistentEntityException("The opmTransaccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OpmTransaccion opmTransaccion;
            try {
                opmTransaccion = em.getReference(OpmTransaccion.class, id);
                opmTransaccion.getNmCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opmTransaccion with id " + id + " no longer exists.", enfe);
            }
            OpmUsuario nmUsuario = opmTransaccion.getNmUsuario();
            if (nmUsuario != null) {
                nmUsuario.getOpmTransaccionList().remove(opmTransaccion);
                nmUsuario = em.merge(nmUsuario);
            }
            OpmPuntoVenta nmPuntoVenta = opmTransaccion.getNmPuntoVenta();
            if (nmPuntoVenta != null) {
                nmPuntoVenta.getOpmTransaccionList().remove(opmTransaccion);
                nmPuntoVenta = em.merge(nmPuntoVenta);
            }
            OpmCuenta nmCuenta = opmTransaccion.getNmCuenta();
            if (nmCuenta != null) {
                nmCuenta.getOpmTransaccionList().remove(opmTransaccion);
                nmCuenta = em.merge(nmCuenta);
            }
            em.remove(opmTransaccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OpmTransaccion> findOpmTransaccionEntities() {
        return findOpmTransaccionEntities(true, -1, -1);
    }

    public List<OpmTransaccion> findOpmTransaccionEntities(int maxResults, int firstResult) {
        return findOpmTransaccionEntities(false, maxResults, firstResult);
    }

    private List<OpmTransaccion> findOpmTransaccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OpmTransaccion.class));
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

    public OpmTransaccion findOpmTransaccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OpmTransaccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpmTransaccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OpmTransaccion> rt = cq.from(OpmTransaccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
