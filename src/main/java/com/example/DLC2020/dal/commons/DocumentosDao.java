/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020.dal.commons;

import com.example.DLC2020.dal.commons.DaoEclipseLink;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import com.example.DLC2020.entities.Documentos;

/**
 *
 * @author Javier
 */
public class DocumentosDao extends DaoEclipseLink<Documentos, Integer>{
    public DocumentosDao(){
        super(Documentos.class);
    }
    
    
    
    
    
    
}
