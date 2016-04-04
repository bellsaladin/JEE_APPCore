package com.seosoft.erp.controller.generic;

import java.util.Map;

import com.seosoft.erp.controller.BaseController;
import com.seosoft.erp.controller.Core;
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
	
	protected boolean isMainModule(){
		System.out.println("Core.getCurrentModuleName() " + Core.getCurrentModuleName() );
		System.out.println("this.getModuleName() " + this.getModuleName() );
		return Core.getCurrentModuleName().equals(this.getModuleName());
	}
	
	public String[] getRelatedModules(){
		// NOTE : This is a mock getter required because it's called every at some level on the view side (xhtml)
		return new String[]{}; //empty string array
	}
	
//	public Class<Type> getClassType(){
//		return Type;
//	}
}
