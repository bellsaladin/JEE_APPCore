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
import com.seosoft.erp.model.entity.Pays;
import com.seosoft.erp.model.entity.Ville;
import com.seosoft.erp.model.entity.embeddable.Adresse;

@FacesComponent("adresseInput")
public class AdresseInputComponent extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------
    
    // Actions ------------------------------------------------------------------------------------
	
    /**
     * Set the selected and available values of the day, month and year fields based on the model.
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        Adresse adresse = (Adresse) getValue();
        if (adresse != null) {
        	// finding components
        	UIInput adresseLigne1Component = (UIInput)findComponent("ligne1");
            UIInput adresseLigne2Component = (UIInput)findComponent("ligne2");
            UIInput adresseCodePostalComponent = (UIInput)findComponent("codePostal");
            UIInput adressePaysComponent = (UIInput)findComponent("pays");
            UIInput adresseVilleComponent = (UIInput)findComponent("ville");
            
            // filling components with corresponding values using the given value provided to the component
            adresseLigne1Component.setValue(adresse.getLigne1());
            adresseLigne2Component.setValue(adresse.getLigne2());
            adresseCodePostalComponent.setValue(adresse.getCodePostal());
            adressePaysComponent.setValue(adresse.getPays());
            adresseVilleComponent.setValue(adresse.getVille());
            
            setVilles(adresse.getPays());
        }
        super.encodeBegin(context);
    }

    /**
     * Returns the submitted value
     */
    @Override
    public Object getSubmittedValue() {
        return this;
    }

    /**
     * Converts the submitted value to concrete {@link Adresse} instance.
     */
    @Override
    protected Object getConvertedValue(FacesContext context, Object newSubmittedValue) throws ConverterException {
    	// gathering components
        UIInput adresseLigne1Component = (UIInput)findComponent("ligne1");
        UIInput adresseLigne2Component = (UIInput)findComponent("ligne2");
        UIInput adresseCodePostalComponent = (UIInput)findComponent("codePostal");
        SelectOneMenu adressePaysComponent = (SelectOneMenu)findComponent("pays");
        SelectOneMenu adresseVilleComponent = (SelectOneMenu)findComponent("ville");
        
        // gathering values
        String ligne1 = (String) adresseLigne1Component.getSubmittedValue();
        String ligne2 = (String) adresseLigne2Component.getSubmittedValue();
        String codePostal = (String) adresseCodePostalComponent.getSubmittedValue();
        Pays  pays  = (Pays)   adressePaysComponent.getConverter().getAsObject(context, adressePaysComponent,  (String) adressePaysComponent.getSubmittedValue());
        Ville ville = (Ville) adresseVilleComponent.getConverter().getAsObject(context, adresseVilleComponent, (String) adresseVilleComponent.getSubmittedValue());
        
        // creating adresse instance & filling it with values
        Adresse adresse = new Adresse();
        adresse.setLigne1(ligne1);
        adresse.setLigne2(ligne2);
        adresse.setCodePostal(codePostal);
        adresse.setPays(pays);
        adresse.setVille(ville);
        
        return adresse;
     }

    /**
     * Update the 'ville' SelectOneMenu component.
     */
    public void updateVilleSelectOneMenu(AjaxBehaviorEvent event) {
    	FacesContext context = FacesContext.getCurrentInstance();
    	
    	SelectOneMenu adressePaysComponent = (SelectOneMenu)findComponent("pays");
    	SelectOneMenu adresseVilleComponent = (SelectOneMenu)findComponent("ville");
    	
    	Pays pays = (Pays) adressePaysComponent.getValue();
    	setVilles(pays);
    	
    	// Update ville selectOneMenu component
        context.getPartialViewContext().getRenderIds().add(adresseVilleComponent.getClientId(context));
    }
    
    /**
     * Update the 'codePostal' InputText component.
     */
    public void updateCodePostalInputText(AjaxBehaviorEvent event) {
    	FacesContext context = FacesContext.getCurrentInstance();
    	
    	UIInput adresseCodePostalComponent = (UIInput)findComponent("codePostal");
    	SelectOneMenu adresseVilleComponent = (SelectOneMenu)findComponent("ville");
    	Ville ville = (Ville) adresseVilleComponent.getValue();
    	
    	adresseCodePostalComponent.setValue(ville.getCodePostal());
    	
    	// Update codePostal inputText component
        context.getPartialViewContext().getRenderIds().add(adresseCodePostalComponent.getClientId(context));
    }

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Return specified attribute value or otherwise the specified default if it's null.
     */
    @SuppressWarnings({ "unchecked", "unused" })
    private <T> T getAttributeValue(String key, T defaultValue) {
        T value = (T) getAttributes().get(key);
        return (value != null) ? value : defaultValue;
    }

    // Getters/setters ----------------------------------------------------------------------------
    
    /**
     * Returns the component family of {@link UINamingContainer}.
     * (that's just required by composite component)
     */
    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }
    
    @SuppressWarnings("unchecked")
	public List<Ville> getVilles() {
		List<Ville> villesList = (List<Ville>) getStateHelper().get("villes");
        return villesList;
    }

	/**
	 * Setting Villes based on pays
	 * @param pays 
	 */
	public void setVilles(Pays pays) {
		List<Ville> villesList = null;
		if(pays != null){
			villesList = ((VilleController)Core.bean("ville")).getList(pays);	
    	}else{
    		// it's necessary to have at least one NULL object
    		villesList = new ArrayList<Ville>();
    		villesList.add(new Ville());
    	}
        getStateHelper().put("villes", villesList);
    }

}