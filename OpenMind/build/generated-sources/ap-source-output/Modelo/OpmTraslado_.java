package Modelo;

import Modelo.OpmDetalleTraslado;
import Modelo.OpmPuntoVenta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmTraslado.class)
public class OpmTraslado_ { 

    public static volatile SingularAttribute<OpmTraslado, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmTraslado, OpmPuntoVenta> nmOrigen;
    public static volatile SingularAttribute<OpmTraslado, Integer> nmDestino;
    public static volatile ListAttribute<OpmTraslado, OpmDetalleTraslado> opmDetalleTrasladoList;
    public static volatile SingularAttribute<OpmTraslado, Date> daFecha;

}