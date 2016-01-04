package com.seosoft.erp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Client;
import com.seosoft.erp.model.entity.FamilleClient;
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
		prepareForCreateNew();
	}
	
	public void prepareForCreateNew(){
		_object = new Client();
	}
	
	protected void onDataReady(){
		
		addRelatedModule((FamilleClientController) Core.bean("familleClient"), "famille", new Action(){
			@Override
			public void run() {
				_object.setFamille(((FamilleClientController) Core.bean("familleClient")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((FamilleClientController) Core.bean("familleClient")).setObject(_object.getFamille());
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
		
		addRelatedModule((ContactController) Core.bean("contact"), "representant" ,new Action(){
			@Override
			public void run() {
				_object.setRepresentant(((ContactController) Core.bean("contact")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((ContactController) Core.bean("contact")).setObject(_object.getRepresentant());
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
		
		/*addRelatedModule((UserController) Core.bean("user"), "user", new Action(){
			@Override
			public void run() {
				_object.setUser(((UserController) Core.bean("user")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((UserController) Core.bean("user")).setObject(_object.getUser());
			}}
		);*/
		
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        try {
            Commons.copyFile(event.getFile().getFileName(), "/resources/images/societe/", event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
		_object.setLogoSrc(event.getFile().getFileName());
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
