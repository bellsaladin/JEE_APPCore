package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.controller.generic.GenericController;
import com.seosoft.erp.util.scopes.request.SpringRequestScoped;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringRequestScoped
public class Core implements Serializable{
	private static final long serialVersionUID = 9100562508344707166L;
		
	public static GenericController<?,?> _bean; // current bean handled by the core
	private static String currentModuleName;
	private static HashMap<String,GenericController<?,?>> _registredBeans;
	
    public Core() {
    	_registredBeans = new HashMap<String,GenericController<?,?>>();
    	currentModuleName = getModuleNameFromUrl();
    	String managedBeanName = currentModuleName + "Controller";
    	loadManagedBeanByName(managedBeanName);
	}
    
    /*
     * Fonction "magique" :P
     * Charge le Controller adapté en se basant sur l'url de la requête en cours
     */
    
    private void loadManagedBeanByName(String managedBeanName){
        FacesContext context = FacesContext.getCurrentInstance();
        _bean = GenericController.class.cast(context.getApplication().evaluateExpressionGet(context, "#{" + managedBeanName + "}", GenericController.class));
    }
    
    /*
     *  retourn le bean en cours
     */
    
	public static GenericController<?,?> bean() {
		return bean(getModuleNameFromUrl());
		//return _bean;
    }
	
	public static GenericController<?,?> bean(String beanName) {
		System.out.println("Core::bean(String name) " + beanName);
		beanName = StringUtils.uncapitalize(beanName);
		FacesContext context = FacesContext.getCurrentInstance();
		GenericController<?, ?> bean = GenericController.class.cast(context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "Controller}", GenericController.class));
		_registredBeans.put(beanName, bean);
        return bean;
    }
	
	/*
    * Charge le nom du module en se basant sur l'url fournie en élémninant la partie base_url de celle-ci pour en 
    * retournant le premier résultat du split fait avec un slash
    */
    private static String getModuleNameFromUrl() {
    	HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String requestUrl = req.getRequestURL().toString();
		//System.out.println("url :: " + requestUrl); 
		requestUrl = requestUrl.substring(_Constants.base_url.length()); // remove base url from the request url
		String[] explodedUrl = requestUrl.split("/");
		return explodedUrl[0];
    }

	public static String getCurrentModuleName() {
		return currentModuleName;
	}

	public static void setCurrentModuleName(String currentModuleName) {
		Core.currentModuleName = currentModuleName;
	}

	public HashMap<String, GenericController<?, ?>> getRegistredBeans() {
		return _registredBeans;
	}
	
	public HashMap<String, GenericController<?, ?>> getNestedRelatedModules() {
		HashMap<String,GenericController<?,?>> nestedRegistredBeans = new HashMap<String,GenericController<?,?>>();
		//for(GenericController<?,?> bean : _registredBeans.get)
		return nestedRegistredBeans;
	}
    
}