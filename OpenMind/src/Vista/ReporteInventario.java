/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ConnectionFactory;
import Controlador.OpmInventarioJpaController;
import Controlador.OpmInventarioPuntoJpaController;
import Controlador.OpmPuntoVentaJpaController;
import Controlador.OpmRolJpaController;
import Modelo.OpmInventario;
import Modelo.OpmInventarioPunto;
import Modelo.OpmPuntoVenta;
import Modelo.OpmRol;
import Modelo.OpmUsuario;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARISTIZABAL
 */
public class ReporteInventario extends javax.swing.JPanel {

    /**
     * Creates new form ReporteInventario
     */
    OpmInventarioJpaController _jpaControladorInventarioFabrica;
    OpmInventarioPuntoJpaController _jpaControladorInventarioPunto;
    OpmPuntoVentaJpaController _jpaControladorPuntoVenta;
    OpmRolJpaController _jpaControladorRol;
    List<OpmPuntoVenta> lsPuntoVenta;
    OpmUsuario usuario;
    OpmPuntoVenta puntoVenta;

    public ReporteInventario() {
        EntityManagerFactory em = new ConnectionFactory().getFactory();
        _jpaControladorInventarioFabrica = new OpmInventarioJpaController(em);
        _jpaControladorInventarioPunto = new OpmInventarioPuntoJpaController(em);
        _jpaControladorRol = new OpmRolJpaController(em);
        _jpaControladorPuntoVenta = new OpmPuntoVentaJpaController(em);

        initComponents();
        jcbxPuntoVenta.setSelected(true);
        jcbxPuntoVenta.setVisible(false);
        
        jcbPuntosVenta.setVisible(false);
        jbtVer.setVisible(false);
        inicializarPuntosVenta();
        actualizarTabla();
    }

    void inicializarPuntosVenta() {
        lsPuntoVenta = _jpaControladorPuntoVenta.findOpmPuntoVentaEntities();
        for (OpmPuntoVenta punto : lsPuntoVenta) {
            jcbPuntosVenta.addItem(punto.getNvNombre());
        }
    }

    public void setUsuario(OpmUsuario usuario) {
        this.usuario = usuario;
        OpmRol rol = _jpaControladorRol.findOpmRol(usuario.getNmRol());
        if (rol.getNvNombre().equals("admin")) {
            jcbxPuntoVenta.setVisible(true);
            jcbPuntosVenta.setVisible(true);
            jbtVer.setVisible(true);
        }else{
            for(OpmPuntoVenta punto : lsPuntoVenta){
                if(punto.getNmAdministrador() == usuario.getNmCodigo()){
                    puntoVenta = punto;
                    break;
                }
            }
        }
    }

    void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jtbInventario.getModel();
        int nroFIlas = modelo.getRowCount();
        for (int i = 0; i < nroFIlas; i++) {
            modelo.removeRow(0);
        }

        if (jcbxPuntoVenta.isSelected()) {
            List<OpmInventarioPunto> lsInventario = puntoVenta.getOpmInventarioPuntoList();
            for (OpmInventarioPunto inventario : lsInventario) {
                Object[] fila;
                String descripcion = String.format("%s %s %s", inventario.getNvReferencia().getNvColor(), inventario.getNvReferencia().getNvTalla(), inventario.getNvReferencia().getNvGenero());
                fila = new Object[]{
                    inventario.getNvReferencia().getNvCodigo(),
                    inventario.getNvReferencia().getNmProducto().getNvNombre(),
                    descripcion,
                    inventario.getNmCantidad()
                };
                modelo.addRow(fila);
            }
            jtbInventario.setModel(modelo);

        } else {
            List<OpmInventario> lsInventario = _jpaControladorInventarioFabrica.findOpmInventarioEntities();
            for (OpmInventario inventario : lsInventario) {
                Object[] fila;
                String descripcion = String.format("%s %s %s", inventario.getNvReferencia().getNvColor(), inventario.getNvReferencia().getNvTalla(), inventario.getNvReferencia().getNvGenero());
                fila = new Object[]{
                    inventario.getNvReferencia().getNvCodigo(),
                    inventario.getNvReferencia().getNmProducto().getNvNombre(),
                    descripcion,
                    inventario.getNmCantidad()
                };
                modelo.addRow(fila);
            }
            jtbInventario.setModel(modelo);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbxPuntoVenta = new javax.swing.JCheckBox();
        jcbPuntosVenta = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbInventario = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jbtVer = new javax.swing.JButton();

        jcbxPuntoVenta.setText("Punto Venta");

        jcbPuntosVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPuntosVentaActionPerformed(evt);
            }
        });

        jtbInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Descripcion", "Cantidad Disponible"
            }
        ));
        jScrollPane1.setViewportView(jtbInventario);

        jButton1.setText("Imprimir");

        jbtVer.setText("Ver Reporte");
        jbtVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbxPuntoVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbPuntosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtVer)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbxPuntoVenta)
                    .addComponent(jcbPuntosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtVer))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbPuntosVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPuntosVentaActionPerformed
        // TODO add your handling code here:
        if (lsPuntoVenta.size() > 0) {
            puntoVenta = lsPuntoVenta.get(jcbPuntosVenta.getSelectedIndex());
        } else {
            puntoVenta = new OpmPuntoVenta();
        }
    }//GEN-LAST:event_jcbPuntosVentaActionPerformed

    private void jbtVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVerActionPerformed
        // TODO add your handling code here:
        actualizarTabla();
    }//GEN-LAST:event_jbtVerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtVer;
    private javax.swing.JComboBox jcbPuntosVenta;
    private javax.swing.JCheckBox jcbxPuntoVenta;
    private javax.swing.JTable jtbInventario;
    // End of variables declaration//GEN-END:variables
}
