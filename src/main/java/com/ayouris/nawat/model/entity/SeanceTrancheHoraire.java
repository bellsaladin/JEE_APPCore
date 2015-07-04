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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "seance_id", "trancheHoraire_id" }))
public class SeanceTrancheHoraire extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 1927412981740941254L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * TODO La séance affectée à la tranche horaire
	 */
	@ManyToOne
	@JoinColumn(name = "seance_id")
	@NotNull
	private Seance seance;

	/**
	 * TODO La tranche horaire où est sensé se dérouler la séance
	 */
	@ManyToOne
	@JoinColumn(name = "trancheHoraire_id")
	@NotNull
	private TrancheHoraire trancheHoraire;

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
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setTrancheHoraire(TrancheHoraire trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}

	public TrancheHoraire getTrancheHoraire() {
		return trancheHoraire;
	}

}
