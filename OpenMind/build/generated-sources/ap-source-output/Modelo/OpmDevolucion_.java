package Modelo;

import Modelo.OpmDetalleVenta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmDevolucion.class)
public class OpmDevolucion_ { 

    public static volatile SingularAttribute<OpmDevolucion, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmDevolucion, OpmDetalleVenta> nmDetalle;
    public static volatile SingularAttribute<OpmDevolucion, Integer> nmCantidad;
    public static volatile SingularAttribute<OpmDevolucion, Date> daFecha;

}