package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class PeriodeFerie extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = 2561804331228160992L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Libelle de la période fériée en langue principale
	 */
	@Length(max = 200)
	@NotNull
	private String libelle;

	/**
	 * Libelle de la période fériée en deuxième langue
	 */
	@Length(max = 200)
	private String libelleLng2;

	/**
	 * La date de début de la période fériée
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateDebut;

	/**
	 * La date de fin de la période fériée
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateFin;

	/**
	 * Le nombre de jours de la période fériée
	 */
	private Integer nbreJours;

	/**
	 * La liste des jours fériés
	 */
	@OneToMany(mappedBy = "periodeFerie", cascade = CascadeType.ALL)
	@OrderBy("id")
	private List<JoursFerie> joursFeries = new ArrayList<JoursFerie>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public PeriodeFerie() {
		super();

	}

	public PeriodeFerie(String id) {
		super();
		this.id = id;
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

	public void setNbreJours(Integer nbreJours) {
		this.nbreJours = nbreJours;
	}

	public Integer getNbreJours() {
		return nbreJours;
	}

	public void setJoursFeries(List<JoursFerie> joursFeries) {
		this.joursFeries = joursFeries;
	}

	public List<JoursFerie> getJoursFeries() {
		return joursFeries;
	}

}
