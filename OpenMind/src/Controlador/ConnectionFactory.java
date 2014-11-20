/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ARISTIZABAL
 */
public class ConnectionFactory {
    private EntityManagerFactory factory;
    
    public ConnectionFactory() {

        factory = Persistence.createEntityManagerFactory("OpenMindPU");
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
}
