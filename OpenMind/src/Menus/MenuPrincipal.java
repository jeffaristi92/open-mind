/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import Vista.AbonoVenta;
import Vista.Cliente;
import Vista.Devolucion;
import Vista.Empleado;
import Vista.Producto;
import Vista.Remision;
import Vista.ReporteEstadoResultados;
import Vista.ReporteInventario;
import Vista.Traslado;
import Vista.Venta;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 *
 * @author ARISTIZABAL
 */
public class MenuPrincipal {
    private JMenuBar jmbMenuPrincipal;
    private JMenu jmnGastos;
    private JMenu jmnGestion;
    private JMenu jmnReportes;
    private JMenu jmnTransacciones;
    private JMenuItem jmeAbono;
    private JMenuItem jmeAdministritativo;
    private JMenuItem jmeCliente;
    private JMenuItem jmeDevolucion;
    private JMenuItem jmeEmpleado;
    private JMenuItem jmeEstadoResultados;
    private JMenuItem jmeGastos;
    private JMenuItem jmeIngresos;
    private JMenuItem jmeInventario;
    private JMenuItem jmeLote;
    private JMenuItem jmeNegocio;
    private JMenuItem jmeProducto;
    private JMenuItem jmeProveedor;
    private JMenuItem jmePuntoVenta;
    private JMenuItem jmeRemision;
    private JMenuItem jmeTraslado;
    private JMenuItem jmeVenta;
    JScrollPane panContenido;
    
    public MenuPrincipal(JScrollPane panel){
        panContenido = panel;
    }
    
    public JMenuBar ConstruirMenu(){
        jmbMenuPrincipal = new JMenuBar();
        jmnGestion = new JMenu();
        jmeProducto = new JMenuItem();
        jmeEmpleado = new JMenuItem();
        jmeCliente = new JMenuItem();
        jmeProveedor = new JMenuItem();
        jmePuntoVenta = new JMenuItem();
        jmnTransacciones = new JMenu();
        jmeLote = new JMenuItem();
        jmeRemision = new JMenuItem();
        jmeVenta = new JMenuItem();
        jmeTraslado = new JMenuItem();
        jmeDevolucion = new JMenuItem();
        jmeAbono = new JMenuItem();
        jmnGastos = new JMenu();
        jmeAdministritativo = new JMenuItem();
        jmeNegocio = new JMenuItem();
        jmnReportes = new JMenu();
        jmeInventario = new JMenuItem();
        jmeGastos = new JMenuItem();
        jmeIngresos = new JMenuItem();
        jmeEstadoResultados = new JMenuItem();
        
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

        jmeProveedor.setText("Proveedores");
        jmnGestion.add(jmeProveedor);

        jmePuntoVenta.setText("Punto Venta");
        jmnGestion.add(jmePuntoVenta);

        jmbMenuPrincipal.add(jmnGestion);

        jmnTransacciones.setText("Transacciones");

        jmeLote.setText("Lote");
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
        
        return jmbMenuPrincipal;
    }
    
    private void jmeProductoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        panContenido.removeAll();
        Producto obj  =new Producto();
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
        panContenido.add(obj);
    }                                           

    private void jmeRemisionActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        panContenido.removeAll();
        Remision obj  =new Remision();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                           

    private void jmeVentaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        panContenido.removeAll();
        Venta obj  =new Venta();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                        

    private void jmeTrasladoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        panContenido.removeAll();
        Traslado obj  =new Traslado();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                           

    private void jmeDevolucionActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        panContenido.removeAll();
        Devolucion obj  =new Devolucion();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                             

    private void jmeAbonoActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        panContenido.removeAll();
        AbonoVenta obj  =new AbonoVenta();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                        

    private void jmeEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        panContenido.removeAll();
        Empleado obj  =new Empleado();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                           

    private void jmeClienteActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        panContenido.removeAll();
        Cliente obj  =new Cliente();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                          

    private void jmeInventarioActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        panContenido.removeAll();
        ReporteInventario obj  =new ReporteInventario();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }                                             

    private void jmeEstadoResultadosActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        // TODO add your handling code here:
        panContenido.removeAll();
        ReporteEstadoResultados obj  =new ReporteEstadoResultados();
        panContenido.add(obj);
        obj.setSize(794, 500);
        obj.validate();
        obj.setVisible(true);
    }
}
