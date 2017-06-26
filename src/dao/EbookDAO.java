package dao;

import java.util.List;

import javax.persistence.Query;

import entities.Category;
import entities.Ebook;

public class EbookDAO extends GenericDAO<Ebook, Integer> {
	
	@SuppressWarnings("unchecked")
	public List<Ebook> findBooksByCategoryNotDeleted(Category c) {
		Query q = em.createNamedQuery("Ebook.findByCategoryNotDeleted");
		q.setParameter("eBookcategory", c);
		List<Ebook> books = q.getResultList();
		return books;
	}

	@SuppressWarnings("unchecked")
	public Object findBooksByCategory(Category c) {
		Query q = em.createNamedQuery("Ebook.findBooksByCategory");
		q.setParameter("eBookcategory", c);
		List<Ebook> books = q.getResultList();
		return books;
	}
	
}
