package com.seosoft.erp.util.components.composite;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.primefaces.context.RequestContext;
import com.seosoft.erp.controller.Core;
import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;

@FacesComponent("oneEntitySelectMenuComponent")
public class OneEntitySelectMenuComponent extends UINamingContainer {
	private enum PropertyKeys {
		entityName,
		callingBean,
		relatedEntityPropertyName;
	}
	
	private String clientId;
	private String componentId;
	private String relatedEntityPropertyName; // e.g : respresentantPricipal, contact
	
	//private String entityName;
	//private BaseEntity value;
	
	@Override
    public void encodeBegin(FacesContext context) throws NullPointerException, IOException {
		System.out.println("OneEntitySelectMenuComponent::encodeBegin() : " + clientId);
		String entityName = (String)getAttributes().get("entityName");	
		
		if(entityName != null){
			final GenericCRUDController<?, ?> callingBean = ((GenericCRUDController<?, ?>)getAttributes().get("callingBean"));
			final GenericCRUDController<?, ?> relatedBean = ((GenericCRUDController<?, ?>)Core.bean(entityName));
			System.out.println("OneEntitySelectMenuComponent::encodeBegin() : callingBean : " + callingBean);
			System.out.println("OneEntitySelectMenuComponent::encodeBegin() : entityName : " + entityName);
			System.out.println(" Value : " + entityName + " RelatedBen " + relatedBean);
			callingBean.addRelatedModule(relatedBean);
			getStateHelper().put(PropertyKeys.entityName, entityName);
			getStateHelper().put(PropertyKeys.callingBean, callingBean);
		}
        super.encodeBegin(context);
    }
	
	
	public void quickUpdate() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		// ******** INIT VALUES
		this.componentId =  (String) getAttributes().get("clientId");
		
		if(getAttributes().get("relatedEntityPropertyName") != null)
			this.relatedEntityPropertyName =  (String) getAttributes().get("relatedEntityPropertyName");
		
		// ******** code
		final String entityName = (String)getAttributes().get("entityName");	

		final GenericCRUDController<?, ?> callingBean = ((GenericCRUDController<?, ?>)getAttributes().get("callingBean"));
		final GenericCRUDController<?, ?> relatedBean = ((GenericCRUDController<?, ?>)Core.bean(entityName));
		System.out.println("Calling bean : " + callingBean);
		System.out.println("Calling bean object : " + callingBean.getObject());
		System.out.println("Related bean : " + relatedBean);
		relatedBean.setQuickDialogUpdateMode(true);
		// OLD CODE REPLACED BY SOME JAVA REFLECTION
		//		relatedBean._relatedModulesActions.get(relatedModuleName+"PreQuickUpdateDialogShowAction").run();
		
		// ------------------------ START - INVOKE GETTER USING REFLECTION ----------------------
		
		try {
			System.out.println("OneEntitySelectMenuComponent : " + WordUtils.capitalize(relatedEntityPropertyName) + "  " + callingBean + "  " + relatedBean);
			Object relatedEntityValue = PropertyUtils.getProperty( callingBean.getObject(), relatedEntityPropertyName);
			System.out.println("OneEntitySelectMenuComponent : relatedEntityValue " + relatedEntityValue);
			PropertyUtils.setProperty(relatedBean, "object", relatedEntityValue);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		// ------------------------- END - INVOKE GETTER USING REFLECTION ---------------------
		relatedBean.setEntitySubjectOfQuickDialog(callingBean.getObject());
		String targetComponentId = "dialog"+ WordUtils.capitalize(entityName);
		// update new dialog that is going to be open
		RequestContext.getCurrentInstance().update(targetComponentId);
		// update origin dialog so that ajax save would still working  
		//RequestContext.getCurrentInstance().update("dialog"+ WordUtils.capitalize(callingBean.getModuleName()));
		
		//RequestContext.getCurrentInstance().addPartialUpdateTarget(((GenericCRUDController<?, ?>)Core.bean()).getComponentById(("dialogContact")).getClientId());
				
		// ------------------------- BEGIN - INVOKE SETTER USING REFLECTION ---------------------
		
		relatedBean.registerAction("PostQuickUpdate", new Action(){

			@Override
			public void run() {
				//  BELOW CODE SHOULD BE BOUND TO 'POST_UPDATE' OF RELATED BEAN
				try {
					PropertyUtils.setProperty(callingBean.getObject(), relatedEntityPropertyName, relatedBean.getObject());
					//relatedBean.setObject(((BaseEntity)relatedEntityValue));
					//PropertyUtils.setProperty( ((GenericCRUDController<?, ?>)Core.bean()).getObject(), "relatedEntityName", relatedEntityValue);
					
					RequestContext.getCurrentInstance().update("dialog"+ WordUtils.capitalize(entityName));
					//RequestContext.getCurrentInstance().update("mainForm");
					RequestContext.getCurrentInstance().update(componentId +":selectOneMenu");
					RequestContext.getCurrentInstance().update("mainForm:messages");
					
					System.out.println("PostQuickUpdate:" + entityName + ":componentId : '" + componentId + ":selectOneMenu'");
					System.out.println("PostQuickUpdate:" + entityName + ":componentId (callingBean.getComponentById(clientId)): '" + callingBean.getComponentById(clientId) );
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
		
		// ------------------------- END - INVOKE SETTER USING REFLECTION ---------------------
		
        //System.out.println("oneEntitySelectMenuComponent : " + getAttributes().get("entityClass"));
		System.out.println("oneEntitySelectMenuComponent::componentId : " + componentId);
        
		getStateHelper().put(PropertyKeys.relatedEntityPropertyName, getAttributes().get("relatedEntityPropertyName"));
	}
	
	public void quickCreate() {
		
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	/*public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}*/

	@Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }
		
}