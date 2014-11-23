package Modelo;

import Modelo.OpmUsuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-22T21:15:29")
@StaticMetamodel(OpmRol.class)
public class OpmRol_ { 

    public static volatile SingularAttribute<OpmRol, Integer> nmCodigo;
    public static volatile SingularAttribute<OpmRol, String> nvNombre;
    public static volatile ListAttribute<OpmRol, OpmUsuario> opmUsuarioList;

}