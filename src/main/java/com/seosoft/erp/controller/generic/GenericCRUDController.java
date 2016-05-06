package com.seosoft.erp.controller.generic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.view.facelets.FaceletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.omnifaces.util.Components;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.seosoft.erp.controller.Core;
import com.seosoft.erp.controller._Constants;
import com.seosoft.erp.model.base.BaseEntity;
import com.seosoft.erp.model.entity.v2_UserNawat;
import com.seosoft.erp.service.generic.GenericService;
import com.seosoft.erp.util.components.ColumnModel;

public class GenericCRUDController<Type extends BaseEntity, Service extends GenericService<Type, String>> extends GenericController<Type,Service>{
	//protected String _moduleName;
	//protected Map<String,Action> _actions;
	protected Type _object;
	protected List<Type> _list;
	protected List<Type> _selectedObjects;
	@Autowired
	protected Service _service;
	protected DataModel _dataModel;
	protected List<String> _dataTableColumnsKeys;
	protected List<ColumnModel> _dataTableColumns;
	protected String _paramId = null; // used to store object's ID to modify, gathered from the URL
	// the following attributes are special attributes used for quick create & update dialogs on related modules
	public HashMap<String,GenericController<?,?>> _relatedModules;
	protected List<String> _boundComponentIds;
	protected String lastQuickDialogCallerComponentId;

	protected Type _objectSelectedAtQuickChoiceDialog; // used to store the selected object on quick choice dialog
	
	protected HashMap<String,Action> _relatedModulesActions;
	protected BaseEntity entitySubjectOfQuickDialog; // FIXME : SHOULD BE DYNAMIC and embeded on a list
	protected boolean quickDialogUpdateMode = false;
	protected Sort _sortBy;
	protected String _sortColumn;
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public GenericCRUDController() {
        if(!FacesContext.getCurrentInstance().isPostback()) {

		//checkAccessPermission();
		_actions = new HashMap<String,Action>();
		_relatedModules = new HashMap<String,GenericController<?,?>>();
		_relatedModulesActions = new HashMap<String,Action>();
		_boundComponentIds = new ArrayList<String>();
		registerDefaultActions();
		registerActions();
		_dataTableColumnsKeys = new ArrayList<String>();
		_dataTableColumns = new ArrayList<ColumnModel>();
		setSortColumn("id");
        }
	}
	
