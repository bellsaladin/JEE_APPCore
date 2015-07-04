package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class AffectationNiveau extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = -5390521268631572599L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * L'entité niveau concérnée par l'affectation
	 */
	@ManyToOne
	@JoinColumn(name = "niveau_id")
	@NotNull
	private Niveau niveau;
	/**
	 * TODO
	 */
	@NotNull
	private Boolean newBulletin;

	/**
	 * TODO
	 */
	@NotNull
	private Boolean normalise;

	/**
	 * TODO
	 */
	@NotNull
	private Integer noteBase;

	@OneToMany(mappedBy = "affectationNiveau", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<DetailService> detailServiceList = new ArrayList<DetailService>();

	@OneToMany(mappedBy = "affectationNiveau", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private List<AffectationUnite> affectationUniteList = new ArrayList<AffectationUnite>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public AffectationNiveau() {
		super();
	}

	public AffectationNiveau(String id) {
		super();
		this.id = id;
	}

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
	public List<DetailService> getDetailServiceList() {
		return detailServiceList;
	}

	public void setDetailServiceList(List<DetailService> detailServiceList) {
		this.detailServiceList = detailServiceList;
	}

	public void setAffectationUniteList(List<AffectationUnite> affectationUniteList) {
		this.affectationUniteList = affectationUniteList;
	}

	public List<AffectationUnite> getAffectationUniteList() {
		return affectationUniteList;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNewBulletin(Boolean newBulletin) {
		this.newBulletin = newBulletin;
	}

	public Boolean getNewBulletin() {
		return newBulletin;
	}

	public void setNormalise(Boolean normalise) {
		this.normalise = normalise;
	}

	public Boolean getNormalise() {
		return normalise;
	}

	public void setNoteBase(Integer noteBase) {
		this.noteBase = noteBase;
	}

	public Integer getNoteBase() {
		return noteBase;
	}

}
