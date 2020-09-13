package br.com.willianschuck.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.google.gson.Gson;

import br.com.willianschuck.radio.prop.PersistenceProperties;

public abstract class AbstractDAO<T> {

	private static final String queryGetAll = "SELECT e FROM %s e";
	
	private static EntityManagerFactory emf;
	private EntityManager entityManager;
	
	public AbstractDAO() {
		
		if (emf == null) {

			HashMap<String, String> properties = new HashMap<String, String>();

			Gson gson = new Gson();
			
			try {
				
				FileReader reader = new FileReader(new File("./bd_prop.json"));
				PersistenceProperties props = gson.fromJson(reader, PersistenceProperties.class);
				
				properties.put("javax.persistence.jdbc.driver", props.getDriver());
				properties.put("javax.persistence.jdbc.url", props.getUrl());
				properties.put("javax.persistence.jdbc.user", props.getUser());
				properties.put("javax.persistence.jdbc.password", props.getPassword());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			emf = Persistence.createEntityManagerFactory("RadioPU", properties);
			
		}
		
		
	}
	
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
	
	public void cadastrar(T entity) {
		
		try {
			entityManager().getTransaction().begin();
			entityManager().persist(entity);
			entityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager().getTransaction().rollback();
		}
		
	}
	
	public T atualizar(T entity) {
		
		T mergedEntity = null;
		try {
			entityManager().getTransaction().begin();
			mergedEntity = entityManager.merge(entity);
			entityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager().getTransaction().rollback();
		}
		return mergedEntity;
		
	}
	
	public T buscar(Object id) {
		
		entityManager().getTransaction().begin();
		T result = entityManager().find(getEntityClass(), id);
		entityManager().getTransaction().commit();
		return result;
		
	}
	
	public void remover(T entity) {
		
		try {
			entityManager().getTransaction().begin();
			entityManager().remove(entity);
			entityManager().getTransaction().commit();
		} catch (Exception e) {
			entityManager().getTransaction().rollback();
			throw e;
		}
		
	}
	
	public List<T> getAll() {
		
		String className = getEntityClass().getSimpleName();
		return entityManager().createQuery(String.format(queryGetAll, className), getEntityClass()).getResultList();
		
	}

}
