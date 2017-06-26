package controllers;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AppUserDAO;
import entities.AppUser;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
	
	private AppUserDAO appUserDao;
	
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try
		{
			AppUser user = appUserDao.login(username, password);
			
			if(user.getAppUserCategoryId() == null)
			{
				LOGGER.info("Admin " + user.getAppUserFirstname() + " " + user.getAppUserLastname() + " logged in");
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", user);
				getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
			}
			else
			{
				LOGGER.info("Subscriber with username " + user.getAppUserUsername() + " logged in");
				HttpSession session = request.getSession(true);
				session.setAttribute("subscriber", user);
				getServletContext().getRequestDispatcher("/MenuSubscriberServlet").forward(request, response);
			}
		}
		catch (NoResultException e) 
		{
			LOGGER.warn("User unathorized! Tried to login with username: " + username + " and password: " + password);
			response.sendRedirect(response.encodeRedirectURL("Login.jsp"));
		}
		catch (ServletException e)
		{
			LOGGER.error(e);
			throw e;
		}
		catch (IOException e)
		{
			LOGGER.error(e);
			throw e;
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
