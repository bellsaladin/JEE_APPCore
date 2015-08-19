package com.seosoft.erp.model.enumeration;

public enum TypeControle {
	CONTROLE("cst.controleType.controle"), NORMALISE(
			"cst.controleType.normalise");

	private final String key;

	TypeControle(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return this.name();
	}
}
