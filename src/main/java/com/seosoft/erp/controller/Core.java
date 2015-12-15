package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.seosoft.erp.controller.generic.GenericController;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class Core implements Serializable{
	private static final long serialVersionUID = 9100562508344707166L;
		
	public static GenericController<?,?> _bean; // current bean handled by the core
	private static String currentModuleName;
	
    public Core() {
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
		return _bean;
    }
	
	public static GenericController<?,?> bean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
        return GenericController.class.cast(context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "Controller}", GenericController.class));
    }
	
	/*
    * Charge le nom du module en se basant sur l'url fournie en élémninant la partie base_url de celle-ci pour en 
    * retournant le premier résultat du split fait avec un slash
    */
    private static String getModuleNameFromUrl() {
    	HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String requestUrl = req.getRequestURL().toString();
		System.out.println("url :: " + requestUrl); 
		requestUrl = requestUrl.substring(_Constants.base_url.length()); // remove base url from the request url
		System.out.println("url :: " + requestUrl); 
		String[] explodedUrl = requestUrl.split("/");
		return explodedUrl[0];
    }

	public static String getCurrentModuleName() {
		return currentModuleName;
	}

	public static void setCurrentModuleName(String currentModuleName) {
		Core.currentModuleName = currentModuleName;
	}
    
}