package com.seosoft.erp.controller;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController implements ApplicationContextAware {

	private static ApplicationContext springAppContext;

	protected Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected boolean hasRole(String roleName) {
		return authentication.getAuthorities().contains(new SimpleGrantedAuthority(roleName));
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		springAppContext = appContext;
	}

	public static ApplicationContext getApplicationContext() {
		return springAppContext;
	}

}
