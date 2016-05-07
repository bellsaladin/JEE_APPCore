package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Client;
import com.seosoft.erp.model.entity.FamilleClient;
import com.seosoft.erp.model.entity.Banque;
import com.seosoft.erp.model.entity.v2_Profil;
import com.seosoft.erp.model.entity.v2_ProfilRole;
import com.seosoft.erp.model.entity.v2_Role;
import com.seosoft.erp.service.business.BanqueService;
import com.seosoft.erp.service.business.ClientService;
import com.seosoft.erp.service.parametrage.v2_ProfilService;
import com.seosoft.erp.service.parametrage.v2_UserService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class BanqueController extends GenericCRUDController<Banque, BanqueService> implements Serializable {
	private static final long serialVersionUID = -1993075880697827090L;

	protected void prepareData(){
		_moduleName = "banque";
		super.prepareData();
		prepareForCreateNew();
	}
	
	public void prepareForCreateNew(){
		_object = new Banque();
	}
	
	protected void onDataReady(){
	
	}
	
	
	protected void registerActions(){
		
	}
	
}
