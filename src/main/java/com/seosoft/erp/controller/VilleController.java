package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Pays;
import com.seosoft.erp.model.entity.Ville;
import com.seosoft.erp.service.business.VilleService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class VilleController extends GenericCRUDController<Ville, VilleService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "ville";
		prepareForCreateNew();
	}
	
	public List<Ville> getList(Pays pays) {
		_list = _service.findByPays(pays);
		return _list;
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new Ville();
	}
}
