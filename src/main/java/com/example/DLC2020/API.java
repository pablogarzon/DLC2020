/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020;

import com.example.DLC2020.entities.Vocabulario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Javier
 */
public abstract class API {
    private static final String PERSISTENCE_UNIT_NAME = "com.example_DLC2020_jar_0.0.1-SNAPSHOTPU";
    
    public static void staticRun() {
        EntityManager em=null;
        try{
            em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
            List<Vocabulario> vocabularios = em.createNamedQuery("Vocabulario.findAll").getResultList();
            for (Vocabulario v : vocabularios){
                System.out.println(v.toString());
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        } finally{
            if (em!= null) em.close();
        }
    }
    
}