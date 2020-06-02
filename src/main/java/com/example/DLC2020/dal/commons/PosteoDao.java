package com.example.DLC2020.dal.commons;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.example.DLC2020.entities.Posteo;

public class PosteoDao extends DaoEclipseLink<Posteo, Integer> {
    
	@Inject
    public PosteoDao(EntityManager manager){
        super(Posteo.class, manager);
    }
}
