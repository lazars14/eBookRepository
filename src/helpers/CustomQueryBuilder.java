package helpers;

import java.util.List;

public class CustomQueryBuilder {
	
	private String query;
	private int counter;
	private boolean first;
	
	public CustomQueryBuilder(){
		this.query = "";
		this.counter = 0;
		this.first = true;
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public String buildQuery(List<SearchField> fields){
		resetSearch();
		
		if(fields.size() > 1){
			for(SearchField sf : fields){
				if(!sf.isSearch()){
					continue;
				}
				
				doField(sf);
				
				/*switch(sf.getName()){
				case "author":
					doField(sf);
					break;
				case "keyword":
					
					break;
				case "title":
					
					break;
				case "content":

					break;
				case "language":

					break;
				}*/
			}
		}
		
		return query;
	}
	
	public void doField(SearchField field){
		if(field.getName().equals("author") || field.getName().equals("keyword")){
			doMultiple(field);
		} else{
			doSingle(field);
		}
	}
	
	public boolean checkIfFirst(int counter){
		if(counter == 0){
			return true;
		}
		
		return false;
	}
	
	public void resetSearch(){
		query = "";
		counter = 0;
		first = true;
	}
	
	public void doSingle(SearchField field){
		if(checkIfFirst(counter)){
			query += field.getName() + ":" + field.getValue();
			counter++;
		} else{
			query += " and " + field.getName() + ":" + field.getValue();
		}	
	}
	
	public void fillOneMultiple(SearchField field, int index){
		if(checkIfFirst(counter)){
			query += field.getName() + ":" + field.getValues()[index];
			counter++;
		} else{
			query += " and " + field.getName() + ":" + field.getValues()[index];
		}
	}
	
	public void doMultiple(SearchField field){
		if(field.getValues().length == 1){
			fillOneMultiple(field, 0);
		} else{
			
			if(checkIfFirst(counter)){
				query += "(";
			} else{
				query += " and (";
			}
			
			for(int i = 0; i < field.getValues().length; i++){
				fillOneMultiple(field, i);
				if(i != field.getValues().length - 1){
					query += " or";
				}
			}
			
			query += ")";
		}
	}
}
