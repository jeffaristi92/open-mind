/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ConnectionFactory;
import Controlador.OpmDetalleRemisionJpaController;
import Controlador.OpmInventarioJpaController;
import Controlador.OpmInventarioPuntoJpaController;
import Controlador.OpmProductoJpaController;
import Controlador.OpmPuntoVentaJpaController;
import Controlador.OpmReferenciaProductoJpaController;
import Controlador.OpmRemisionJpaController;
import Modelo.OpmDetalleLote;
import Modelo.OpmDetalleRemision;
import Modelo.OpmInventario;
import Modelo.OpmInventarioPunto;
import Modelo.OpmLote;
import Modelo.OpmProducto;
import Modelo.OpmPuntoVenta;
import Modelo.OpmReferenciaProducto;
import Modelo.OpmRemision;
import Modelo.OpmUsuario;
import Transversal.Util;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARISTIZABAL
 */
public final class Remision extends javax.swing.JPanel {

    /**
     * Creates new form Remision
     */
    OpmReferenciaProductoJpaController _jpaControladorProducto;
    OpmPuntoVentaJpaController _jpaControladorPuntoVenta;
    OpmRemisionJpaController _jpaControladorRemision;
    OpmDetalleRemisionJpaController _jpaControladorDetalleRemision;
    OpmInventarioPuntoJpaController _jpaControladorInventarioPunto;
    OpmInventarioJpaController _jpaControladorInventarioFabrica;

    OpmUsuario usuario;
    List<OpmReferenciaProducto> lsProductos;
    List<OpmPuntoVenta> lsPuntosVenta;
    PanelFormularioProducto formularioProducto;
    double _totalRemision;

    public Remision() {
        EntityManagerFactory em = (new ConnectionFactory()).getFactory();
        _jpaControladorProducto = new OpmReferenciaProductoJpaController(em);
        _jpaControladorPuntoVenta = new OpmPuntoVentaJpaController(em);
        _jpaControladorRemision = new OpmRemisionJpaController(em);
        _jpaControladorDetalleRemision = new OpmDetalleRemisionJpaController(em);
        _jpaControladorInventarioPunto = new OpmInventarioPuntoJpaController(em);
        _jpaControladorInventarioFabrica = new OpmInventarioJpaController(em);

        initComponents();
        jdcFecha.setDate(new Date());
        formularioProducto = new PanelFormularioProducto();
        formularioProducto.setVista(PanelFormularioProducto.Vista.ConsultaFabrica);
        jpnFormularioProducto.add(formularioProducto);
        formularioProducto.setSize(1100, 180);
        formularioProducto.setVisible(true);
        formularioProducto.validate();

        inicializarProductos();
        inicializarPuntosVenta();
    }

    void inicializarProductos() {
        lsProductos = _jpaControladorProducto.findOpmReferenciaProductoEntities();
        for (OpmReferenciaProducto producto : lsProductos) {
            String nombre;
            nombre = String.format("%s %s %s %s %s", producto.getNvCodigo(), producto.getNmProducto().getNvNombre(), producto.getNvColor(), producto.getNvTalla(), producto.getNvGenero().toString());
            jcbProductos.addItem(nombre);
        }
    }

    void inicializarPuntosVenta() {
        lsPuntosVenta = _jpaControladorPuntoVenta.findOpmPuntoVentaEntities();
        for (OpmPuntoVenta puntoVenta : lsPuntosVenta) {
            jcbPuntosVenta.addItem(puntoVenta.getNvNombre());
        }
    }

