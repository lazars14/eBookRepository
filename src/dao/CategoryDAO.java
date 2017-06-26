package dao;

import java.util.List;

import javax.persistence.Query;

import entities.Category;

public class CategoryDAO extends GenericDAO<Category, Integer> {
	
	public List<Category> findAllNotDeleted() {
		Query q = em.createNamedQuery("Category.findAllNotDeleted");
		@SuppressWarnings("unchecked")
		List<Category> listOfCategories = q.getResultList();
		return listOfCategories;
	}
}
