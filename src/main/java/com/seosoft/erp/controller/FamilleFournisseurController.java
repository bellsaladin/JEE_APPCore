package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.model.entity.Client;
import com.seosoft.erp.model.entity.FamilleClient;
import com.seosoft.erp.model.entity.FamilleFournisseur;
import com.seosoft.erp.service.parametrage.FamilleClientService;
import com.seosoft.erp.service.parametrage.FamilleFournisseurService;
import com.seosoft.erp.service.parametrage.impl.ArticleServiceImpl;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class FamilleFournisseurController extends GenericCRUDController<FamilleFournisseur, FamilleFournisseurService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		_moduleName = "familleFournisseur";
		super.prepareData();
		prepareForCreateNew();
	}
	
	public void prepareForCreateNew(){
		_object = new FamilleFournisseur();
	}
	
	protected void registerActions(){
		_actions.put("test", new Action(){
			@Override
			public void run() {
				FacesMessage msg = new FacesMessage("Test action called");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
	}
	
}
