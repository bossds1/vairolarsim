package com.challenge.category;

public interface Category {
	
	void processLine(String line);
	
	String getResult();
	
	default String getName() {
		String name = this.getClass().getSimpleName();
		return name.substring(0, name.indexOf("Category")).toUpperCase();
		
	}
	
	default boolean isEmpty() {
		String result = getResult();
		return result == null || result.trim().equals("");
	}
	
}
