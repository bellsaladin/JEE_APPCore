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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "seance_id", "absence_id" }))
public class AbsenceSeance extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 2232528727061005645L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * L'entité séance concérnée par l'absence
	 */
	@ManyToOne
	@JoinColumn(name = "seance_id")
	@NotNull
	private Seance seance;

	/**
	 * L'entité absence
	 */
	@ManyToOne
	@JoinColumn(name = "absence_id")
	@NotNull
	private Absence absence;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return "Absence à la séance " + seance.getLibelle();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setAbsence(Absence absence) {
		this.absence = absence;
	}

	public Absence getAbsence() {
		return absence;
	}

}
