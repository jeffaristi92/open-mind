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
import Modelo.OpmInventarioPunto;
import java.util.ArrayList;
import java.util.List;
import Modelo.OpmTraslado;
import Modelo.OpmGastos;
import Modelo.OpmPuntoVenta;
import Modelo.OpmVenta;
import Modelo.OpmTransaccion;
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
        if (opmPuntoVenta.getOpmInventarioPuntoList() == null) {
            opmPuntoVenta.setOpmInventarioPuntoList(new ArrayList<OpmInventarioPunto>());
        }
        if (opmPuntoVenta.getOpmTrasladoList() == null) {
            opmPuntoVenta.setOpmTrasladoList(new ArrayList<OpmTraslado>());
        }
        if (opmPuntoVenta.getOpmGastosList() == null) {
            opmPuntoVenta.setOpmGastosList(new ArrayList<OpmGastos>());
        }
        if (opmPuntoVenta.getOpmVentaList() == null) {
            opmPuntoVenta.setOpmVentaList(new ArrayList<OpmVenta>());
        }
        if (opmPuntoVenta.getOpmTransaccionList() == null) {
            opmPuntoVenta.setOpmTransaccionList(new ArrayList<OpmTransaccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<OpmInventarioPunto> attachedOpmInventarioPuntoList = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPuntoToAttach : opmPuntoVenta.getOpmInventarioPuntoList()) {
                opmInventarioPuntoListOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoList.add(opmInventarioPuntoListOpmInventarioPuntoToAttach);
            }
            opmPuntoVenta.setOpmInventarioPuntoList(attachedOpmInventarioPuntoList);
            List<OpmTraslado> attachedOpmTrasladoList = new ArrayList<OpmTraslado>();
            for (OpmTraslado opmTrasladoListOpmTrasladoToAttach : opmPuntoVenta.getOpmTrasladoList()) {
                opmTrasladoListOpmTrasladoToAttach = em.getReference(opmTrasladoListOpmTrasladoToAttach.getClass(), opmTrasladoListOpmTrasladoToAttach.getNmCodigo());
                attachedOpmTrasladoList.add(opmTrasladoListOpmTrasladoToAttach);
            }
            opmPuntoVenta.setOpmTrasladoList(attachedOpmTrasladoList);
            List<OpmGastos> attachedOpmGastosList = new ArrayList<OpmGastos>();
            for (OpmGastos opmGastosListOpmGastosToAttach : opmPuntoVenta.getOpmGastosList()) {
                opmGastosListOpmGastosToAttach = em.getReference(opmGastosListOpmGastosToAttach.getClass(), opmGastosListOpmGastosToAttach.getNmCodigo());
                attachedOpmGastosList.add(opmGastosListOpmGastosToAttach);
            }
            opmPuntoVenta.setOpmGastosList(attachedOpmGastosList);
            List<OpmVenta> attachedOpmVentaList = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListOpmVentaToAttach : opmPuntoVenta.getOpmVentaList()) {
                opmVentaListOpmVentaToAttach = em.getReference(opmVentaListOpmVentaToAttach.getClass(), opmVentaListOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaList.add(opmVentaListOpmVentaToAttach);
            }
            opmPuntoVenta.setOpmVentaList(attachedOpmVentaList);
            List<OpmTransaccion> attachedOpmTransaccionList = new ArrayList<OpmTransaccion>();
            for (OpmTransaccion opmTransaccionListOpmTransaccionToAttach : opmPuntoVenta.getOpmTransaccionList()) {
                opmTransaccionListOpmTransaccionToAttach = em.getReference(opmTransaccionListOpmTransaccionToAttach.getClass(), opmTransaccionListOpmTransaccionToAttach.getNmCodigo());
                attachedOpmTransaccionList.add(opmTransaccionListOpmTransaccionToAttach);
            }
            opmPuntoVenta.setOpmTransaccionList(attachedOpmTransaccionList);
            em.persist(opmPuntoVenta);
            for (OpmInventarioPunto opmInventarioPuntoListOpmInventarioPunto : opmPuntoVenta.getOpmInventarioPuntoList()) {
                OpmPuntoVenta oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto = opmInventarioPuntoListOpmInventarioPunto.getNmPuntoVenta();
                opmInventarioPuntoListOpmInventarioPunto.setNmPuntoVenta(opmPuntoVenta);
                opmInventarioPuntoListOpmInventarioPunto = em.merge(opmInventarioPuntoListOpmInventarioPunto);
                if (oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto != null) {
                    oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto.getOpmInventarioPuntoList().remove(opmInventarioPuntoListOpmInventarioPunto);
                    oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto = em.merge(oldNmPuntoVentaOfOpmInventarioPuntoListOpmInventarioPunto);
                }
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
            for (OpmGastos opmGastosListOpmGastos : opmPuntoVenta.getOpmGastosList()) {
                OpmPuntoVenta oldNmPuntoVentaOfOpmGastosListOpmGastos = opmGastosListOpmGastos.getNmPuntoVenta();
                opmGastosListOpmGastos.setNmPuntoVenta(opmPuntoVenta);
                opmGastosListOpmGastos = em.merge(opmGastosListOpmGastos);
                if (oldNmPuntoVentaOfOpmGastosListOpmGastos != null) {
                    oldNmPuntoVentaOfOpmGastosListOpmGastos.getOpmGastosList().remove(opmGastosListOpmGastos);
                    oldNmPuntoVentaOfOpmGastosListOpmGastos = em.merge(oldNmPuntoVentaOfOpmGastosListOpmGastos);
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
            for (OpmTransaccion opmTransaccionListOpmTransaccion : opmPuntoVenta.getOpmTransaccionList()) {
                OpmPuntoVenta oldNmPuntoVentaOfOpmTransaccionListOpmTransaccion = opmTransaccionListOpmTransaccion.getNmPuntoVenta();
                opmTransaccionListOpmTransaccion.setNmPuntoVenta(opmPuntoVenta);
                opmTransaccionListOpmTransaccion = em.merge(opmTransaccionListOpmTransaccion);
                if (oldNmPuntoVentaOfOpmTransaccionListOpmTransaccion != null) {
                    oldNmPuntoVentaOfOpmTransaccionListOpmTransaccion.getOpmTransaccionList().remove(opmTransaccionListOpmTransaccion);
                    oldNmPuntoVentaOfOpmTransaccionListOpmTransaccion = em.merge(oldNmPuntoVentaOfOpmTransaccionListOpmTransaccion);
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
            List<OpmInventarioPunto> opmInventarioPuntoListOld = persistentOpmPuntoVenta.getOpmInventarioPuntoList();
            List<OpmInventarioPunto> opmInventarioPuntoListNew = opmPuntoVenta.getOpmInventarioPuntoList();
            List<OpmTraslado> opmTrasladoListOld = persistentOpmPuntoVenta.getOpmTrasladoList();
            List<OpmTraslado> opmTrasladoListNew = opmPuntoVenta.getOpmTrasladoList();
            List<OpmGastos> opmGastosListOld = persistentOpmPuntoVenta.getOpmGastosList();
            List<OpmGastos> opmGastosListNew = opmPuntoVenta.getOpmGastosList();
            List<OpmVenta> opmVentaListOld = persistentOpmPuntoVenta.getOpmVentaList();
            List<OpmVenta> opmVentaListNew = opmPuntoVenta.getOpmVentaList();
            List<OpmTransaccion> opmTransaccionListOld = persistentOpmPuntoVenta.getOpmTransaccionList();
            List<OpmTransaccion> opmTransaccionListNew = opmPuntoVenta.getOpmTransaccionList();
            List<String> illegalOrphanMessages = null;
            for (OpmInventarioPunto opmInventarioPuntoListOldOpmInventarioPunto : opmInventarioPuntoListOld) {
                if (!opmInventarioPuntoListNew.contains(opmInventarioPuntoListOldOpmInventarioPunto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmInventarioPunto " + opmInventarioPuntoListOldOpmInventarioPunto + " since its nmPuntoVenta field is not nullable.");
                }
            }
            for (OpmTraslado opmTrasladoListOldOpmTraslado : opmTrasladoListOld) {
                if (!opmTrasladoListNew.contains(opmTrasladoListOldOpmTraslado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmTraslado " + opmTrasladoListOldOpmTraslado + " since its nmOrigen field is not nullable.");
                }
            }
            for (OpmGastos opmGastosListOldOpmGastos : opmGastosListOld) {
                if (!opmGastosListNew.contains(opmGastosListOldOpmGastos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmGastos " + opmGastosListOldOpmGastos + " since its nmPuntoVenta field is not nullable.");
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
            for (OpmTransaccion opmTransaccionListOldOpmTransaccion : opmTransaccionListOld) {
                if (!opmTransaccionListNew.contains(opmTransaccionListOldOpmTransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OpmTransaccion " + opmTransaccionListOldOpmTransaccion + " since its nmPuntoVenta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<OpmInventarioPunto> attachedOpmInventarioPuntoListNew = new ArrayList<OpmInventarioPunto>();
            for (OpmInventarioPunto opmInventarioPuntoListNewOpmInventarioPuntoToAttach : opmInventarioPuntoListNew) {
                opmInventarioPuntoListNewOpmInventarioPuntoToAttach = em.getReference(opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getClass(), opmInventarioPuntoListNewOpmInventarioPuntoToAttach.getNmCodigo());
                attachedOpmInventarioPuntoListNew.add(opmInventarioPuntoListNewOpmInventarioPuntoToAttach);
            }
            opmInventarioPuntoListNew = attachedOpmInventarioPuntoListNew;
            opmPuntoVenta.setOpmInventarioPuntoList(opmInventarioPuntoListNew);
            List<OpmTraslado> attachedOpmTrasladoListNew = new ArrayList<OpmTraslado>();
            for (OpmTraslado opmTrasladoListNewOpmTrasladoToAttach : opmTrasladoListNew) {
                opmTrasladoListNewOpmTrasladoToAttach = em.getReference(opmTrasladoListNewOpmTrasladoToAttach.getClass(), opmTrasladoListNewOpmTrasladoToAttach.getNmCodigo());
                attachedOpmTrasladoListNew.add(opmTrasladoListNewOpmTrasladoToAttach);
            }
            opmTrasladoListNew = attachedOpmTrasladoListNew;
            opmPuntoVenta.setOpmTrasladoList(opmTrasladoListNew);
            List<OpmGastos> attachedOpmGastosListNew = new ArrayList<OpmGastos>();
            for (OpmGastos opmGastosListNewOpmGastosToAttach : opmGastosListNew) {
                opmGastosListNewOpmGastosToAttach = em.getReference(opmGastosListNewOpmGastosToAttach.getClass(), opmGastosListNewOpmGastosToAttach.getNmCodigo());
                attachedOpmGastosListNew.add(opmGastosListNewOpmGastosToAttach);
            }
            opmGastosListNew = attachedOpmGastosListNew;
            opmPuntoVenta.setOpmGastosList(opmGastosListNew);
            List<OpmVenta> attachedOpmVentaListNew = new ArrayList<OpmVenta>();
            for (OpmVenta opmVentaListNewOpmVentaToAttach : opmVentaListNew) {
                opmVentaListNewOpmVentaToAttach = em.getReference(opmVentaListNewOpmVentaToAttach.getClass(), opmVentaListNewOpmVentaToAttach.getNmCodigo());
                attachedOpmVentaListNew.add(opmVentaListNewOpmVentaToAttach);
            }
            opmVentaListNew = attachedOpmVentaListNew;
            opmPuntoVenta.setOpmVentaList(opmVentaListNew);
            List<OpmTransaccion> attachedOpmTransaccionListNew = new ArrayList<OpmTransaccion>();
            for (OpmTransaccion opmTransaccionListNewOpmTransaccionToAttach : opmTransaccionListNew) {
                opmTransaccionListNewOpmTransaccionToAttach = em.getReference(opmTransaccionListNewOpmTransaccionToAttach.getClass(), opmTransaccionListNewOpmTransaccionToAttach.getNmCodigo());
                attachedOpmTransaccionListNew.add(opmTransaccionListNewOpmTransaccionToAttach);
            }
            opmTransaccionListNew = attachedOpmTransaccionListNew;
            opmPuntoVenta.setOpmTransaccionList(opmTransaccionListNew);
            opmPuntoVenta = em.merge(opmPuntoVenta);
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
            for (OpmGastos opmGastosListNewOpmGastos : opmGastosListNew) {
                if (!opmGastosListOld.contains(opmGastosListNewOpmGastos)) {
                    OpmPuntoVenta oldNmPuntoVentaOfOpmGastosListNewOpmGastos = opmGastosListNewOpmGastos.getNmPuntoVenta();
                    opmGastosListNewOpmGastos.setNmPuntoVenta(opmPuntoVenta);
                    opmGastosListNewOpmGastos = em.merge(opmGastosListNewOpmGastos);
                    if (oldNmPuntoVentaOfOpmGastosListNewOpmGastos != null && !oldNmPuntoVentaOfOpmGastosListNewOpmGastos.equals(opmPuntoVenta)) {
                        oldNmPuntoVentaOfOpmGastosListNewOpmGastos.getOpmGastosList().remove(opmGastosListNewOpmGastos);
                        oldNmPuntoVentaOfOpmGastosListNewOpmGastos = em.merge(oldNmPuntoVentaOfOpmGastosListNewOpmGastos);
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
            for (OpmTransaccion opmTransaccionListNewOpmTransaccion : opmTransaccionListNew) {
                if (!opmTransaccionListOld.contains(opmTransaccionListNewOpmTransaccion)) {
                    OpmPuntoVenta oldNmPuntoVentaOfOpmTransaccionListNewOpmTransaccion = opmTransaccionListNewOpmTransaccion.getNmPuntoVenta();
                    opmTransaccionListNewOpmTransaccion.setNmPuntoVenta(opmPuntoVenta);
                    opmTransaccionListNewOpmTransaccion = em.merge(opmTransaccionListNewOpmTransaccion);
                    if (oldNmPuntoVentaOfOpmTransaccionListNewOpmTransaccion != null && !oldNmPuntoVentaOfOpmTransaccionListNewOpmTransaccion.equals(opmPuntoVenta)) {
                        oldNmPuntoVentaOfOpmTransaccionListNewOpmTransaccion.getOpmTransaccionList().remove(opmTransaccionListNewOpmTransaccion);
                        oldNmPuntoVentaOfOpmTransaccionListNewOpmTransaccion = em.merge(oldNmPuntoVentaOfOpmTransaccionListNewOpmTransaccion);
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
            List<OpmInventarioPunto> opmInventarioPuntoListOrphanCheck = opmPuntoVenta.getOpmInventarioPuntoList();
            for (OpmInventarioPunto opmInventarioPuntoListOrphanCheckOpmInventarioPunto : opmInventarioPuntoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmInventarioPunto " + opmInventarioPuntoListOrphanCheckOpmInventarioPunto + " in its opmInventarioPuntoList field has a non-nullable nmPuntoVenta field.");
            }
            List<OpmTraslado> opmTrasladoListOrphanCheck = opmPuntoVenta.getOpmTrasladoList();
            for (OpmTraslado opmTrasladoListOrphanCheckOpmTraslado : opmTrasladoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmTraslado " + opmTrasladoListOrphanCheckOpmTraslado + " in its opmTrasladoList field has a non-nullable nmOrigen field.");
            }
            List<OpmGastos> opmGastosListOrphanCheck = opmPuntoVenta.getOpmGastosList();
            for (OpmGastos opmGastosListOrphanCheckOpmGastos : opmGastosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmGastos " + opmGastosListOrphanCheckOpmGastos + " in its opmGastosList field has a non-nullable nmPuntoVenta field.");
            }
            List<OpmVenta> opmVentaListOrphanCheck = opmPuntoVenta.getOpmVentaList();
            for (OpmVenta opmVentaListOrphanCheckOpmVenta : opmVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmVenta " + opmVentaListOrphanCheckOpmVenta + " in its opmVentaList field has a non-nullable nmPunto field.");
            }
            List<OpmTransaccion> opmTransaccionListOrphanCheck = opmPuntoVenta.getOpmTransaccionList();
            for (OpmTransaccion opmTransaccionListOrphanCheckOpmTransaccion : opmTransaccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This OpmPuntoVenta (" + opmPuntoVenta + ") cannot be destroyed since the OpmTransaccion " + opmTransaccionListOrphanCheckOpmTransaccion + " in its opmTransaccionList field has a non-nullable nmPuntoVenta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
