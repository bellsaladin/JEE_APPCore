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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Reglement extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -6941939607627464381L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le numéro de règlement
	 */

	@Length(max = 8)
	private String numero;

	/**
	 * Le montant total du règlement
	 */
	private Float totalMontant;

	/**
	 * Le payeur du règlement : tuteur, conjoint, eleve ou autre
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Payeur payeur;

	/**
	 * Le nom du payeur
	 */
	@Length(max = 50)
	private String nomPayeur;

	/**
	 * Le mode de règlement : espèce, chèque...
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private ReglementMode reglementMode;

	/**
	 * La référence du règlement
	 */
	@Length(max = 50)
	private String referenceReglement;

	/**
	 * La banque concernée
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "banque_id")
	private Banque banque;

	/**
	 * Le montant du chèque
	 */
	private Float montantCheque;

	/**
	 * La date de règlement
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateReglement;

	/**
	 * La caisse concernée par règlement
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caisse_id")
	@NotNull
	private Caisse caisse;

	/**
	 * L'inscription concernée par le règlement
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inscription_id")
	@NotNull
	private Inscription inscription;

	/**
	 * TODO
	 */
	@OneToMany(mappedBy = "reglement", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @OrderBy("moisScolaire")
	private List<Payement> payements = new ArrayList<Payement>();

	/**
	 * Des observations générales
	 */
	@Lob
	private String observations;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public Reglement() {
		super();
		totalMontant = new Float(0);
		reglementMode = ReglementMode.ES;
		payeur = Payeur.TITEUR;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum ReglementMode {
		CH("cst.cheque"), ES("cst.espece"), VR("cst.virement"), CH_ES("cst.chequeEspece");

		private final String key;

		ReglementMode(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	public enum Payeur {
		TITEUR("cst.tuteur"), CONJOINT("cst.conjoint"), ELEVE("cst.eleve"), AUTRE("cst.autre");

		private final String key;

		Payeur(String key) {
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
		// TODO
		return numero;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public Float getTotalMontant() {
		return totalMontant;
	}

	public void setTotalMontant(Float totalMontant) {
		this.totalMontant = totalMontant;
	}

	public String getReferenceReglement() {
		return referenceReglement;
	}

	public void setReferenceReglement(String referenceReglement) {
		this.referenceReglement = referenceReglement;
	}

	public List<Payement> getPayements() {
		return payements;
	}

	public void setPayements(List<Payement> payements) {
		this.payements = payements;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public ReglementMode getReglementMode() {
		return reglementMode;
	}

	public void setReglementMode(ReglementMode reglementMode) {
		this.reglementMode = reglementMode;
	}

	public void setDateReglement(LocalDate dateReglement) {
		this.dateReglement = dateReglement;
	}

	public LocalDate getDateReglement() {
		return dateReglement;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setPayeur(Payeur payeur) {
		this.payeur = payeur;
	}

	public Payeur getPayeur() {
		return payeur;
	}

	public void setNomPayeur(String nomPayeur) {
		this.nomPayeur = nomPayeur;
	}

	public String getNomPayeur() {
		return nomPayeur;
	}

	public void setMontantCheque(Float montantCheque) {
		this.montantCheque = montantCheque;
	}

	public Float getMontantCheque() {
		return montantCheque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setCaisse(Caisse caisse) {
		this.caisse = caisse;
	}

	public Caisse getCaisse() {
		return caisse;
	}

}
