package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Absence extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -3796975681269906447L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * L'entité inscription concernée par l'absence
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "inscription_id")
	private Inscription inscription;

	/**
	 * La date de début de l'absence
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateDebut;

	/**
	 * La date de fin de l'absence
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateFin;

	/**
	 * La séance de début de l'absence
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seanceDebut_id")
	private Seance seanceDebut;

	/**
	 * La séance de fin de l'absence
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seanceFin_id")
	private Seance seanceFin;

	/**
	 * Flag indiquant si l'absence est justifée
	 */
	@NotNull
	private Boolean justifie;

	/**
	 * Le motif de l'absence
	 */
	@Length(max = 500)
	private String motif;

	/**
	 * Liste des séances concernées par l'absence
	 */
	@OneToMany(mappedBy = "absence", fetch = FetchType.LAZY)
	private List<AbsenceSeance> absenceSeanceList = new ArrayList<AbsenceSeance>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public Absence() {
		super();
		justifie = false;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public enum TypeTrancheHoraire {
		PAUSE("cst.typeTrancheHoraire.pause"), COURS("cst.typeTrancheHoraire.cours");

		private final String key;

		TypeTrancheHoraire(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return "Absence du " + dateDebut + " au " + dateFin;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
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

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getMotif() {
		return motif;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setAbsenceSeanceList(List<AbsenceSeance> absenceSeanceList) {
		this.absenceSeanceList = absenceSeanceList;
	}

	public List<AbsenceSeance> getAbsenceSeanceList() {
		return absenceSeanceList;
	}

	public void setJustifie(Boolean justifie) {
		this.justifie = justifie;
	}

	public Boolean getJustifie() {
		return justifie;
	}

	public void setSeanceDebut(Seance seanceDebut) {
		this.seanceDebut = seanceDebut;
	}

	public Seance getSeanceDebut() {
		return seanceDebut;
	}

	public void setSeanceFin(Seance seanceFin) {
		this.seanceFin = seanceFin;
	}

	public Seance getSeanceFin() {
		return seanceFin;
	}

}
