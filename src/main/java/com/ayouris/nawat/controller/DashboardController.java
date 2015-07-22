package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.controller.generic.Action;
import com.ayouris.nawat.controller.generic.GenericCRUDController;
import com.ayouris.nawat.controller.generic.GenericSimpleController;
import com.ayouris.nawat.model.entity.v2_Profil;
import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.parametrage.v2_ProfilService;
import com.ayouris.nawat.service.parametrage.v2_UserService;
import com.ayouris.nawat.util.scopes.session.SpringSessionScoped;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class DashboardController extends GenericSimpleController implements Serializable {
	private static final long serialVersionUID = -7782682770197008821L;
	private int count = 5;
	
	
	protected void prepareData(){
		_moduleName = "Dashboard";
	}
	
	@Override
	protected void registerActions(){
		_actions.put("loadStatsData", new Action(){
			@Override
			public void run() {
				count++;
				FacesMessage msg = new FacesMessage("Stats data : " + count);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("test", new Action(){
			@Override
			public void run() {
				FacesMessage msg = new FacesMessage("Test action called : variable (_messageHello) : " + count);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
