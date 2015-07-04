package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseParam;

@Entity
public class Remarque extends BaseParam implements Serializable {
	private static final long serialVersionUID = 3827300208900349110L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * TODO Note minimale pour la remrque
	 */
	@NotNull
	private Float noteMin;

	/**
	 * TODO Note maximale pour la remrque
	 */
	@NotNull
	private Float noteMax;

	/**
	 * Le contenu de la remarque
	 */
	@NotNull
	@Length(max = 500)
	private String remarque;

	/**
	 * L'unité concernée par la remarque
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unite_id")
	@NotNull
	private Unite unite;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO
		return remarque;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public Float getNoteMin() {
		return noteMin;
	}

	public void setNoteMin(Float noteMin) {
		this.noteMin = noteMin;
	}

	public Float getNoteMax() {
		return noteMax;
	}

	public void setNoteMax(Float noteMax) {
		this.noteMax = noteMax;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public Unite getUnite() {
		return unite;
	}

}
