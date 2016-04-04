package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.ModeleTarif;
import com.seosoft.erp.service.business.ModeleTarifService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ModeleTarifController extends GenericCRUDController<ModeleTarif, ModeleTarifService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		_moduleName = "modeleTarif";
		super.prepareData();
		prepareForCreateNew();
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new ModeleTarif();
	}
}
