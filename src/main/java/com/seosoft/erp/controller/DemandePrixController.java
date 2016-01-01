package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.DemandePrix;
import com.seosoft.erp.service.business.DemandePrixService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class DemandePrixController extends GenericCRUDController<DemandePrix, DemandePrixService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "demandePrix";
		prepareForCreateNew();
	}
	
	public void prepareForCreateNew(){
		_object = new DemandePrix();
	}
	
	protected void onDataReady(){
		
		addRelatedModule((ModeTransportController) Core.bean("modeTransport"), "modeTransport", new Action(){
			@Override
			public void run() {
				_object.setModeTransport(((ModeTransportController) Core.bean("modeTransport")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((ModeTransportController) Core.bean("modeTransport")).setObject(_object.getModeTransport());
			}}
		);
		
		addRelatedModule((ModeExpeditionController) Core.bean("modeExpedition"), "modeExpedition", new Action(){
			@Override
			public void run() {
				_object.setModeExpedition(((ModeExpeditionController) Core.bean("modeExpedition")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((ModeExpeditionController) Core.bean("modeExpedition")).setObject(_object.getModeExpedition());
			}}
		);
		
		addRelatedModule((ModeReglementController) Core.bean("modeReglement"), "modeReglement", new Action(){
			@Override
			public void run() {
				_object.setModeReglement(((ModeReglementController) Core.bean("modeReglement")).getObject());
			}},new Action(){
			@Override
			public void run() {
				((ModeReglementController) Core.bean("modeReglement")).setObject(_object.getModeReglement());
			}}
		);
		
	}
	
	
	protected void registerActions(){
		_actions.put("updateAdresseCommande", new Action(){
			@Override
			public void run() {
				_object.setAdresseCommandeLigne1(_object.getFournisseur().getAdresseLigne1());
				_object.setAdresseCommandeLigne2(_object.getFournisseur().getAdresseLigne2());
				_object.setAdresseCommandeCodePostal(_object.getFournisseur().getAdresseCodePostal());
				_object.setAdresseCommandeVille(_object.getFournisseur().getAdresseVille());
				_object.setAdresseCommandePays(_object.getFournisseur().getAdressePays());
			}
		});
		
		_actions.put("updateAdresseLivraison", new Action(){
			@Override
			public void run() {
				_object.setAdresseLivraisonLigne1(_object.getDepot().getAdresseLigne1());
				_object.setAdresseLivraisonLigne2(_object.getDepot().getAdresseLigne2());
				_object.setAdresseLivraisonCodePostal(_object.getDepot().getAdresseCodePostal());
				_object.setAdresseLivraisonVille(_object.getDepot().getAdresseVille());
				_object.setAdresseLivraisonPays(_object.getDepot().getAdressePays());
			}
		});
		
		_actions.put("valider", new Action(){
			@Override
			public void run() {
				FacesMessage msg = new FacesMessage("La demande(s) de prix a été validé(s)");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("transformer", new Action(){
			@Override
			public void run() {
				FacesMessage msg = new FacesMessage("La demande(s) de prix a été validé(s)");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
	}
}
