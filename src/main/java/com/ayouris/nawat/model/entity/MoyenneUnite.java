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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;
import com.ayouris.nawat.model.enumeration.TypeControle;

@Entity
public class MoyenneUnite extends BaseEcole implements Serializable {

	private static final long serialVersionUID = 7555362800657657486L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La moyenne obtenue dans l'unité
	 */
	@NotNull
	private Float moyenne;

	/**
	 * Le classement de l'élève dans l'unité
	 */
	private Integer classement;

	/**
	 * La remarque concernant l'élève pour la moyenne obtenue
	 */
	@Length(max = 200)
	private String remarque;

	/**
	 * Le bulletin auquel appartient cette moyenne unité
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bulletin_id")
	@NotNull
	private Bulletin bulletin;

	/**
	 * La période scolaire où cette moyenne a été obtenue
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periodeScolaire_id")
	@NotNull
	private PeriodeScolaire periodeScolaire;

	/**
	 * L'affectation-unite concernée
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "affectationUnite_id")
	@NotNull
	private AffectationUnite affectationUnite;

	/**
	 * L'inscription pour laquelle cette moyenne est donnée
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inscription_id")
	@NotNull
	private Inscription inscription;

	/**
	 * Le type du contrôle passé
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 15)
	private TypeControle typeControle;

	/**
	 * La liste des moyennes obtenus dans les matières de cette unité
	 */
	@OneToMany(mappedBy = "moyenneUnite", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<MoyenneMatiere> moyenneMatieres = new ArrayList<MoyenneMatiere>();

	/**
	 * L'année scolaire concernée par la moyenne de l'unité
	 */
	@ManyToOne
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires ||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * TODO
	 */
	@Transient
	private int coefficient;

	/**
	 * Transitoire permettant de stocker la somme des notes
	 */
	@Transient
	private float sommeNotes;

	/**
	 * Transitoire permettant de stocker la somme des coefficients
	 */
	@Transient
	private int sommeCoefficient;

	/**
	 * TODO
	 */
	@Transient
	private boolean activiteUnite;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return String.valueOf(moyenne);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public void setMoyenne(Float moyenne) {
		this.moyenne = moyenne;
	}

	public Float getMoyenne() {
		return moyenne;
	}

	public void setClassement(Integer classement) {
		this.classement = classement;
	}

	public Integer getClassement() {
		return classement;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setPeriodeScolaire(PeriodeScolaire periodeScolaire) {
		this.periodeScolaire = periodeScolaire;
	}

	public PeriodeScolaire getPeriodeScolaire() {
		return periodeScolaire;
	}

	public void setAffectationUnite(AffectationUnite affectationUnite) {
		this.affectationUnite = affectationUnite;
	}

	public AffectationUnite getAffectationUnite() {
		return affectationUnite;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setSommeNotes(float sommeNotes) {
		this.sommeNotes = sommeNotes;
	}

	public float getSommeNotes() {
		return sommeNotes;
	}

	public void setSommeCoefficient(int sommeCoefficient) {
		this.sommeCoefficient = sommeCoefficient;
	}

	public int getSommeCoefficient() {
		return sommeCoefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setMoyenneMatieres(List<MoyenneMatiere> moyenneMatieres) {
		this.moyenneMatieres = moyenneMatieres;
	}

	public List<MoyenneMatiere> getMoyenneMatieres() {
		return moyenneMatieres;
	}

	public void setActiviteUnite(boolean activiteUnite) {
		this.activiteUnite = activiteUnite;
	}

	public boolean isActiviteUnite() {
		return activiteUnite;
	}

	public void setTypeControle(TypeControle typeControle) {
		this.typeControle = typeControle;
	}

	public TypeControle getTypeControle() {
		return typeControle;
	}

}
