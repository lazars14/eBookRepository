package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import entities.AppUser;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(LogoutServlet.class);
	
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AppUser loggedUser = new AppUser();
			String role = "";
			
			if(request.getSession().getAttribute("admin") != null)
			{
				loggedUser = (AppUser) request.getSession().getAttribute("admin");
				role = "Admin ";
			}
			else if(request.getSession().getAttribute("subscriber") != null)
			{
				loggedUser = (AppUser) request.getSession().getAttribute("subscriber");
				role = "Subscriber ";
			}
			else
			{
				response.sendRedirect("/MenuVisitorServlet");
			}
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			LOGGER.info(role + loggedUser.getAppUserFirstname() + " " + loggedUser.getAppUserLastname() + " has just logged out");
			
			response.sendRedirect("Login.jsp");

		} catch (IOException e) {
			LOGGER.error(e);
			throw e;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
