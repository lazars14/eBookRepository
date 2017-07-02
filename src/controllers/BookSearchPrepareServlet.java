package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.lucene.search.BooleanClause.Occur;

import dao.BookLanguageDAO;
import entities.AppUser;
import helpers.Indexer;
import helpers.SearchType;

public class BookSearchPrepareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private final Logger LOGGER = LogManager.getLogger(BookSearchPrepareServlet.class);
	
	private BookLanguageDAO bookLanguageDao;
	
    public BookSearchPrepareServlet() {
        super();
        Indexer.getInstance().index(new File(ResourceBundle.getBundle("app").getString("storage")));
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookLanguageDao = new BookLanguageDAO();
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
			
			List<String> occures = new ArrayList<String>();
			for(Occur o : Occur.values()){
				occures.add(o.toString());
			}
			
			request.setAttribute("occures", occures);
			request.setAttribute("searchTypes", SearchType.getMessages());
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
