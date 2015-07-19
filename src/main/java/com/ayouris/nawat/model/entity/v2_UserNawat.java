package com.ayouris.nawat.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.ayouris.nawat.model.base.BaseEcole;
import com.ayouris.nawat.model.enumeration.TypeUtilisateur;

@Entity
@Table(name = "v2_usersnawat")
public class v2_UserNawat extends BaseEcole implements Serializable {
	private static final long serialVersionUID = -4079419928626570009L;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	/**
	 * Le nom d'utilisateur de l'utilisateur nawat
	 */
	@Length(max = 30)
	@NotNull
	private String login;

	/**
	 * Le mot de passe l'utilisateur nawat
	 */
	@Length(max = 30)
	@NotNull
	private String password;

	/**
	 * Le nom de l'utilisateur nawat
	 */
	@Length(max = 50)
	@NotNull
	private String nom;

	/**
	 * Le prénom de l'utilisateur nawat
	 */
	@Length(max = 50)
	@NotNull
	private String prenom;

	/**
	 * Le prénom de l'utilisateur nawat
	 */
	@Length(max = 50)
	@NotNull
	private String email;

	/**
	 * Le type de l'utilisateur
	 */

	@NotNull
	private String type;

	/**
	 * Flag indiquant s'il s'agit d'un super administrateur
	 */

	@NotNull
	private Boolean superAdministrateur;

	/**
	 * Le profil de l'utilisateur nawat
	 */
	@ManyToOne
	@JoinColumn(name = "profil_id")
	private v2_Profil profil;

	/**
	 * Le flag indiquant si l'utilisateur nawat est actif
	 */
	@NotNull
	private Boolean actif;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Transient
	private String passwordConfirm;

	// // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public v2_UserNawat() {
		super();
		setSuperAdministrateur(false);
		// type = TypeUtilisateur.Utilisateur.getKey().toString();
		setType("utilisateur");
	}

	public v2_UserNawat(String id) {
		super(id);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@Override
	public String getDisplayText() {
		return login;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public String getNomPrenom() {
		return nom + " " + prenom;
	}

	public String getPrenomNom() {
		return prenom + " " + nom;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Boolean getActif() {
		return actif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProfil(v2_Profil profil) {
		this.profil = profil;
	}

	public v2_Profil getProfil() {
		return profil;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getSuperAdministrateur() {
		return superAdministrateur;
	}

	public void setSuperAdministrateur(Boolean superAdministrateur) {
		this.superAdministrateur = superAdministrateur;
	}

}
