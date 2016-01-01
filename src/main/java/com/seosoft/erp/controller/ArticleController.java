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
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.model.entity.Stock;
import com.seosoft.erp.service.business.StockService;
import com.seosoft.erp.service.parametrage.ArticleService;
import com.seosoft.erp.service.parametrage.v2_ProfilRoleService;
import com.seosoft.erp.service.parametrage.v2_RoleService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ArticleController extends GenericCRUDController<Article, ArticleService> implements Serializable {
	private static final long serialVersionUID = -986331859933454787L;
	
	@Autowired
	private StockService stockService;
	
	private Stock stock = new Stock(); ;
	
	protected void prepareData(){
		super.prepareData();
		_moduleName = "article";
		prepareForCreateNew();
		
	}
	
	public void prepareForCreateNew(){
		_object = new Article();
	}

	protected void onDataReady(){
		
		loadStockOfDepot();
		
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
		
		_actions.put("loadDetailsStock", new Action(){
			@Override
			public void run() {
				loadStockOfDepot();
			}
		});
	}
	
	public void loadStockOfDepot(){
		if(((DepotController) Core.bean("depot")).getObject() == null && _object.getId() == null)
			return;
		
		stock = stockService.findByArticleAndDepot(_object, ((DepotController) Core.bean("depot")).getObject());
		if(stock == null)
			stock = new Stock(); // creer un objet vide pour mettre les attributs aux valeurs par défaut
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
	
}
