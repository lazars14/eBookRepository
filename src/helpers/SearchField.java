package helpers;

public class SearchField {
	
	private String name;
	private String value;
	private String[] values;
	private boolean search;
	
	public SearchField(String name, String value, boolean search){
		this.name = name;
		this.value = value;
		this.search = search;
	}
	
	public SearchField(String name, String[] values, boolean search){
		this.name = name;
		this.values = values;
		this.search = search;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}
}
