package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.BookLanguageDAO;
import entities.BookLanguage;

public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final Logger LOGGER = LogManager.getLogger(BookSearchServlet.class);
	
	private BookLanguageDAO languageDao;
	
    public BookSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String keyword = request.getParameter("keyword");
			String content = request.getParameter("content");
			BookLanguage language = languageDao.findById(Integer.parseInt(request.getParameter("languageSelect")));
			
			// ovo ne bi trebalo ovako, trebalo bi preko ajax-a!!!
			
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

}
