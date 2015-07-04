package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class AffectationUnite extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = 1927412981740941254L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le coefficient de l'unité par rapport à l'affectation niveau
	 */
	@NotNull
	private Integer coefficient;

	/**
	 * Le flag de l'activité scolaire
	 */
	private Boolean activiteScolaire;

	/**
	 * Flag indiquant si cette unité fait partie du normalisé
	 */
	private Boolean normaliser;

	/**
	 * L'affectation niveau concernée
	 */
	@ManyToOne
	@JoinColumn(name = "affectationniveau_id")
	@NotNull
	private AffectationNiveau affectationNiveau;

	/**
	 * L'affectation unité parent de cette AffectationUnite
	 */
	@ManyToOne
	@JoinColumn(name = "uniteparent_id")
	private AffectationUnite uniteParent;

	/**
	 * L'entité unité concernée
	 */
	@ManyToOne
	@JoinColumn(name = "unite_id")
	@NotNull
	private Unite unite;

	/**
	 * La liste des affectation matières liées
	 */
	@OneToMany(mappedBy = "affectationUnite", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@OrderBy("id")
	private final List<AffectationMatiere> affectationMatiereList = new ArrayList<AffectationMatiere>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public AffectationUnite() {
		super();

	}

	public AffectationUnite(String id) {
		super();
		this.id = id;
	}

	public AffectationUnite(AffectationNiveau affectationNiveau, Unite unite) {
		super();
		this.affectationNiveau = affectationNiveau;
		this.unite = unite;

	}

	public AffectationUnite(AffectationNiveau affectationNiveau) {
		super();
		this.affectationNiveau = affectationNiveau;

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return String.valueOf(coefficient);
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

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public Unite getUnite() {
		return unite;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public List<AffectationMatiere> getAffectationMatiereList() {
		return affectationMatiereList;
	}

	public void setUniteParent(AffectationUnite uniteParent) {
		this.uniteParent = uniteParent;
	}

	public AffectationUnite getUniteParent() {
		return uniteParent;
	}

}
