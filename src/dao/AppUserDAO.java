package dao;

import javax.persistence.Query;

import entities.AppUser;

public class AppUserDAO extends GenericDAO<AppUser, Integer> {
	
	public AppUser login(String username, String password) {
		Query q = em.createNamedQuery("AppUser.findLogin");
		q.setParameter("username", username);
		q.setParameter("password", password);
		AppUser user = (AppUser) q.getSingleResult();
		return user;
	}

	public void updateUserPassword(AppUser loggedUser, String password) {
		loggedUser.setAppUserPassword(password);
		em.merge(loggedUser);
	}

	public void updateUserInfo(AppUser loggedUser, String firstname, String lastname) {
		loggedUser.setAppUserFirstname(firstname);
		loggedUser.setAppUserLastname(lastname);
		em.merge(loggedUser);
	}
}
