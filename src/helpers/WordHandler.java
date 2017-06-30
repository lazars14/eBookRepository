package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class WordHandler extends DocumentHandler {

	public Document getDocument(File file) {
		InputStream is;
		Document doc = new Document();

		try {
			is = new FileInputStream(file);
			// pomocu WordExtractor objekta izvuci tekst
			WordExtractor we = new WordExtractor(is);
			String text = we.getText();
			doc.add(new TextField("text", text, Store.NO));

			// pomocu SummaryInformation objekta izvuci ostale metapodatke
			SummaryInformation si = we.getSummaryInformation();
			String title = si.getTitle();
			doc.add(new TextField("title", title, Store.YES));

			String keywords = si.getKeywords();
			String[] splittedKeywords = keywords.split(" ");
			for (String keyword : splittedKeywords) {
				doc.add(new TextField("keyword", keyword, Store.YES));
			}
			doc.add(new StringField("filename", file.getCanonicalPath(),
					Store.YES));

		} catch (FileNotFoundException e1) {
			System.out.println("Dokument ne postoji");
		} catch (Exception e) {
			System.out.println("Problem pri parsiranju doc fajla");
		}

		return doc;
	}

	@Override
	public String getText(File file) {
		try {
			WordExtractor we = new WordExtractor(new FileInputStream(file));
			String text = we.getText();
			return text;
		} catch (FileNotFoundException e1) {
			System.out.println("Dokument ne postoji");
		} catch (Exception e) {
			System.out.println("Problem pri parsiranju doc fajla");
		}
		return null;
	}

}
