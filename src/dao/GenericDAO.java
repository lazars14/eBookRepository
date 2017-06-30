package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class GenericDAO<T, ID extends Serializable> {
	
	private static final Logger LOGGER = LogManager.getLogger(GenericDAO.class);
	
	private Class<T> entityType;

	/* @PersistenceContext(unitName = "eBookProject")  */
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory( "eBookProject" );
	protected static EntityManager em;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		entityType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getEntityType() {
		return entityType;
	}

	public T findById(ID id) {
		em = GenericDAO.getEM();
		T entity;
		entity = em.find(entityType, id);
		em.close();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		em = GenericDAO.getEM();
		Query q = em.createQuery("SELECT x FROM " + entityType.getSimpleName()
				+ " x");
		List<T> result = q.getResultList();
		em.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> findBy(String query) {
		em = GenericDAO.getEM();
		Query q = em.createQuery(query);
		List<T> result = q.getResultList();
		em.close();
		return result;
	}

	public T persist(T entity) {
		em = GenericDAO.getEM();
		em.getTransaction().begin();
		LOGGER.info("em.persist: " + entity);
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	public T merge(T entity) {
		em = GenericDAO.getEM();
		em.getTransaction().begin();
		LOGGER.info("em.merge: " + entity);
		entity = em.merge(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	public void remove(T entity) {		
		em = GenericDAO.getEM();
		em.getTransaction().begin();
		LOGGER.info("em.remove: " + entity);
		entity = em.merge(entity);
		em.remove(entity);
		em.getTransaction().commit();
		em.close();
	}

	public void flush() {
		em = GenericDAO.getEM();
		em.flush();
		em.clear();
	}

	public void clear() {
		em = GenericDAO.getEM();
		em.clear();
		em.close();
	}

	public static EntityManager getEM() {
		return emf.createEntityManager();
	}
	
	public static void checkIfEMExists(){
		if(em == null){
			em = emf.createEntityManager();
		}
	}

}