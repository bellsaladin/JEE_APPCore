package com.seosoft.erp.controller.generic;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.seosoft.erp.controller.BaseController;
import com.seosoft.erp.controller.Core;
import com.seosoft.erp.model.base.BaseEntity;
import com.seosoft.erp.model.entity.Favori;
import com.seosoft.erp.model.entity.UserNawat;
import com.seosoft.erp.model.entity.v2_UserNawat;
import com.seosoft.erp.service.generic.GenericService;
import com.seosoft.erp.service.parametrage.FavoriService;

public class GenericCRUDController<Type extends BaseEntity, Service extends GenericService<Type, String>> extends GenericController{
	protected Type _object;
	protected List<Type> _list;
	protected List<Type> _selectedObjects;
	protected Map<String,Action> _actions;
	@Autowired
	protected Service _service;
	protected DataModel _dataModel;
	protected String _moduleName;
	protected String _paramId; // used to set object to modify
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public GenericCRUDController() {
		//checkAccessPermission();
		
		_actions = new HashMap<String,Action>();
		registerDefaultActions();
		registerActions();
	}
	
	@PostConstruct
	public void initialize() {
		checkAccessPermission();
		// try to get parameter data
		_paramId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		prepareData();
		setUpdateObjectSubject();
		onDataReady();
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes publics   |||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public void setUpdateObjectSubject(){
		System.out.println("paramId : " + _paramId);
		if(_paramId != null){
			_object = _service.findOne(_paramId);
		}
	}
	
	public Type getLastSelectedObject(){
		if(_selectedObjects != null && _selectedObjects.size() > 0)
			return _selectedObjects.get(_selectedObjects.size() - 1);
		return null;
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes protégées implémenté dans les sous-classes  |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	protected void prepareData() {
		_list = _service.findAll();
		_dataModel = new DataModel(_list);
	}
	
	protected void onDataReady() {
		
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
		_actions.put("redirectUpdate", new Action(){
			@Override
			public void run() {
				_service.save(_object);
				FacesMessage msg = new FacesMessage(_moduleName + " modifié");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("persist", new Action(){
			@Override
			public void run() {
				_service.save(_object);
				FacesMessage msg = new FacesMessage(_moduleName + " enregistré");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("delete", new Action(){
			@Override
			public void run() {
				_service.delete(_object);
				FacesMessage msg = new FacesMessage(_moduleName + " supprimé");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("getPrevious", new Action(){
			@Override
			public void run() {
				for(Object object: _list){
					if(object.equals(_object)){
						int indexOfCurrentObject = _list.indexOf(object);
						if(indexOfCurrentObject - 1 >= 0){
							_object = _list.get(indexOfCurrentObject - 1);
							return;
						}else{
							_object = _list.get(_list.size() - 1);
						}
					}
				}	
			}
		});
		_actions.put("getNext", new Action(){
			@Override
			public void run() {
				for(Object object: _list){
					if(object.equals(_object)){
						int indexOfCurrentObject = _list.indexOf(object);
						if(indexOfCurrentObject + 1 < _list.size()){
							_object = _list.get(indexOfCurrentObject + 1);
							return;
						}else{
							_object = _list.get(0);
						}
					}
				}	
			}
		});
	}
	
	protected void registerActions() {
		// implemented on subClass
	}
	
	protected void checkAccessPermission(){
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		v2_UserNawat currentUser  = (v2_UserNawat) context.getBean("currentUser");
		if(!currentUser.getSuperAdministrateur()){
			/*try {
				FacesContext.getCurrentInstance().getExternalContext().dispatch("forbidden");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}

	public void onRowSelect(SelectEvent event) throws IOException {
		Type selectedObject = ((Type) event.getObject());
		Faces.redirect(Faces.getRequestContextPath() + _moduleName + "/view?id=" + selectedObject.getId());
	}
	
	public void doFilter() {
		FacesMessage msg = new FacesMessage("Do Filter");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| ObjectDataModel |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public class DataModel extends ListDataModel<Type> implements SelectableDataModel<Type> {
		
		public DataModel(List<Type> data) {
			super(data);
		}

		@SuppressWarnings("unchecked")
		@Override
		public Type getRowData(String rowKey) {

			List<Type> list = (List<Type>) getWrappedData();

			for (Type user : list) {
				if (user.getId().equals(rowKey))
					return user;
			}

			return null;
		}

		@Override
		public Object getRowKey(Type user) {
			return user.getId();
		}

	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public Type getObject() {
		return _object;
	}

	public void setObject(Type object) {
		this._object = object;
	}
	
	public List<Type> getList() {
		return _list;
	}

	public void setList(List<Type> list) {
		this._list = list;
	}

	public DataModel getDataModel() {
		return _dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this._dataModel = dataModel;
	}

	public List<Type> getSelectedObjects() {
		return _selectedObjects;
	}

	public void setSelectedObjects(List<Type> selectedObjects) {
		this._selectedObjects = selectedObjects;
	}

	public String getModuleName() {
		return _moduleName;
	}

	public void setModuleName(String moduleName) {
		this._moduleName = moduleName;
	}
		
	public String getParamId() {
		return _paramId;
	}

	public void setParamId(String paramId) {
		this._paramId = paramId;
	}

}
