package Modelo;

import Modelo.OpmVenta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmAbono.class)
public class OpmAbono_ { 

    public static volatile SingularAttribute<OpmAbono, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmAbono, Double> nmValor;
    public static volatile SingularAttribute<OpmAbono, OpmVenta> nmVenta;
    public static volatile SingularAttribute<OpmAbono, Date> daFecha;

}