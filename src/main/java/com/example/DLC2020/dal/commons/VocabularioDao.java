package com.example.DLC2020.dal.commons;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Expression;

import com.example.DLC2020.dal.exceptions.TechnicalException;
import com.example.DLC2020.entities.Vocabulario;

public class VocabularioDao extends DaoEclipseLink<Vocabulario, Integer> {

	@Inject
	public VocabularioDao(EntityManager entityManager) {
		super(Vocabulario.class, entityManager);
	}

	public Vocabulario findByWord(String word) {
		try {
			Expression<Boolean> expression = criteriaBuilder.equal(root.get("palabra"), word);
			List<Vocabulario> words = super.findByFilter(expression);
			if (words.size() > 0) {
				return words.get(0);
			} else {
				return null;
			}
		} catch (NoResultException e) {
			return null;
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}
}
