package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.{{ENTITY}};
import com.seosoft.erp.service.business.{{ENTITY}}Service;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class {{ENTITY}}Controller extends GenericCRUDController<{{ENTITY}}, {{ENTITY}}Service> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		_moduleName = "{{ENTITY_FIRSTLETTER_LOWERCASE}}";
		prepareForCreateNew();
		super.prepareData();
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new {{ENTITY}}();
	}
}
