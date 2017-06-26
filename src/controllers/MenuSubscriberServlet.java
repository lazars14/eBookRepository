package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.CategoryDAO;
import dao.EbookDAO;
import entities.AppUser;
import entities.Category;

public class MenuSubscriberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(MenuSubscriberServlet.class);
	
	private CategoryDAO categoryDao;
	
	private EbookDAO eBookDao;
	
    public MenuSubscriberServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int categoryId;
		
		try
		{
			if(session.getAttribute("subscriber") == null)
			{
				response.sendRedirect("/MenuVisitorServlet");
			}
			else
			{	
				String login = request.getParameter("login");
				
				AppUser loggedUser;
				Category subscriberCategory = null;
				
				if(login.equals("false")){
					try
					{
						categoryId = Integer.parseInt(request.getParameter("id"));
					}
					catch(NumberFormatException e)
					{
						categoryId = 1;
					}
					
					subscriberCategory = categoryDao.findById(categoryId);
					
				} else {
					loggedUser = (AppUser) session.getAttribute("subscriber");
					subscriberCategory = loggedUser.getAppUserCategoryId();
					
					// if the subscriber is subscribed to all categories
					if(subscriberCategory == null){
						subscriberCategory = categoryDao.findById(1);
					}
				}
				
				request.setAttribute("subscriberCategory", subscriberCategory);
				request.setAttribute("subscriberCategories", eBookDao.findBooksByCategoryNotDeleted(subscriberCategory));
				request.setAttribute("subscriberBooks", eBookDao.findBooksByCategoryNotDeleted(subscriberCategory));
				
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
