package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.CategoryDAO;
import dao.EbookDAO;
import entities.Category;
import entities.Ebook;

public class MenuVisitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(MenuVisitorServlet.class);
	
	private CategoryDAO categoryDao;
	
	private EbookDAO booksDao;
	
    public MenuVisitorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId;
		try
		{
			try
			{
				categoryId = Integer.parseInt(request.getParameter("id"));
			}
			catch(NumberFormatException e)
			{
				categoryId = 1;
			}
			
			Category category = categoryDao.findById(categoryId);
			List<Ebook> books = booksDao.findBooksByCategoryNotDeleted(category);
			
			request.setAttribute("userCategory", category);
			request.setAttribute("userCategories", categoryDao.findAllNotDeleted());
			request.setAttribute("userBooksForCategory", books);
			
			getServletContext().getRequestDispatcher("/MenuVisitor.jsp").forward(request, response);
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
