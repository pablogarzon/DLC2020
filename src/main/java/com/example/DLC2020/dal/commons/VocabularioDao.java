package com.example.DLC2020.dal.commons;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.example.DLC2020.dal.exceptions.TechnicalException;
import com.example.DLC2020.entities.Vocabulario;

public class VocabularioDao extends DaoEclipseLink<Vocabulario, Integer>{
	
	@Inject
    public VocabularioDao(EntityManager entityManager){
        super(Vocabulario.class, entityManager);
    }
	
	public Vocabulario findByWord(String word) {
		 try
	        {
	        	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	            CriteriaQuery<Vocabulario> cq = cb.createQuery(Vocabulario.class);
	            Root<Vocabulario> rootQuery = cq.from(Vocabulario.class);
	            ParameterExpression<String> p = cb.parameter(String.class);
	            cq.select(rootQuery).where(cb.equal(rootQuery.get("palabra"), p));
	            TypedQuery<Vocabulario> query = entityManager.createQuery(cq);
	            query.setParameter(p, word);
	            return query.getSingleResult();
	        }
	        catch (Exception ex)
	        {
	            throw new TechnicalException(ex);
	        }
	}    
}
