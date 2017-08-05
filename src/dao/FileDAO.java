package dao;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FileDAO {
	
	private final static Logger LOGGER = LogManager.getLogger(FileDAO.class);
	
	private static final String PATH = ResourceBundle.getBundle("app").getString("storage");
	private static final String PDF_EXTENSION = ".pdf";
	private static final String DELIMITER = "\\";

	public void createFolder(int categoryId){
		StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append("\\");
		sb.append(String.valueOf(categoryId));
        
		new File(sb.toString()).mkdirs();
        
        LOGGER.info("Created folder with category id " + categoryId);
    }
    
    public void deleteFolder(int categoryId){
    	StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append(DELIMITER);
		sb.append(String.valueOf(categoryId));
    	
    	File folder = new File(sb.toString());
        if(folder.delete()){
        	LOGGER.info("Deleted folder with category id " + categoryId);
        }
    }

	public void deleteFile(String fileName, int categoryId) {
		StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append(DELIMITER);
		sb.append(String.valueOf(categoryId));
		sb.append(DELIMITER);
    	sb.append(fileName);
		sb.append(PDF_EXTENSION);
    	
    	File file = new File(sb.toString());
        if(file.delete()){
        	LOGGER.info("Deleted file with name " + fileName);
        }
		
	}
	
	public String buildFileNamePath(String fileName, int categoryId){
		System.out.println(PATH + DELIMITER + categoryId + DELIMITER + fileName + PDF_EXTENSION);
		return PATH + DELIMITER + categoryId + DELIMITER + fileName + PDF_EXTENSION;
	}

	public String buildFolderPath(Integer categoryId) {
		return PATH + DELIMITER + categoryId;
	}
}
