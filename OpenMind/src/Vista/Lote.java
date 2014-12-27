/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ConnectionFactory;
import Controlador.OpmDetalleLoteJpaController;
import Controlador.OpmInventarioJpaController;
import Controlador.OpmLoteJpaController;
import Controlador.OpmPuntoVentaJpaController;
import Controlador.OpmReferenciaProductoJpaController;
import Modelo.OpmDetalleLote;
import Modelo.OpmInventario;
import Modelo.OpmLote;
import Modelo.OpmPuntoVenta;
import Modelo.OpmReferenciaProducto;
import Modelo.OpmUsuario;
import Transversal.Util;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARISTIZABAL
 */
public final class Lote extends javax.swing.JPanel {

    /**
     * Creates new form Remision
     */
    OpmReferenciaProductoJpaController _jpaControladorProducto;
    OpmPuntoVentaJpaController _jpaControladorPuntoVenta;
    OpmInventarioJpaController _jpaControladorInventarioFabrica;
    OpmLoteJpaController _jpaControladorLote;
    OpmDetalleLoteJpaController _jpaControladorDetalleLote;
    OpmUsuario usuario;

    List<OpmReferenciaProducto> lsProductos;
    List<OpmPuntoVenta> lsPuntosVenta;
    PanelFormularioProducto formularioProducto;
    double _totalLote;

    public Lote() {
        _totalLote = 0;
        EntityManagerFactory em = (new ConnectionFactory()).getFactory();
        _jpaControladorProducto = new OpmReferenciaProductoJpaController(em);
        _jpaControladorPuntoVenta = new OpmPuntoVentaJpaController(em);
        _jpaControladorInventarioFabrica = new OpmInventarioJpaController(em);
        _jpaControladorLote = new OpmLoteJpaController(em);
        _jpaControladorDetalleLote = new OpmDetalleLoteJpaController(em);

        initComponents();
        jdcFecha.setDate(new Date());

        formularioProducto = new PanelFormularioProducto();
        formularioProducto.setVista(PanelFormularioProducto.Vista.ConsultaFabrica);
        jpnFormularioProducto.add(formularioProducto);
        formularioProducto.setSize(1100, 180);
        formularioProducto.setVisible(true);
        formularioProducto.validate();

        inicializarProductos();
    }

    void inicializarProductos() {
        lsProductos = _jpaControladorProducto.findOpmReferenciaProductoEntities();
        for (OpmReferenciaProducto producto : lsProductos) {
            String nombre;
            nombre = String.format("%s %s %s %s %s", producto.getNvCodigo(), producto.getNmProducto().getNvNombre(), producto.getNvColor(), producto.getNvTalla(), producto.getNvGenero().toString());
            jcbProductos.addItem(nombre);
        }
    }

