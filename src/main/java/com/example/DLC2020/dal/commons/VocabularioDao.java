/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020.dal.commons;

import com.example.DLC2020.dal.commons.DaoEclipseLink;
import com.example.DLC2020.entities.Vocabulario;
/**
 *
 * @author Javier
 */
public class VocabularioDao extends DaoEclipseLink{
    public VocabularioDao(){
        super(Vocabulario.class);
    }
    
}
