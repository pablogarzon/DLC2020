package com.example.DLC2020.dal.commons;

import java.util.List;

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
			List<Documento> docs = findByFilter(expression);
			if(docs.size() > 0)
				return docs.get(0);
			else 
				return null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}
}
