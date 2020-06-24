package com.example.DLC2020.dal.commons;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Expression;

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
	            Expression<Boolean> expression = criteriaBuilder.equal(root.get("palabra"), word);
	            return super.findByFilter(expression).get(0);   
	        }
		 	catch (NoResultException e) {
				return null;
			}
	        catch (Exception ex)
	        {
	            throw new TechnicalException(ex);
	        }
	}    
}
