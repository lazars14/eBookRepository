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

public class ChangePersonalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(ChangePersonalInfoServlet.class);
	
	private AppUserDAO appUserDao;
	
    public ChangePersonalInfoServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
		AppUser loggedUser = null;
		
		appUserDao = new AppUserDAO();
		
		try
		{
			if(request.getSession().getAttribute("admin") != null)
			{
				loggedUser = (AppUser) request.getSession().getAttribute("admin");
				appUserDao.updateUserInfo(loggedUser, firstname, lastname);
				LOGGER.info("Admin with username " + loggedUser.getAppUserUsername() + " changed his personal info");
				response.sendRedirect("MenuAdminServlet");
			}
			else if(request.getSession().getAttribute("subscriber") != null)
			{
				loggedUser = (AppUser) request.getSession().getAttribute("subscriber");
				appUserDao.updateUserInfo(loggedUser, firstname, lastname);
				LOGGER.info("Subscriber with username " + loggedUser.getAppUserUsername() + " changed his personal info");
				response.sendRedirect("MenuSubcriberServlet");
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
