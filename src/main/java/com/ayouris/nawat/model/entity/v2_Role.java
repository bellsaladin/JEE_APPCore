package com.ayouris.nawat.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;

@Entity
public class v2_Role extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -6035977919509653910L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le code du role
	 */
	@Length(max = 20)
	@NotNull
	private String code;

	/**
	 * Le libelleKey utilisé pour récuperer la valeur des fichiers d'internationalization
	 */
	@Length(max = 50)
	@NotNull
	private String libelleKey;

	/**
	 * Flag indiquant si ce role n'est utile sans son role parent
	 */
	@Length(max = 50)
	@NotNull
	private Boolean dependantOfParent;

	/**
	 * Le role parent de ce role
	 */
	@ManyToOne
	@JoinColumn(name = "parentRole_id")
	private v2_Role parentRole;

	/**
	 * La liste des roles fils de ce role
	 */
	@OneToMany(mappedBy = "parentRole", fetch = FetchType.EAGER)
	private final List<v2_Role> roleChildren = new ArrayList<v2_Role>();

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| ENUMERATIONS |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public enum RoleType {
		PAGE("cst.roleType.page"), ACTION("cst.roleType.action"), RAPPORT("cst.roleType.rapport"), MODULE("cst.roleType.module");

		private final String key;

		RoleType(String key) {
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
		return libelleKey;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleKey() {
		return libelleKey;
	}

	public void setLibelleKey(String libelleKey) {
		this.libelleKey = libelleKey;
	}

	public Boolean getDependantOfParent() {
		return dependantOfParent;
	}

	public void setDependantOfParent(Boolean dependantOfParent) {
		this.dependantOfParent = dependantOfParent;
	}

	public v2_Role getParentRole() {
		return parentRole;
	}

	public void setParentRole(v2_Role parentRole) {
		this.parentRole = parentRole;
	}

	public List<v2_Role> getRoleChildren() {
		return roleChildren;
	}

}
