package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Note extends BaseEcole implements Serializable {

	private static final long serialVersionUID = 7909583509760734935L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La valeur de la note
	 */
	private Float note;

	/**
	 * Egale "true" si l'élève est absent par exemple
	 */
	private Boolean sansNote;

	/**
	 * Le motif expliquant pourquoi l'élève n'a pas eu de note
	 */
	@Length(max = 100)
	private String motifSansNote;

	/**
	 * Le controle où cette note a été obtenue
	 */
	@ManyToOne
	@JoinColumn(name = "controle_id", nullable = false)
	private Controle controle;

	/**
	 * L'inscription concernée
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inscription_id", nullable = false)
	private Inscription inscription;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public Note() {
		super();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return String.valueOf(note);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public Float getNote() {
		return note;
	}

	public void setNote(Float note) {
		this.note = note;
	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setSansNote(Boolean sansNote) {
		this.sansNote = sansNote;
	}

	public Boolean getSansNote() {
		return sansNote;
	}

	public void setMotifSansNote(String motifSansNote) {
		this.motifSansNote = motifSansNote;
	}

	public String getMotifSansNote() {
		return motifSansNote;
	}

}
