package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.service.parametrage.ArticleService;
import com.seosoft.erp.util.scopes.session.SpringSessionScoped;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ArticleController extends GenericCRUDController<Article, ArticleService> implements Serializable {
	private static final long serialVersionUID = -986331859933454787L;
	
	private String _messageHello = "helllo mannnn";
	
	protected void prepareData(){
		super.prepareData();
		_moduleName = "article";
		_object = new Article();
		_object.setLibelle("sssss");
		_object.setType("Service");
	}
	
	protected void registerActions(){
		_actions.put("test", new Action(){
			@Override
			public void run() {
				_service.save(_object);
				FacesMessage msg = new FacesMessage("Test action called : variable (_messageHello) : " + _messageHello);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
	}
	
	
	/*public void onProfilChange() {
		ProfilDuplicateController profilDuplicateController = (ProfilDuplicateController) getApplicationContext().getBean(
				"profilDuplicateController");
		profilDuplicateController.setProfilId(object.getProfil().getId());
	}*/

}
