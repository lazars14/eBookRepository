package helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

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
		boolean indexed = false;
		
		List<Category> categories = categoryDao.findAllNotDeleted();
		
		for(Category category : categories){
			List<Ebook> books = bookDao.findBooksByCategoryNotDeleted(category);
			
			for(Ebook book : books){
				indexed = indexBook(book);
			}
		}
		
		if(indexed){
			LOGGER.info("Successfully indexed books");
		} else {
			LOGGER.error("There was an error in the indexing process!");
		}
	}
	
	public boolean indexBook(Ebook book){
		Indexer indexer = Indexer.getInstance();
		Document doc = new Document();
		
		String[] keywords = getKeywords(book.getEBookkeywords());
		for(String s : keywords){
			doc.add(new TextField("keyword", s, Store.YES));
		}
		
		boolean singleAuthor = false;
		String[] authors = getAuthors(book.getEBookauthor(), singleAuthor);
		if(singleAuthor){
			doc.add(new TextField("author", book.getEBookauthor(), Store.YES));
		} else {
			for(String a : authors){
				doc.add(new TextField("author", a, Store.YES));
			}
		}
		
		BookFile bookFile = bookFileDao.findById(book.getEBookid());
		String bookText = pdfHandler.getText(new File(fileDao.buildFileNamePath(bookFile.getFileName(), book.getEBookcategory().getCategoryId())));
		
		doc.add(new TextField("title", book.getEBooktitle(), Store.YES));
		doc.add(new TextField("content", bookText, Store.YES));
		doc.add(new TextField("language", book.getEBooklanguage().getLanguageId().toString(), Store.YES));
		doc.add(new TextField("filename", bookFile.getFileName(), Store.YES));
		doc.add(new TextField("category", book.getEBookcategory().getCategoryId().toString(), Store.YES));
		doc.add(new TextField("bookFileId", bookFile.getFileId().toString(), Store.YES));
		
		return indexer.add(doc);
	}
	
	public boolean deleteIndex(Ebook book){
		Indexer indexer = Indexer.getInstance();
		
		return indexer.delete(book.getEBookfileid().getFileId().toString());
	}
	
	public boolean editIndex(Ebook book){
		Indexer indexer = Indexer.getInstance();
		Document doc = new Document();
		
		String[] keywords = getKeywords(book.getEBookkeywords());
		for(String s : keywords){
			doc.add(new TextField("keyword", s, Store.YES));
		}
		
		boolean singleAuthor = false;
		String[] authors = getAuthors(book.getEBookauthor(), singleAuthor);
		if(singleAuthor){
			doc.add(new TextField("author", book.getEBookauthor(), Store.YES));
		} else {
			for(String a : authors){
				doc.add(new TextField("author", a, Store.YES));
			}
		}
		
		BookFile bookFile = bookFileDao.findById(book.getEBookid());
		String bookText = pdfHandler.getText(new File(fileDao.buildFileNamePath(bookFile.getFileName(), book.getEBookcategory().getCategoryId())));
		
		doc.add(new TextField("title", book.getEBooktitle(), Store.YES));
		doc.add(new TextField("content", bookText, Store.YES));
		doc.add(new TextField("language", book.getEBooklanguage().getLanguageId().toString(), Store.YES));
		doc.add(new TextField("filename", bookFile.getFileName(), Store.YES));
		doc.add(new TextField("category", book.getEBookcategory().getCategoryId().toString(), Store.YES));
		doc.add(new TextField("bookFileId", bookFile.getFileId().toString(), Store.YES));
		
		return indexer.updateDocument(book.getEBookfileid().getFileId().toString(), doc.getFields());
	}
	
	public String[] getAuthors(String authorsString, boolean singleAuthor){
		String[] authorsList = null;
		
		try {
			authorsList = authorsString.split("\\|");
		} catch(Exception e){
			// single author
			singleAuthor = true;
		}
		
		return authorsList;
	}
	
	public String[] getKeywords(String keywordsString){
		String[] keywordsList = null;
		
		try {
			keywordsList = keywordsString.split("\\|");
		} catch(Exception e){
			LOGGER.error("Cannot split keywords, bad input!");
		}
		
		return keywordsList;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> search(String query){
		Indexer indexer = Indexer.getInstance();
		
		return indexer.search(query);
	}
}
