package com.seosoft.erp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
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
		Favori favori = new Favori();
		favori.setNomModule(moduleName);
		_favoriService.save(favori);
		// update list
		setFavoris(_favoriService.findAll());
	}

	public List<Favori> getFavoris() {
		return favoris;
	}

	public void setFavoris(List<Favori> favoris) {
		this.favoris = favoris;
	}
}
