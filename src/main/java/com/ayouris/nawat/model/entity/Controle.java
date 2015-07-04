package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;
import com.ayouris.nawat.model.enumeration.TypeControle;

@Entity
public class Controle extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 1392891970740134457L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La date du controle
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateControle;

	/**
	 * L'état du controle
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 15)
	private EtatControle etatControle;

	/**
	 * Le type du controle
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 15)
	private TypeControle typeControle;

	/**
	 * Le cours concerné par le controle
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cours_id")
	private Cours cours;

	/**
	 * Le cours d'unité concerné par le controle
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coursUnite_id")
	private CoursUnite coursUnite;

	/**
	 * La session scolaire concernée par le controle
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sessionScolaire_id")
	private SessionScolaire sessionScolaire;

	/**
	 * La période scolaire concernée par le controle
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periodeScolaire_id")
	private PeriodeScolaire periodeScolaire;

	/**
	 * Flag indiquant si le controle sera fait par groupe
	 */
	@NotNull
	private Boolean parGroupe;

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

	public enum EtatControle {
		VALIDE("cst.controleEtat.valide"), ANNULE("cst.controleEtat.annuler"), INSTANCE("cst.controleEtat.enInstance");

		private final String key;

		EtatControle(String key) {
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
		return typeControle + " planifié le " + dateControle + " en " + cours.getDisplayText();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public void setDateControle(LocalDate dateControle) {
		this.dateControle = dateControle;
	}

	public LocalDate getDateControle() {
		return dateControle;
	}

	public void setEtatControle(EtatControle etatControle) {
		this.etatControle = etatControle;
	}

	public EtatControle getEtatControle() {
		return etatControle;
	}

	public void setSessionScolaire(SessionScolaire sessionScolaire) {
		this.sessionScolaire = sessionScolaire;
	}

	public SessionScolaire getSessionScolaire() {
		return sessionScolaire;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Cours getCours() {
		return cours;
	}

	public void setTypeControle(TypeControle typeControle) {
		this.typeControle = typeControle;
	}

	public TypeControle getTypeControle() {
		return typeControle;
	}

	public void setParGroupe(Boolean parGroupe) {
		this.parGroupe = parGroupe;
	}

	public Boolean getParGroupe() {
		return parGroupe;
	}

	public void setPeriodeScolaire(PeriodeScolaire periodeScolaire) {
		this.periodeScolaire = periodeScolaire;
	}

	public PeriodeScolaire getPeriodeScolaire() {
		return periodeScolaire;
	}

	public void setCoursUnite(CoursUnite coursUnite) {
		this.coursUnite = coursUnite;
	}

	public CoursUnite getCoursUnite() {
		return coursUnite;
	}

}
