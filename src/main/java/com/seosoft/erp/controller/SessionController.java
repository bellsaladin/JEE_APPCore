package com.seosoft.erp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.model.entity.Favori;
import com.seosoft.erp.service.parametrage.FavoriService;
import com.seosoft.erp.util.scopes.session.SpringSessionScoped;

@Named
//@ManagedBean
@SpringSessionScoped
public class SessionController {
	
	@Autowired
	private FavoriService _favoriService;
	private List<Favori> favoris; 
	
	@PostConstruct
	public void initialize() {
		// try to get parameter data
		favoris = _favoriService.findAll();
	}
	
	public void addToFavorites(String moduleName) {
		System.out.println("SessionController::addToFavorites()");
		//
		for(Favori favori : favoris){
			if(favori.getNomModule() == moduleName){
				FacesMessage msg = new FacesMessage("Ce module est déjà ajouté dans vos favoris !");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return;
			}
		}
		// create new favori
		Favori favori = new Favori();
		favori.setNomModule(moduleName);
		_favoriService.save(favori);
		// show msg
		FacesMessage msg = new FacesMessage("Module '" + moduleName +"' added to favoris !");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// add created object to list 
		favoris.add(favori);
	}

	public List<Favori> getFavoris() {
		return favoris;
	}

	public void setFavoris(List<Favori> favoris) {
		this.favoris = favoris;
	}
}
