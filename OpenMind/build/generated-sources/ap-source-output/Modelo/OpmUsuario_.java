package Modelo;

import Modelo.OpmLote;
import Modelo.OpmPuntoVenta;
import Modelo.OpmRemision;
import Modelo.OpmRol;
import Modelo.OpmVenta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmUsuario.class)
public class OpmUsuario_ { 

    public static volatile SingularAttribute<OpmUsuario, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmUsuario, OpmRol> nmRol;
    public static volatile ListAttribute<OpmUsuario, OpmPuntoVenta> opmPuntoVentaList;
    public static volatile ListAttribute<OpmUsuario, OpmRemision> opmRemisionList;
    public static volatile SingularAttribute<OpmUsuario, String> nvTel;
    public static volatile ListAttribute<OpmUsuario, OpmVenta> opmVentaList;
    public static volatile SingularAttribute<OpmUsuario, Boolean> btActivo;
    public static volatile SingularAttribute<OpmUsuario, String> nvNombre;
    public static volatile SingularAttribute<OpmUsuario, String> nvEmail;
    public static volatile ListAttribute<OpmUsuario, OpmLote> opmLoteList;

}