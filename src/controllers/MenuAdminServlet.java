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
import entities.Category;

public class MenuAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(MenuAdminServlet.class);
	
	private CategoryDAO categoryDao;
	
	private EbookDAO eBookDao;
	
    public MenuAdminServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try
		{
			if(session.getAttribute("admin") == null)
			{
				response.sendRedirect("/MenuVisitorServlet");
			}
			else
			{
				String idStr = request.getParameter("id");
				Integer idInt;
				try
				{
					idInt = Integer.parseInt(idStr);
				}
				catch(NumberFormatException e)
				{
					idInt = 1;
				}
				
				Category c = categoryDao.findById(idInt);
				
				request.setAttribute("adminCategory", c);
				request.setAttribute("adminCategories", categoryDao.findAll());				
				request.setAttribute("adminBooks", eBookDao.findBooksByCategory(c));
				
				getServletContext().getRequestDispatcher("/MenuAdmin.jsp").forward(request, response);
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
