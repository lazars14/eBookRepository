package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import entities.BookFile;
import entities.Ebook;
import helpers.CustomIndexer;

public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(BookAddServlet.class);
	
	private EbookDAO eBookDao = new EbookDAO();
	private BookFileDAO bookFileDao = new BookFileDAO();
	private CategoryDAO categoryDao = new CategoryDAO();
	private BookLanguageDAO bookLanguageDao = new BookLanguageDAO();
	private FileDAO fileDao = new FileDAO();
	private CustomIndexer customIndexer = new CustomIndexer();
	
    public BookAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("MenuVisitorServlet");
		}
		
		Ebook newEbook = new Ebook();
		newEbook.setEBookdeleted(false);
		
		BookFile newBookFile = new BookFile();
		
		AppUser loggedUser = (AppUser) request.getSession().getAttribute("admin");
		
		String storagePath = "";
		
		try{
			// String storagePath = ResourceBundle.getBundle("app").getString("storage");
			
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
					boolean valid = false;
					for(FileItem item : items){ //trebalo bi da ima samo 1
						if(!item.isFormField()){
							fileName = item.getName();
							if(fileName.endsWith("pdf")){
								LOGGER.info("Usao u pdf");
								valid = true;
							}
							
							if(valid){
								uploadedFile = new File(storagePath, fileName);
								fileItem = item;
							}
						}
						else{
							String fieldName = item.getFieldName();
							
							switch(fieldName){
							
							case "title":
								newEbook.setEBooktitle(item.getString());
								break;
							case "author":
								newEbook.setEBookauthor(item.getString());
								break;
							case "publicationYear":
								newEbook.setEBookpublicationyear(Integer.parseInt(item.getString()));
								break;
							case "keywords":
								newEbook.setEBookkeywords(item.getString());
								break;
							case "languageSelect":
								newEbook.setEBooklanguage(bookLanguageDao.findById(Integer.parseInt(item.getString())));
								break;
							case "categorySelect":
								newEbook.setEBookcategory(categoryDao.findById(Integer.parseInt(item.getString())));
								storagePath = fileDao.buildFolderPath(newEbook.getEBookcategory().getCategoryId());
								break;
							}
						}
					}
					
					if(!valid){
						LOGGER.info("The user didn't put any file!");
						getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
					}
					
					uploadedFile.createNewFile();
					fileItem.write(uploadedFile);
					
					newBookFile.setFileName(fileName.substring(0, fileName.length() - 4));
					newBookFile.setFileMime("application/pdf");
					
					bookFileDao.persist(newBookFile);
					
					newEbook.setEBookfileid(newBookFile);
					
					eBookDao.persist(newEbook);
					
					customIndexer.indexBook(newEbook);
					
					LOGGER.info("A book with the name: " + newEbook.getEBooktitle() + " has been added by " + loggedUser.getAppUserUsername());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			else {
				LOGGER.info("Not multipart");
				getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
			}
		
			getServletContext().getRequestDispatcher("/MenuAdminServlet").forward(request, response);
		} 
		catch(NumberFormatException e) {
			LOGGER.error(e);
			throw e;
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
