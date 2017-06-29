package dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.AppUser;
import entities.Category;

public class AppUserDAO extends GenericDAO<AppUser, Integer> {
	
	public AppUser login(String username, String password){
		EntityManager em = GenericDAO.getEM();
		Query q = em.createNamedQuery("AppUser.findLogin");
		q.setParameter("username", username);
		q.setParameter("password", password);
		AppUser user = (AppUser) q.getSingleResult();
		em.close();
		return user;
	}

	public void updateUserPassword(AppUser loggedUser, String password) {
		loggedUser.setAppUserPassword(password);
		em.merge(loggedUser);
		em.close();
	}

	public void updateUserInfo(AppUser loggedUser, String firstname, String lastname) {
		loggedUser.setAppUserFirstname(firstname);
		loggedUser.setAppUserLastname(lastname);
		em.merge(loggedUser);
		em.close();
	}
	
	public String checkIfSubscribed(AppUser subscriber, Category bookCategory){
		int subscriberCategoryId = subscriber.getAppUserCategoryId().getCategoryId();
		
		if(subscriberCategoryId == bookCategory.getCategoryId()){
			return "1";
		}
		
		return "0";
	}
}
