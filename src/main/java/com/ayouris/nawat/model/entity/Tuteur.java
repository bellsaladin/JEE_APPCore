package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Tuteur extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 5986838820165232231L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le nom du tuteur
	 */
	@Length(max = 50)
	@NotNull
	private String nom;

	/**
	 * Le prénom du tuteur
	 */
	@Length(max = 50)
	@NotNull
	private String prenom;

	/**
	 * Le cin du tuteur
	 */
	@Length(max = 10)
	private String cin;

	/**
	 * La prefession du tuteur
	 */
	@Length(max = 100)
	private String profession;

	/**
	 * Le numéro gsm du tuteur
	 */
	@Length(max = 15)
	private String gsm;

	/**
	 * Le numéro de telephone fixe du tuteur
	 */
	@Length(max = 15)
	private String tel;

	/**
	 * Le numéro de telephone du travail
	 */
	@Length(max = 15)
	private String telTravail;

	/**
	 * Le fax du tuteur
	 */
	@Length(max = 15)
	private String fax;

	/**
	 * L'email du tuteur
	 */
	@Length(max = 50)
	@Email
	private String email;

	/**
	 * L'adresse du tuteur
	 */
	private String adresse;

	/**
	 * Le code postal du tuteur
	 */
	@Length(max = 6)
	private String codePostal;

	/**
	 * La ville de résidence du tuteur
	 */
	@Length(max = 30)
	private String ville;

	/**
	 * TODO
	 */
	@Lob
	private String adresseMap;

	/**
	 * Le lien de parenté avec la famille
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	@JoinColumn(name = "lienParente_id")
	private LienParente lienParente;

	/**
	 * La famille du tuteur
	 */
	@OneToOne(mappedBy = "tuteur", fetch = FetchType.LAZY)
	private Famille famille;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return prenom + " " + nom;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
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

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelTravail(String telTravail) {
		this.telTravail = telTravail;
	}

	public String getTelTravail() {
		return telTravail;
	}

	public void setAdresseMap(String adresseMap) {
		this.adresseMap = adresseMap;
	}

	public String getAdresseMap() {
		return adresseMap;
	}

	public LienParente getLienParente() {
		return lienParente;
	}

	public void setLienParente(LienParente lienParente) {
		this.lienParente = lienParente;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public Famille getFamille() {
		return famille;
	}

}
