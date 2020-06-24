package com.example.DLC2020.dal.commons;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.example.DLC2020.dal.exceptions.TechnicalException;

/**
 * @param <E> Tipo de la entidad asociada
 * @param <K> Tipo de la clave primaria de la entidad asociada
 */
public abstract class DaoEclipseLink<E extends DalEntity, K> implements Dao<E, K> {
	
	protected EntityManager entityManager;
	
	protected final Class<E> entityClass;
	protected final CriteriaBuilder criteriaBuilder;
	protected final CriteriaQuery<E> criteriaQuery;
	protected final Root<E> root;

	public DaoEclipseLink(Class<E> entityClass, EntityManager entityManager) {
		this.entityManager = entityManager;
		this.entityClass = entityClass;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		this.criteriaQuery = criteriaBuilder.createQuery(entityClass);
		this.root = criteriaQuery.from(entityClass);
		this.criteriaQuery.select(root);
	}

	@Override
	@Transactional
	public E create(E pData) {
		try {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(pData);
			tx.commit();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}

		return pData;
	}

	@Override
	public boolean createBatch(E[] pData) {
		try {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			for (int i = 0; i < pData.length; i++) {
				if (pData[i] != null)
					entityManager.persist(pData[i]);
			}

			tx.commit();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}

		return true;
	}

	@Override
	@Transactional
	public void update(E pData) {
		try {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			E managed = entityManager.merge(pData);
			entityManager.persist(managed);
			tx.commit();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}

	@Override
	@Transactional
	public void delete(K pKey) {
		try {
			entityManager.remove(retrieve(pKey));
			entityManager.flush();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}

	@Override
	public E retrieve(K pKey) {
		return entityManager.find(entityClass, pKey);
	}

	@Override
	public List<E> findAll() {
		try {
			TypedQuery<E> query = entityManager.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}

	}

	@Override
	public List<E> findByFilter(Expression<Boolean> expression) {
		try {
			criteriaQuery.where(expression);
			TypedQuery<E> query = entityManager.createQuery(criteriaQuery);
			return query.getResultList();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}		
	}

	@Override
	public long count() {
		try {
			CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
			cq.select(criteriaBuilder.count(root));
			return entityManager.createQuery(cq).getSingleResult();
		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}

}
