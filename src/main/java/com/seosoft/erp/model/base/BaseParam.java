package com.seosoft.erp.model.base;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Classe des objets paramètrables qui permet de facilement gérer l'activité d'un ensemble d'objets ainsi que leur ordre d'affichage)
 * 
 * @version 1.1
 */

@MappedSuperclass
public abstract class BaseParam extends BaseEcole {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Indique si l'objet est actif ou non
	 */
	@NotNull
	protected Boolean actif;

	/**
	 * L'ordre d'affichage personnalisé
	 */
	@NotNull
	protected Integer ordre;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public BaseParam() {
		super();
	}

	public BaseParam(String id) {
		super(id);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Integer getOrdre() {
		return ordre;
	}
}