package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "jours_id", "trancheHoraire_id", "personnel_id" }))
public class TempsOccupe extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 7768066760976200700L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le jours du temps occupé
	 */
	@ManyToOne
	@JoinColumn(name = "jours_id")
	@NotNull
	private Jours jours;

	/**
	 * La tranche horaire du temps occupé
	 */
	@ManyToOne
	@JoinColumn(name = "trancheHoraire_id")
	@NotNull
	private TrancheHoraire trancheHoraire;

	/**
	 * Le personnel concerné
	 */
	@ManyToOne
	@JoinColumn(name = "personnel_id")
	@NotNull
	private Personnel personnel;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO
		return null;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public void setJours(Jours jours) {
		this.jours = jours;
	}

	public Jours getJours() {
		return jours;
	}

	public void setTrancheHoraire(TrancheHoraire trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}

	public TrancheHoraire getTrancheHoraire() {
		return trancheHoraire;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

}
