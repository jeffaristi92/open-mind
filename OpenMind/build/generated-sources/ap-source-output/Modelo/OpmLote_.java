package Modelo;

import Modelo.OpmDetalleLote;
import Modelo.OpmUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmLote.class)
public class OpmLote_ { 

    public static volatile SingularAttribute<OpmLote, OpmUsuario> nmEmpleado;
    public static volatile SingularAttribute<OpmLote, Integer> nmCodigo;
    public static volatile ListAttribute<OpmLote, OpmDetalleLote> opmDetalleLoteList;
    public static volatile SingularAttribute<OpmLote, Date> daFecha;

}