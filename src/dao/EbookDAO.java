package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Category;
import entities.Ebook;

public class EbookDAO extends GenericDAO<Ebook, Integer> {
	
	@SuppressWarnings("unchecked")
	public List<Ebook> findBooksByCategoryNotDeleted(Category c) {
		EntityManager em = GenericDAO.getEM();
		Query q = em.createNamedQuery("Ebook.findByCategoryNotDeleted");
		q.setParameter("eBookcategory", c);
		List<Ebook> books = q.getResultList();
		em.close();
		return books;
	}

	@SuppressWarnings("unchecked")
	public Object findBooksByCategory(Category c) {
		EntityManager em = GenericDAO.getEM();
		Query q = em.createNamedQuery("Ebook.findBooksByCategory");
		q.setParameter("eBookcategory", c);
		List<Ebook> books = q.getResultList();
		em.close();
		return books;
	}

	public ArrayList<Ebook> findByBookFileIds(ArrayList<String> search) {
		ArrayList<Ebook> books = new ArrayList<Ebook>();
		EntityManager em = GenericDAO.getEM();
		Ebook book;
		
		for(int i = 0; i < search.size(); i++){
			Query q = em.createNamedQuery("Ebook.findBooksByBookFileId");
			q.setParameter("bookFileId", search.get(i));
			
			book = (Ebook) q.getSingleResult();
			books.add(book);
		}
		
		em.close();
		
		return books;
	}
	
}
