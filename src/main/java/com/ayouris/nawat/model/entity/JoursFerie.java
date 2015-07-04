package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class JoursFerie extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -3796975681269906447L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La date du jour férié
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateJourFerie;

	/**
	 * La période à laquelle appartient le jour fériée
	 */
	@ManyToOne
	@JoinColumn(name = "periodeFerie_id")
	@NotNull
	private PeriodeFerie periodeFerie;

	/**
	 * La semaine du jour feriée
	 */
	@ManyToOne
	@JoinColumn(name = "semaine_id")
	@NotNull
	private Semaine semaine;

	/**
	 * L'entité jour du jour férié
	 */
	@ManyToOne
	@JoinColumn(name = "jours_id")
	@NotNull
	private Jours jours;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum EtatCaisse {
		OUVERTE("cst.caisseEtat.ouverte"), CLOTURE("cst.caisseEtat.cloture");

		private final String key;

		EtatCaisse(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

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
		return dateJourFerie.toString();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public void setPeriodeFerie(PeriodeFerie periodeFerie) {
		this.periodeFerie = periodeFerie;
	}

	public PeriodeFerie getPeriodeFerie() {
		return periodeFerie;
	}

	public void setSemaine(Semaine semaine) {
		this.semaine = semaine;
	}

	public Semaine getSemaine() {
		return semaine;
	}

	public void setDateJourFerie(LocalDate dateJourFerie) {
		this.dateJourFerie = dateJourFerie;
	}

	public LocalDate getDateJourFerie() {
		return dateJourFerie;
	}

	public void setJours(Jours jours) {
		this.jours = jours;
	}

	public Jours getJours() {
		return jours;
	}

}
