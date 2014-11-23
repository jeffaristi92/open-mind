package Modelo;

import Modelo.OpmDetalleRemision;
import Modelo.OpmPuntoVenta;
import Modelo.OpmUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmRemision.class)
public class OpmRemision_ { 

    public static volatile SingularAttribute<OpmRemision, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmRemision, OpmUsuario> nmEmpleado;
    public static volatile ListAttribute<OpmRemision, OpmDetalleRemision> opmDetalleRemisionList;
    public static volatile SingularAttribute<OpmRemision, OpmPuntoVenta> nmPunto;
    public static volatile SingularAttribute<OpmRemision, Date> daFecha;

}