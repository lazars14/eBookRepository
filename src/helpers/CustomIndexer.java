package helpers;

import java.io.File;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import dao.BookFileDAO;
import dao.CategoryDAO;
import dao.EbookDAO;
import dao.FileDAO;
import entities.Category;
import entities.Ebook;
import entities.BookFile;

public class CustomIndexer {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomIndexer.class);
	
	private FileDAO fileDao = new FileDAO();
	private CategoryDAO categoryDao = new CategoryDAO();
	private EbookDAO bookDao = new EbookDAO();
	private BookFileDAO bookFileDao = new BookFileDAO();
	private PDFHandler pdfHandler = new PDFHandler();
	
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
		Indexer indexer = Indexer.getInstance();
		Document doc = new Document();
		
		String[] keywords = getKeywords(book.getEBookkeywords());
		if(keywords.length > 0){
			for(String s : keywords){
				doc.add(new TextField("keyword", s, Store.YES));
			}
			
			BookFile bookFile = bookFileDao.findById(book.getEBookid());
			String bookText = pdfHandler.getText(new File(fileDao.buildFileNamePath(bookFile.getFileName(), book.getEBookcategory().getCategoryId())));
			
			doc.add(new TextField("title", book.getEBooktitle(), Store.YES));
			doc.add(new TextField("author", book.getEBookauthor(), Store.YES));
			doc.add(new TextField("content", bookText, Store.YES));
			doc.add(new IntField("language", book.getEBooklanguage().getLanguageId(), Store.YES));
			
			indexer.add(doc);
		} else {
			
		}
	}
	
	public String[] getKeywords(String keywordsString){
		String[] keywordsList = null;
		
		try {
			keywordsList = keywordsString.split(",");
		} catch(Exception e){
			
		}
		
		return keywordsList;
	}
}
