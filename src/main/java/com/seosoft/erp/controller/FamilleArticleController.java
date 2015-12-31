package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.FamilleArticle;
import com.seosoft.erp.service.business.FamilleArticleService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class FamilleArticleController extends GenericCRUDController<FamilleArticle, FamilleArticleService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "familleArticle";
		prepareForCreateNew();
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new FamilleArticle();
	}
}
