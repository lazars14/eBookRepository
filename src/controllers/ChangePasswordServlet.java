package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AppUserDAO;
import entities.AppUser;

public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AppUserDAO appUserDao;
	
	private static final Logger LOGGER = LogManager.getLogger(ChangePasswordServlet.class);
	
    public ChangePasswordServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("newPassword");
		AppUser loggedUser = null;
		
		appUserDao = new AppUserDAO();
		
		try
		{
			if(request.getSession().getAttribute("admin") != null)
			{
				loggedUser = (AppUser) request.getSession().getAttribute("admin");
				appUserDao.updateUserPassword(loggedUser, newPassword);
				LOGGER.info("Admin with username " + loggedUser.getAppUserUsername() + " changed his password");
				response.sendRedirect("MenuAdminServlet");
			}
			else if(request.getSession().getAttribute("subscriber") != null)
			{
				loggedUser = (AppUser) request.getSession().getAttribute("subscriber");
				appUserDao.updateUserPassword(loggedUser, newPassword);
				LOGGER.info("Subscriber with username " + loggedUser.getAppUserUsername() + " changed his password");
				response.sendRedirect("MenuSubscriberServlet");
			}
			else
			{		
				response.sendRedirect("MenuVisitorServlet");
			}
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
