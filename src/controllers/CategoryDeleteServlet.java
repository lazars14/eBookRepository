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

public class CategoryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(CategoryDeleteServlet.class);
	
	private CategoryDAO categoryDao;
	
	private FileDAO fileDao;
	
    public CategoryDeleteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("MenuVisitorServlet");
		}
		
		categoryDao = new CategoryDAO();
		fileDao = new FileDAO();
		
		try
		{
			Category selectedCategory = categoryDao.findById(Integer.parseInt(request.getParameter("id")));
			AppUser admin = (AppUser) request.getSession().getAttribute("admin");
			
			selectedCategory.setCategoryDeleted(true);
			categoryDao.merge(selectedCategory);
			LOGGER.info("Category " + selectedCategory.getCategoryName() + " has been deleted by " + admin.getAppUserUsername());
			
			fileDao.deleteFolder(selectedCategory.getCategoryId());
			
			getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
		}
		catch (ServletException e) {
			LOGGER.error(e);
			throw e;
		}
		catch (IOException e) {
			LOGGER.error(e);
			throw e;
		}
	}

}
