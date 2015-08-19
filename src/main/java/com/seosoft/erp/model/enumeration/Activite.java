package com.seosoft.erp.model.enumeration;

public enum Activite {

	RESSOURCE("cst.activite.ressource"), EFFICACITE(
			"cst.activite.efficacite");

	private final String key;

	Activite(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return this.name();
	}
}
