/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openmind;

import Modelo.OpmRecursosRol;
import Modelo.OpmUsuario;
import Transversal.Componente;
import Vista.Login;
import Vista.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author ARISTIZABAL
 */
public class OpenMind {

    /**
     * @param args the command line arguments
     */
    Principal principal;
    Login login;
    OpmUsuario usuario;

    public OpenMind() {
        principal = new Principal();
        principal.setVisible(true);
        login = new Login();
        login.setPrograma(this);
        principal.setComponente(login);
    }

    public void setUsuario(OpmUsuario usuario) {
        this.usuario = usuario;       
    }
    
    public void setRecursos(List<OpmRecursosRol> lstRecursos) {
        principal.limpiar();
        principal.setJMenuBar(generarMenu(lstRecursos));
    }

    JMenuBar generarMenu(List<OpmRecursosRol> lstRecursos) {
        JMenuBar menuBarra = new JMenuBar();
        List<OpmRecursosRol> lstPadres = getPadres(lstRecursos);

        for (OpmRecursosRol padre : lstPadres) {
            JMenu menuPadre = new JMenu();
            menuPadre.setText(padre.getNmRecurso().getNvNombre());
            menuBarra.add(menuPadre);

            List<OpmRecursosRol> lstHijos = getHijos(padre, lstRecursos);
            for (OpmRecursosRol hijo : lstHijos) {
                JMenuItem menuHijo = new JMenuItem();
                menuHijo.setText(hijo.getNmRecurso().getNvNombre());
                menuHijo.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        menuItmeActionPerformed(evt);
                    }
                });
                menuPadre.add(menuHijo);
            }
        }

        JMenu menuCerrarSesion = new JMenu();
        menuCerrarSesion.setText("Cerrar Sesion");
        JMenuItem menuSalir = new JMenuItem();
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCerrarSesion(evt);
            }
        });
        menuCerrarSesion.add(menuSalir);
        menuBarra.add(menuCerrarSesion);
        return menuBarra;
    }

    void menuItmeActionPerformed(java.awt.event.ActionEvent evt) {
        principal.setComponente(Componente.getComponente(((JMenuItem) evt.getSource()).getText(),usuario));
    }

    void menuCerrarSesion(java.awt.event.ActionEvent evt) {
        usuario = null;
        principal.limpiar();
        principal.setJMenuBar(null);
        principal.repaint();
        login = new Login();
        login.setPrograma(this);
        principal.setComponente(login);
    }

    List<OpmRecursosRol> getPadres(List<OpmRecursosRol> lstRecursos) {
        List<OpmRecursosRol> lstPadres = new ArrayList<>();
        for (OpmRecursosRol recurso : lstRecursos) {
            if (recurso.getNmRecurso().getNmPadre() == null) {
                lstPadres.add(recurso);
            }
        }
        return lstPadres;
    }

    List<OpmRecursosRol> getHijos(OpmRecursosRol padre, List<OpmRecursosRol> lstRecursos) {
        List<OpmRecursosRol> lstHijos = new ArrayList<>();
        for (OpmRecursosRol recurso : lstRecursos) {
            if (recurso.getNmRecurso().getNmPadre() != null) {
                if (recurso.getNmRecurso().getNmPadre().equals(padre.getNmRecurso().getNmCodigo())) {
                    lstHijos.add(recurso);
                }
            }
        }
        return lstHijos;
    }

    public static void main(String[] args) {
        // TODO code application logic here
            /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        OpenMind openMind = new OpenMind();
    }
}
