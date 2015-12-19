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
import com.seosoft.erp.model.entity.FamilleClient;
import com.seosoft.erp.model.entity.Fournisseur;
import com.seosoft.erp.model.entity.v2_Profil;
import com.seosoft.erp.model.entity.v2_ProfilRole;
import com.seosoft.erp.model.entity.v2_Role;
import com.seosoft.erp.service.parametrage.ClientService;
import com.seosoft.erp.service.parametrage.FournisseurService;
import com.seosoft.erp.service.parametrage.v2_ProfilService;
import com.seosoft.erp.service.parametrage.v2_UserService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class FournisseurController extends GenericCRUDController<Fournisseur, FournisseurService> implements Serializable {
	private static final long serialVersionUID = -1993075880697827090L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "fournisseur";
		prepareForCreateNew();
	}
	
	public void prepareForCreateNew(){
		_object = new Fournisseur();
	}
	
	protected void onDataReady(){
		
		addRelatedModule((FamilleFournisseurController) Core.bean("familleFournisseur"), new Action(){
			@Override
			public void run() {
				_object.setFamille(((FamilleFournisseurController) Core.bean("familleFournisseur")).getObject());
				RequestContext.getCurrentInstance().update("mainForm:familleFournisseur:familleFournisseurSelectOneMenu");
			}},new Action(){
			@Override
			public void run() {
				((FamilleFournisseurController) Core.bean("familleFournisseur")).setObject(_object.getFamille());
				RequestContext.getCurrentInstance().update("dialogFamilleFournisseur");
			}}
		);
		
		addRelatedModule((FamilleClientController) Core.bean("familleClient"), new Action(){
			@Override
			public void run() {
				_object.setFamilleClient(((FamilleClientController) Core.bean("familleClient")).getObject());
				RequestContext.getCurrentInstance().update("mainForm:familleClient:familleClientSelectOneMenu");
			}},new Action(){
			@Override
			public void run() {
				((FamilleClientController) Core.bean("familleClient")).setObject(_object.getFamilleClient());
				RequestContext.getCurrentInstance().update("dialogFamilleClient");
			}}
		);
		
		addRelatedModule((UserController) Core.bean("user"), new Action(){
			@Override
			public void run() {
				_object.setUser(((UserController) Core.bean("user")).getObject());
				RequestContext.getCurrentInstance().update("mainForm:user:userSelectOneMenu");
			}},new Action(){
			@Override
			public void run() {
				((UserController) Core.bean("user")).setObject(_object.getUser());
				RequestContext.getCurrentInstance().update("dialogUser");
			}}
		);
		
	}
	
	
	protected void registerActions(){
		
	}
	
}
