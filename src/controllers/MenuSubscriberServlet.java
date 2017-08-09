package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AppUserDAO;
import dao.CategoryDAO;
import dao.EbookDAO;
import entities.AppUser;
import entities.Category;

public class MenuSubscriberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(MenuSubscriberServlet.class);
	
	private CategoryDAO categoryDao = new CategoryDAO();
	
	private EbookDAO eBookDao = new EbookDAO();
	
	private AppUserDAO appUserDao = new AppUserDAO();
	
    public MenuSubscriberServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int categoryId;
		
		if(session.getAttribute("subscriber") == null){
			response.sendRedirect("MenuVisitorServlet");
		}
		
		try
		{
			if(session.getAttribute("subscriber") == null)
			{
				response.sendRedirect("/MenuVisitorServlet");
			}
			else
			{	
				Category subscriberCategory = null;
				
				try
				{
					categoryId = Integer.parseInt(request.getParameter("id"));
				}
				catch(NumberFormatException e)
				{
					categoryId = 1;
				}
				
				subscriberCategory = categoryDao.findById(categoryId);
				AppUser subscriber = (AppUser) session.getAttribute("subscriber");
				
				request.setAttribute("subscriberCategory", subscriberCategory);
				request.setAttribute("subscriberCategories", categoryDao.findAllNotDeleted());
				request.setAttribute("subscriberBooks", eBookDao.findBooksByCategoryNotDeleted(subscriberCategory));
				request.setAttribute("subscribed", appUserDao.checkIfSubscribed(subscriber, subscriberCategory));
				
				getServletContext().getRequestDispatcher("/MenuSubscriber.jsp").forward(request, response);
			}
		}
		catch(ServletException e)
		{
			LOGGER.error(e);
			throw e;
		}
		catch (IOException e) {
			LOGGER.error(e);
			throw e;
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
}