    boolean productoAgregado(OpmReferenciaProducto producto) {
        DefaultTableModel modelo = (DefaultTableModel) jtbDetalleRemision.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).toString().equals(producto.getNvCodigo())) {
                return true;
            }
        }
        return false;
    }

    void actualizarTotalRemision() {
        jtfTotalRemision.setText(Util.getFormatoMoneda(_totalRemision));
    }

    OpmRemision getDatos() {
        OpmRemision remision = new OpmRemision();
        remision.setDaFecha(jdcFecha.getDate());
        remision.setNvNumero(jtfNroRemision.getText());
        remision.setNmPunto(lsPuntosVenta.get(jcbPuntosVenta.getSelectedIndex()).getNmCodigo());
        remision.setNmEmpleado(usuario.getNmCodigo());
        return remision;
    }

    public void setUsuario(OpmUsuario usuario) {
        this.usuario = usuario;
    }

    void actualizarInventarioPuntoVenta(OpmDetalleRemision detalle) {
        List<OpmInventarioPunto> lsInventario = _jpaControladorInventarioPunto.findOpmInventarioPuntoEntities();
        boolean actualizado = false;
        if (lsInventario != null && lsInventario.size() > 0) {

            for (OpmInventarioPunto producto : lsInventario) {
                if (Objects.equals(producto.getNmPuntoVenta().getNmCodigo(), lsPuntosVenta.get(jcbPuntosVenta.getSelectedIndex()).getNmCodigo()) && detalle.getNvReferencia().getNvCodigo().equals(producto.getNvReferencia().getNvCodigo())) {
                    producto.setNmCantidad(detalle.getNmCantidad() + producto.getNmCantidad());
                    try {
                        _jpaControladorInventarioPunto.edit(producto);
                        actualizado = true;
                    } catch (Exception ex) {
                        Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        }

        if (!actualizado) {
            OpmInventarioPunto inventario = new OpmInventarioPunto();
            inventario.setNvReferencia(detalle.getNvReferencia());
            inventario.setNmCantidad(detalle.getNmCantidad());
            inventario.setNmPuntoVenta(lsPuntosVenta.get(jcbPuntosVenta.getSelectedIndex()));
            _jpaControladorInventarioPunto.create(inventario);
        }

    }
    
    void actualizarInventarioFabrica(OpmDetalleRemision detalle) {
        List<OpmInventario> lsInventario = _jpaControladorInventarioFabrica.findOpmInventarioEntities();
        if (lsInventario != null && lsInventario.size() > 0) {
            
            for (OpmInventario producto : lsInventario) {
                if(detalle.getNvReferencia().getNvCodigo().equals(producto.getNvReferencia().getNvCodigo())){
                    producto.setNmCantidad(producto.getNmCantidad()-detalle.getNmCantidad());
                    try {
                        _jpaControladorInventarioFabrica.edit(producto);
                    } catch (Exception ex) {
                        Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        }
    }

    void limpiarFormulario() {
        jtfNroRemision.setText("");
        jtfCantidad.setText("");
        DefaultTableModel modelo = (DefaultTableModel) jtbDetalleRemision.getModel();
        int nroFIlas = modelo.getRowCount();
        for (int i = 0; i < nroFIlas; i++) {
            modelo.removeRow(0);
        }

        _totalRemision = 0;
        actualizarTotalRemision();
    }
    
    void setCantidadDisponible(OpmReferenciaProducto producto){
        List<OpmInventario> lsInventario =  _jpaControladorInventarioFabrica.findOpmInventarioEntities();
        boolean asignado = false;
        for(OpmInventario inventario : lsInventario){
            if(inventario.getNvReferencia().getNvCodigo().equals(producto.getNvCodigo())){
                jtfCantidadDisponible.setText(inventario.getNmCantidad()+"");
                asignado = true;
                break;
            }
        }
        if(!asignado){
        jtfCantidadDisponible.setText("0");
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

        jLabel1 = new javax.swing.JLabel();
        jtfNroRemision = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jcbProductos = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jtfCantidad = new javax.swing.JTextField();
        jbtAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDetalleRemision = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jtfTotalRemision = new javax.swing.JTextField();
        jbtRemover = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jcbPuntosVenta = new javax.swing.JComboBox();
        jpnFormularioProducto = new javax.swing.JPanel();
        jbnRegistrar = new javax.swing.JButton();
        jtfCantidadDisponible = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jLabel1.setText("Nro Remision");

        jLabel2.setText("Fecha");

        jLabel3.setText("Producto");

        jcbProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProductosActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad");

        jtfCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfCantidadKeyTyped(evt);
            }
        });

        jbtAgregar.setText("Agregar");
        jbtAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAgregarActionPerformed(evt);
            }
        });

        jtbDetalleRemision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Referencia", "Cantidad", "Detalle", "Valor Unitario", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbDetalleRemision);

        jLabel5.setText("Total");

        jtfTotalRemision.setEditable(false);

        jbtRemover.setText("Remover");
        jbtRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoverActionPerformed(evt);
            }
        });

        jLabel6.setText("Punto Venta");

        javax.swing.GroupLayout jpnFormularioProductoLayout = new javax.swing.GroupLayout(jpnFormularioProducto);
        jpnFormularioProducto.setLayout(jpnFormularioProductoLayout);
        jpnFormularioProductoLayout.setHorizontalGroup(
            jpnFormularioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnFormularioProductoLayout.setVerticalGroup(
            jpnFormularioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jbnRegistrar.setText("Registrar");
        jbnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnRegistrarActionPerformed(evt);
            }
        });

        jtfCantidadDisponible.setEditable(false);

        jLabel7.setText("Cantidad Disponible");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnFormularioProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbnRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfTotalRemision, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(168, 168, 168))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfNroRemision)
                                    .addComponent(jcbPuntosVenta, 0, 201, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtAgregar))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addComponent(jcbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfCantidadDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfNroRemision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbPuntosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnFormularioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfCantidadDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtAgregar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtRemover)
                    .addComponent(jtfTotalRemision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jbnRegistrar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAgregarActionPerformed
        // TODO add your handling code here:
        OpmReferenciaProducto producto = lsProductos.get(jcbProductos.getSelectedIndex());
        int cantidad = 0;
        int cantidadDisponible = Integer.parseInt(jtfCantidadDisponible.getText());
        if (Util.validarNumero(jtfCantidad.getText(), true)) {
            cantidad = Integer.parseInt(jtfCantidad.getText());
        }
        if (!productoAgregado(producto) && cantidad <= cantidadDisponible) {
            DefaultTableModel modelo = (DefaultTableModel) jtbDetalleRemision.getModel();
            double valorTotal = cantidad * producto.getNmProducto().getNmValorPv();
            _totalRemision += valorTotal;
            actualizarTotalRemision();
            modelo.addRow(new Object[]{producto.getNvCodigo(), jtfCantidad.getText(), producto.getNmProducto().getNvNombre(), Util.getFormatoMoneda(producto.getNmProducto().getNmCostoPp()), Util.getFormatoMoneda(valorTotal)});
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad valida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtAgregarActionPerformed

    private void jbtRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoverActionPerformed
        // TODO add your handling code here:
        double aux = Double.parseDouble(Util.getValorMoneda(jtbDetalleRemision.getValueAt(jtbDetalleRemision.getSelectedRow(), 4).toString()));
        _totalRemision -= aux;
        DefaultTableModel modelo = (DefaultTableModel) jtbDetalleRemision.getModel();
        modelo.removeRow(jtbDetalleRemision.getSelectedRow());
        actualizarTotalRemision();
    }//GEN-LAST:event_jbtRemoverActionPerformed

    private void jbnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnRegistrarActionPerformed
        // TODO add your handling code here:
        try {
            if (!jtfNroRemision.getText().isEmpty()) {
                if (jtbDetalleRemision.getModel().getRowCount() > 0) {
                    OpmRemision remision = getDatos();
                    _jpaControladorRemision.create(remision);
                    OpmRemision aux = new OpmRemision();
                    List<OpmRemision> lsRemisiones = _jpaControladorRemision.findOpmRemisionEntities();

                    for (OpmRemision auxItem : lsRemisiones) {
                        if (auxItem.getNvNumero().equals(remision.getNvNumero())) {
                            aux = auxItem;
                        }
                    }

                    for (int i = 0; i < jtbDetalleRemision.getModel().getRowCount(); i++) {
                        OpmDetalleRemision detalle = new OpmDetalleRemision();
                        detalle.setNvReferencia(_jpaControladorProducto.findOpmReferenciaProducto(jtbDetalleRemision.getValueAt(i, 0).toString()));
                        detalle.setNmCantidad(Integer.parseInt(jtbDetalleRemision.getValueAt(i, 1).toString()));
                        detalle.setNmRemision(aux);
                        _jpaControladorDetalleRemision.create(detalle);
                        actualizarInventarioPuntoVenta(detalle);
                        actualizarInventarioFabrica(detalle);
                    }
                    JOptionPane.showMessageDialog(this, "Remision registrada con exito", "Lote", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Agregue por lo menos un detalle", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Introduzca el Nro de Remision", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "No se pudo registrar remision. Verifique el Numero de lote", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbnRegistrarActionPerformed

    private void jcbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbProductosActionPerformed
        // TODO add your handling code here:
        OpmReferenciaProducto producto = lsProductos.get(jcbProductos.getSelectedIndex());
        formularioProducto.setDatos(producto.getNmProducto());
        setCantidadDisponible(producto);
    }//GEN-LAST:event_jcbProductosActionPerformed

    private void jtfCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCantidadKeyTyped
        // TODO add your handling code here:
        if (!Util.validarCampoNumerico(evt, false, jtfCantidad.getText())) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfCantidadKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbnRegistrar;
    private javax.swing.JButton jbtAgregar;
    private javax.swing.JButton jbtRemover;
    private javax.swing.JComboBox jcbProductos;
    private javax.swing.JComboBox jcbPuntosVenta;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel jpnFormularioProducto;
    private javax.swing.JTable jtbDetalleRemision;
    private javax.swing.JTextField jtfCantidad;
    private javax.swing.JTextField jtfCantidadDisponible;
    private javax.swing.JTextField jtfNroRemision;
    private javax.swing.JTextField jtfTotalRemision;
    // End of variables declaration//GEN-END:variables
}
