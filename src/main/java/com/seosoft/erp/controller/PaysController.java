package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Pays;
import com.seosoft.erp.service.business.PaysService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class PaysController extends GenericCRUDController<Pays, PaysService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "pays";
		prepareForCreateNew();
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new Pays();
	}
}
