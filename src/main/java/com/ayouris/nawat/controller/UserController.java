package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.controller.generic.GenericCRUDController;
import com.ayouris.nawat.model.entity.v2_Profil;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.parametrage.v2_ProfilService;
import com.ayouris.nawat.service.parametrage.v2_UserService;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class UserController extends GenericCRUDController<v2_UserNawat, v2_UserService> implements Serializable {
	private static final long serialVersionUID = 5858194089825501468L;
	
	private List<v2_Profil> _profils;
	@Autowired
	private v2_ProfilService _v2_profilService;
	
	protected void prepareData(){
		super.prepareData();
		_moduleName = "user";
		_object = new v2_UserNawat();
		setProfils(_v2_profilService.findAll());
	}
	
	public void onProfilChange() {
		ProfilDuplicateController profilDuplicateController = (ProfilDuplicateController) getApplicationContext().getBean(
				"profilDuplicateController");
		profilDuplicateController.setProfilId(_object.getProfil().getId());
	}

	public List<v2_Profil> getProfils() {
		return _profils;
	}

	public void setProfils(List<v2_Profil> profils) {
		this._profils = profils;
	}
}
