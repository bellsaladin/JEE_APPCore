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
public class UserUpdateController extends BaseController implements Serializable {
	private static final long serialVersionUID = 2170084197330123158L;

	@Autowired
	private v2_UserService v2_userService;

	@Autowired
	private v2_ProfilService v2_profilService;

	private List<v2_Profil> profils;

	private v2_UserNawat user;

	private String profilId;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		updateProfilSelectOneMenu();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes utiles  |||||||||||||||||||||||||||||||||||||| ||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void updateProfilSelectOneMenu() {
		profils = v2_profilService.findAll();
		RequestContext.getCurrentInstance().update("dlgUpdateUserForm:userUpdateProfilPanel");
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Déclanché suite à une action faite ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void doUpdateUser() {
		// Update the user
		if (profilId != null && !profilId.isEmpty())
			user.setProfil(v2_profilService.findOne(profilId));
		v2_userService.save(user);

		// Update of userDataModel in UserListController
		UserListController userListController = (UserListController) getApplicationContext().getBean("userListController");
		userListController.updateUserDataModel();

		// Show success message
        FacesMessage msg = new FacesMessage("Utilisateur modifié");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void doRemoveUser() {
		// Remove the user
		v2_userService.delete(user);

		// Update of userDataModel in UserListController
		UserListController userListController = (UserListController) getApplicationContext().getBean("userListController");
		userListController.updateUserDataModel();

		// show success message
		FacesMessage msg = new FacesMessage("Utilisateur supprimé");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onProfilChange() {
		ProfilDuplicateController profilDuplicateController = (ProfilDuplicateController) getApplicationContext().getBean(
				"profilDuplicateController");
		//v2_Profil profil = v2_profilService.findOne(user.getProfil().getId());
		profilDuplicateController.setProfilId(user.getProfil().getId());
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

	public List<v2_Profil> getProfils() {
		return profils;
	}

	public void setProfils(List<v2_Profil> profils) {
		this.profils = profils;
	}

	public v2_UserNawat getUser() {
		return user;
	}

	public void setUser(v2_UserNawat user) {
		this.user = user;
	}

	public String getProfilId() {
		return profilId;
	}

	public void setProfilId(String profilId) {
		this.profilId = profilId;
	}

}
