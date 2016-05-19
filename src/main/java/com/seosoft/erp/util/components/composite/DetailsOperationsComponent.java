package com.seosoft.erp.util.components.composite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.selectonemenu.SelectOneMenu;

import com.seosoft.erp.controller.Core;
import com.seosoft.erp.controller.VilleController;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.base.BaseEntity;
import com.seosoft.erp.model.entity.Fournisseur;
import com.seosoft.erp.model.entity.Pays;
import com.seosoft.erp.model.entity.Ville;
import com.seosoft.erp.model.entity.embeddable.Adresse;
import com.seosoft.erp.service.business.BonReceptionService;
import com.seosoft.erp.service.business.DemandePrixService;
import com.seosoft.erp.service.business.FactureFournisseurService;
import com.seosoft.erp.util.components.ColumnModel;

@FacesComponent("detailsOperations")
public class DetailsOperationsComponent extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------
    
    // Actions ------------------------------------------------------------------------------------
	
    /**
     * Set the selected and available values of the day, month and year fields based on the model.
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        super.encodeBegin(context);
    }
    
    /**
     * Update the 'codePostal' InputText component.
     */
    public void prepareData() {
    	FacesContext context = FacesContext.getCurrentInstance();
    	
    	// Update codePostal inputText component
        // context.getPartialViewContext().getRenderIds().add(adresseCodePostalComponent.getClientId(context));
    }

    // Helpers ------------------------------------------------------------------------------------

    // Getters/setters ----------------------------------------------------------------------------
    
    /**
     * Returns the component family of {@link UINamingContainer}.
     * (that's just required by composite component)
     */
    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }
    
    public List<?> getData(String entityName) {
    	//String entityName = "demandePrix";
    	if(entityName.equals("demandePrix")){
	    	GenericCRUDController<?, ?> bean = ((GenericCRUDController<?, ?>)Core.bean(entityName));
	    	DemandePrixService service = ((DemandePrixService) bean.getService());
	    	return service.findByFournisseur((Fournisseur)((GenericCRUDController<?, ?>)Core.bean("fournisseur")).getObject());
    	}
    	if(entityName.equals("bonReception")){
	    	GenericCRUDController<?, ?> bean = ((GenericCRUDController<?, ?>)Core.bean(entityName));
	    	BonReceptionService service = ((BonReceptionService) bean.getService());
	    	return service.findByFournisseur((Fournisseur)((GenericCRUDController<?, ?>)Core.bean("fournisseur")).getObject());
    	}
    	
    	if(entityName.equals("factureFournisseur")){
	    	GenericCRUDController<?, ?> bean = ((GenericCRUDController<?, ?>)Core.bean(entityName));
	    	FactureFournisseurService service = ((FactureFournisseurService) bean.getService());
	    	return service.findByFournisseur((Fournisseur)((GenericCRUDController<?, ?>)Core.bean("fournisseur")).getObject());
    	}
        return new ArrayList<Object>();
    }
    
    public List<ColumnModel> getColumns() {
    	String entityName = "demandePrix";
    	 GenericCRUDController<?, ?> bean = ((GenericCRUDController<?, ?>)Core.bean(entityName));
        return bean.get_dataTableColumns();
    }

}