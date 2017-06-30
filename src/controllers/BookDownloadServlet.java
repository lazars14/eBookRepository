package controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.EbookDAO;
import dao.FileDAO;
import entities.AppUser;
import entities.BookFile;
import entities.Ebook;

public class BookDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final static Logger LOGGER = LogManager.getLogger(BookDownloadServlet.class);
	
	private EbookDAO ebookDao;
	private FileDAO fileDao;
	
    public BookDownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null && request.getSession().getAttribute("subscriber") == null)
		{
			response.sendRedirect("MenuVisitorServlet");
		}
		 
		ebookDao = new EbookDAO();
		fileDao = new FileDAO();
		
		Ebook book = ebookDao.findById(Integer.parseInt(request.getParameter("id")));
		BookFile bookFile = book.getEBookfileid();
		
		String filename = fileDao.buildFileNamePath(bookFile.getFileName(), book.getEBookcategory().getCategoryId());
		LOGGER.info(filename + " ovo je filename");
		
		try {
		if(filename != null) {
			InputStream stream = new BufferedInputStream(new FileInputStream(new File(filename)));
			ServletOutputStream out;
			out = response.getOutputStream();
			byte[] bbuf = new byte[100];
			int length = 0;
			while ((stream != null) && ((length = stream.read(bbuf)) != -1))
			   {
			       out.write(bbuf,0,length);
			   }
			out.close();
			stream.close();
		}
		} catch (Exception e) {
			LOGGER.error("Error on downloading book with id " + book.getEBookid() + ".");
			e.printStackTrace();
		}
		
		LOGGER.info("Successfully downloaded book with id " + book.getEBookid() + ".");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
