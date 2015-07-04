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
public class ProfilRole extends BaseEcole implements Serializable {
	private static final long serialVersionUID = 230507145687910556L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le porfil auquel le rôle est affecté
	 */
	@ManyToOne
	@JoinColumn(name = "profil_id")
	@NotNull
	private Profil profil;

	/**
	 * Le role affecté au profil
	 */
	@ManyToOne
	@JoinColumn(name = "role_id")
	@NotNull
	private Role role;

	/**
	 * Flag indiquant si le profil a le droit d'accès pour le rôle qui lui est affecté
	 */
	@NotNull
	private Boolean droitAcces;

	/**
	 * Flag indiquant si le profil a le droit de mise à jour pour le rôle qui lui est affecté
	 */
	@NotNull
	private Boolean droitMaj;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		// TODO
		return profil.getLibelle() + "-" + role.getLibelle() + " : DAccès(" + droitAcces + ") DMaj(" + droitMaj + ")";
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++/
	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setDroitAcces(Boolean droitAcces) {
		this.droitAcces = droitAcces;
	}

	public Boolean getDroitAcces() {
		return droitAcces;
	}

	public void setDroitMaj(Boolean droitMaj) {
		this.droitMaj = droitMaj;
	}

	public Boolean getDroitMaj() {
		return droitMaj;
	}

}
