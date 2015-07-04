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

@Entity
public class Payement extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -6948133478287547588L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La date d'échéance vis à vis du paiement
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEcheance;

	/**
	 * La date d'effet du paiement
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateEffet;

	/**
	 * Le mois scolaire concernée
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moisScolaire_id")
	@NotNull
	private MoisScolaire moisScolaire;

	/**
	 * Le montant à payer
	 */
	@NotNull
	private Float montant;

	/**
	 * L'état du paiement
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private PayementEtat payementEtat;

	/**
	 * Le reglement du paiement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reglement_id")
	private Reglement reglement;

	/**
	 * L'affectation-service concernée
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "affectationService_id")
	@NotNull
	private AffectationService affectationService;

	/**
	 * L'inscription concernée
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inscription_id")
	@NotNull
	private Inscription inscription;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum PayementEtat {
		PAY("cst.paye"), NPAY("cst.nonPaye"), ANNULE("cst.annule");

		private final String key;

		PayementEtat(String key) {
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
		return String.valueOf(montant);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public Reglement getReglement() {
		return reglement;
	}

	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}

	public void setAffectationService(AffectationService affectationService) {
		this.affectationService = affectationService;
	}

	public AffectationService getAffectationService() {
		return affectationService;
	}

	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public LocalDate getDateEcheance() {
		return dateEcheance;
	}

	/**
	 * @param payementEtat
	 *            the payementEtat to set
	 */
	public void setPayementEtat(PayementEtat payementEtat) {
		this.payementEtat = payementEtat;
	}

	/**
	 * @return the payementEtat
	 */
	public PayementEtat getPayementEtat() {
		return payementEtat;
	}

	public void setMoisScolaire(MoisScolaire moisScolaire) {
		this.moisScolaire = moisScolaire;
	}

	public MoisScolaire getMoisScolaire() {
		return moisScolaire;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setDateEffet(LocalDate dateEffet) {
		this.dateEffet = dateEffet;
	}

	public LocalDate getDateEffet() {
		return dateEffet;
	}

}
