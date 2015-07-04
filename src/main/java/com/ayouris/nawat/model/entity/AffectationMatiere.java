package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class AffectationMatiere extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = 1927412981740941254L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * Le coefficient de la matière
	 */
	@NotNull
	private Integer coefficient;

	/**
	 * Flag indiquant s'il y a une activité scolaire ou non
	 */
	private Boolean activiteScolaire;

	/**
	 * Flag indiquant si la matière fait partie du normalisé
	 */
	private Boolean normaliser;

	/**
	 * La matière concérnée par l'affectation
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "matiere_id")
	@NotNull
	private Matiere matiere;

	/**
	 * TODO
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "affectationunite_id")
	@NotNull
	private AffectationUnite affectationUnite;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Constructeurs |||||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public AffectationMatiere() {
		super();

		coefficient = 1;
	}

	public AffectationMatiere(String id) {
		super();
		this.id = id;
		coefficient = 1;
	}

	public AffectationMatiere(AffectationUnite affectationUnite) {
		super();
		this.affectationUnite = affectationUnite;

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO
		return "Matière (" + matiere.getDisplayText() + ")";
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}

	public Integer getCoefficient() {
		return coefficient;
	}

	public void setActiviteScolaire(Boolean activiteScolaire) {
		this.activiteScolaire = activiteScolaire;
	}

	public Boolean getActiviteScolaire() {
		return activiteScolaire;
	}

	public void setNormaliser(Boolean normaliser) {
		this.normaliser = normaliser;
	}

	public Boolean getNormaliser() {
		return normaliser;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setAffectationUnite(AffectationUnite affectationUnite) {
		this.affectationUnite = affectationUnite;
	}

	public AffectationUnite getAffectationUnite() {
		return affectationUnite;
	}

}
