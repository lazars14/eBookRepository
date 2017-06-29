package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import entities.BookLanguage;
import dao.BookLanguageDAO;

public class BookSearchPrepareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private final Logger LOGGER = LogManager.getLogger(BookSearchPrepareServlet.class);
	
	private BookLanguageDAO bookLanguage;
	
    public BookSearchPrepareServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookLanguage = new BookLanguageDAO();
		
		try {
			List<BookLanguage> languages = bookLanguage.findAllNotDeleted();
			request.setAttribute("languages", languages);
			
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
