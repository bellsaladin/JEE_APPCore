package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEntity;

@Entity
public class Ecole extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 8884791891749884343L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le nom de l'école en langue principale
	 */
	@Column
	@NotNull
	@Length(max = 100)
	private String nom;

	/**
	 * Le nom de l'école en deuxième langue
	 */
	@Length(max = 100)
	private String nomLng2;

	/**
	 * Le numéro de l'autorisation
	 */
	@Length(max = 30)
	private String numeroAutorisation;

	/**
	 * La date de l'autorisation
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateAutorisation;

	/**
	 * La ligne 1 de l'adresse en première langue
	 */
	@Length(max = 50)
	private String adresseLigne1;

	/**
	 * La ligne 2 de l'adresse en première langue
	 */
	@Length(max = 50)
	private String adresseLigne2;

	/**
	 * La ligne 1 de l'adresse en deuxième langue
	 */
	@Length(max = 50)
	private String adresseLigne1Lng2;

	/**
	 * La ligne 2 de l'adresse en deuxième langue
	 */
	@Length(max = 50)
	private String adresseLigne2Lng2;

	/**
	 * Le code postal
	 */
	@Length(max = 6)
	private String codePostal;

	/**
	 * La ville en première langue
	 */
	@Length(max = 50)
	private String ville;

	/**
	 * La ville en deuxième langue
	 */
	@Length(max = 50)
	private String villeLng2;

	/**
	 * Le numéro de téléphone principal
	 */
	@Length(max = 15)
	private String tel1;

	/**
	 * Le numéro de téléphone secondaire
	 */
	@Length(max = 15)
	private String tel2;

	/**
	 * Le fax de l'école
	 */
	@Length(max = 15)
	private String fax;

	/**
	 * L'email de l'école
	 */
	@Length(max = 50)
	@Email
	private String email;

	/**
	 * L'adresse web de l'école
	 */
	@Length(max = 50)
	private String adresseWeb;

	/**
	 * TODO
	 */
	@Lob
	private String adresseMap;

	/**
	 * La langue de l'interface de l'école
	 */
	@Length(max = 2)
	@NotNull
	private String langueInterface;

	/**
	 * Les observations concernant l'école
	 */
	@Lob
	private String observations;

	/**
	 * Le nom de l'acadèmie, en langue principale, à laquelle appartient l'école
	 */
	@Length(max = 100)
	private String academie;

	/**
	 * Le nom de l'acadèmie, en deuxième langue, à laquelle appartient l'école
	 */
	@Length(max = 100)
	private String academieLng2;

	/**
	 * Le nom de la délégation, en langue principale, à laquelle appartient l'école
	 */
	@Length(max = 100)
	private String delegation;

	/**
	 * Le nom de délégation, en deuxième langue, à laquelle appartient l'école
	 */
	@Length(max = 100)
	private String delegationLng2;

	/**
	 * Le nom de la commune, en langue principale, à laquelle appartient l'école
	 */
	@Length(max = 200)
	private String commune;

	/**
	 * Le nom de la commune, en deuxième langue, à laquelle appartient l'école
	 */
	@Length(max = 200)
	private String communeLng2;

	/**
	 * Le titre du signataire
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Genre titreSignataire;

	/**
	 * Le nom complet du signataire en langue principale
	 */
	@NotNull
	@Length(max = 50)
	private String signataire;

	/**
	 * Le nom complet du signataire en deuxième langue
	 */
	@NotNull
	@Length(max = 50)
	private String signataireLng2;

	/**
	 * La clé de licenc de l'application nawat affecté à l'école
	 */
	@Length(max = 32)
	private String licence;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires ||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/*
	 * Transitoire permettant de verifié si l'école dispose de la license de l'application
	 */
	@Transient
	private boolean licensed;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum EtatCaisse {
		OUVERTE("cst.caisseEtat.ouverte"), CLOTURE("cst.caisseEtat.cloture");

		private final String key;

		EtatCaisse(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	public enum Genre {
		MONSIEUR("cst.ecole.monsieur"), MADAME("cst.ecole.madame");

		private final String key;

		Genre(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Ecole() {
		super();
	}

	public Ecole(String id) {
		super(id);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return nom;
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

	public String getNomLng2() {
		return nomLng2;
	}

	public void setNomLng2(String nomLng2) {
		this.nomLng2 = nomLng2;
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

	public String getVilleLng2() {
		return villeLng2;
	}

	public void setVilleLng2(String villeLng2) {
		this.villeLng2 = villeLng2;
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

	public String getAdresseWeb() {
		return adresseWeb;
	}

	public void setAdresseWeb(String adresseWeb) {
		this.adresseWeb = adresseWeb;
	}

	public String getAdresseMap() {
		return adresseMap;
	}

	public void setAdresseMap(String adresseMap) {
		this.adresseMap = adresseMap;
	}

	public String getLangueInterface() {
		return langueInterface;
	}

	public void setLangueInterface(String langueInterface) {
		this.langueInterface = langueInterface;
	}

	public void setAdresseLigne1(String adresseLigne1) {
		this.adresseLigne1 = adresseLigne1;
	}

	public String getAdresseLigne1() {
		return adresseLigne1;
	}

	public void setAdresseLigne2(String adresseLigne2) {
		this.adresseLigne2 = adresseLigne2;
	}

	public String getAdresseLigne2() {
		return adresseLigne2;
	}

	public void setAdresseLigne1Lng2(String adresseLigne1Lng2) {
		this.adresseLigne1Lng2 = adresseLigne1Lng2;
	}

	public String getAdresseLigne1Lng2() {
		return adresseLigne1Lng2;
	}

	public void setAdresseLigne2Lng2(String adresseLigne2Lng2) {
		this.adresseLigne2Lng2 = adresseLigne2Lng2;
	}

	public String getAdresseLigne2Lng2() {
		return adresseLigne2Lng2;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel2() {
		return tel2;
	}

	public void setAcademie(String academie) {
		this.academie = academie;
	}

	public String getAcademie() {
		return academie;
	}

	public void setAcademieLng2(String academieLng2) {
		this.academieLng2 = academieLng2;
	}

	public String getAcademieLng2() {
		return academieLng2;
	}

	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}

	public String getDelegation() {
		return delegation;
	}

	public void setDelegationLng2(String delegationLng2) {
		this.delegationLng2 = delegationLng2;
	}

	public String getDelegationLng2() {
		return delegationLng2;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommuneLng2(String communeLng2) {
		this.communeLng2 = communeLng2;
	}

	public String getCommuneLng2() {
		return communeLng2;
	}

	public void setNumeroAutorisation(String numeroAutorisation) {
		this.numeroAutorisation = numeroAutorisation;
	}

	public String getNumeroAutorisation() {
		return numeroAutorisation;
	}

	public void setDateAutorisation(LocalDate dateAutorisation) {
		this.dateAutorisation = dateAutorisation;
	}

	public LocalDate getDateAutorisation() {
		return dateAutorisation;
	}

	public void setSignataire(String signataire) {
		this.signataire = signataire;
	}

	public String getSignataire() {
		return signataire;
	}

	public void setSignataireLng2(String signataireLng2) {
		this.signataireLng2 = signataireLng2;
	}

	public String getSignataireLng2() {
		return signataireLng2;
	}

	public void setTitreSignataire(Genre titreSignataire) {
		this.titreSignataire = titreSignataire;
	}

	public Genre getTitreSignataire() {
		return titreSignataire;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicensed(boolean licensed) {
		this.licensed = licensed;
	}

	public boolean isLicensed() {
		return licensed;
	}

}
