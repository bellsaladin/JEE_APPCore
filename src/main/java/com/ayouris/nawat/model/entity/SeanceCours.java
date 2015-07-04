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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "seance_id", "Cours_id" }))
public class SeanceCours extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 230507145687910556L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * TODO La séance à laquelle est affecté le cours
	 */
	@ManyToOne
	@JoinColumn(name = "seance_id")
	@NotNull
	private Seance seance;

	/**
	 * TODO Le cours effecté à la séance
	 */
	@ManyToOne
	@JoinColumn(name = "Cours_id")
	@NotNull
	private Cours cours;

	/**
	 * TODO La durée du cours
	 */
	@NotNull
	private float duree;

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

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Cours getCours() {
		return cours;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public float getDuree() {
		return duree;
	}

}
