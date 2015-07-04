package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;
import com.ayouris.nawat.model.enumeration.TypeControle;

@Entity
public class MoyenneMatiere extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 7555362800557657486L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La moyenne obtenue dans la matière
	 */
	@NotNull
	private Float moyenne;

	/**
	 * Le classement de l'élève dans la matiere
	 */
	private Integer classement;

	/**
	 * La remarque concernant l'élève pour la moyenne obtenue
	 */
	@Length(max = 200)
	private String remarque;

	/**
	 * La moyenne unité à laquelle appartient cette moyenne matière
	 */
	@ManyToOne
	@JoinColumn(name = "moyenneUnite_id")
	@NotNull
	private MoyenneUnite moyenneUnite;

	/**
	 * La période scolaire où cette moyenne a été obtenue
	 */
	@ManyToOne
	@JoinColumn(name = "periodeScolaire_id")
	@NotNull
	private PeriodeScolaire periodeScolaire;

	/**
	 * L'affectation matière concernée
	 */
	@ManyToOne
	@JoinColumn(name = "affectationMatiere_id")
	@NotNull
	private AffectationMatiere affectationMatiere;

	/**
	 * L'inscription concernée
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inscription_id")
	@NotNull
	private Inscription inscription;

	/**
	 * Le type du controle
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 15)
	private TypeControle typeControle;

	/**
	 * L'année scolaire
	 */
	@ManyToOne
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires ||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * TODO Attribut transitoire
	 */
	@Transient
	private List<Note> notes = new ArrayList<Note>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return String.valueOf(moyenne);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public void setPeriodeScolaire(PeriodeScolaire periodeScolaire) {
		this.periodeScolaire = periodeScolaire;
	}

	public PeriodeScolaire getPeriodeScolaire() {
		return periodeScolaire;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setAffectationMatiere(AffectationMatiere affectationMatiere) {
		this.affectationMatiere = affectationMatiere;
	}

	public AffectationMatiere getAffectationMatiere() {
		return affectationMatiere;
	}

	public void setClassement(Integer classement) {
		this.classement = classement;
	}

	public Integer getClassement() {
		return classement;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setMoyenneUnite(MoyenneUnite moyenneUnite) {
		this.moyenneUnite = moyenneUnite;
	}

	public MoyenneUnite getMoyenneUnite() {
		return moyenneUnite;
	}

	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public void setTypeControle(TypeControle typeControle) {
		this.typeControle = typeControle;
	}

	public TypeControle getTypeControle() {
		return typeControle;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Note> getNotes() {
		return notes;
	}

}
