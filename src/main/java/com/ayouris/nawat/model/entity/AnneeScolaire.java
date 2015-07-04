package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class AnneeScolaire extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -2382240749671497309L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le libelle de l'année scolaire
	 */
	@Length(max = 10)
	@NotNull
	private String libelle;

	/**
	 * La date de début de l'année scolaire
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateDebut;

	/**
	 * La date de fin de l'année scolaire
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateFin;

	/**
	 * Flag qui indique si l'année est active
	 */
	private Boolean anneeActive;

	/**
	 * Liste des détails des services d'une année scolaire
	 */
	@OneToMany(mappedBy = "anneeScolaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DetailService> detailServices = new ArrayList<DetailService>();

	/**
	 * Liste des périodes scolaires d'une année scolaire
	 */
	@OneToMany(mappedBy = "anneeScolaire", cascade = CascadeType.ALL)
	private List<PeriodeScolaire> periodeScolaireListe = new ArrayList<PeriodeScolaire>();

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
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public void setDetailServices(List<DetailService> detailServices) {
		this.detailServices = detailServices;
	}

	public List<DetailService> getDetailServices() {
		return detailServices;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setAnneeActive(Boolean anneeActive) {
		this.anneeActive = anneeActive;
	}

	public Boolean getAnneeActive() {
		return anneeActive;
	}

	public void setPeriodeScolaireListe(List<PeriodeScolaire> periodeScolaireListe) {
		this.periodeScolaireListe = periodeScolaireListe;
	}

	public List<PeriodeScolaire> getPeriodeScolaireListe() {
		return periodeScolaireListe;
	}

}
