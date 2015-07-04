package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Seance extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 1927412981740941254L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le personnel chargé de l'animation du cours de la séance
	 */
	@ManyToOne
	@JoinColumn(name = "personnel_id")
	@NotNull
	private Personnel personnel;

	/**
	 * Flag indiquant si la séance se passera par groupe
	 */
	private Boolean parGroupe;

	/**
	 * Le groupe concerné par la séance
	 */
	@ManyToOne
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;

	/**
	 * La classe concernée par la séance
	 */
	@ManyToOne
	@JoinColumn(name = "classe_id")
	@NotNull
	private Classe classe;

	/**
	 * Le jour de la séance
	 */
	@ManyToOne
	@JoinColumn(name = "jours_id")
	@NotNull
	private Jours jours;

	/**
	 * L'unité de la séance
	 */
	@ManyToOne
	@JoinColumn(name = "affectationUnite_id")
	@NotNull
	private AffectationUnite affectationUnite;

	/**
	 * La salle où se passera la séance
	 */
	@ManyToOne
	@JoinColumn(name = "salle_id")
	@NotNull
	private Salle salle;

	/**
	 * L'année scolaire de la séance
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	/**
	 * La tranche horaire de début de la séancé
	 */
	@ManyToOne
	@JoinColumn(name = "trancheHoraireDebut_id")
	@NotNull
	private TrancheHoraire trancheHoraireDebut;

	/**
	 * La tranche horaire de fin de la séancé
	 */
	@ManyToOne
	@JoinColumn(name = "trancheHoraireFin_id")
	@NotNull
	private TrancheHoraire trancheHoraireFin;

	/**
	 * TODO La liste des séances tranches horaires
	 */
	@OneToMany(mappedBy = "seance", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<SeanceTrancheHoraire> seanceTrancheHoraireList = new ArrayList<SeanceTrancheHoraire>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires ||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Transient
	private String libelle;

	@Transient
	private String debut;

	@Transient
	private String fin;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Seance() {
		super();
		parGroupe = false;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO Auto-generated method stub
		return null;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
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

	public void setAffectationUnite(AffectationUnite affectationUnite) {
		this.affectationUnite = affectationUnite;
	}

	public AffectationUnite getAffectationUnite() {
		return affectationUnite;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setJours(Jours jours) {
		this.jours = jours;
	}

	public Jours getJours() {
		return jours;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setSeanceTrancheHoraireList(List<SeanceTrancheHoraire> seanceTrancheHoraireList) {
		this.seanceTrancheHoraireList = seanceTrancheHoraireList;
	}

	public List<SeanceTrancheHoraire> getSeanceTrancheHoraireList() {
		return seanceTrancheHoraireList;
	}

	public void setParGroupe(Boolean parGroupe) {
		this.parGroupe = parGroupe;
	}

	public Boolean getParGroupe() {
		return parGroupe;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setTrancheHoraireDebut(TrancheHoraire trancheHoraireDebut) {
		this.trancheHoraireDebut = trancheHoraireDebut;
	}

	public TrancheHoraire getTrancheHoraireDebut() {
		return trancheHoraireDebut;
	}

	public void setTrancheHoraireFin(TrancheHoraire trancheHoraireFin) {
		this.trancheHoraireFin = trancheHoraireFin;
	}

	public TrancheHoraire getTrancheHoraireFin() {
		return trancheHoraireFin;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getDebut() {
		return debut;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getFin() {
		return fin;
	}

}
