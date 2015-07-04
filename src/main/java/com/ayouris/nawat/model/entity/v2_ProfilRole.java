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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "profil_id", "role_id" }))
public class v2_ProfilRole extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -9074290043008603025L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le profil auquel est affecté le rôle
	 */
	@ManyToOne
	@JoinColumn(name = "profil_id")
	@NotNull
	private v2_Profil profil;

	/**
	 * Le role affecté au profil
	 */
	@ManyToOne
	@JoinColumn(name = "role_id")
	@NotNull
	private v2_Role role;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO
		return null;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/

	public v2_Profil getProfil() {
		return profil;
	}

	public void setProfil(v2_Profil profil) {
		this.profil = profil;
	}

	public v2_Role getRole() {
		return role;
	}

	public void setRole(v2_Role role) {
		this.role = role;
	}

}
