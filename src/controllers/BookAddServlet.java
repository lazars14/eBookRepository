package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.BookFileDAO;
import dao.BookLanguageDAO;
import dao.CategoryDAO;
import dao.EbookDAO;
import dao.FileDAO;
import entities.AppUser;
import entities.Ebook;
import helpers.Indexer;

public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(BookAddServlet.class);
	
	private EbookDAO eBookDao;
	private BookFileDAO bookFileDao;
	private CategoryDAO categoryDao;
	private BookLanguageDAO bookLanguageDao;
	private FileDAO fileDao;
	
    public BookAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("/MenuVisitorServlet");
		}
		
		eBookDao = new EbookDAO();
		bookFileDao = new BookFileDAO();
		categoryDao = new CategoryDAO();
		bookLanguageDao = new BookLanguageDAO();
		fileDao = new FileDAO();
		
		Ebook newEbook = new Ebook();
		
		AppUser loggedUser = (AppUser) request.getSession().getAttribute("admin");
		
		try{
			newEbook.setEBooktitle(request.getParameter("title"));
			newEbook.setEBookauthor(request.getParameter("author"));
			newEbook.setEBookpublicationyear(Integer.parseInt(request.getParameter("publicationYear")));
			newEbook.setEBookdeleted(false);
			newEbook.setEBookkeywords(request.getParameter("keywords"));
			newEbook.setEBooklanguage(bookLanguageDao.findById(Integer.parseInt(request.getParameter("languageSelect"))));
			newEbook.setEBookcategory(categoryDao.findById(Integer.parseInt(request.getParameter("categorySelect"))));
			
			// String storagePath = ResourceBundle.getBundle("app").getString("storage");
			String storagePath = fileDao.buildFolderPath(newEbook.getEBookcategory().getCategoryId());
			
			if(ServletFileUpload.isMultipartContent(request)){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024);
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					@SuppressWarnings("unchecked")
					List<FileItem> items = upload.parseRequest(request);
					FileItem fileItem = null;
					File uploadedFile = null;
					String fileName = "";
					String extension = "";
					boolean valid = false;
					for(FileItem item : items){ //trebalo bi da ima samo 1
						if(!item.isFormField()){
							fileName = item.getName();
							if(fileName.endsWith("pdf")){
								extension = ".pdf";
								valid = true;
							}
							
							if(valid){
								
								
								fileName += extension;
								uploadedFile = new File(storagePath, fileName);
								fileItem = item;
								break;
							}
						}
					}
					uploadedFile.createNewFile();
					fileItem.write(uploadedFile);
					Indexer.getInstance().index(uploadedFile);
					response.sendRedirect("upload.jsp?success");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
			
			eBookDao.persist(newEbook);
			LOGGER.info("A book with the name: " + newEbook.getEBooktitle() + " has been added by " + loggedUser.getAppUserUsername());
			
			getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
		} 
		catch(NumberFormatException e) {
			getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
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

}
