package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.inject.Named;

import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Contact;
import com.seosoft.erp.service.business.ContactService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ContactController extends GenericCRUDController<Contact, ContactService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		_moduleName = "contact";
		super.prepareData();
		prepareForCreateNew();
	}
	
	protected void registerActions(){

	}
	
	public void prepareForCreateNew(){
		_object = new Contact();
	}
}
