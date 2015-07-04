package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class AffectationGroupe extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -8621376801796624819L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * L'entité inscription de l'élève affecté au groupe
	 */
	@ManyToOne
	@JoinColumn(name = "inscription_id")
	@NotNull
	private Inscription inscription;

	/**
	 * L'entité groupe auquel est affecté l'élève
	 */
	@ManyToOne
	@JoinColumn(name = "groupe_id")
	@NotNull
	private Groupe groupe;

	/**
	 * L'entité affectationUnité qui permet de savoir quelle unité est concérnée par cette affectation de groupe
	 */
	@ManyToOne
	@JoinColumn(name = "affectationUnite_id")
	@NotNull
	private AffectationUnite affectationUnite;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return "Le Groupe (" + groupe.getDisplayText() + ") par rapport à l'unité (" + affectationUnite.getUnite().getDisplayText() + ")";
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setAffectationUnite(AffectationUnite affectationUnite) {
		this.affectationUnite = affectationUnite;
	}

	public AffectationUnite getAffectationUnite() {
		return affectationUnite;
	}

}
