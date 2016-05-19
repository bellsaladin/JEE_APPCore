package com.seosoft.erp.controller;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController implements ApplicationContextAware {

	private static ApplicationContext springAppContext;
	
	@PersistenceContext
    private EntityManager entityManager;

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
	
	
	public UIComponent getComponentById(String componentId){
		return getComponentById(componentId, FacesContext.getCurrentInstance().getViewRoot(), 0);
	}
	
	public UIComponent getComponentById(String componentId, UIComponent component){
		return getComponentById(componentId, component, 0);
	}
	
	public UIComponent getComponentById(String componentId, UIComponent component, int level){
		UIComponent foundComponent = null;
		List<UIComponent> childrenComponents = component.getChildren();
		for(UIComponent childComponent : childrenComponents){
			//System.out.println(level + " : " + component.getClientId() + " : " + childComponent.getClientId());
			if(childComponent.getId().equals(componentId))
				return childComponent;
			else
				foundComponent =  getComponentById(componentId, childComponent, level + 1);
			//System.out.println("foundComponent : "  + ((foundComponent != null)?foundComponent.getClientId():"null"));
			if(foundComponent != null){	
				return foundComponent;
			}
		}
		return foundComponent;
	}
	
	
	public HttpSession getSession(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		return session;
	}
	
	public Object getValueFromSession(String key){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		return session.getValue(key);
	}
	
	public void setValueToSession(String key, Object value){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 session.putValue(key, value);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public EntityManager getTransactionalEntityManager() {
		return entityManager.getEntityManagerFactory().createEntityManager();
	}

}
