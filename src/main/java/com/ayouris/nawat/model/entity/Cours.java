package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Cours extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 1927412981740941254L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * L'affectation de matière concernée par le cours
	 */
	@ManyToOne
	@JoinColumn(name = "affectationMatiere_id")
	@NotNull
	private AffectationMatiere affectationMatiere;

	/**
	 * Le professeur qui se chargera du cours
	 */
	@ManyToOne
	@JoinColumn(name = "personnel_id")
	@NotNull
	private Personnel personnel;

	/**
	 * La classe concernée par le cours
	 */
	@ManyToOne
	@JoinColumn(name = "classe_id")
	@NotNull
	private Classe classe;

	/**
	 * Flag indiquant si le cours sera fait par groupe
	 */
	private Boolean parGroupe;

	/**
	 * Le groupe concerné par le cours : Sera null si le cours sera fait pour tout le groupe
	 */
	@ManyToOne
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;

	/**
	 * L'année scolaire
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO comment gèrer l'affichage en langue arabe vis a vis des constantes écrits en dûr ?
		return "Cours-unité " + affectationMatiere.getDisplayText();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public void setAffectationMatiere(AffectationMatiere affectationMatiere) {
		this.affectationMatiere = affectationMatiere;
	}

	public AffectationMatiere getAffectationMatiere() {
		return affectationMatiere;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setParGroupe(Boolean parGroupe) {
		this.parGroupe = parGroupe;
	}

	public Boolean getParGroupe() {
		return parGroupe;
	}

}
