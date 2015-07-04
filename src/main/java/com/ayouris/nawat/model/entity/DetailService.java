package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class DetailService extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = -8792715738094941473L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le prix du service
	 */
	@Column(nullable = false)
	private Float prix;

	/**
	 * La périodicité de paiement du service
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Periodicite periodicite;

	/**
	 * Flag indiquant si le service est obligatoire
	 */
	private Boolean obligatoire;

	/**
	 * L'affectation niveau concernée
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "affectationniveau_id", nullable = false)
	private AffectationNiveau affectationNiveau;

	/**
	 * Le service concernée
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public DetailService() {
		super();

	}

	public DetailService(String id) {
		super();
		this.id = id;
	}

	public DetailService(AffectationNiveau affectationNiveau) {
		super();
		this.affectationNiveau = affectationNiveau;

	}

	public DetailService(AffectationNiveau affectationNiveau, Service service) {
		super();
		this.affectationNiveau = affectationNiveau;
		this.service = service;

	}

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

	public enum Periodicite {
		// ME("cst.mensuelle"), TR("cst.trimestrielle"),
		// AN("cst.annuelle");
		ME("cst.mensuelle"), AN("cst.annuelle");

		private final String key;

		Periodicite(String key) {
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
		return String.valueOf(prix);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public AffectationNiveau getAffectationNiveau() {
		return affectationNiveau;
	}

	public void setAffectationNiveau(AffectationNiveau affectationNiveau) {
		this.affectationNiveau = affectationNiveau;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Service getService() {
		return service;
	}

	public void setObligatoire(Boolean obligatoire) {
		this.obligatoire = obligatoire;
	}

	public Boolean getObligatoire() {
		return obligatoire;
	}

	public void setPeriodicite(Periodicite periodicite) {
		this.periodicite = periodicite;
	}

	public Periodicite getPeriodicite() {
		return periodicite;
	}

}
