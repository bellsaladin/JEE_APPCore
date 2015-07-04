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

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class SessionScolaire extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = -269226130186883752L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le libelle de la session scolaire en langue principale
	 */
	@Length(max = 20)
	@NotNull
	private String libelle;

	/**
	 * Le libelle de la session scolaire en deuxième langue
	 */
	@Length(max = 20)
	private String libelleLng2;

	/**
	 * La date de début de la session scolaire
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateDebut;

	/**
	 * La date de fin de la session scolaire
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateFin;

	/**
	 * TODO La période scolaire à laquelle appartient cette période scolaire
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periodeScolaire_id")
	@NotNull
	private PeriodeScolaire periodeScolaire;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public SessionScolaire() {
		super();

	}

	public SessionScolaire(String id) {
		super();
		this.id = id;
	}

	public SessionScolaire(PeriodeScolaire periodeScolaire) {
		super();

		this.periodeScolaire = periodeScolaire;
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

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setPeriodeScolaire(PeriodeScolaire periodeScolaire) {
		this.periodeScolaire = periodeScolaire;
	}

	public PeriodeScolaire getPeriodeScolaire() {
		return periodeScolaire;
	}

}
