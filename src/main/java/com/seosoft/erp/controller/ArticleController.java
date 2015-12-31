package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.service.parametrage.ArticleService;
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
	}
	

	protected void onDataReady(){
		
		addRelatedModule((FamilleArticleController) Core.bean("familleArticle"), new Action(){
			@Override
			public void run() {
				_object.setFamille(((FamilleArticleController) Core.bean("familleArticle")).getObject());
				UIComponent component = getComponentById("famille", FacesContext.getCurrentInstance().getViewRoot());
				RequestContext.getCurrentInstance().update(component.getClientId() + ":selectOneMenu");
				//RequestContext.getCurrentInstance().update("mainForm:@(.familleOneEntitySelectMenuComponent)");
			}},new Action(){
			@Override
			public void run() {
				((FamilleArticleController) Core.bean("familleArticle")).setObject(_object.getFamille());
			}}
		);
		
		addRelatedModule((SousFamilleArticleController) Core.bean("sousFamilleArticle"), new Action(){
			@Override
			public void run() {
				_object.setSousFamille(((SousFamilleArticleController) Core.bean("sousFamilleArticle")).getObject());
				RequestContext.getCurrentInstance().update(":sousFamilleArticle:sousFamilleArticleSelectOneMenu");
			}},new Action(){
			@Override
			public void run() {
				((SousFamilleArticleController) Core.bean("sousFamilleArticle")).setObject(_object.getSousFamille());
				RequestContext.getCurrentInstance().update("dialogSousFamilleArticle");
			}}
		);
		
		addRelatedModule((CategorieArticleController) Core.bean("categorieArticle"), new Action(){
			@Override
			public void run() {
				_object.setCategorie(((CategorieArticleController) Core.bean("categorieArticle")).getObject());
				RequestContext.getCurrentInstance().update(":categorieArticle:categorieArticleSelectOneMenu");
			}},new Action(){
			@Override
			public void run() {
				((CategorieArticleController) Core.bean("categorieArticle")).setObject(_object.getCategorie());
				RequestContext.getCurrentInstance().update("dialogCategorieArticle");
			}}
		);
		
		addRelatedModule((TvaController) Core.bean("tva"), new Action(){
			@Override
			public void run() {
				_object.setTva(((TvaController) Core.bean("tva")).getObject());
				RequestContext.getCurrentInstance().update(":tva:tvaSelectOneMenu");
			}},new Action(){
			@Override
			public void run() {
				((TvaController) Core.bean("tva")).setObject(_object.getTva());
				RequestContext.getCurrentInstance().update("dialogTva");
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
