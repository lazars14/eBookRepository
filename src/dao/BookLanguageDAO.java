package dao;

import java.util.List;

import javax.persistence.Query;

import entities.BookLanguage;

public class BookLanguageDAO extends GenericDAO<BookLanguage, Integer> {
	
	@SuppressWarnings("unchecked")
	public List<BookLanguage> findAllNotDeleted() {
		Query q = em.createNamedQuery("BookLanguage.findAllNotDeleted");
		List<BookLanguage> languages = q.getResultList();
		return languages;
	}
	
}
