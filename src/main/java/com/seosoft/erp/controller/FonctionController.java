package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.inject.Named;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Fonction;
import com.seosoft.erp.service.business.FonctionService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class FonctionController extends GenericCRUDController<Fonction, FonctionService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		_moduleName = "fonction";
		super.prepareData();
		prepareForCreateNew();
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new Fonction();
	}
}
