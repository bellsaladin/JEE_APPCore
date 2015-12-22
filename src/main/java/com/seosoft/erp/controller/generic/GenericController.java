package com.seosoft.erp.controller.generic;

import java.util.Map;

import com.seosoft.erp.controller.BaseController;
import com.seosoft.erp.model.base.BaseEntity;
import com.seosoft.erp.service.generic.GenericService;

public abstract class GenericController<Type extends BaseEntity, Service extends GenericService<Type, String>> extends BaseController{
	
	protected Map<String,Action> _actions;
	protected String _moduleName;
	
	
	public String getModuleName() {
		return _moduleName;
	}

	public void setModuleName(String moduleName) {
		this._moduleName = moduleName;
	}
	
	public String[] getRelatedModules(){
		return new String[]{}; //empty string array
	}
	
//	public Class<Type> getClassType(){
//		return Type;
//	}
}
