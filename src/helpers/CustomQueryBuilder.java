package helpers;

import java.util.List;

public class CustomQueryBuilder {
	
	public static String buildQuery(List<SearchField> fields){
		String query = "";
		
		if(fields.size() > 1){
			for(SearchField sf : fields){
				switch(sf.getName()){
				case "author":
					
					break;
				case "keyword":
					
					break;
				case "title":
		
					break;
				case "content":
		
					break;
				case "language":
		
					break;
				}
			}
		}
		
		return query;
	}
	
}
