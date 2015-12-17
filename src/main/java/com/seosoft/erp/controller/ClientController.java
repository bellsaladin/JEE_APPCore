package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Client;
import com.seosoft.erp.model.entity.v2_Profil;
import com.seosoft.erp.model.entity.v2_ProfilRole;
import com.seosoft.erp.model.entity.v2_Role;
import com.seosoft.erp.service.parametrage.ClientService;
import com.seosoft.erp.service.parametrage.v2_ProfilService;
import com.seosoft.erp.service.parametrage.v2_UserService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ClientController extends GenericCRUDController<Client, ClientService> implements Serializable {
	private static final long serialVersionUID = -1993075880697827090L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "client";
		_object = new Client();
		
	}
	
	protected void onDataReady(){
		addRelatedModule("familleClient",(FamilleClientController) Core.bean("familleClient"), new Action(){
			@Override
			public void run() {
				_object.setFamille(((FamilleClientController) Core.bean("familleClient")).getObject());
				RequestContext.getCurrentInstance().update("mainForm:familleClient:familleClientSelectOneMenu");
			}},new Action(){
			@Override
			public void run() {
				((FamilleClientController) Core.bean("familleClient")).setObject(_object.getFamille());
				RequestContext.getCurrentInstance().update("dialogFamilleClient");
			}}
		);
	}
	
	
	protected void registerActions(){
		
	}
	
	public void prepareForCreateNew(){
		_object = new Client();
	}
}
