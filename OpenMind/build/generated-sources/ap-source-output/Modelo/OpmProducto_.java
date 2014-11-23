package Modelo;

import Modelo.OpmDetalleLote;
import Modelo.OpmDetalleRemision;
import Modelo.OpmDetalleTraslado;
import Modelo.OpmDetalleVenta;
import Modelo.OpmInventario;
import Modelo.OpmInventarioPunto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmProducto.class)
public class OpmProducto_ { 

    public static volatile SingularAttribute<OpmProducto, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmProducto, Double> nmCosto;
    public static volatile ListAttribute<OpmProducto, OpmInventarioPunto> opmInventarioPuntoList;
    public static volatile SingularAttribute<OpmProducto, Double> nmValor;
    public static volatile SingularAttribute<OpmProducto, String> nvDescripcion;
    public static volatile ListAttribute<OpmProducto, OpmDetalleLote> opmDetalleLoteList;
    public static volatile ListAttribute<OpmProducto, OpmDetalleRemision> opmDetalleRemisionList;
    public static volatile SingularAttribute<OpmProducto, Boolean> btActivo;
    public static volatile SingularAttribute<OpmProducto, String> nvNombre;
    public static volatile ListAttribute<OpmProducto, OpmInventario> opmInventarioList;
    public static volatile ListAttribute<OpmProducto, OpmDetalleTraslado> opmDetalleTrasladoList;
    public static volatile ListAttribute<OpmProducto, OpmDetalleVenta> opmDetalleVentaList;

}