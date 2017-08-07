package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.EbookDAO;
import dao.FileDAO;
import entities.AppUser;
import entities.Ebook;
import helpers.CustomIndexer;

public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(BookDeleteServlet.class);
	
	private EbookDAO eBookDao;
	private FileDAO fileDao;
	private CustomIndexer customIndexer;
	
    public BookDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("MenuVisitorServlet");
		}
		
		eBookDao = new EbookDAO();
		fileDao = new FileDAO();
		customIndexer = new CustomIndexer();
		
		try
		{
			Ebook selectedEbook = eBookDao.findById(Integer.parseInt(request.getParameter("id")));
			AppUser admin = (AppUser) request.getSession().getAttribute("admin");
			
			selectedEbook.setEBookdeleted(true);
			fileDao.deleteFile(selectedEbook.getEBookfileid().getFileName(), selectedEbook.getEBookcategory().getCategoryId());
			eBookDao.merge(selectedEbook);
			
			boolean deletedIndex = customIndexer.deleteIndex(selectedEbook);
			
			if(deletedIndex){
				LOGGER.info("Ebook " + selectedEbook.getEBooktitle() + " has been deleted by " + admin.getAppUserUsername());
			} else {
				LOGGER.info("Couldn't delete index for Ebook with id " + selectedEbook.getEBookid() + ".");
			}
			
			// because boolean can't be null
			request.setAttribute("bookDeletion", "yes");
			request.setAttribute("bookCategory", selectedEbook.getEBookcategory().getCategoryId());
			
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
