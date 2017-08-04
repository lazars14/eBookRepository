package helpers;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.CategoryDAO;
import dao.EbookDAO;
import entities.Category;
import entities.Ebook;

public class CustomIndexer {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomIndexer.class);
	
	private CategoryDAO categoryDao;
	private EbookDAO bookDao;
	
	public void indexBooks(){
		List<Category> categories = categoryDao.findAllNotDeleted();
		
		for(Category category : categories){
			List<Ebook> books = bookDao.findBooksByCategoryNotDeleted(category);
			
			for(Ebook book : books){
				indexBook(book);
			}
		}
	}
	
	public void indexBook(Ebook book){
		
	}
}
