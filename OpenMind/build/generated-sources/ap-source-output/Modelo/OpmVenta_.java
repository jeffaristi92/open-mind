package Modelo;

import Modelo.OpmAbono;
import Modelo.OpmDetalleVenta;
import Modelo.OpmPuntoVenta;
import Modelo.OpmUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmVenta.class)
public class OpmVenta_ { 

    public static volatile SingularAttribute<OpmVenta, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmVenta, OpmUsuario> nmCliente;
    public static volatile ListAttribute<OpmVenta, OpmAbono> opmAbonoList;
    public static volatile SingularAttribute<OpmVenta, Double> nmFlete;
    public static volatile SingularAttribute<OpmVenta, OpmPuntoVenta> nmPunto;
    public static volatile SingularAttribute<OpmVenta, Date> daFecha;
    public static volatile ListAttribute<OpmVenta, OpmDetalleVenta> opmDetalleVentaList;

}