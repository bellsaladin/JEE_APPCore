package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class MoisScolaire extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -5390521268631572599L;

	/** Code mois scolaire effectif */
	public static String MOIS_SCOLAIRE_FICTIF_CODE = "00";

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La date d'écheance du mois scolaire
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEcheance;

	/**
	 * La date d'effect du mois scolaire
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEffet;

	/**
	 * Le numero du mois
	 */
	@Length(max = 2)
	private String mois;

	/**
	 * Le libelle du mois scolaire en langue principale
	 */
	@Length(max = 20)
	private String libelle;

	/**
	 * Le libelle du mois scolaire en deuxième langue
	 */
	@Length(max = 20)
	private String libelleLng2;

	/**
	 * L'année scolaire
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anneeScolaire_id")
	@NotNull
	private AnneeScolaire anneeScolaire;

	/**
	 * l'année du mois socolaire
	 */
	private Integer annee;

	/**
	 * L'ordre d'affichage du mois scolaire
	 */
	private Integer ordre;

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
	public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}

	public AnneeScolaire getAnneeScolaire() {
		return anneeScolaire;
	}

	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public LocalDate getDateEcheance() {
		return dateEcheance;
	}

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

	public String getLibelleMoisAnnee() {
		return libelle + " " + annee;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getMois() {
		return mois;
	}

	public void setDateEffet(LocalDate dateEffet) {
		this.dateEffet = dateEffet;
	}

	public LocalDate getDateEffet() {
		return dateEffet;
	}

}
