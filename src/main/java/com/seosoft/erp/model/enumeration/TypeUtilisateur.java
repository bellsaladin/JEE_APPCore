package com.seosoft.erp.model.enumeration;

public enum TypeUtilisateur {
	Utilisateur("Utilisateur"), Admin("Admin"), Invité("Invité");

	private final String key;

	TypeUtilisateur(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return this.name();
	}
	
	public TypeUtilisateur[] getDataList() {
        return TypeUtilisateur.values();
    }
}
