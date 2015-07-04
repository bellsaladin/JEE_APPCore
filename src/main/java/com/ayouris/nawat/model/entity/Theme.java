package com.ayouris.nawat.model.entity;

import com.ayouris.nawat.model.base.BaseParam;

public class Theme extends BaseParam {

	private String libelle;

	private String darkCouleur;

	private String lightCouleur;

	public Theme(String nom, String darkCouleur, String lightCouleur) {
		super();
		this.libelle = nom;
		this.darkCouleur = darkCouleur;
		this.lightCouleur = lightCouleur;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setNom(String nom) {
		this.libelle = nom;
	}

	public String getDarkCouleur() {
		return darkCouleur;
	}

	public void setDarkCouleur(String darkCouleur) {
		this.darkCouleur = darkCouleur;
	}

	public String getLightCouleur() {
		return lightCouleur;
	}

	public void setLightCouleur(String lightCouleur) {
		this.lightCouleur = lightCouleur;
	}

	@Override
	public String getDisplayText() {
		return libelle;
	}
}
