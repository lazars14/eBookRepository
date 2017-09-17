package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.EbookDAO;
import entities.Ebook;
import helpers.CustomIndexer;
import helpers.CustomQueryBuilder;
import helpers.SearchField;
import helpers.SearchHelper;

public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final Logger LOGGER = LogManager.getLogger(BookSearchServlet.class);
	
	private SearchHelper searchHelper = new SearchHelper();
	private CustomQueryBuilder queryBuilder = new CustomQueryBuilder();
	private CustomIndexer customIndexer = new CustomIndexer();
	private EbookDAO bookDao = new EbookDAO();
	
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
			
			boolean author_checkbox = (request.getParameter("author_checkbox") != null) ? true : false;
			boolean keyword_checkbox = (request.getParameter("keyword_checkbox") != null) ? true : false;
			boolean title_checkbox = (request.getParameter("title_checkbox") != null) ? true : false;
			boolean content_checkbox = (request.getParameter("content_checkbox") != null) ? true : false;
			boolean language_checkbox = (request.getParameter("language_checkbox") != null) ? true : false;
			
			List<SearchField> fields = new ArrayList<SearchField>();
			fields.add(new SearchField("author", authors, author_checkbox));
			fields.add(new SearchField("keyword", keywords, keyword_checkbox));
			fields.add(new SearchField("title", title, title_checkbox));
			fields.add(new SearchField("content", content, content_checkbox));
			fields.add(new SearchField("language", languageId, language_checkbox));
			
			String query = queryBuilder.buildQuery(fields);
			
			ArrayList<Ebook> eBooks = bookDao.findByBookFileIds(customIndexer.search(query));
			
			request.setAttribute("booksResult", eBooks);
			
			getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
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
