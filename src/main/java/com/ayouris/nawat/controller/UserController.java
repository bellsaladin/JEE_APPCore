package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.model.entity.v2_Profil;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.parametrage.v2_ProfilService;
import com.ayouris.nawat.service.parametrage.v2_UserService;
import com.ayouris.nawat.util.scopes.session.SpringSessionScoped;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class UserController extends GenericController<v2_UserNawat, v2_UserService> implements Serializable {
	private static final long serialVersionUID = 5858194089825501468L;
	
	private List<v2_Profil> profils;
	@Autowired
	private v2_ProfilService v2_profilService;
	
	protected void prepareData(){
		super.prepareData();
		moduleName = "user";
		object = new v2_UserNawat();
		setProfils(v2_profilService.findAll());
	}
	
	public void onProfilChange() {
		ProfilDuplicateController profilDuplicateController = (ProfilDuplicateController) getApplicationContext().getBean(
				"profilDuplicateController");
		profilDuplicateController.setProfilId(object.getProfil().getId());
	}

	public List<v2_Profil> getProfils() {
		return profils;
	}

	public void setProfils(List<v2_Profil> profils) {
		this.profils = profils;
	}
}
