package com.ayouris.nawat.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.model.base.BaseEntity;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.generic.GenericService;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

public abstract  class GenericController<Type extends BaseEntity, Service extends GenericService<Type, String>> extends BaseController{
	protected Type object;
	protected List<Type> list;	
	protected List<Type> selectedObjects;
	@Autowired
	protected Service service;
	protected DataModel dataModel;
	protected String moduleName;
	protected String paramId; // used to set object to modify
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	@PostConstruct
	public void initialize() {
		// try to get parameter data
		paramId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		
		prepareData();
		setUpdateObjectSubject();
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes publics   |||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public void setUpdateObjectSubject(){
		System.out.println("paramId : " + paramId);
		if(paramId != null){
			object = service.findOne(paramId);
		}
	}
	
	public Type getLastSelectedObject(){
		return list.get(list.size() - 1);
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes protégées implémenté dans les sous-classes  |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	protected void prepareData() {
		list = service.findAll();
		dataModel = new DataModel(list);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//


		
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Déclanché suite à une action faite ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void onRowSelect(SelectEvent event) throws IOException {
		Type selectedObject = ((Type) event.getObject());
		Faces.redirect(Faces.getRequestContextPath() + moduleName + "/view?id=" + selectedObject.getId());
	}

	public void doPersist() {
		service.save(object);
		FacesMessage msg = new FacesMessage("Do Edit" + moduleName);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void doDelete() {
		service.delete(object);
		FacesMessage msg = new FacesMessage("Do Delete" + moduleName);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void doGetPrevious() {
		for(Object object: list){
			if(this.object.equals(object)){
				int indexOfCurrentObject = list.indexOf(object);
				if(indexOfCurrentObject - 1 >= 0){
					this.object = list.get(indexOfCurrentObject - 1);
					return;
				}else{
					this.object = list.get(list.size() - 1);
				}
			}
		}
			
	}
	
	public void doGetNext() {
		for(Object object: list){
			if(this.object.equals(object)){
				int indexOfCurrentObject = list.indexOf(object);
				if(indexOfCurrentObject + 1 < list.size()){
					this.object = list.get(indexOfCurrentObject + 1);
					return;
				}else{
					this.object = list.get(0);
				}
			}
		}
			
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
		return object;
	}

	public void setObject(Type object) {
		this.object = object;
	}
	
	public List<Type> getList() {
		return list;
	}

	public void setList(List<Type> list) {
		this.list = list;
	}

	public DataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public List<Type> getSelectedObjects() {
		return selectedObjects;
	}

	public void setSelectedObjects(List<Type> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
		
	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

}
