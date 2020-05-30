/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020.dal.commons;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import com.example.DLC2020.entities.Posteo;

/**
 *
 * @author Javier
 */
public class PosteoDao extends DaoEclipseLink<Posteo, Integer> {
    
    public PosteoDao(){
        super(Posteo.class);
    }
    
    
}
