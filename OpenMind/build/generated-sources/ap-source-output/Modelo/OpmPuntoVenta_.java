package Modelo;

import Modelo.OpmInventarioPunto;
import Modelo.OpmRemision;
import Modelo.OpmTraslado;
import Modelo.OpmUsuario;
import Modelo.OpmVenta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmPuntoVenta.class)
public class OpmPuntoVenta_ { 

    public static volatile SingularAttribute<OpmPuntoVenta, Integer> nmCodigo;
    public static volatile ListAttribute<OpmPuntoVenta, OpmRemision> opmRemisionList;
    public static volatile SingularAttribute<OpmPuntoVenta, String> nvDireccion;
    public static volatile ListAttribute<OpmPuntoVenta, OpmTraslado> opmTrasladoList;
    public static volatile ListAttribute<OpmPuntoVenta, OpmInventarioPunto> opmInventarioPuntoList;
    public static volatile SingularAttribute<OpmPuntoVenta, String> nvTel;
    public static volatile ListAttribute<OpmPuntoVenta, OpmVenta> opmVentaList;
    public static volatile SingularAttribute<OpmPuntoVenta, OpmUsuario> nmAdministrador;

}