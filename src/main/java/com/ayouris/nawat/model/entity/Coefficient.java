package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;
import com.ayouris.nawat.model.enumeration.Activite;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "sessionScolaire_id", "activite", "affectationNiveau_id" }))
public class Coefficient extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = 794090923757004981L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La session scolaire pour lequel le coefficient est affecté
	 */
	@ManyToOne
	@JoinColumn(name = "sessionScolaire_id")
	@NotNull
	private SessionScolaire sessionScolaire;

	/**
	 * TODO
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	@NotNull
	private Activite activite;

	/**
	 * L'affectation de niveau pour lequel le coefficient est affecté
	 */
	@ManyToOne
	@JoinColumn(name = "affectationNiveau_id")
	@NotNull
	private AffectationNiveau affectationNiveau;

	/**
	 * La valeur du coefficient
	 */
	@NotNull
	private Integer coefficient;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Coefficient() {
		super();

	}

	public Coefficient(String id) {
		super();
		this.id = id;
	}

	public Coefficient(AffectationNiveau affectationNiveau, Activite activite) {
		super();

		this.affectationNiveau = affectationNiveau;
		this.activite = activite;
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

	public void setSessionScolaire(SessionScolaire sessionScolaire) {
		this.sessionScolaire = sessionScolaire;
	}

	public SessionScolaire getSessionScolaire() {
		return sessionScolaire;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
	}

	public Activite getActivite() {
		return activite;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}

	public Integer getCoefficient() {
		return coefficient;
	}

}
