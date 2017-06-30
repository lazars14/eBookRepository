package dao;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FileDAO {
	
	private final static Logger LOGGER = LogManager.getLogger(FileDAO.class);
	
	private static final String PATH = "E:\\eBooks";
	
	public void createFolder(int categoryId){
		StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append(String.valueOf(categoryId));
        
		new File(sb.toString()).mkdirs();
        
        LOGGER.info("Created folder with category id " + categoryId);
    }
    
    public void deleteFolder(int categoryId){
    	StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append(String.valueOf(categoryId));
    	
    	File folder = new File(sb.toString());
        if(folder.delete()){
        	LOGGER.info("Deleted folder with category id " + categoryId);
        }
    }
	
	public String buildFileNamePath(String fileName, int categoryId){
		return PATH + "\\" + categoryId + "\\" + fileName + ".pdf";
	}
}
