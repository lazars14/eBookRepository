package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@SuppressWarnings({"serial", "unchecked"})
public class FileUploadServlet {
	
	public void init() throws ServletException {
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String storagePath = ResourceBundle.getBundle("app").getString("storage");
		
		/*
		if(ServletFileUpload.isMultipartContent(request)){
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest();
				FileItem fileItem = null;
				File uploadedFile = null;
				String fileName = "";
				String extension = "";
				for(FileItem item : items){ //trebalo bi da ima samo 1
					if(!item.isFormField()){
						fileName = item.getName();
						if(fileName.endsWith("pdf")){
							extension = ".pdf";
						}else if(fileName.endsWith("doc")){
							extension = ".doc";
						}else if(fileName.endsWith("docx")){
							extension = ".docx";
						}else if(fileName.endsWith("txt")){
							extension = ".txt";
						}else{
							return;
						}
						fileName = System.currentTimeMillis() + extension;
						uploadedFile = new File(storagePath, fileName);
						fileItem = item;
						break;
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
				response.sendRedirect("upload.jsp?error");
			}
			
			
		}*/
	}

}
