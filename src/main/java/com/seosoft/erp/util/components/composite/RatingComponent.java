package com.seosoft.erp.util.components.composite;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

@FacesComponent("ratingComponent")
public class RatingComponent extends UINamingContainer {
	private enum PropertyKeys {
		starsCount;
	}
	
	// FIXME the only way to save the state of this variable value is use getStateHelper, this is because  FacesComponents are not beans and have no scope, they are recreated (reinitialized) at each request
	
	@Override
    public void encodeBegin(FacesContext context) throws IOException {
		Object totalStars = getAttributes().get("totalStars");
		int starsCount = Integer.valueOf(String.valueOf(totalStars));
		getStateHelper().put(PropertyKeys.starsCount, starsCount);
        super.encodeBegin(context);
    }
	
    public Object[] getItems() {
    	int	starsCount = Integer.valueOf(getStateHelper().get(PropertyKeys.starsCount).toString());
        return new Object[starsCount];
    }
    
    public void testAction(){
    	System.out.println("RatingComponent::testAction() CALLED");
    	FacesMessage msg = new FacesMessage("RatingComponent::testAction()","Hello man this was an action executed upon the Composite component ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void updateRate(int val){
    	int starsCount = Integer.valueOf(getStateHelper().get(PropertyKeys.starsCount).toString());
    	starsCount+= val;
    	getStateHelper().put(PropertyKeys.starsCount, starsCount);
    	FacesMessage msg = new FacesMessage("RatingComponent::updateRate()","Stars count : " + starsCount);
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}