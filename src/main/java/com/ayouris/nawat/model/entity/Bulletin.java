package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;
import com.ayouris.nawat.model.enumeration.TypeControle;

@Entity
public class Bulletin extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 7555362800657657486L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La moyenne obtenue par l'élève
	 */
	@NotNull
	private Float moyenne;

	/**
	 * Le classement d'un élève
	 */
	private Integer classement;

	/**
	 * Remarque du bultin
	 */
	@Length(max = 200)
	private String remarque;

	/**
	 * Flag indiquant s'il s'agit d'une moyenne générale
	 */
	private Boolean moyenneGenerale;

	/**
	 * Le type de contrôle
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private TypeControle typeControle;

	/**
	 * La période scolaire
	 */
	@ManyToOne
	@JoinColumn(name = "periodeScolaire_id")
	@NotNull
	private PeriodeScolaire periodeScolaire;

	/**
	 * L'inscription concernée par le bulletin
	 */
	@ManyToOne
	@JoinColumn(name = "inscription_id")
	@NotNull
	private Inscription inscription;

	/**
	 * Liste des moyennes d'unités
	 */
	@OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<MoyenneUnite> moyenneUnites = new ArrayList<MoyenneUnite>();

	/**
	 * L'année scolaire à laquelle faite partie ce bulletin
	 */
	@ManyToOne
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	/**
	 * Flag indiquant si la saisie a été faite manuellement
	 */
	private Boolean saisieManuelle;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires ||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * Transitoire utilisé pour stocker la somme des notes calculée
	 */
	@Transient
	private float sommeNotes;

	/**
	 * Transitoire uitlisé pour stocker la somme des coefficients
	 */
	@Transient
	private int sommeCoefficient;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Bulletin() {
		super();
		saisieManuelle = false;
	}

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

	public void setSommeNotes(float sommeNotes) {
		this.sommeNotes = sommeNotes;
	}

	public float getSommeNotes() {
		return sommeNotes;
	}

	public void setSommeCoefficient(int sommeCoefficient) {
		this.sommeCoefficient = sommeCoefficient;
	}

	public int getSommeCoefficient() {
		return sommeCoefficient;
	}

	public void setMoyenneUnites(List<MoyenneUnite> moyenneUnites) {
		this.moyenneUnites = moyenneUnites;
	}

	public List<MoyenneUnite> getMoyenneUnites() {
		return moyenneUnites;
	}

	public void setMoyenne(Float moyenne) {
		this.moyenne = moyenne;
	}

	public Float getMoyenne() {
		return moyenne;
	}

	public void setTypeControle(TypeControle typeControle) {
		this.typeControle = typeControle;
	}

	public TypeControle getTypeControle() {
		return typeControle;
	}

	public void setMoyenneGenerale(Boolean moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	public Boolean getMoyenneGenerale() {
		return moyenneGenerale;
	}

	public void setSaisieManuelle(Boolean saisieManuelle) {
		this.saisieManuelle = saisieManuelle;
	}

	public Boolean getSaisieManuelle() {
		return saisieManuelle;
	}

}
