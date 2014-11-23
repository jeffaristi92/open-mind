package Modelo;

import Modelo.OpmProducto;
import Modelo.OpmTraslado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmDetalleTraslado.class)
public class OpmDetalleTraslado_ { 

    public static volatile SingularAttribute<OpmDetalleTraslado, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmDetalleTraslado, Integer> nmCantidad;
    public static volatile SingularAttribute<OpmDetalleTraslado, OpmTraslado> nmTraslado;
    public static volatile SingularAttribute<OpmDetalleTraslado, OpmProducto> nmProducto;

}