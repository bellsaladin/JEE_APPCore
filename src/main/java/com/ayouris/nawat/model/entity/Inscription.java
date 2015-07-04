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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Inscription extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 1927412981740941254L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le numéro d'inscription
	 */
	@Length(max = 15)
	private String numeroInscription;

	/**
	 * La date d'inscription
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateInscription;

	/**
	 * L'état de l'inscription
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 15)
	private InscriptionEtat inscriptionEtat;

	/**
	 * Le résultat de l'inscription
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private InscriptionResultat inscriptionResultat;

	/**
	 * L'affectation niveau de l'inscription
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "affectationniveau_id")
	@NotNull
	private AffectationNiveau affectationNiveau;

	/**
	 * La classe concernée par l'inscription
	 */
	@ManyToOne
	@JoinColumn(name = "classe_id")
	private Classe classe;

	/**
	 * L'année scolaire de l'inscription
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	/**
	 * L'élève concernée par l'inscription
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eleve_id")
	@NotNull
	private Eleve eleve;

	/**
	 * Le mois scolaire de l'inscription
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "moisScolaire_id")
	@NotNull
	private MoisScolaire moisScolaire;

	/**
	 * La préinscription
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "preinscription_id")
	private Preinscription preinscription;

	/**
	 * la liste des affectation-service
	 */
	@OneToMany(mappedBy = "inscription", cascade = { CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<AffectationService> affectationServices = new ArrayList<AffectationService>();

	/**
	 * La date d'entrée
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEntree;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Inscription() {
		super();
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

	public enum InscriptionEtat {
		VALIDE("cst.inscriptionEtat.valide"), CESSATION("cst.inscriptionEtat.cessation");

		private final String key;

		InscriptionEtat(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	public enum InscriptionResultat {
		REUSSI("cst.inscriptionResultat.reussi"), REDOUBLE("cst.inscriptionResultat.redouble");

		private final String key;

		InscriptionResultat(String key) {
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
		return numeroInscription;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setAffectationServices(List<AffectationService> affectationServices) {
		this.affectationServices = affectationServices;
	}

	public List<AffectationService> getAffectationServices() {
		return affectationServices;
	}

	public void setNumeroInscription(String numeroInscription) {
		this.numeroInscription = numeroInscription;
	}

	public String getNumeroInscription() {
		return numeroInscription;
	}

	public void setInscriptionEtat(InscriptionEtat inscriptionEtat) {
		this.inscriptionEtat = inscriptionEtat;
	}

	public InscriptionEtat getInscriptionEtat() {
		return inscriptionEtat;
	}

	public void setInscriptionResultat(InscriptionResultat inscriptionResultat) {
		this.inscriptionResultat = inscriptionResultat;
	}

	public InscriptionResultat getInscriptionResultat() {
		return inscriptionResultat;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setMoisScolaire(MoisScolaire moisScolaire) {
		this.moisScolaire = moisScolaire;
	}

	public MoisScolaire getMoisScolaire() {
		return moisScolaire;
	}

	public void setPreinscription(Preinscription preinscription) {
		this.preinscription = preinscription;
	}

	public Preinscription getPreinscription() {
		return preinscription;
	}

	public void setDateEntree(LocalDate dateEntree) {
		this.dateEntree = dateEntree;
	}

	public LocalDate getDateEntree() {
		return dateEntree;
	}

}
