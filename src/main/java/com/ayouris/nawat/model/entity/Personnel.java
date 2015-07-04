package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Personnel extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -8906376852846029244L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le numéro CIN de l'employé
	 */
	@Length(max = 10)
	private String cin;

	/**
	 * Le nom de l'employé en langue principale
	 */
	@Length(max = 50)
	@NotNull
	private String nom;

	/**
	 * Le nom de l'employé en deuxième langue
	 */
	@Length(max = 50)
	private String nomLng2;

	/**
	 * Le prénom de l'employé en langue principale
	 */
	@Length(max = 50)
	@NotNull
	private String prenom;

	/**
	 * Le prénom de l'employé en deuxième langue
	 */
	@Length(max = 50)
	private String prenomLng2;

	/**
	 * Le sexe de l'employé
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sexe_id")
	@NotNull
	private Sexe sexe;

	/**
	 * La date de naissance de l'employé
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateNaissance;

	/**
	 * La nationalité de l'employé
	 */
	@Length(max = 30)
	private String nationalite;

	/**
	 * L'adresse de l'employé
	 */
	private String adresse;

	/**
	 * Le code postal de l'employé
	 */
	@Length(max = 6)
	private String codePostal;

	/**
	 * La ville de résidence de l'employé
	 */
	@Length(max = 100)
	private String ville;

	/**
	 * Le numéro de telephone de l'employé
	 */
	@Length(max = 15)
	private String tel;

	/**
	 * Le numéro gsm de l'employé
	 */
	@Length(max = 15)
	private String gsm;

	/**
	 * L'email de l'employé
	 */
	@Length(max = 50)
	@Email
	private String email;

	/**
	 * La date d'embauche de l'employé
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEmbauche;

	/**
	 * Le diplome qu'a le personnel
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diplome_id")
	private Diplome diplome;

	/**
	 * La fonction de l'employé au sein de l'établissement
	 */
	@ManyToOne
	@JoinColumn(name = "fonction_id")
	private Fonction fonction;

	/**
	 * Flag indiquant si l'employé occupe le rôle d'enseignant
	 */
	private Boolean enseignant;

	/**
	 * Flag indiquant si l'employé n'a pas le statut permanent
	 */
	private Boolean nonPermanent;

	/**
	 * Le numero de somme de l'employé
	 */
	@Length(max = 10)
	private String numeroSomme;

	/**
	 * TODO : nom d'école actuelle ou précedente ???
	 */
	@Length(max = 50)
	private String nomEcole;

	/**
	 * La catègorie de personnel à laquelle appartient cet employé
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 20)
	private PersonnelCategorie personnelCategorie;

	/**
	 * Les observations pour cet employé
	 */
	private String observations;

	/**
	 * Le lieu de naissance de l'employé
	 */
	@Length(max = 50)
	private String lieuNaissance;

	/**
	 * Le niveau enseigné par cet employé
	 */
	@ManyToOne
	@JoinColumn(name = "niveauenseigne_id")
	private Niveau niveauEnseigne;

	/**
	 * L'unité d'enseignement à laquelle appartient l'employé
	 */
	@ManyToOne
	@JoinColumn(name = "uniteenseigne_id")
	private Unite uniteEnseigne;

	/**
	 * Le numéro de caisse nationale de sécurité sociale
	 */
	@Length(max = 10)
	private String numeroCnss;

	/**
	 * TODO Flag indiquant si l'employé est dèja parti
	 */
	@NotNull
	private Boolean depart;

	/**
	 * La date de départ de l'employé
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateDepart;

	/**
	 * Le permis dont dispose l'employé
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PersonnelPermis personnelPermis;

	/**
	 * Les temps occupés de cet employé
	 */
	@OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TempsOccupe> tempsOccupeList = new ArrayList<TempsOccupe>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum PersonnelCategorie {
		ADMINISTRATION("cst.administration"), SERVICE("cst.service"), ENSEIGNANT("cst.enseignant");

		private final String key;

		PersonnelCategorie(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	public enum PersonnelPermis {
		CATEGORIE_A("cst.permis.categorieA"), CATEGORIE_B("cst.permis.categorieB"), CATEGORIE_C("cst.permis.categorieC"), CATEGORIE_D(
				"cst.permis.categorieD"), CATEGORIE_E("cst.permis.categorieE");

		private final String key;

		PersonnelPermis(String key) {
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
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {

		return prenom + " " + nom;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenomLng2() {
		return prenomLng2;
	}

	public void setPrenomLng2(String prenomLng2) {
		this.prenomLng2 = prenomLng2;
	}

	public String getNomPrenom() {
		return nom + " " + prenom;
	}

	public String getPrenomNom() {
		return prenom + " " + nom;
	}

	public String getNomPrenomLng2() {
		return nomLng2 + " " + prenomLng2;
	}

	public String getPrenomNomLng2() {
		return prenomLng2 + " " + nomLng2;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getGsm() {
		return gsm;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public void setDiplome(Diplome diplome) {
		this.diplome = diplome;
	}

	public Diplome getDiplome() {
		return diplome;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public Fonction getFonction() {
		return fonction;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setPersonnelCategorie(PersonnelCategorie personnelCategorie) {
		this.personnelCategorie = personnelCategorie;
	}

	public PersonnelCategorie getPersonnelCategorie() {
		return personnelCategorie;
	}

	public void setEnseignant(Boolean enseignant) {
		this.enseignant = enseignant;
	}

	public Boolean getEnseignant() {
		return enseignant;
	}

	public void setTempsOccupeList(List<TempsOccupe> tempsOccupeList) {
		this.tempsOccupeList = tempsOccupeList;
	}

	public List<TempsOccupe> getTempsOccupeList() {
		return tempsOccupeList;
	}

	public void setNumeroSomme(String numeroSomme) {
		this.numeroSomme = numeroSomme;
	}

	public String getNumeroSomme() {
		return numeroSomme;
	}

	public void setNomEcole(String nomEcole) {
		this.nomEcole = nomEcole;
	}

	public String getNomEcole() {
		return nomEcole;
	}

	public void setNonPermanent(Boolean nonPermanent) {
		this.nonPermanent = nonPermanent;
	}

	public Boolean getNonPermanent() {
		return nonPermanent;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNumeroCnss(String numeroCnss) {
		this.numeroCnss = numeroCnss;
	}

	public String getNumeroCnss() {
		return numeroCnss;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setDepart(Boolean depart) {
		this.depart = depart;
	}

	public Boolean getDepart() {
		return depart;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setNiveauEnseigne(Niveau niveauEnseigne) {
		this.niveauEnseigne = niveauEnseigne;
	}

	public Niveau getNiveauEnseigne() {
		return niveauEnseigne;
	}

	public void setUniteEnseigne(Unite uniteEnseigne) {
		this.uniteEnseigne = uniteEnseigne;
	}

	public Unite getUniteEnseigne() {
		return uniteEnseigne;
	}

	public void setPersonnelPermis(PersonnelPermis personnelPermis) {
		this.personnelPermis = personnelPermis;
	}

	public PersonnelPermis getPersonnelPermis() {
		return personnelPermis;
	}

}
