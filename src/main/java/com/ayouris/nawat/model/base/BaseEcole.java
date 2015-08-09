package com.ayouris.nawat.model.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

/**
 * Classe mère des objets qui sont en relation avec l'école
 * 
 * @version 1.1
 */

@FilterDef(name = "ecole_filter", defaultCondition = "ecole_id = :idEcole", parameters = @ParamDef(name = "idEcole", type = "java.lang.String"))
@Filter(name = "ecole_filter")
@MappedSuperclass
public abstract class BaseEcole extends BaseEntity {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Identifiant de l'école
	 */
	@NotNull
	@Column(name = "ecole_id")
	private String ecoleId;

	/**
	 * Identifiant de l'utilisateur
	 */
	@Column(name = "user_id")
	private String userId;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public BaseEcole() {
		super();
	}

	public BaseEcole(String id) {
		super(id);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public String getEcoleId() {
		return ecoleId;
	}

	public void setEcoleId(String ecoleId) {
		this.ecoleId = ecoleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}