	@PostConstruct
	public void initialize() {
        if(!FacesContext.getCurrentInstance().isPostback()) {

		checkAccessPermission();
		prepareData();
		setUpdateObjectSubject();
		onDataReady();
        }
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
	
	/*
	 * To be implemented on the sub classes, this function is mainly used for quick create behavior on related modules
	 */
	public void prepareForCreateNew(){
		
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes protégées implémenté dans les sous-classes  |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	protected void prepareData() {
		// try to get parameter data
		_paramId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		System.out.println("isMainModule" + isMainModule());
		if(isMainModule()){
			_sortColumn = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sortBy");
			_sortColumn = (_sortColumn != null)?_sortColumn:"id";
			System.out.println("_sortColumn" + _sortColumn);
			setSortColumn(_sortColumn);
		}
		// get list of data
		handleFilter();
	}
	
	protected void onDataReady() {
		
	}
	
	public void addRelatedModule(String relatedModuleName){
		_relatedModules.put(relatedModuleName, null);
	}
	
	public void addRelatedModule(final GenericCRUDController<?,?> relatedBean){
		final String relatedModuleName = relatedBean.getModuleName();
		
		if(!_relatedModules.containsKey(relatedModuleName)){
			_relatedModules.put(relatedModuleName, relatedBean);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FaceletContext faceletContext = (FaceletContext) facesContext.getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
			String includePath = "/design1/ui/" + relatedModuleName + "/dialogs/form.xhtml";
			System.out.println("GenericCRUDController::addRelatedModule : includePath = " + includePath);
			try {
				faceletContext.includeFacelet(facesContext.getViewRoot(), includePath);
				//FacesContext.getCurrentInstance().getPartialViewContext().setRenderAll(true);
				System.out.println("CC :: " + faceletContext);
				System.out.println("EE :: " + facesContext.getViewRoot());
				System.out.println("DD :: " + FacesContext.getCurrentInstance().getPartialViewContext());
				//(facesContext.getViewRoot(), includePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addRelatedModule(final GenericCRUDController<?,?> relatedBean, String componentId, Action postUpdateAction, Action preQuickUpdateDialogShowAction){
		final String relatedModuleName = relatedBean.getModuleName();
		// FIXME module._relatedModules.put(name,module);
		//_relatedModules.put(relatedModuleName, relatedBean);
		//relatedBean._boundComponentIds.add(componentId);
		/*relatedBean._relatedModulesActions.put(relatedModuleName +"PostUpdateAction", postUpdateAction);
		relatedBean._relatedModulesActions.put(relatedModuleName +"PreQuickUpdateDialogShowAction", preQuickUpdateDialogShowAction);		
		_actions.put("quickNouveau" + WordUtils.capitalize(relatedModuleName), new Action(){
			@Override
			public void run() {
				System.out.println("quickNouveau" + WordUtils.capitalize(relatedModuleName) + " CLICK");
				relatedBean.setQuickDialogUpdateMode(false);
				relatedBean.setEntitySubjectOfQuickDialog(_object);
				relatedBean.prepareForCreateNew();
				RequestContext.getCurrentInstance().update("dialog"+ WordUtils.capitalize(relatedModuleName));
			}
		});
		
		_actions.put("quickUpdate" + WordUtils.capitalize(relatedModuleName), new Action(){
			@Override
			public void run() {
				System.out.println("quickUpdate" + WordUtils.capitalize(relatedModuleName) + "  CLICK");
				relatedBean.setQuickDialogUpdateMode(true);
				relatedBean._relatedModulesActions.get(relatedModuleName+"PreQuickUpdateDialogShowAction").run();
				relatedBean.setEntitySubjectOfQuickDialog(_object);
				RequestContext.getCurrentInstance().update("dialog"+ WordUtils.capitalize(relatedModuleName));
			}
		});*/
	}
	
	public void handleFilter(){
		Specification<Type> spec = null;
		_list = _service.findAll(spec, _sortBy);
		_dataModel = new DataModel(_list);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//


		
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Actions du controlleur |||||||||||| ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	

	public void runAction(String actionName, String callerComponentId){
		this.lastQuickDialogCallerComponentId = callerComponentId;
		runAction(actionName);
	}
	
	public void runAction(String actionName){
		System.out.println("runAction: " + actionName);
		Action action = _actions.get(actionName);
		if(action != null){
			action.run();
			if(actionName.equals("persist")){
				System.out.println("Module " + _moduleName);
				if(_actions.get("postPersist") != null)_actions.get("postPersist").run();
			}
		}
		else{
			System.out.println("GenericController : Action not found ############### ############### ############### ###############");
		}
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
				System.out.println("SAVE : "  + _moduleName);

				boolean isNewInsert = (_object.getId()==null)?true:false;
				_service.save(_object); 
				String message = "Enregistrement '" + StringUtils.capitalize(_moduleName) + "' effectué avec succés ! ";
				message = (isNewInsert)?message:"Modification '" + StringUtils.capitalize(_moduleName) + "' effectuée avec succés ! ";
				FacesMessage msg = new FacesMessage(message);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("delete", new Action(){
			@Override
			public void run() {
				try{
					_service.delete(_object);
					FacesMessage msg = new FacesMessage(_moduleName + " supprimé");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}catch(Exception ex){
					FacesMessage msg = new FacesMessage("Impossible de supprimer cet enregistrement car il est lié avec d'autres données.");
					msg.setSeverity(FacesMessage.SEVERITY_FATAL);
					FacesContext.getCurrentInstance().addMessage("errorMsg", msg);
				}
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
						}else{
							_object = _list.get(_list.size() - 1);
						}
						onDataReady();
						return;
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
						}else{
							_object = _list.get(0);
						}
						onDataReady();
						return;
					}
				}	
			}
		});
		
		// called after the action persist is done. e.g : in the case of QuickDialog (create or update) 
		_actions.put("postPersist", new Action(){
			@Override
			public void run() {
				if(!quickDialogUpdateMode && !Core.getCurrentModuleName().equals(_moduleName) )
					_list.add(_object);
				
				if(_list.indexOf(_object) != -1) // if creating a new object
					_list.set(_list.indexOf(_object), _object); // replace the object at the list so that when navigating (show previous / next ) not the old version of the object would be shown
				
				// FIXME : FOR TEST PURPOSE ONLY
				// called after quick dialog save
				if(entitySubjectOfQuickDialog != null) {
					_actions.get("PostQuickUpdate").run();
					
					//_relatedModulesActions.get(_moduleName+"PostUpdateAction").run();
					System.out.println("YYYYYYY " + _moduleName + " PostUpdateAction ");
					
					// update all related components 
					for(String componentId : _boundComponentIds){
						System.out.println("YYYYYYY " + _moduleName + " " +  componentId);
						UIComponent component = getComponentById(componentId, FacesContext.getCurrentInstance().getViewRoot());
						//RequestContext.getCurrentInstance().update(component.getClientId() + ":selectOneMenu");
						RequestContext.getCurrentInstance().update(component.getClientId() + ":selectOneMenu" );
					}
				}
			}
		});

		_actions.put("redirectToFormView", new Action(){
			@Override
			public void run() {
				if(_selectedObjects.size() > 0){
					String id = _selectedObjects.get(_selectedObjects.size() -1).getId();
					String url = _Constants.base_url + _moduleName +"/form?id=" + id + "&sortBy="+ _sortColumn;
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect( url);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		_actions.put("updateDataTableColumns", new Action(){
			@Override
			public void run() {
				System.out.println("updateDataTableColumnsnew Action()");
				UIComponent dataTable = FacesContext.getCurrentInstance().getViewRoot().findComponent(":mainForm:dataTable");
		        dataTable.setValueExpression("sortBy", null);
				_dataTableColumns = new ArrayList<ColumnModel>();   
		         
		        for(String columnKey : GenericCRUDController.this._dataTableColumnsKeys) {
		            String key = columnKey.trim(); 
		            _dataTableColumns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
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
	
	public String[] getRelatedModules(){
		Set<String> keys = _relatedModules.keySet();
		/*System.out.println("getRelatedModules:" + _moduleName + " : " + _relatedModules.size());
		System.out.println("getRelatedModules:" + _moduleName +" : " + _actions.size());
		for(String moduleName : keys){
			System.out.println("getRelatedModules:" + _moduleName +" : " + moduleName);
		}
		for(String actionName : _actions.keySet()){
			System.out.println("getRelatedModules:" + _moduleName + ":" + actionName);
		}*/
		return keys.toArray(new String[keys.size()]);
	}
	
	public String[] getDialogIncludes(){
		Set<String> keys = _relatedModules.keySet();
		/*System.out.println("getRelatedModules:" + _moduleName + " : " + _relatedModules.size());
		System.out.println("getRelatedModules:" + _moduleName +" : " + _actions.size());
		for(String moduleName : keys){
			System.out.println("getRelatedModules:" + _moduleName +" : " + moduleName);
		}
		for(String actionName : _actions.keySet()){
			System.out.println("getRelatedModules:" + _moduleName + ":" + actionName);
		}*/
		String[] dialogIncludes = keys.toArray(new String[keys.size()]);
		for(int i = 0; i < dialogIncludes.length; i++){
			dialogIncludes[i] = "//design1//ui//" + dialogIncludes[i] + "//dialogs//form.xhtml";
			
		}
		
		return dialogIncludes;
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

	public List<ColumnModel> get_dataTableColumns() {
		return _dataTableColumns;
	}

	public void set_dataTableColumns(List<ColumnModel> _dataTableColumns) {
		this._dataTableColumns = _dataTableColumns;
	}

	public List<String> get_dataTableColumnsKeys() {
		return _dataTableColumnsKeys;
	}

	public void set_dataTableColumnsKeys(List<String> _dataTableColumnsKeys) {
		this._dataTableColumnsKeys = _dataTableColumnsKeys;
	}

	public List<Type> getSelectedObjects() {
		return _selectedObjects;
	}

	public void setSelectedObjects(List<Type> selectedObjects) {
		this._selectedObjects = selectedObjects;
	}
		
	public String getParamId() {
		return _paramId;
	}

	public void setParamId(String paramId) {
		this._paramId = paramId;
	}

	public BaseEntity getEntitySubjectOfQuickDialog() {
		return entitySubjectOfQuickDialog;
	}

	public void setEntitySubjectOfQuickDialog(BaseEntity entitySubjectOfQuickDialog) {
		this.entitySubjectOfQuickDialog = entitySubjectOfQuickDialog;
	}

	public boolean isQuickDialogUpdateMode() {
		return quickDialogUpdateMode;
	}

	public void setQuickDialogUpdateMode(boolean quickDialogUpdateMode) {
		this.quickDialogUpdateMode = quickDialogUpdateMode;
	}
	
	public String getSortColumn() {
		return _sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this._sortColumn = sortColumn;
		_sortBy = new Sort(Sort.Direction.ASC, sortColumn);
	}

	public Type getObjectSelectedAtQuickChoiceDialog() {
		return _objectSelectedAtQuickChoiceDialog;
	}

	public void setObjectSelectedAtQuickChoiceDialog(Type objectSelectedAtQuickChoiceDialog) {
		this._objectSelectedAtQuickChoiceDialog = objectSelectedAtQuickChoiceDialog;
		System.out.println("_objectSelectedAtQuickChoiceDialog : " + _objectSelectedAtQuickChoiceDialog + " :" + _moduleName);
	}
	
	public void registerAction(String name, Action action){
		_actions.put(name, action);
	}
	
}
