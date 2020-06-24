package com.example.DLC2020.dal.commons;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Expression;

import com.example.DLC2020.dal.exceptions.TechnicalException;
import com.example.DLC2020.entities.Documento;

public class DocumentoDao extends DaoEclipseLink<Documento, Integer> {
	
	@Inject
	public DocumentoDao(EntityManager entityManager) {
		super(Documento.class, entityManager);
	}
	
	public Documento findByName(String name) {
		try {
			Expression<Boolean> expression = criteriaBuilder.equal(root.get("nombre"), name);
			return super.findByFilter(expression).get(0);
		} catch (NoResultException e) {
			return null;
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}
}
