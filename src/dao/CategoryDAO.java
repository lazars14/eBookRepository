package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Category;

public class CategoryDAO extends GenericDAO<Category, Integer> {
	
	public List<Category> findAllNotDeleted() {
		EntityManager em = GenericDAO.getEM();
		Query q = em.createNamedQuery("Category.findAllNotDeleted");
		@SuppressWarnings("unchecked")
		List<Category> listOfCategories = q.getResultList();
		em.close();
		return listOfCategories;
	}
}
