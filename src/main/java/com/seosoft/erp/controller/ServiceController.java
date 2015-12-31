package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.inject.Named;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Service;
import com.seosoft.erp.service.business.ServiceService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ServiceController extends GenericCRUDController<Service, ServiceService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "service";
		prepareForCreateNew();
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new Service();
	}
}
