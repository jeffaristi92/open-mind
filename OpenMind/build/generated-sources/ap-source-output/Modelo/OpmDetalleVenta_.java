package Modelo;

import Modelo.OpmDevolucion;
import Modelo.OpmProducto;
import Modelo.OpmVenta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmDetalleVenta.class)
public class OpmDetalleVenta_ { 

    public static volatile SingularAttribute<OpmDetalleVenta, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmDetalleVenta, OpmVenta> nmVenta;
    public static volatile SingularAttribute<OpmDetalleVenta, Double> nmPrecio;
    public static volatile SingularAttribute<OpmDetalleVenta, Integer> nmCantidad;
    public static volatile SingularAttribute<OpmDetalleVenta, OpmProducto> nmProducto;
    public static volatile ListAttribute<OpmDetalleVenta, OpmDevolucion> opmDevolucionList;

}