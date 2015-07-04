package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.model.entity.v2_Profil;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.parametrage.v2_ProfilService;
import com.ayouris.nawat.service.parametrage.v2_UserService;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class UserCreateController extends BaseController implements Serializable {
	private static final long serialVersionUID = 85637234485828154L;

	@Autowired
	private v2_UserService v2_userService;

	@Autowired
	private v2_ProfilService v2_profilService;

	private List<v2_Profil> profils;

	private String login;

	private String password;

	private String passwordConfirm;

	private String nom;

	private String prenom;

	private String type;

	private String profilId;

	private boolean actif;

	private String email;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		updateProfilSelectOneMenu();
		type = "utilisateur";
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes utiles  |||||||||||||||||||||||||||||||||||||| ||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void updateProfilSelectOneMenu() {
		profils = v2_profilService.findAll();
		RequestContext.getCurrentInstance().update("dlgUpdateUserForm:userUpdateProfilPanel");
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Déclanché suite à une action faite ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void doCreateUser() {
		// Save the user to Database
		v2_UserNawat user = new v2_UserNawat();
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		if (profilId != null && !profilId.isEmpty())
			user.setProfil(v2_profilService.findOne(profilId));
		user.setSuperAdministrateur(false);
		user.setType(type);
		user.setActif(actif);
		v2_userService.save(user);

		// Update of userDataModel in UserListController	
		UserListController userListController = (UserListController) getApplicationContext().getBean("userListController");
		userListController.updateUserDataModel();

		// Show success message		
		FacesMessage msg = new FacesMessage("Nouvel utilisateur créé");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onProfilChange() {
		ProfilDuplicateController profilDuplicateController = (ProfilDuplicateController) getApplicationContext().getBean(
				"profilDuplicateController");
		profilDuplicateController.setProfilId(profilId);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public v2_UserService getV2_userService() {
		return v2_userService;
	}

	public void setV2_userService(v2_UserService v2_userService) {
		this.v2_userService = v2_userService;
	}

	public v2_ProfilService getV2_profilService() {
		return v2_profilService;
	}

	public void setV2_profilService(v2_ProfilService v2_profilService) {
		this.v2_profilService = v2_profilService;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilId() {
		return profilId;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public void setProfilId(String profilId) {
		this.profilId = profilId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<v2_Profil> getProfils() {
		return profils;
	}

	public void setProfils(List<v2_Profil> profils) {
		this.profils = profils;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

}
