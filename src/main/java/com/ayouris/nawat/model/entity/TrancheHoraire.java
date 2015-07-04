package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseAnneeScolaire;

@Entity
public class TrancheHoraire extends BaseAnneeScolaire implements Serializable {
	private static final long serialVersionUID = -3796975681269906447L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * L'heure de début de la tranche horaire
	 */
	@Length(max = 5)
	@NotNull
	private String heureDebut;

	/**
	 * L'heure de fin de la tranche horaire
	 */
	@Length(max = 5)
	@NotNull
	private String heureFin;

	/**
	 * Le type : cours, déjeuner ou pause de la tranche horaire
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 15)
	private TypeTrancheHoraire typeTrancheHoraire;

	/**
	 * La durée de la tranche horaire
	 */
	private Integer duree;

	/**
	 * Flag indiquant si la tranche horaire est visible
	 */
	private Boolean visible;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public TrancheHoraire() {
		super();

	}

	public TrancheHoraire(String id) {
		super();
		this.id = id;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum TypeTrancheHoraire {
		PAUSE("cst.typeTrancheHoraire.pause"), DEJEUNER("cst.typeTrancheHoraire.dejeuner"), COURS("cst.typeTrancheHoraire.cours");

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
		// TODO
		return String.valueOf(duree);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void setTypeTrancheHoraire(TypeTrancheHoraire typeTrancheHoraire) {
		this.typeTrancheHoraire = typeTrancheHoraire;
	}

	public TypeTrancheHoraire getTypeTrancheHoraire() {
		return typeTrancheHoraire;
	}

	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	public String getHeureDebut() {
		return heureDebut;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public String getTranche() {
		return heureDebut.concat(" - ").concat(heureFin);
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getVisible() {
		return visible;
	}

}
