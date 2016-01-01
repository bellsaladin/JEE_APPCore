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
		
		addRelatedModule((FamilleFournisseurController) Core.bean("familleFournisseur"), "famille", new Action(){
			@Override
			public void run() {
				_object.setFamille(((FamilleFournisseurController) Core.bean("familleFournisseur")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((FamilleFournisseurController) Core.bean("familleFournisseur")).setObject(_object.getFamille());
			}}
		);
		
		addRelatedModule((ContactController) Core.bean("contact"), "contactPrincipal" ,new Action(){
			@Override
			public void run() {
				_object.setContactPrincipal(((ContactController) Core.bean("contact")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((ContactController) Core.bean("contact")).setObject(_object.getContactPrincipal());
			}}
		);
		
		addRelatedModule((ContactController) Core.bean("contact"), "acheteur", new Action(){
			@Override
			public void run() {
				_object.setAcheteur(((ContactController) Core.bean("contact")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((ContactController) Core.bean("contact")).setObject(_object.getAcheteur());
			}}
		);
		
		addRelatedModule((ContactController) Core.bean("contact"), "demandeur", new Action(){
			@Override
			public void run() {
				_object.setDemandeur(((ContactController) Core.bean("contact")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((ContactController) Core.bean("contact")).setObject(_object.getDemandeur());
			}}
		);
		
		addRelatedModule((BanqueController) Core.bean("banque"), "banque",new Action(){
			@Override
			public void run() {
				_object.setBanque(((BanqueController) Core.bean("banque")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((BanqueController) Core.bean("banque")).setObject(_object.getBanque());
			}}
		);
	}
	
	
	protected void registerActions(){
		_actions.put("persist", new Action(){
			@Override
			public void run() {
				boolean isNewInsert = (_object.getId()==null)?true:false;
				
				if(isNewInsert)
					_object.setDateCreation(DateTime.now());
				
				_object.setDateModification(DateTime.now());
				
				_service.save(_object); 
				String message = "Enregistrement '" + StringUtils.capitalize(_moduleName) + "' effectué avec succés ! ";
				message = (isNewInsert)?message:"Modification '" + StringUtils.capitalize(_moduleName) + "' effectuée avec succés ! ";
				FacesMessage msg = new FacesMessage(message);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
	}
	
}
