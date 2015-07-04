package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OrderBy;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class AffectationService extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 4545793651893814255L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * La valeur de réduction
	 */
	private Float valeurReduction;

	/**
	 * Le pourcentage de réduction
	 */
	private Float pourcentageReduction;

	/**
	 * Le mois scolaire de début
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "moisScolaireDebut_id")
	@NotNull
	private MoisScolaire moisScolaireDebut;

	/**
	 * Le mois scolaire de fin
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "moisScolaireFin_id")
	@NotNull
	private MoisScolaire moisScolaireFin;

	/**
	 * Le détails-service concernée
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "detailService_id")
	@NotNull
	private DetailService detailService;

	/**
	 * L'inscription concernée
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inscription_id")
	@NotNull
	private Inscription inscription;

	/**
	 * La liste de paiement pour cette affecation-service
	 */
	@OneToMany(mappedBy = "affectationService", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy(clause = "dateEcheance asc")
	private final List<Payement> payements = new ArrayList<Payement>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return detailService.getDisplayText() + "; " + inscription.getDisplayText();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public Float getValeurReduction() {
		return valeurReduction;
	}

	public void setValeurReduction(Float valeurReduction) {
		this.valeurReduction = valeurReduction;
	}

	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	public DetailService getDetailService() {
		return detailService;
	}

	public Float getMontant() {
		if (detailService != null) {
			if (valeurReduction != null) {
				return (detailService.getPrix() - valeurReduction);
			} else {
				return detailService.getPrix();
			}

		} else {
			return null;
		}

	}

	public List<Payement> getPayements() {
		return payements;
	}

	public void setMoisScolaireDebut(MoisScolaire moisScolaireDebut) {
		this.moisScolaireDebut = moisScolaireDebut;
	}

	public MoisScolaire getMoisScolaireDebut() {
		return moisScolaireDebut;
	}

	public void setMoisScolaireFin(MoisScolaire moisScolaireFin) {
		this.moisScolaireFin = moisScolaireFin;
	}

	public MoisScolaire getMoisScolaireFin() {
		return moisScolaireFin;
	}

	public void setPourcentageReduction(Float pourcentageReduction) {
		this.pourcentageReduction = pourcentageReduction;
	}

	public Float getPourcentageReduction() {
		return pourcentageReduction;
	}

}
