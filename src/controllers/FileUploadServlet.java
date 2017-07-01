package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.BookFileDAO;
import dao.FileDAO;
import helpers.Indexer;

@SuppressWarnings({"serial", "unchecked"})
public class FileUploadServlet {
	
	public void init() throws ServletException {
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null)
		{
			response.sendRedirect("/MenuVisitorServlet");
		}
		
		String storagePath = ResourceBundle.getBundle("app").getString("storage");
		
		if(ServletFileUpload.isMultipartContent(request)){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
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
							fileName = System.currentTimeMillis() + extension;
							uploadedFile = new File(storagePath, fileName);
							fileItem = item;
							break;
						}
					}
				}
				while (uploadedFile.exists()) {
					fileName = System.currentTimeMillis() + extension;
					uploadedFile = new File(storagePath, fileName);
				}
				uploadedFile.createNewFile();
				fileItem.write(uploadedFile);
				Indexer.getInstance().index(uploadedFile);
				response.sendRedirect("upload.jsp?success");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}

}
