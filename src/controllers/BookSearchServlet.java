package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import helpers.CustomQueryBuilder;
import helpers.SearchField;
import helpers.SearchHelper;
import javafx.util.Pair;

public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final Logger LOGGER = LogManager.getLogger(BookSearchServlet.class);
	
	private SearchHelper searchHelper = new SearchHelper();
	
    public BookSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String author = request.getParameter("author");
			String keyword = request.getParameter("keyword");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String languageId = request.getParameter("languageSelect");
			
			String[] authors = searchHelper.fillAuthorsKeywords(author);
			String[] keywords = searchHelper.fillAuthorsKeywords(keyword);
			
			boolean author_checkbox = Boolean.parseBoolean(request.getParameter("author_checkbox"));
			boolean keyword_checkbox = Boolean.parseBoolean(request.getParameter("keyword_checkbox"));
			boolean title_checkbox = Boolean.parseBoolean(request.getParameter("title_checkbox"));
			boolean content_checkbox = Boolean.parseBoolean(request.getParameter("content_checkbox"));
			boolean language_checkbox = Boolean.parseBoolean(request.getParameter("language_checkbox"));
			
			List<SearchField> fields = new ArrayList<SearchField>();
			
			fields.add(new SearchField("author", authors, author_checkbox));
			fields.add(new SearchField("keyword", keywords, keyword_checkbox));
			fields.add(new SearchField("title", title, title_checkbox));
			fields.add(new SearchField("content", content, content_checkbox));
			fields.add(new SearchField("language", languageId, language_checkbox));
			
			String query = CustomQueryBuilder.buildQuery(fields);
			
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
