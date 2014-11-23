package Modelo;

import Modelo.OpmProducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmInventario.class)
public class OpmInventario_ { 

    public static volatile SingularAttribute<OpmInventario, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmInventario, Integer> nmCantidad;
    public static volatile SingularAttribute<OpmInventario, OpmProducto> nmProducto;

}