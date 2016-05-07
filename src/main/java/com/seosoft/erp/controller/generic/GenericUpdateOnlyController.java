package com.seosoft.erp.controller.generic;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.seosoft.erp.model.base.BaseEntity;
import com.seosoft.erp.service.generic.GenericService;

public class GenericUpdateOnlyController<Type extends BaseEntity, Service extends GenericService<Type, String>> extends GenericController<BaseEntity,GenericService<BaseEntity, String>>{
	
	protected Type _object;
	@Autowired
	protected Service _service;
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public GenericUpdateOnlyController() {
		_actions = new HashMap<String,Action>();
		registerDefaultActions();
		registerActions();
		
	}
	
	@PostConstruct
	public void initialize() {
		//prepareData();
		_object = _service.findOne("00000001");
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes publics   |||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	

	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes protégées implémenté dans les sous-classes  |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	protected void prepareData() {
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//


		
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Actions du controlleur |||||||||||| ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public void runAction(String actionName){
		Action action = _actions.get(actionName);
		if(action != null) 
			action.run();
		else
			System.out.println("GenericController : Action not found ############### ############### ############### ###############");
	}
	
	
	private void registerDefaultActions() {
		_actions.put("persist", new Action(){
			@Override
			public void run() {
				_object.setId("00000001"); // set the id statically so that it can be saved upon the same entity 
				_service.save(_object); 
				String message = "Modification '" + StringUtils.capitalize(_moduleName) + "' effectuée avec succés ! ";
				FacesMessage msg = new FacesMessage(message);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
	}
	
	protected void registerActions() {
		// implemented on subClass
	}

	public void doFilter() {
		FacesMessage msg = new FacesMessage("Do Filter");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public Type getObject() {
		return _object;
	}

	public void setObject(Type _object) {
		this._object = _object;
	}

	public Service getService() {
		return _service;
	}

	public void setService(Service _service) {
		this._service = _service;
	}

}
