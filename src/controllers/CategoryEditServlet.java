package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.CategoryDAO;
import entities.AppUser;
import entities.Category;

public class CategoryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(CategoryEditServlet.class);
	
	private CategoryDAO categoryDao = new CategoryDAO();
	
    public CategoryEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("MenuVisitorServlet");
		}
		
		try
		{
			Boolean deleted = Boolean.parseBoolean(request.getParameter("deleted"));
			
			AppUser loggedAdmin = (AppUser) request.getSession().getAttribute("admin");
			Category newCategory = new Category();
			
			newCategory.setCategoryId(Integer.parseInt(request.getParameter("id")));
			newCategory.setCategoryName(request.getParameter("name"));
			newCategory.setCategoryDeleted(deleted);
			
			categoryDao.merge(newCategory);
			LOGGER.info("Category with id: " + newCategory.getCategoryId() + " has been edited to name: " + newCategory.getCategoryName() + " by " + loggedAdmin.getAppUserUsername());
			
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
