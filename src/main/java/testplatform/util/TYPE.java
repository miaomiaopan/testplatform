package testplatform.util;

public enum TYPE {
	HEADER("header"), BODY("body");

	private String name;

	private TYPE(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static TYPE getByName(String name){
		if(name.equals("body")){
			return BODY;
		}
		
		if(name.equals("header")){
			return HEADER;
		}
		
		return null;
	}
}
