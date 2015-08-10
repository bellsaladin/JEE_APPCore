package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ayouris.nawat.model.entity.Ecole;
import com.ayouris.nawat.model.entity.Theme;
import com.ayouris.nawat.model.entity.UserNawat;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.parametrage.EcoleService;
import com.ayouris.nawat.service.parametrage.UserService;
import com.ayouris.nawat.service.parametrage.v2_UserService;
import com.ayouris.nawat.util.scopes.session.SpringSessionScoped;

@Named
@SpringSessionScoped
public class SessionManager extends BaseController implements Serializable {
	private final static String LANGUE_AR = "ar";
	private final static String DIRECTION_LTR = "ltr";
	private final static String DIRECTION_RTL = "rtl";
	private static final long serialVersionUID = -1795802343834566516L;

	private Ecole currentEcole;
	private v2_UserNawat currentUser;

	private Locale locale;
	private Theme theme;

	@Inject
	v2_UserService userService;

	@Inject
	EcoleService ecoleService;

	@Bean(name = "currentEcole")
	@SpringSessionScoped
	public Ecole getCurrentEcole() {
		return currentEcole;
	}

	@Bean(name = "currentUser")
	@SpringSessionScoped
	public v2_UserNawat getCurrentUser() {
		return currentUser;
	}

	@PostConstruct
	public void initialize() {
		System.out.println("SessionManager.Created");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println("sssssssss" + currentPrincipalName);
		System.out.println("sssssssss" + authentication.getDetails());
		theme = new Theme("bootstrap", "#BABEC5", "#FAFAFA");
		currentUser = userService.findOne("E003000000001");
		currentEcole = ecoleService.findOne(currentUser.getEcoleId());
		// locale = getFacesContext().getViewRoot().getLocale();
		locale = new Locale(currentEcole.getLangueInterface());
		System.out.println("SessionManager::currentEcole::" + currentEcole);
	}

	public Locale getLocale() {
		return locale;
	}

	public Theme getTheme() {
		return theme;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void setLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public synchronized String getDirection() {
		return (getLanguage() == LANGUE_AR) ? DIRECTION_RTL : DIRECTION_LTR;
	}

	public boolean isArabic() {
		return (getLanguage() == LANGUE_AR) ? true : false;
	}

	public void setCurrentUser(v2_UserNawat user) {
		this.currentUser = user;
		System.out.println("SessionManager : " + currentUser.getDisplayText());
	}

}
