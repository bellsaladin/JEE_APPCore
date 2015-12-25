package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.controller.generic.GenericUpdateOnlyController;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.model.entity.Client;
import com.seosoft.erp.model.entity.FamilleClient;
import com.seosoft.erp.model.entity.Parametre;
import com.seosoft.erp.service.parametrage.FamilleClientService;
import com.seosoft.erp.service.parametrage.ParametreService;
import com.seosoft.erp.service.parametrage.impl.ArticleServiceImpl;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ParametreController extends GenericUpdateOnlyController<Parametre, ParametreService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "parametre";
		prepareForCreateNew();
		
	}
	
	protected void registerActions(){
		
	}
	
	
	/*public void onProfilChange() {
		ProfilDuplicateController profilDuplicateController = (ProfilDuplicateController) getApplicationContext().getBean(
				"profilDuplicateController");
		profilDuplicateController.setProfilId(object.getProfil().getId());
	}*/
	
	
	public void prepareForCreateNew(){
		_object = new Parametre();
	}
}
