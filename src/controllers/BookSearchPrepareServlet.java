package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.BookLanguageDAO;
import entities.AppUser;

public class BookSearchPrepareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private final Logger LOGGER = LogManager.getLogger(BookSearchPrepareServlet.class);
	
	private BookLanguageDAO bookLanguageDao = new BookLanguageDAO();
	
    public BookSearchPrepareServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String user = "";
		int userCategory = 0;
		
		try {
			if(session.getAttribute("admin") != null){
				user = "admin";
			} else if(session.getAttribute("subscriber") != null){
				user = "subscriber";
				AppUser subscriber = (AppUser) session.getAttribute("subscriber");
				if(subscriber.getAppUserCategoryId() != null){
					userCategory = subscriber.getAppUserCategoryId().getCategoryId();
				}
			} else{
				user = "visitor";
			}
			
			request.setAttribute("user", user);
			request.setAttribute("userCategory", userCategory);
			request.setAttribute("languages", bookLanguageDao.findAllNotDeleted());
			
			getServletContext().getRequestDispatcher("/BookSearch.jsp").forward(request, response);
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
