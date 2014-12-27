/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transversal;

import Modelo.OpmUsuario;
import Vista.AbonoVenta;
import Vista.Devolucion;
import Vista.Empleado;
import Vista.Lote;
import Vista.Producto;
import Vista.PuntoVenta;
import Vista.ReferenciaProducto;
import Vista.Remision;
import Vista.ReporteEstadoResultados;
import Vista.ReporteInventario;
import Vista.Traslado;
import Vista.Venta;
import java.awt.Component;

/**
 *
 * @author ARISTIZABAL
 */
public class Componente {

    public static Component getComponente(String nombre, OpmUsuario usuario) {
        switch (nombre) {
            case "Punto Venta":
                return new PuntoVenta();
            case "Producto":
                return new Producto();
            case "Referencia Producto":
                return new ReferenciaProducto();
            case "Empleado":
                return new Empleado();
            case "Lote":
                Lote lote =  new Lote();
                lote.setUsuario(usuario);
                return lote;
            case "Remision":
                Remision remision = new Remision();
                remision.setUsuario(usuario);
                return remision;
            case "Venta":
                Venta venta = new Venta();
                venta.setUsuario(usuario);
                return venta;
            case "Traslado":
                return new Traslado();
            case "Devolucion":
                return new Devolucion();
            case "Abono Venta":
                return new AbonoVenta();
            case "Reporte Estado Resultados":
                return new ReporteEstadoResultados();
            case "Reporte Inventario":
                ReporteInventario inventario = new ReporteInventario();
                inventario.setUsuario(usuario);
                return inventario;
            default:
                return null;
        }
    }
}
