package com.example.demo.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * 
 * @author t.almeida
 *
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDao<T, PK extends Serializable> {

	private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * retorna o entity manager
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * cria consulta
	 * 
	 * @param jpql
	 * @param params
	 * @return
	 */
	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		return query.getResultList();
	}

	/**
	 * salva registro
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		entityManager.persist(entity);
	}

	/**
	 * atualiza registro
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		entityManager.merge(entity);
	}

	/**
	 * apaga registro
	 * 
	 * @param id
	 */
	public void delete(PK id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}

	/**
	 * encontra registro por id
	 * 
	 * @param id
	 * @return
	 */
	public T findById(PK id) {
		return entityManager.find(entityClass, id);
	}

	/**
	 * lista todos os registros
	 * 
	 * @return
	 */
	public List<T> listAll() {
		return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
	}
}
