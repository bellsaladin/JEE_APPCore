package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Preinscription extends BaseEcole implements Serializable {

	private static final long serialVersionUID = 1871304518371519363L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le nom de l'élève
	 */
	@Length(max = 30)
	@NotNull
	private String nomEleve;

	/**
	 * Le prénom de l'élève
	 */
	@Length(max = 30)
	@NotNull
	private String prenomEleve;

	/**
	 * Le sexe de l'élève
	 */
	@ManyToOne
	@JoinColumn(name = "sexe_id")
	private Sexe sexe;

	/**
	 * La date de naissance de l'élève
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateNaissance;

	/**
	 * L'affectation niveau
	 */
	@ManyToOne
	@JoinColumn(name = "affectationniveau_id")
	private AffectationNiveau affectationNiveau;

	/**
	 * La date de préinscription
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate datePreinscription;

	/**
	 * Le nom du tuteur
	 */
	@Length(max = 30)
	private String nomTuteur;

	/**
	 * Le prénom du tuteur
	 */
	@Length(max = 30)
	private String prenomTuteur;

	/**
	 * La profession du tuteur
	 */
	@Length(max = 100)
	private String professionTuteur;

	/**
	 * Le lien de parenté
	 */
	@ManyToOne
	@JoinColumn(name = "lienParente_id")
	private LienParente lienParente;

	/**
	 * L'adresse du tuteur
	 */
	private String adresseTuteur;

	/**
	 * Le code postal
	 */
	@Length(max = 6)
	private String codePostal;

	/**
	 * La ville
	 */
	@Length(max = 30)
	private String ville;

	/**
	 * Le téléphone
	 */
	@Length(max = 15)
	private String tel;

	/**
	 * L'email
	 */
	@Length(max = 50)
	@Email
	private String email;

	/**
	 * TODO Flag indiquant ...
	 */
	private Boolean testAdmission;

	/**
	 * La date du test d'admission
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateTest;

	/**
	 * La note obtenue dans le test d'admission
	 */
	private Float noteTest;

	/**
	 * Le nom du professeur chargé du test d'admission
	 */
	@Length(max = 30)
	private String nomProfesseurTest;

	/**
	 * Le résultat du test d'admission
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 10)
	private ResultatTest resultatTest;

	/**
	 * La date limite d'inscription
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateLimiteInscription;

	/**
	 * L'année scolaire de la préinscription
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	/**
	 * L'élève concernée par cette préinscription
	 */
	@ManyToOne
	@JoinColumn(name = "eleve_id")
	private Eleve eleve;

	/**
	 * Des observations generales
	 */
	@Lob
	private String observations;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Preinscription() {
		super();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum ResultatTest {
		ADMIS("cst.admis"), NONADMIS("cst.nonAdmis"), ATTENTE("cst.attente");

		private final String key;

		ResultatTest(String key) {
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
		return prenomEleve + " " + nomEleve;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public LienParente getLienParente() {
		return lienParente;
	}

	public void setLienParente(LienParente lienParente) {
		this.lienParente = lienParente;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public String getNomEleve() {
		return nomEleve;
	}

	public void setNomEleve(String nomEleve) {
		this.nomEleve = nomEleve;
	}

	public String getPrenomEleve() {
		return prenomEleve;
	}

	public void setPrenomEleve(String prenomEleve) {
		this.prenomEleve = prenomEleve;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNomTuteur() {
		return nomTuteur;
	}

	public void setNomTuteur(String nomTuteur) {
		this.nomTuteur = nomTuteur;
	}

	public String getPrenomTuteur() {
		return prenomTuteur;
	}

	public void setPrenomTuteur(String prenomTuteur) {
		this.prenomTuteur = prenomTuteur;
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

	public void setTestAdmission(Boolean testAdmission) {
		this.testAdmission = testAdmission;
	}

	public Boolean getTestAdmission() {
		return testAdmission;
	}

	public LocalDate getDateTest() {
		return dateTest;
	}

	public void setDateTest(LocalDate dateTest) {
		this.dateTest = dateTest;
	}

	public Float getNoteTest() {
		return noteTest;
	}

	public void setNoteTest(Float noteTest) {
		this.noteTest = noteTest;
	}

	public LocalDate getDateLimiteInscription() {
		return dateLimiteInscription;
	}

	public void setDateLimiteInscription(LocalDate dateLimiteInscription) {
		this.dateLimiteInscription = dateLimiteInscription;
	}

	public void setProfessionTuteur(String professionTuteur) {
		this.professionTuteur = professionTuteur;
	}

	public String getProfessionTuteur() {
		return professionTuteur;
	}

	public void setNomProfesseurTest(String nomProfesseurTest) {
		this.nomProfesseurTest = nomProfesseurTest;
	}

	public String getNomProfesseurTest() {
		return nomProfesseurTest;
	}

	public void setAdresseTuteur(String adresseTuteur) {
		this.adresseTuteur = adresseTuteur;
	}

	public String getAdresseTuteur() {
		return adresseTuteur;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public void setDatePreinscription(LocalDate datePreinscription) {
		this.datePreinscription = datePreinscription;
	}

	public LocalDate getDatePreinscription() {
		return datePreinscription;
	}

	public void setResultatTest(ResultatTest resultatTest) {
		this.resultatTest = resultatTest;
	}

	public ResultatTest getResultatTest() {
		return resultatTest;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Eleve getEleve() {
		return eleve;
	}

}
