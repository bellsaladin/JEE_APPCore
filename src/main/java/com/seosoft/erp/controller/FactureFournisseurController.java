package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.model.entity.FactureFournisseur;
import com.seosoft.erp.model.entity.DetailsFactureFournisseur;
import com.seosoft.erp.model.entity.FactureFournisseur;
import com.seosoft.erp.service.business.DetailsFactureFournisseurService;
import com.seosoft.erp.service.business.FactureFournisseurService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class FactureFournisseurController extends GenericCRUDController<FactureFournisseur, FactureFournisseurService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

	@Autowired
	private DetailsFactureFournisseurService detailsFactureFournisseurService;
	
	private List<DetailsFactureFournisseur> detailsList;
	private Article emptyArticle = new Article();
	

	protected void prepareData(){
		_moduleName = "factureFournisseur";
		super.prepareData();
		prepareForCreateNew();
	}
	
	public void prepareForCreateNew(){
		_object = new FactureFournisseur();
	}
	
	protected void onDataReady(){
		
		if(_object.getId() == null || _object.getId() ==""){
			detailsList = new ArrayList<DetailsFactureFournisseur>();
			addNewRowToDetailsDatatable();
		}else{
			detailsList = detailsFactureFournisseurService.findByFactureFournisseur(_object);
		}
				
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
				_object.setAdresseCommande(_object.getFournisseur().getAdresse());
			}
		});
		
		_actions.put("updateAdresseLivraison", new Action(){
			@Override
			public void run() {
				_object.setAdresseLivraison(_object.getDepot().getAdresse());
			}
		});
		
		_actions.put("valider", new Action(){
			@Override
			public void run() {
				for(FactureFournisseur FactureFournisseur : _selectedObjects){
					FactureFournisseur.setValide(true);
				}
				_service.save(_selectedObjects);
				FacesMessage msg = new FacesMessage("La demande(s) de prix a été validé(s)");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("transformer", new Action(){
			@Override
			public void run() {
				for(FactureFournisseur FactureFournisseur : _selectedObjects){
					if(!FactureFournisseur.getValide()){
						FacesMessage msg = new FacesMessage("Impossible de transformer la séléction, il y a des objets non validés !");
						msg.setSeverity(FacesMessage.SEVERITY_WARN);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
					FactureFournisseur.setLivre(true);
				}
				//_service.save(_selectedObjects);
				FacesMessage msg = new FacesMessage("La demande(s) de prix a été tranformée(s)");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("persist", new Action(){
			@Override
			public void run() {
				System.out.println("SAVE : "  + _moduleName);

				boolean isNewInsert = (_object.getId()==null)?true:false;
				
				// save Demande Prix
				_service.save(_object); 
				// save details Demande Prix
				//detailsFactureFournisseurService.save(detailsFactureFournisseurList);
				
				for(DetailsFactureFournisseur ligneDetails : detailsList){
					ligneDetails.setFactureFournisseur(_object);
					detailsFactureFournisseurService.save(ligneDetails);
				}
				
				String message = "Enregistrement '" + StringUtils.capitalize(_moduleName) + "' effectué avec succés ! ";
				message = (isNewInsert)?message:"Modification '" + StringUtils.capitalize(_moduleName) + "' effectuée avec succés ! ";
				FacesMessage msg = new FacesMessage(message);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
	
	}
	
	/**************************************************************************************************/
	/********************************** UTIL FUNCTIONS ****************************************/
	/************************************************************************************************/
	
	public void addRowToDetailsDatatable(){
		addNewRowToDetailsDatatable();
		for(DetailsFactureFournisseur ligneDetails : detailsList){
			System.out.println("DetailsFactureFournisseur : " + ligneDetails.getArticle().getDisplayText());
		}
	}
	
	
	public void removeRowToDetailsDatatable(int index){
		detailsList.remove(index);
	}
	
	public void onDetailsDatatableCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        //if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell ChangedOld: " + oldValue + ", New:" + newValue, "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        //}
            
       calculDesTotaux();     
    }
	
	private void calculDesTotaux() {
		float totalHt = 0;
		for(DetailsFactureFournisseur ligneDetails : detailsList){
			totalHt += ligneDetails.getArticle().getPrixUnitaireAchat() * ligneDetails.getQte();
		}
		_object.setTotalHt(totalHt);
		RequestContext.getCurrentInstance().update(getComponentById("detailsDatatable").getClientId());
		RequestContext.getCurrentInstance().update(getComponentById("tabView").getClientId());
	}

	private void addNewRowToDetailsDatatable(){
		DetailsFactureFournisseur ligneDetail = new DetailsFactureFournisseur();
		ligneDetail.setFactureFournisseur(_object);
		emptyArticle.setLibelle("...");
		if(((ArticleController) Core.bean("article")).getList().size() > 0){
			ligneDetail.setArticle(((ArticleController) Core.bean("article")).getList().get(0));
		}else{
			ligneDetail.setArticle(emptyArticle);
		}
		detailsList.add(ligneDetail);
	}
	
	/**************************************************************************************************/
	/********************************** GETTERS & SETTERS *********************************************/
	/**************************************************************************************************/
	
	public List<DetailsFactureFournisseur> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<DetailsFactureFournisseur> detailsList) {
		this.detailsList = detailsList;
	}

	public Article getEmptyArticle() {
		return emptyArticle;
	}

	public void setEmptyArticle(Article emptyArticle) {
		this.emptyArticle = emptyArticle;
	}
	
}
