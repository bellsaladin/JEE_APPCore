package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class AffectationPiecesInscription extends BaseAnneeScolaire implements Serializable {

	private static final long serialVersionUID = 253396120505814567L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * Le nombre de pièces d'inscription
	 */
	@NotNull
	private Integer nombrePieces = 1;
	/**
	 * Flag indiquant si ces pieces sont obligatoires
	 */
	private Boolean obligatoire;

	/**
	 * L'affectation du niveau pour laquelle cette affectation de pièces d'inscription est faite
	 */
	@ManyToOne
	@JoinColumn(name = "affectationniveau_id", nullable = false)
	private AffectationNiveau affectationNiveau;

	/**
	 * Les pièces d'inscription concernées par l'affectation
	 */
	@ManyToOne
	@JoinColumn(name = "piecesinscription_id", nullable = false)
	private PiecesInscription piecesInscription;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public AffectationPiecesInscription() {
		super();

	}

	public AffectationPiecesInscription(String id) {
		super();
		this.id = id;
	}

	public AffectationPiecesInscription(AffectationNiveau affectationNiveau) {
		super();
		this.affectationNiveau = affectationNiveau;

	}

	public AffectationPiecesInscription(AffectationNiveau affectationNiveau, PiecesInscription piecesInscription) {
		super();
		this.affectationNiveau = affectationNiveau;
		this.piecesInscription = piecesInscription;

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO
		return String.valueOf(nombrePieces);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public void setPiecesInscription(PiecesInscription piecesInscription) {
		this.piecesInscription = piecesInscription;
	}

	public PiecesInscription getPiecesInscription() {
		return piecesInscription;
	}

	public void setObligatoire(Boolean obligatoire) {
		this.obligatoire = obligatoire;
	}

	public Boolean getObligatoire() {
		return obligatoire;
	}

	public void setNombrePieces(Integer nombrePieces) {
		this.nombrePieces = nombrePieces;
	}

	public Integer getNombrePieces() {
		return nombrePieces;
	}

}
