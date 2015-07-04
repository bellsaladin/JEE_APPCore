package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

//
// @Entity
public class PlanningControle extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 1392891970740134457L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * L'année scolaire du planning de controle
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	/**
	 * La période scolaire du planning de controle
	 */
	@ManyToOne
	@JoinColumn(name = "periodeScolaire_id")
	private PeriodeScolaire periodeScolaire;

	/**
	 * La session scolaire du planning de controle
	 */
	@ManyToOne
	@JoinColumn(name = "sessionScolaire_id")
	private SessionScolaire sessionScolaire;

	/**
	 * La date de controle
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateControle;

	/**
	 * L'affectation de niveau concernée
	 */
	@ManyToOne
	@JoinColumn(name = "affectationniveau_id")
	@NotNull
	private AffectationNiveau affectationNiveau;

	/**
	 * L'identifiant de la classe concernée
	 */
	@ManyToOne
	@JoinColumn(name = "classe_id")
	private Classe classe;

	/**
	 * L'affectation unité du planning de controle
	 */
	@ManyToOne
	@JoinColumn(name = "affectationUnite_id")
	@NotNull
	private AffectationUnite affectationUnite;

	/**
	 * L'affectation matière du planning de controle
	 */
	@ManyToOne
	@JoinColumn(name = "affectationMatiere_id")
	@NotNull
	private AffectationMatiere affectationMatiere;

	/**
	 * Le contenu inclut dans le controle
	 */
	@Lob
	private String contenuCours;

	/**
	 * L'état du planning de controle
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 15)
	private EtatPlanningControle etatPlanningControle;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum EtatPlanningControle {
		VALIDE("cst.controleEtat.valide"), ANNULE("cst.controleEtat.annuler");

		private final String key;

		EtatPlanningControle(String key) {
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
		// TODO
		return dateControle.toString();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public void setDateControle(LocalDate dateControle) {
		this.dateControle = dateControle;
	}

	public LocalDate getDateControle() {
		return dateControle;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setAffectationUnite(AffectationUnite affectationUnite) {
		this.affectationUnite = affectationUnite;
	}

	public AffectationUnite getAffectationUnite() {
		return affectationUnite;
	}

	public void setAffectationMatiere(AffectationMatiere affectationMatiere) {
		this.affectationMatiere = affectationMatiere;
	}

	public AffectationMatiere getAffectationMatiere() {
		return affectationMatiere;
	}

	public void setEtatPlanningControle(EtatPlanningControle etatPlanningControle) {
		this.etatPlanningControle = etatPlanningControle;
	}

	public EtatPlanningControle getEtatPlanningControle() {
		return etatPlanningControle;
	}

	public void setPeriodeScolaire(PeriodeScolaire periodeScolaire) {
		this.periodeScolaire = periodeScolaire;
	}

	public PeriodeScolaire getPeriodeScolaire() {
		return periodeScolaire;
	}

	public void setSessionScolaire(SessionScolaire sessionScolaire) {
		this.sessionScolaire = sessionScolaire;
	}

	public SessionScolaire getSessionScolaire() {
		return sessionScolaire;
	}

	public void setContenuCours(String contenuCours) {
		this.contenuCours = contenuCours;
	}

	public String getContenuCours() {
		return contenuCours;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

}
