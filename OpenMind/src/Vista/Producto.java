/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ConnectionFactory;
import Controlador.OpmProductoJpaController;
import Modelo.OpmInventario;
import Modelo.OpmProducto;
import Transversal.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARISTIZABAL
 */
public class Producto extends javax.swing.JPanel {

    /**
     * Creates new form Producto
     */
    enum FuenteDatos {Formulario, Tabla};
    OpmProductoJpaController _jpaControladorProducto;

    public Producto() {
        initComponents();
        _jpaControladorProducto = new OpmProductoJpaController((new ConnectionFactory()).getFactory());
        mtdActualizarTabla();
    }

    void mtdActualizarTabla() {
        List<OpmProducto> lstProductos = _jpaControladorProducto.findOpmProductoEntities();
        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(0);
        }

        for (OpmProducto producto : lstProductos) {
            Object[] fila;
            fila = new Object[]{
                (Object) producto.getNmCodigo(),
                producto.getNvNombre(),
                producto.getNvDescripcion(),
                producto.getNmCosto(),
                producto.getNmValor(),
                (producto.getBtActivo()) ? "Si" : "No"
            };
            modelo.addRow(fila);
        }
        tblProductos.setModel(modelo);
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
        txfCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txfNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txfDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txfCosto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txfVenta = new javax.swing.JTextField();
        jbtBuscar = new javax.swing.JButton();
        jbtActualizar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        rbtActivo = new javax.swing.JRadioButton();

        jLabel1.setText("Codigo");

        jLabel2.setText("Nombre(*)");

        jLabel3.setText("Descripcion");

        jLabel4.setText("Costo Produccion(*)");

        jLabel5.setText("Costo Venta(*)");

        jbtBuscar.setText("Buscar");

        jbtActualizar.setText("Actualizar");
        jbtActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtActualizarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        tblProductos.setAutoCreateRowSorter(true);
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Descripcion", "Costo Produccion", "Precio Venta", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setToolTipText("");
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        rbtActivo.setSelected(true);
        rbtActivo.setText("Activo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txfCodigo)
                            .addComponent(txfCosto))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jbtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbtActivo)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txfDescripcion)
                                    .addComponent(txfVenta))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtActivo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txfDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txfVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtBuscar)
                    .addComponent(jbtActualizar)
                    .addComponent(btnRegistrar))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Obtener datos del Formulario
    private OpmProducto getDatos(FuenteDatos fuente) {
        OpmProducto producto = new OpmProducto();

        if (fuente == FuenteDatos.Formulario) {
            producto.setNvNombre(txfNombre.getText());
            producto.setNvDescripcion(txfDescripcion.getText());
            producto.setNmCosto(Double.parseDouble(txfCosto.getText()));
            producto.setNmValor(Double.parseDouble(txfVenta.getText()));
            producto.setBtActivo(rbtActivo.isSelected());
        } else {
            producto.setNmCodigo(Integer.parseInt(tblProductos.getValueAt(tblProductos.getSelectedRow(), 0) + ""));
            producto.setNvNombre(tblProductos.getValueAt(tblProductos.getSelectedRow(), 1) + "");
            producto.setNvDescripcion(tblProductos.getValueAt(tblProductos.getSelectedRow(), 2) + "");
            producto.setNmCosto(Double.parseDouble(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3) + ""));
            producto.setNmValor(Double.parseDouble(tblProductos.getValueAt(tblProductos.getSelectedRow(), 4) + ""));
            producto.setBtActivo(tblProductos.getValueAt(tblProductos.getSelectedRow(), 5).equals("Si"));
        }
        return producto;
    }

    private void setDatos(OpmProducto producto) {
        txfCodigo.setText(producto.getNmCodigo().toString());
        rbtActivo.setSelected(producto.getBtActivo());
        txfNombre.setText(producto.getNvNombre());
        txfDescripcion.setText(producto.getNvDescripcion());
        txfCosto.setText(producto.getNmCosto() + "");
        txfVenta.setText(producto.getNmValor() + "");
    }

    // Validar campos
    private boolean validarCamposObligatorios() {
        List<String> camposAlfanumericos = new ArrayList<>();
        camposAlfanumericos.add(txfNombre.getText());
        List<String> camposNumericos = new ArrayList<>();
        camposNumericos.add(txfCosto.getText());
        camposNumericos.add(txfVenta.getText());
        if (Util.validarCamposObligatorios(camposAlfanumericos, camposNumericos)) {
            return true;
        } else {
            return false;
        }
    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (validarCamposObligatorios()) {
            try {
                OpmProducto producto = getDatos(FuenteDatos.Formulario);
                _jpaControladorProducto.create(producto);
                mtdActualizarTabla();
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el producto.\n Verifique que no existe otro producto con el mismo nombre", "Error", ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Faltan campos obligatorios", "Advertencia", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    // Actualizar
    private void jbtActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtActualizarActionPerformed
        try {
            OpmProducto producto = getDatos(FuenteDatos.Formulario);
            producto.setNmCodigo(Integer.parseInt(txfCodigo.getText()));
            /*List<OpmInventario> listaInventario = new LinkedList<>();
            listaInventario.add(new OpmInventario());
            producto.setOpmInventarioList(listaInventario);
            */
            _jpaControladorProducto.edit(producto);
            mtdActualizarTabla();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto.\n Verifique que los campos son correctos", "Error", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtActualizarActionPerformed

    // Actualizar
    private void tblProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setDatos(getDatos(FuenteDatos.Tabla));
        }
    }//GEN-LAST:event_tblProductosKeyPressed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        // TODO add your handling code here:
        setDatos(getDatos(FuenteDatos.Tabla));
    }//GEN-LAST:event_tblProductosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtActualizar;
    private javax.swing.JButton jbtBuscar;
    private javax.swing.JRadioButton rbtActivo;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txfCodigo;
    private javax.swing.JTextField txfCosto;
    private javax.swing.JTextField txfDescripcion;
    private javax.swing.JTextField txfNombre;
    private javax.swing.JTextField txfVenta;
    // End of variables declaration//GEN-END:variables
}
