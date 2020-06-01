package com.example.DLC2020.dal.commons;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.example.DLC2020.dal.exceptions.*;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * @param <E> Tipo de la entidad asociada
 * @param <K> Tipo de la clave primaria de la entidad asociada
 */
public abstract class DaoEclipseLink<E extends DalEntity, K> implements Dao<E, K>
{
    //@Inject
    @PersistenceContext(unitName="com.example_DLC2020_jar_0.0.1-SNAPSHOTPU")
    protected EntityManager entityManager;
	
    private final Class<E> entityClass;

    private String className;

    public DaoEclipseLink(Class<E> entityClass) {
		this.entityClass = entityClass;
		this.className = entityClass.getSimpleName();
	}

    @Override
    @Transactional
    public E create(E pData)
    {
        try
        {
            entityManager.persist(pData);
            entityManager.flush();
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
            Query query = entityManager.createNamedQuery(className + ".findAll");
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
