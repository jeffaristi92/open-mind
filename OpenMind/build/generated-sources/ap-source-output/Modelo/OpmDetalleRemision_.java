package Modelo;

import Modelo.OpmProducto;
import Modelo.OpmRemision;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmDetalleRemision.class)
public class OpmDetalleRemision_ { 

    public static volatile SingularAttribute<OpmDetalleRemision, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmDetalleRemision, OpmRemision> nmRemision;
    public static volatile SingularAttribute<OpmDetalleRemision, Integer> nmCantidad;
    public static volatile SingularAttribute<OpmDetalleRemision, OpmProducto> nmProducto;

}