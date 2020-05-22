package br.com.willianschuck.radio.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public abstract class DAO<T> {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RadioPU");
	private static EntityManager entityManager;
	
	public abstract Class<T> getEntityClass(); 
	
	public EntityManager entityManager() {
		
		if (entityManager == null || !entityManager.isOpen()) {
			synchronized (this) {
				if (entityManager == null || !entityManager.isOpen()) {
					entityManager = emf.createEntityManager();
					entityManager.setFlushMode(FlushModeType.COMMIT);
				}
			}
		}
		return entityManager;
		
	}
	
	public void persist(T entity) {
		
		EntityManager entityManager = entityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		
	}
	
	public T merge(T entity) {
		
		EntityManager entityManager = entityManager();

		entityManager.getTransaction().begin();
		T mergedEntity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		
		return mergedEntity;
		
	}
	
	public T find(Object id) {
		
		EntityManager entityManager = entityManager();
		
		entityManager.getTransaction().begin();
		T result = entityManager.find(getEntityClass(), id);
		entityManager.getTransaction().commit();
		
		return result;
		
	}
	
	public void remove(T entity) {
		
		EntityManager entityManager = entityManager();
		
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		
	}

}
