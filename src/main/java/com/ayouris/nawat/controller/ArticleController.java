package com.ayouris.nawat.controller;

import java.io.Serializable;

import javax.inject.Named;

import com.ayouris.nawat.controller.generic.GenericController;
import com.ayouris.nawat.model.entity.Article;
import com.ayouris.nawat.service.parametrage.ArticleService;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ArticleController extends GenericController<Article, ArticleService> implements Serializable {
	private static final long serialVersionUID = -986331859933454787L;

	protected void prepareData(){
		super.prepareData();
		moduleName = "article";
		object = new Article();
		object.setLibelle("sssss");
		object.setType("Service");
	}
	
	/*public void onProfilChange() {
		ProfilDuplicateController profilDuplicateController = (ProfilDuplicateController) getApplicationContext().getBean(
				"profilDuplicateController");
		profilDuplicateController.setProfilId(object.getProfil().getId());
	}*/

}
