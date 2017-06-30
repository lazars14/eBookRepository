package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.CategoryDAO;
import entities.Category;

public class CategoryPrepareAddEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger LOGGER = LogManager.getLogger(CategoryPrepareAddEditServlet.class);
	
	private CategoryDAO categoryDao;
	
    public CategoryPrepareAddEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("MenuVisitorServlet");
		}
		
		categoryDao = new CategoryDAO();
		String action = "";
		
		try
		{
			String idStr = request.getParameter("id");
			Category category = new Category();
			int idInt;
			
			try
			{
				idInt = Integer.parseInt(idStr);
				category = categoryDao.findById(idInt);
			}
			catch (NumberFormatException e)
			{
				action = "add";
			}
			
			request.setAttribute("categoryAddEdit", category);
			request.setAttribute("action", action);
			getServletContext().getRequestDispatcher("/CategoryAddEdit.jsp").forward(request, response);	
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
