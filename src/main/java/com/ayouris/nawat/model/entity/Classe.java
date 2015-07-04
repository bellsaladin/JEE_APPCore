package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class Classe extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = 7555362800657657486L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le code de la classe
	 */
	@Length(max = 10)
	@NotNull
	private String code;

	/**
	 * Le libelle en langue principale
	 */
	@Length(max = 100)
	private String libelle;

	/**
	 * Le libelle en deuxième langue
	 */
	@Length(max = 100)
	private String libelleLng2;

	/**
	 * L'affectation de niveau de la classe
	 */
	@ManyToOne
	@JoinColumn(name = "affectationniveau_id")
	@NotNull
	private AffectationNiveau affectationNiveau;

	/**
	 * Liste des groupes de la classe
	 */
	@OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
	private List<Groupe> groupes = new ArrayList<Groupe>();

	/**
	 * Couleur d'affichage de la classe
	 */
	@Length(max = 50)
	private String couleur;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires ||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * FIXME Transitoire
	 */
	@Transient
	private boolean byGroup;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Classe() {
		super();

	}

	public Classe(String id) {
		super();
		this.id = id;
	}

	public Classe(AffectationNiveau affectationNiveau) {
		super();

		this.affectationNiveau = affectationNiveau;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return libelle;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelleLng2(String libelleLng2) {
		this.libelleLng2 = libelleLng2;
	}

	public String getLibelleLng2() {
		return libelleLng2;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setByGroup(boolean byGroup) {
		this.byGroup = byGroup;
	}

	public boolean isByGroup() {
		return byGroup;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getCouleur() {
		return couleur;
	}

}
