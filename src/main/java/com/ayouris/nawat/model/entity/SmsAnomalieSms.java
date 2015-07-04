package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "sms_id", "smsAnomalie_id" }))
public class SmsAnomalieSms extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 5872304252105911957L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le sms concerné par l'anomalie-sms
	 */
	@ManyToOne
	@JoinColumn(name = "sms_id")
	@NotNull
	private Sms sms;

	/**
	 * L'anomalie sms
	 */
	@ManyToOne
	@JoinColumn(name = "smsAnomalie_id")
	@NotNull
	private SmsAnomalie smsAnomalie;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO
		return sms.getId() + " " + smsAnomalie.getCode();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public Sms getSms() {
		return sms;
	}

	public void setSms(Sms sms) {
		this.sms = sms;
	}

	public SmsAnomalie getSmsAnomalie() {
		return smsAnomalie;
	}

	public void setSmsAnomalie(SmsAnomalie smsAnomalie) {
		this.smsAnomalie = smsAnomalie;
	}

}
