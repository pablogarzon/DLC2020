package com.example.DLC2020.dal.commons;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.example.DLC2020.dal.exceptions.TechnicalException;

/**
 * @param <E> Tipo de la entidad asociada
 * @param <K> Tipo de la clave primaria de la entidad asociada
 */
public abstract class DaoEclipseLink<E extends DalEntity, K> implements Dao<E, K>
{
    protected EntityManager entityManager;
	
    private final Class<E> entityClass;

    private String className;

    public DaoEclipseLink(Class<E> entityClass, EntityManager entityManager) {
    	this.entityManager = entityManager;
		this.entityClass = entityClass;
		this.className = entityClass.getSimpleName();
	}

    @Override
    @Transactional
    public E create(E pData)
    {
        try
        {
        	EntityTransaction tx = entityManager.getTransaction();
        	tx.begin();
        	entityManager.persist(pData);
        	tx.commit();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }

        return pData;
    }

    @Override
    @Transactional
    public void update(E pData)
    {
        try
        {
            E managed = entityManager.merge(pData);
            entityManager.persist(managed);
            entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }
    }

    @Override
    @Transactional
    public void delete(K pKey)
    {
        try
        {
            entityManager.remove(retrieve(pKey));
            entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }
    }

    @Override
    public E retrieve(K pKey)
    {
        return entityManager.find(entityClass, pKey);
    }

    @Override
    public List<E> findAll()
    {
        try
        {
        	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> cq = cb.createQuery(entityClass);
            Root<E> rootQuery = cq.from(entityClass);
            cq.select(rootQuery);
            TypedQuery<E> query = entityManager.createQuery(cq);
            return query.getResultList();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }

    }
    public List<E> findByFilter(String filter)
    {
        try
        {
            Query query = entityManager.createNamedQuery(className + ".findByFilter")
                .setParameter(":filter", filter);

            return query.getResultList();
        }
        catch (Exception ex)
        {
            throw new TechnicalException(ex);
        }

    }
    
    @Override
    public long count() {
    	 try
         {
    		 CriteriaBuilder qb = entityManager.getCriteriaBuilder();
    		 CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    		 cq.select(qb.count(cq.from(entityClass)));
             return entityManager.createQuery(cq).getSingleResult();
         }
         catch (Exception ex)
         {
             throw new TechnicalException(ex);
         }
    }

}
