package Modelo;

import Modelo.OpmProducto;
import Modelo.OpmPuntoVenta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmInventarioPunto.class)
public class OpmInventarioPunto_ { 

    public static volatile SingularAttribute<OpmInventarioPunto, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmInventarioPunto, Integer> nmCantidad;
    public static volatile SingularAttribute<OpmInventarioPunto, OpmProducto> nmProducto;
    public static volatile SingularAttribute<OpmInventarioPunto, OpmPuntoVenta> nmPuntoVenta;

}