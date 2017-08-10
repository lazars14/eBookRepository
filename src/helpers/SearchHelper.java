package helpers;

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
