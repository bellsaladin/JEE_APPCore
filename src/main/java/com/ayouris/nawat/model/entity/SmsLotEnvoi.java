package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class SmsLotEnvoi extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = 8484336044220671659L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le numéro du lot d'envoi d'sms
	 */
	@NotNull
	private Integer numero;

	/**
	 * La date et l'heure de la génération du lot d'envoi d'sms
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime dateHeureGeneration;

	/**
	 * La date planifié pour le lot d'envoi d'sms
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime dateHeurePlanifie;

	/**
	 * La date effective de début d'envoi du lot d'envoi d'sms
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime dateHeureDebutEnvoi;

	/**
	 * La date de fin d'envoi du lot d'envoi d'sms
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime dateHeureFinEnvoi;

	/**
	 * Le mode d'envoi du lot d'sms
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	private SmsLotEnvoiModeEnvoi modeEnvoi;

	/**
	 * L'état du lot d'envoi d'sms
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	private SmsLotEnvoiEtat etat;

	/**
	 * Flag indiquant si les sms seront envoyés avec accusé de reception
	 */
	@NotNull
	private Boolean avecAccuseReception;

	/**
	 * Flag indiquant si l'envoi de message avec plusieurs sms, au cas où la limite d'sms est dépassée
	 */
	@NotNull
	private Boolean autoriserPlusieursSms;

	/**
	 * Flag indiquant qu'il faut ajouter la signature d'école aux sms
	 */
	@NotNull
	private Boolean ajouterSignatureEcole;

	/**
	 * Liste des critères de selection choisies
	 */
	@Lob
	private String criteresSelection;

	/**
	 * Le type d'sms auquel appartient cet sms
	 */
	@JoinColumn(name = "smsType_id")
	@NotNull
	private SmsType smsType;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum SmsLotEnvoiEtat {
		ENVOI_EN_COURS("cst.smsLotEnvoiEtat.envoiEnCours"), TERMINE("cst.smsLotEnvoiEtat.termine"), ANNULE("cst.smsLotEnvoiEtat.annule"), EN_INSTANCE(
				"cst.smsLotEnvoiEtat.enInstance"), PLANIFIE("cst.smsLotEnvoiEtat.planifie");

		private final String key;

		SmsLotEnvoiEtat(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getName() {
			return this.name();
		}
	}

	public enum SmsLotEnvoiModeEnvoi {
		DIFFERE("cst.smsLotEnvoiModeEnvoi.differe"), INSTANTANE("cst.smsLotEnvoiModeEnvoi.instatane");

		private final String key;

		SmsLotEnvoiModeEnvoi(String key) {
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
		return String.valueOf(numero);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public DateTime getDateHeureGeneration() {
		return dateHeureGeneration;
	}

	public void setDateHeureGeneration(DateTime dateHeureGeneration) {
		this.dateHeureGeneration = dateHeureGeneration;
	}

	public DateTime getDateHeurePlanifie() {
		return dateHeurePlanifie;
	}

	public void setDateHeurePlanifie(DateTime dateHeurePlanifie) {
		this.dateHeurePlanifie = dateHeurePlanifie;
	}

	public DateTime getDateHeureDebutEnvoi() {
		return dateHeureDebutEnvoi;
	}

	public void setDateHeureDebutEnvoi(DateTime dateHeureDebutEnvoi) {
		this.dateHeureDebutEnvoi = dateHeureDebutEnvoi;
	}

	public DateTime getDateHeureFinEnvoi() {
		return dateHeureFinEnvoi;
	}

	public void setDateHeureFinEnvoi(DateTime dateHeureFinEnvoi) {
		this.dateHeureFinEnvoi = dateHeureFinEnvoi;
	}

	public SmsLotEnvoiModeEnvoi getModeEnvoi() {
		return modeEnvoi;
	}

	public void setModeEnvoi(SmsLotEnvoiModeEnvoi modeEnvoi) {
		this.modeEnvoi = modeEnvoi;
	}

	public Boolean getAutoriserPlusieursSms() {
		return autoriserPlusieursSms;
	}

	public void setAutoriserPlusieursSms(Boolean autoriserPlusieursSms) {
		this.autoriserPlusieursSms = autoriserPlusieursSms;
	}

	public Boolean getAjouterSignatureEcole() {
		return ajouterSignatureEcole;
	}

	public void setAjouterSignatureEcole(Boolean ajouterSignatureEcole) {
		this.ajouterSignatureEcole = ajouterSignatureEcole;
	}

	public Boolean getAvecAccuseReception() {
		return avecAccuseReception;
	}

	public void setAvecAccuseReception(Boolean avecAccuseReception) {
		this.avecAccuseReception = avecAccuseReception;
	}

	public SmsLotEnvoiEtat getEtat() {
		return etat;
	}

	public void setEtat(SmsLotEnvoiEtat etat) {
		this.etat = etat;
	}

	public String getCriteresSelection() {
		return criteresSelection;
	}

	public void setCriteresSelection(String criteresSelection) {
		this.criteresSelection = criteresSelection;
	}

	public SmsType getSmsType() {
		return smsType;
	}

	public void setSmsType(SmsType smsType) {
		this.smsType = smsType;
	}

}
