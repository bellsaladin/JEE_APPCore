package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Conjoint extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -3220258044693856597L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le nom du conjoint
	 */
	@Length(max = 50)
	private String nom;

	/**
	 * Le prénom du conjoint
	 */
	@Length(max = 50)
	private String prenom;

	/**
	 * Le numéro de la carte d'identité nationale
	 */
	@Length(max = 10)
	private String cin;

	/**
	 * Le numéro du telephone mobile
	 */
	@Length(max = 15)
	private String gsm;

	/**
	 * Le numéro du deuxième telephone (fix)
	 */
	@Length(max = 15)
	private String tel;

	/**
	 * L'email du conjoint
	 */
	@Length(max = 50)
	@Email
	private String email;

	/**
	 * La profession du conjoint
	 */
	@Length(max = 100)
	private String profession;

	/**
	 * L'adresse du conjoint
	 */
	@Length(max = 250)
	private String adresse;

	/**
	 * La ville de résidence
	 */
	@Length(max = 30)
	private String ville;

	/**
	 * Le code postal
	 */
	@Length(max = 6)
	private String codePostal;

	/**
	 * Le lien de parenté avec l'élève
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lienParente_id")
	private LienParente lienParente;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return prenom + " " + nom;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNomPrenom() {
		return nom + " " + prenom;
	}

	public String getPrenomNom() {
		return prenom + " " + nom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getGsm() {
		return gsm;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProfession() {
		return profession;
	}

	public LienParente getLienParente() {
		return lienParente;
	}

	public void setLienParente(LienParente lienParente) {
		this.lienParente = lienParente;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel() {
		return tel;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
