package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.seosoft.erp.model.entity.UserNawat;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class UserProfilController extends BaseController implements Serializable {
	private static final long serialVersionUID = -4408198587763708242L;

	private UserNawat userNawat;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		userNawat = (UserNawat) authentication.getDetails();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public UserNawat getUserNawat() {
		return userNawat;
	}

	public void setUserNawat(UserNawat user) {
		this.userNawat = user;
	}

}
