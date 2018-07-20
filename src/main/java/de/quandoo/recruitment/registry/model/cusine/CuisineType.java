package de.quandoo.recruitment.registry.model.cusine;

public enum CuisineType {

	FRENCH("French"),
	ITALIAN("Italian"),
	GERMAN("German");

	private String cuisineName;

	CuisineType(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	public String getCusineName(){
		return cuisineName;
	}
}