    boolean productoAgregado(OpmReferenciaProducto producto) {
        DefaultTableModel modelo = (DefaultTableModel) jtbDetalleLote.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).toString().equals(producto.getNvCodigo())) {
                return true;
            }
        }
        return false;
    }

    void actualizarTotalLote() {
        jtfTotalLote.setText(Util.getFormatoMoneda(_totalLote));
    }

    OpmLote getDatos() {
        OpmLote lote = new OpmLote();
        lote.setDaFecha(jdcFecha.getDate());
        lote.setNvNumero(jtfNroLote.getText());
        lote.setNmEmpleado(usuario);
        return lote;
    }

    public void setUsuario(OpmUsuario usuario) {
        this.usuario = usuario;
    }

    void limpiarFormulario() {
        jtfNroLote.setText("");
        jtfCantidad.setText("");
        DefaultTableModel modelo = (DefaultTableModel) jtbDetalleLote.getModel();
        int nroFIlas = modelo.getRowCount();
        for (int i = 0; i < nroFIlas; i++) {
            modelo.removeRow(0);
        }

        _totalLote = 0;
        actualizarTotalLote();
    }

    void actualizarInventario(OpmDetalleLote detalle) {
        List<OpmInventario> lsInventario = _jpaControladorInventarioFabrica.findOpmInventarioEntities();
        boolean actualizado = false;
        if (lsInventario != null && lsInventario.size() > 0) {
            
            for (OpmInventario producto : lsInventario) {
                if(detalle.getNvReferencia().getNvCodigo().equals(producto.getNvReferencia().getNvCodigo())){
                    producto.setNmCantidad(detalle.getNmCantidad()+producto.getNmCantidad());
                    try {
                        _jpaControladorInventarioFabrica.edit(producto);
                        actualizado = true;
                    } catch (Exception ex) {
                        Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        }
        
        if(!actualizado){
            OpmInventario inventario =  new OpmInventario();
            inventario.setNvReferencia(detalle.getNvReferencia());
            inventario.setNmCantidad(detalle.getNmCantidad());
            _jpaControladorInventarioFabrica.create(inventario);
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
        jtfNroLote = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jcbProductos = new javax.swing.JComboBox();
        jbtAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDetalleLote = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jtfTotalLote = new javax.swing.JTextField();
        jbtRemover = new javax.swing.JButton();
        jpnFormularioProducto = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jtfCantidad = new javax.swing.JTextField();
        jbtRegistrar = new javax.swing.JButton();

        jLabel1.setText("Nro Lote");

        jLabel2.setText("Fecha");

        jLabel3.setText("Producto");

        jcbProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProductosActionPerformed(evt);
            }
        });

        jbtAgregar.setText("Agregar");
        jbtAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAgregarActionPerformed(evt);
            }
        });

        jtbDetalleLote.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbDetalleLote);

        jLabel5.setText("Total");

        jtfTotalLote.setEditable(false);

        jbtRemover.setText("Remover");
        jbtRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnFormularioProductoLayout = new javax.swing.GroupLayout(jpnFormularioProducto);
        jpnFormularioProducto.setLayout(jpnFormularioProductoLayout);
        jpnFormularioProductoLayout.setHorizontalGroup(
            jpnFormularioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnFormularioProductoLayout.setVerticalGroup(
            jpnFormularioProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        jLabel6.setText("Cantidad");

        jtfCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfCantidadKeyTyped(evt);
            }
        });

        jbtRegistrar.setText("Registrar");
        jbtRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRegistrarActionPerformed(evt);
            }
        });

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
                        .addGap(18, 18, 18)
                        .addComponent(jbtRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfTotalLote, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfNroLote, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jcbProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtAgregar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jtfNroLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jcbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnFormularioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtAgregar)
                    .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtRemover)
                    .addComponent(jtfTotalLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jbtRegistrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAgregarActionPerformed
        // TODO add your handling code here:
        OpmReferenciaProducto producto = lsProductos.get(jcbProductos.getSelectedIndex());
        int cantidad = 0;
        if (Util.validarNumero(jtfCantidad.getText(), true)) {
            cantidad = Integer.parseInt(jtfCantidad.getText());
        }
        if (!productoAgregado(producto) && cantidad > 0) {
            DefaultTableModel modelo = (DefaultTableModel) jtbDetalleLote.getModel();
            double valorTotal = cantidad * producto.getNmProducto().getNmValorPv();
            _totalLote += valorTotal;
            actualizarTotalLote();
            modelo.addRow(new Object[]{producto.getNvCodigo(), jtfCantidad.getText(), producto.getNmProducto().getNvNombre(), Util.getFormatoMoneda(producto.getNmProducto().getNmCostoPp()), Util.getFormatoMoneda(valorTotal)});
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad valida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtAgregarActionPerformed

    private void jcbProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbProductosActionPerformed
        // TODO add your handling code here:
        OpmReferenciaProducto producto = lsProductos.get(jcbProductos.getSelectedIndex());
        formularioProducto.setDatos(producto.getNmProducto());
    }//GEN-LAST:event_jcbProductosActionPerformed

    private void jtfCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCantidadKeyTyped
        // TODO add your handling code here:
        if (!Util.validarCampoNumerico(evt, false, jtfCantidad.getText())) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfCantidadKeyTyped

    private void jbtRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoverActionPerformed
        // TODO add your handling code here:
        double aux = Double.parseDouble(Util.getValorMoneda(jtbDetalleLote.getValueAt(jtbDetalleLote.getSelectedRow(), 4).toString()));
        _totalLote -= aux;
        DefaultTableModel modelo = (DefaultTableModel) jtbDetalleLote.getModel();
        modelo.removeRow(jtbDetalleLote.getSelectedRow());
        actualizarTotalLote();
    }//GEN-LAST:event_jbtRemoverActionPerformed

    private void jbtRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRegistrarActionPerformed
        // TODO add your handling code here:
        try {
            if (!jtfNroLote.getText().isEmpty()) {
                if (jtbDetalleLote.getModel().getRowCount() > 0) {
                    OpmLote lote = getDatos();
                    _jpaControladorLote.create(lote);
                    OpmLote aux = new OpmLote();
                    List<OpmLote> lsLotes = _jpaControladorLote.findOpmLoteEntities();

                    for (OpmLote auxItem : lsLotes) {
                        if (auxItem.getNvNumero().equals(lote.getNvNumero())) {
                            aux = auxItem;
                        }
                    }

                    for (int i = 0; i < jtbDetalleLote.getModel().getRowCount(); i++) {
                        OpmDetalleLote detalle = new OpmDetalleLote();
                        detalle.setNvReferencia(_jpaControladorProducto.findOpmReferenciaProducto(jtbDetalleLote.getValueAt(i, 0).toString()));
                        detalle.setNmCantidad(Integer.parseInt(jtbDetalleLote.getValueAt(i, 1).toString()));
                        detalle.setNmLote(aux);
                        _jpaControladorDetalleLote.create(detalle);
                        actualizarInventario(detalle);
                    }
                    JOptionPane.showMessageDialog(this, "Lote registrado con exito", "Lote", JOptionPane.INFORMATION_MESSAGE);
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Agregue por lo menos un detalle", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Introduzca el Nro de Lote", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "No se pudo registrar lote. Verifique el Numero de lote", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAgregar;
    private javax.swing.JButton jbtRegistrar;
    private javax.swing.JButton jbtRemover;
    private javax.swing.JComboBox jcbProductos;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JPanel jpnFormularioProducto;
    private javax.swing.JTable jtbDetalleLote;
    private javax.swing.JTextField jtfCantidad;
    private javax.swing.JTextField jtfNroLote;
    private javax.swing.JTextField jtfTotalLote;
    // End of variables declaration//GEN-END:variables
}
