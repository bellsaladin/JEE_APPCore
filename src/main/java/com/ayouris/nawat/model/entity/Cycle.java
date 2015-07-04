package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.LocalDate;

import com.ayouris.nawat.model.base.BaseParam;

@Entity
public class Cycle extends BaseParam implements Serializable {
	private static final long serialVersionUID = 8849598259754710228L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le libelle du cycle en langue principale
	 */
	@Length(max = 100)
	@NotNull
	private String libelle;

	/**
	 * Le libelle du cycle en deuxième langue
	 */
	@Length(max = 100)
	private String libelleLng2;

	/**
	 * Le numéro de l'autorisation
	 */
	@Length(max = 30)
	private String numeroAutorisation;

	/**
	 * La date de l'autorisation
	 */
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateAutorisation;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return libelle;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public String getNumeroAutorisation() {
		return numeroAutorisation;
	}

	public void setNumeroAutorisation(String numeroAutorisation) {
		this.numeroAutorisation = numeroAutorisation;
	}

	public LocalDate getDateAutorisation() {
		return dateAutorisation;
	}

	public void setDateAutorisation(LocalDate dateAutorisation) {
		this.dateAutorisation = dateAutorisation;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelleLng2(String libelleLng2) {
		this.libelleLng2 = libelleLng2;
	}

	public String getLibelleLng2() {
		return libelleLng2;
	}

}
