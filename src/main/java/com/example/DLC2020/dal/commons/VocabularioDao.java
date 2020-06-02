package com.example.DLC2020.dal.commons;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.example.DLC2020.dal.commons.DaoEclipseLink;
import com.example.DLC2020.entities.Vocabulario;

public class VocabularioDao extends DaoEclipseLink<Vocabulario, Integer>{
	
	@Inject
    public VocabularioDao(EntityManager entityManager){
        super(Vocabulario.class, entityManager);
    }
    
}
