package Modelo;

import Modelo.OpmLote;
import Modelo.OpmProducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmDetalleLote.class)
public class OpmDetalleLote_ { 

    public static volatile SingularAttribute<OpmDetalleLote, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmDetalleLote, OpmProducto> nmProducto;
    public static volatile SingularAttribute<OpmDetalleLote, Integer> nmCantidad;
    public static volatile SingularAttribute<OpmDetalleLote, OpmLote> nmLote;

}