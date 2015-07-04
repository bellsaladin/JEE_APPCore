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
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Eleve extends BaseEcole implements Serializable, Cloneable {
	private static final long serialVersionUID = 739680977161465446L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le numéro national de l'élève
	 */
	@Length(max = 15)
	private String numeroNational;

	/**
	 * Le nom de l'élève en langue principale
	 */
	@Length(max = 100)
	@NotNull
	private String nom;

	/**
	 * Le nom d'élève en deuxième langue
	 */
	@Length(max = 100)
	private String nomLng2;

	/**
	 * Le prenom de l'élève en langue principale
	 */
	@Length(max = 100)
	@NotNull
	private String prenom;

	/**
	 * Le prenom de l'élève en deuxième langue
	 */
	@Length(max = 100)
	private String prenomLng2;

	/**
	 * Le sexe de l'élève
	 */
	@ManyToOne
	@JoinColumn(name = "sexe_id")
	@NotNull
	private Sexe sexe;

	/**
	 * La date de naissance de l'élève
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateNaissance;

	/**
	 * Le lieu de naissance de l'élève en langue principale
	 */
	@Length(max = 50)
	private String lieuNaissance;

	/**
	 * Le lieu de naissance de l'élève en deuxième langue
	 */
	@Length(max = 50)
	private String lieuNaissanceLng2;

	/**
	 * La nationalité de l'élève
	 */
	@Length(max = 30)
	private String nationalite;

	/**
	 * Le numéro de telephone de l'élève
	 */
	@Length(max = 20)
	private String tel;

	/**
	 * L'email de l'élève
	 */
	@Length(max = 50)
	private String email;

	/**
	 * La date de de première entrée
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate datePremiereEntree;

	/**
	 * La scolarité de l'élève
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private Scolarite scolarisation;

	/**
	 * Le niveau scolaire aquis
	 */
	@Length(max = 30)
	private String niveauScolaireAquis;

	/**
	 * L'école d'origine
	 */
	@Length(max = 150)
	private String ecoleOrigine;

	/**
	 * Le numéro d'inscription à l'école d'origine
	 */
	@Length(max = 30)
	private String numeroInscriptionEcoleOrigine;

	/**
	 * Le nombre d'année reboublées
	 */
	private Integer nbreAnneeDouble;

	/**
	 * Flag indiquant si l'élève est handicapé
	 */
	private Boolean handicape;

	/**
	 * Le type d'handicapement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeHandicape_id")
	private TypeHandicape typeHandicape;

	/**
	 * Le groupe sangin de l'élève
	 */
	@Length(max = 4)
	private String groupeSangin;

	/**
	 * Les allérgies desquels l'élève est atteint
	 */
	@Lob
	private String allergies;

	/**
	 * Flag indiquant si l'élève est vacciné
	 */
	private Boolean vacccination;

	/**
	 * Les observations liées à cet élève
	 */
	@Lob
	private String observations;

	/**
	 * La famile de l'élève
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "famille_id")
	private Famille famille;

	/**
	 * Flag indiquant si l'élève est en cessation
	 */
	private Boolean cessation;

	/**
	 * Flag indiquant si l'élève est actif
	 */
	private Boolean actif;

	/**
	 * La date de cessation
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateCessation;

	/**
	 * Le type de cessation
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private CessationType cessationType;

	/**
	 * La décission du conseil
	 */
	@Length(max = 150)
	private String decisionConseil;

	/**
	 * La remarque de cessation
	 */
	@Lob
	private String remarqueCessation;

	/**
	 * Le nom du pédiatre de l'élève
	 */
	@Length(max = 100)
	private String pediatre;

	/**
	 * Le numéro de telephone du pédiatre de l'élève
	 */
	@Length(max = 20)
	private String telPediatre;

	/**
	 * Les particularités de l'élève
	 */
	@Lob
	private String particularites;

	/**
	 * Les apreciations par rapport à l'élève
	 */
	@Lob
	private String appreciations;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Eleve() {
		super();
		scolarisation = Scolarite.NONSCOLARISE;
		// nationalite = SeamResourceBundle.getBundle().getString(
		// "cst.nationalite");
		cessation = false;
	}

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

	public enum Scolarite {
		NONSCOLARISE("cst.nonScolarise"), PRESCOLAIRE("cst.prescolaire"), SCOLARISEPRIVE("cst.scolarisePrive"), SCOLARISEPUBLIC(
				"cst.scolarisePublic");

		private final String key;

		Scolarite(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	public enum CessationType {
		DEPART("cst.eleve.depart"), EXCLU("cst.eleve.exclu"), DECROCHE("cst.eleve.decroche"), NON_ARRIVE("cst.eleve.nonArrive");

		private final String key;

		CessationType(String key) {
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
	// |||||||||||||||||||||||||||||||||||||||||||||||| CLONE |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public Object clone() {
		Eleve eleve = null;
		try {
			eleve = (Eleve) super.clone();
		} catch (CloneNotSupportedException cnse) {
			cnse.printStackTrace(System.err);
		}
		return eleve;
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

	// public String getNomPrenom() {
	// return nom + " " + prenom;
	// }

	public String getPrenomNom() {
		return prenom + " " + nom;
	}

	public String getNomPrenomLng2() {
		String nomLng2Temps = nomLng2 == null ? "" : nomLng2;
		String prenomLng2Temps = prenomLng2 == null ? "" : prenomLng2;

		return nomLng2Temps + " " + prenomLng2Temps;
	}

	public String getPrenomNomLng2() {
		String prenomLng2Temps = prenomLng2 == null ? "" : prenomLng2;
		String nomLng2Temps = nomLng2 == null ? "" : nomLng2;

		return prenomLng2Temps + " " + nomLng2Temps;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	public String getEcoleOrigine() {
		return ecoleOrigine;
	}

	public void setEcoleOrigine(String ecoleOrigine) {
		this.ecoleOrigine = ecoleOrigine;
	}

	public String getGroupeSangin() {
		return groupeSangin;
	}

	public void setGroupeSangin(String groupeSangin) {
		this.groupeSangin = groupeSangin;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public Famille getFamille() {
		return famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public void setVacccination(Boolean vacccination) {
		this.vacccination = vacccination;
	}

	public Boolean getVacccination() {
		return vacccination;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissanceLng2(String lieuNaissanceLng2) {
		this.lieuNaissanceLng2 = lieuNaissanceLng2;
	}

	public String getLieuNaissanceLng2() {
		return lieuNaissanceLng2;
	}

	public void setNbreAnneeDouble(Integer nbreAnneeDouble) {
		this.nbreAnneeDouble = nbreAnneeDouble;
	}

	public Integer getNbreAnneeDouble() {
		return nbreAnneeDouble;
	}

	public void setHandicape(Boolean handicape) {
		this.handicape = handicape;
	}

	public Boolean getHandicape() {
		return handicape;
	}

	public void setTypeHandicape(TypeHandicape typeHandicape) {
		this.typeHandicape = typeHandicape;
	}

	public TypeHandicape getTypeHandicape() {
		return typeHandicape;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setScolarisation(Scolarite scolarisation) {
		this.scolarisation = scolarisation;
	}

	public Scolarite getScolarisation() {
		return scolarisation;
	}

	public void setNiveauScolaireAquis(String niveauScolaireAquis) {
		this.niveauScolaireAquis = niveauScolaireAquis;
	}

	public String getNiveauScolaireAquis() {
		return niveauScolaireAquis;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setCessation(Boolean cessation) {
		this.cessation = cessation;
	}

	public Boolean getCessation() {
		return cessation;
	}

	public void setDateCessation(LocalDate dateCessation) {
		this.dateCessation = dateCessation;
	}

	public LocalDate getDateCessation() {
		return dateCessation;
	}

	public void setDatePremiereEntree(LocalDate datePremiereEntree) {
		this.datePremiereEntree = datePremiereEntree;
	}

	public LocalDate getDatePremiereEntree() {
		return datePremiereEntree;
	}

	public void setNumeroInscriptionEcoleOrigine(String numeroInscriptionEcoleOrigine) {
		this.numeroInscriptionEcoleOrigine = numeroInscriptionEcoleOrigine;
	}

	public String getNumeroInscriptionEcoleOrigine() {
		return numeroInscriptionEcoleOrigine;
	}

	public void setNumeroNational(String numeroNational) {
		this.numeroNational = numeroNational;
	}

	public String getNumeroNational() {
		return numeroNational;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setCessationType(CessationType cessationType) {
		this.cessationType = cessationType;
	}

	public CessationType getCessationType() {
		return cessationType;
	}

	public void setDecisionConseil(String decisionConseil) {
		this.decisionConseil = decisionConseil;
	}

	public String getDecisionConseil() {
		return decisionConseil;
	}

	public void setRemarqueCessation(String remarqueCessation) {
		this.remarqueCessation = remarqueCessation;
	}

	public String getRemarqueCessation() {
		return remarqueCessation;
	}

	public void setPediatre(String pediatre) {
		this.pediatre = pediatre;
	}

	public String getPediatre() {
		return pediatre;
	}

	public void setTelPediatre(String telPediatre) {
		this.telPediatre = telPediatre;
	}

	public String getTelPediatre() {
		return telPediatre;
	}

	public void setParticularites(String particularites) {
		this.particularites = particularites;
	}

	public String getParticularites() {
		return particularites;
	}

	public void setAppreciations(String appreciations) {
		this.appreciations = appreciations;
	}

	public String getAppreciations() {
		return appreciations;
	}

}
