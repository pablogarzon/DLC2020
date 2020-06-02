package com.example.DLC2020.dal.commons;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.example.DLC2020.entities.Documento;

public class DocumentoDao extends DaoEclipseLink<Documento, Integer> {
	
	@Inject
	public DocumentoDao(EntityManager entityManager) {
		super(Documento.class, entityManager);
	}
}
