package helpers;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;

public class SearchHelper {
	
	public String[] fillAuthorsKeywords(String input){
		String[] list = new String[10];
		
		try {
			list = input.split("\\|");
		} catch(Exception e){
			list[0] = input;
		}
		
		return list;
	}
}
