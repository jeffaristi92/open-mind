/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author ARISTIZABAL
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        panContenido = new javax.swing.JPanel();
        jmbMenuPrincipal = new javax.swing.JMenuBar();
        jmnGestion = new javax.swing.JMenu();
        jmeProducto = new javax.swing.JMenuItem();
        jmeEmpleado = new javax.swing.JMenuItem();
        jmeCliente = new javax.swing.JMenuItem();
        jmePuntoVenta = new javax.swing.JMenuItem();
        jmnTransacciones = new javax.swing.JMenu();
        jmeLote = new javax.swing.JMenuItem();
        jmeRemision = new javax.swing.JMenuItem();
        jmeVenta = new javax.swing.JMenuItem();
        jmeTraslado = new javax.swing.JMenuItem();
        jmeDevolucion = new javax.swing.JMenuItem();
        jmeAbono = new javax.swing.JMenuItem();
        jmnGastos = new javax.swing.JMenu();
        jmeAdministritativo = new javax.swing.JMenuItem();
        jmeNegocio = new javax.swing.JMenuItem();
        jmnReportes = new javax.swing.JMenu();
        jmeInventario = new javax.swing.JMenuItem();
        jmeGastos = new javax.swing.JMenuItem();
        jmeIngresos = new javax.swing.JMenuItem();
        jmeEstadoResultados = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panContenidoLayout = new javax.swing.GroupLayout(panContenido);
        panContenido.setLayout(panContenidoLayout);
        panContenidoLayout.setHorizontalGroup(
            panContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        panContenidoLayout.setVerticalGroup(
            panContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jmnGestion.setText("Gestionar");

        jmeProducto.setText("Productos");
        jmeProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeProductoActionPerformed(evt);
            }
        });
        jmnGestion.add(jmeProducto);

        jmeEmpleado.setText("Empleados");
        jmeEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeEmpleadoActionPerformed(evt);
            }
        });
        jmnGestion.add(jmeEmpleado);

        jmeCliente.setText("Clientes");
        jmeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeClienteActionPerformed(evt);
            }
        });
        jmnGestion.add(jmeCliente);

        jmePuntoVenta.setText("Punto Venta");
        jmnGestion.add(jmePuntoVenta);

        jmbMenuPrincipal.add(jmnGestion);

        jmnTransacciones.setText("Transacciones");

        jmeLote.setText("Lote");
        jmeLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeLoteActionPerformed(evt);
            }
        });
        jmnTransacciones.add(jmeLote);

        jmeRemision.setText("Remision");
        jmeRemision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeRemisionActionPerformed(evt);
            }
        });
        jmnTransacciones.add(jmeRemision);

        jmeVenta.setText("Venta");
        jmeVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeVentaActionPerformed(evt);
            }
        });
        jmnTransacciones.add(jmeVenta);

        jmeTraslado.setText("Traslado");
        jmeTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeTrasladoActionPerformed(evt);
            }
        });
        jmnTransacciones.add(jmeTraslado);

        jmeDevolucion.setText("Devolucion");
        jmeDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeDevolucionActionPerformed(evt);
            }
        });
        jmnTransacciones.add(jmeDevolucion);

        jmeAbono.setText("Abonos");
        jmeAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeAbonoActionPerformed(evt);
            }
        });
        jmnTransacciones.add(jmeAbono);

        jmbMenuPrincipal.add(jmnTransacciones);

        jmnGastos.setText("Gastos");

        jmeAdministritativo.setText("Admistrativos");
        jmnGastos.add(jmeAdministritativo);

        jmeNegocio.setText("Negocio");
        jmnGastos.add(jmeNegocio);

        jmbMenuPrincipal.add(jmnGastos);

        jmnReportes.setText("Reportes");

        jmeInventario.setText("Inventario");
        jmeInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeInventarioActionPerformed(evt);
            }
        });
        jmnReportes.add(jmeInventario);

        jmeGastos.setText("Gastos");
        jmnReportes.add(jmeGastos);

        jmeIngresos.setText("Ingresos");
        jmnReportes.add(jmeIngresos);

        jmeEstadoResultados.setText("Estado Resultados");
        jmeEstadoResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeEstadoResultadosActionPerformed(evt);
            }
        });
        jmnReportes.add(jmeEstadoResultados);

        jmbMenuPrincipal.add(jmnReportes);

        setJMenuBar(jmbMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmeProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeProductoActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Producto obj  =new Producto();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeProductoActionPerformed

    private void jmeRemisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeRemisionActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Remision obj  =new Remision();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeRemisionActionPerformed

    private void jmeVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeVentaActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Venta obj  =new Venta();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeVentaActionPerformed

    private void jmeTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeTrasladoActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Traslado obj  =new Traslado();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeTrasladoActionPerformed

    private void jmeDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeDevolucionActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Devolucion obj  =new Devolucion();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeDevolucionActionPerformed

    private void jmeAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeAbonoActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        AbonoVenta obj  =new AbonoVenta();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeAbonoActionPerformed

    private void jmeEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeEmpleadoActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Empleado obj  =new Empleado();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeEmpleadoActionPerformed

    private void jmeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeClienteActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Cliente obj  =new Cliente();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeClienteActionPerformed

    private void jmeInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeInventarioActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        ReporteInventario obj  =new ReporteInventario();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeInventarioActionPerformed

    private void jmeEstadoResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeEstadoResultadosActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        ReporteEstadoResultados obj  =new ReporteEstadoResultados();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeEstadoResultadosActionPerformed

    private void jmeLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeLoteActionPerformed
        // TODO add your handling code here:
        panContenido.removeAll();
        Lote obj  =new Lote();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }//GEN-LAST:event_jmeLoteActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar jmbMenuPrincipal;
    private javax.swing.JMenuItem jmeAbono;
    private javax.swing.JMenuItem jmeAdministritativo;
    private javax.swing.JMenuItem jmeCliente;
    private javax.swing.JMenuItem jmeDevolucion;
    private javax.swing.JMenuItem jmeEmpleado;
    private javax.swing.JMenuItem jmeEstadoResultados;
    private javax.swing.JMenuItem jmeGastos;
    private javax.swing.JMenuItem jmeIngresos;
    private javax.swing.JMenuItem jmeInventario;
    private javax.swing.JMenuItem jmeLote;
    private javax.swing.JMenuItem jmeNegocio;
    private javax.swing.JMenuItem jmeProducto;
    private javax.swing.JMenuItem jmePuntoVenta;
    private javax.swing.JMenuItem jmeRemision;
    private javax.swing.JMenuItem jmeTraslado;
    private javax.swing.JMenuItem jmeVenta;
    private javax.swing.JMenu jmnGastos;
    private javax.swing.JMenu jmnGestion;
    private javax.swing.JMenu jmnReportes;
    private javax.swing.JMenu jmnTransacciones;
    private javax.swing.JPanel panContenido;
    // End of variables declaration//GEN-END:variables
}

