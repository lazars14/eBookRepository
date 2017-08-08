package helpers;

import java.io.File;
import java.util.ResourceBundle;

public class StartIndexer {
	
	public static void main(String[] args) {
		CustomIndexer ci = new CustomIndexer();
		ci.indexBooks();
		// code for indexing
		// Indexer.getInstance().index(new File(ResourceBundle.getBundle("app").getString("storage")));
	}

}
