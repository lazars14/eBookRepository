package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.CategoryDAO;
import dao.FileDAO;
import entities.AppUser;
import entities.Category;

public class CategoryAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = LogManager.getLogger(CategoryAddServlet.class);
	
	private CategoryDAO categoryDao;
	
	private FileDAO fileDao;
	
    public CategoryAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("/MenuVisitorServlet");
		}
		
		try
		{	
			Category newCategory = new Category();
			
			AppUser loggedUser = (AppUser) request.getSession().getAttribute("admin");
			
			newCategory.setCategoryName(request.getParameter("name"));
			newCategory.setCategoryDeleted(false);
			
			categoryDao.persist(newCategory);
			LOGGER.info("A category with the name: " + newCategory.getCategoryName() + " has been added by " + loggedUser.getAppUserUsername());
			
			fileDao.createFolder(newCategory.getCategoryId());
			
			getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
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

}
