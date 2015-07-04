package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class Sms extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 7127209220567760394L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le numéro gsm du destinataire de l'sms
	 */
	@NotNull
	@Length(max = 10)
	private String numeroGsm;

	/**
	 * Le contenu du message de l'sms
	 */
	@NotNull
	private String message;

	/**
	 * Le nombre d'sms du message
	 */
	@NotNull
	private Integer nbrSms;

	/**
	 * La date et l'heure de l'émission de l'sms
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime dateHeureEmission;

	/**
	 * Le date et l'heure de l'accusé de reception
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime dateHeureAccuseReception;

	/**
	 * L'état de l'sms
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	private SmsEtat etat;

	/**
	 * Le lot d'envoi auquel appartient cet sms
	 */
	@JoinColumn(name = "smsLotEnvoi_id")
	@NotNull
	private SmsLotEnvoi smsLotEnvoi;

	/**
	 * Le tuteur destinataire de l'sms
	 */
	@JoinColumn(name = "tuteur_id")
	private Tuteur tuteur;

	/**
	 * L'élève concerné par l'sms
	 */
	@JoinColumn(name = "eleve_id")
	private Eleve eleve;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum SmsEtat {
		ENVOI_EN_ATTENTE("cst.smsEtat.envoiEnAttente"), ENVOYE("cst.smsEtat.envoye"), ACCUSE("cst.smsEtat.accuse"), NON_VALIDE(
				"cst.smsEtat.nonValide"), IGNORE("cst.smsEtat.ignore");

		private final String key;

		SmsEtat(String key) {
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
		return message;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public String getNumeroGsm() {
		return numeroGsm;
	}

	public void setNumeroGsm(String numeroGsm) {
		this.numeroGsm = numeroGsm;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DateTime getDateHeureEmission() {
		return dateHeureEmission;
	}

	public void setDateHeureEmission(DateTime dateHeureEmission) {
		this.dateHeureEmission = dateHeureEmission;
	}

	public Integer getNbrSms() {
		return nbrSms;
	}

	public void setNbrSms(Integer nbrSms) {
		this.nbrSms = nbrSms;
	}

	public DateTime getDateHeureAccuseReception() {
		return dateHeureAccuseReception;
	}

	public void setDateHeureAccuseReception(DateTime dateHeureAccuseReception) {
		this.dateHeureAccuseReception = dateHeureAccuseReception;
	}

	public SmsEtat getEtat() {
		return etat;
	}

	public void setEtat(SmsEtat etat) {
		this.etat = etat;
	}

	public Tuteur getTuteur() {
		return tuteur;
	}

	public SmsLotEnvoi getSmsLotEnvoi() {
		return smsLotEnvoi;
	}

	public void setSmsLotEnvoi(SmsLotEnvoi smsLotEnvoi) {
		this.smsLotEnvoi = smsLotEnvoi;
	}

	public void setTuteur(Tuteur tuteur) {
		this.tuteur = tuteur;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

}
