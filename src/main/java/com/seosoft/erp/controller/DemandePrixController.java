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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.base.SearchCriteria;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.model.entity.DemandePrix;
import com.seosoft.erp.model.entity.DetailsDemandePrix;
import com.seosoft.erp.service.business.DemandePrixService;
import com.seosoft.erp.service.business.DetailsDemandePrixService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class DemandePrixController extends GenericCRUDController<DemandePrix, DemandePrixService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;
	
	private DemandePrix.Filter _filter;
	
	@Autowired
	private DetailsDemandePrixService detailsDemandePrixService;
	
	private List<DetailsDemandePrix> detailsDemandePrixList;
	private Article emptyArticle = new Article();

	protected void prepareData(){
		super.prepareData();
		_moduleName = "demandePrix";
		prepareForCreateNew();
		_filter = new DemandePrix.Filter();
	}
	

	public void prepareForCreateNew(){
		_object = new DemandePrix();
	}
	
	protected void onDataReady(){
		
		if(_object.getId() == null || _object.getId() ==""){
			detailsDemandePrixList = new ArrayList<DetailsDemandePrix>();
			addNewRowToDetailsDatatable();
		}else{
			detailsDemandePrixList = detailsDemandePrixService.findByDemandePrix(_object);
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
				for(DemandePrix demandePrix : _selectedObjects){
					demandePrix.setValide(true);
				}
				_service.save(_selectedObjects);
				FacesMessage msg = new FacesMessage("La demande(s) de prix a été validé(s)");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		_actions.put("transformer", new Action(){
			@Override
			public void run() {
				for(DemandePrix demandePrix : _selectedObjects){
					if(!demandePrix.getValide()){
						FacesMessage msg = new FacesMessage("Impossible de transformer la séléction, il y a des objets non validés !");
						msg.setSeverity(FacesMessage.SEVERITY_WARN);
						FacesContext.getCurrentInstance().addMessage(null, msg);
						return;
					}
					demandePrix.setTransforme(true);
				}
				_service.save(_selectedObjects);
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
				//detailsDemandePrixService.save(detailsDemandePrixList);
				
				for(DetailsDemandePrix ligneDetails : detailsDemandePrixList){
					ligneDetails.setDemandePrix(_object);
					detailsDemandePrixService.save(ligneDetails);
				}
				
				String message = "Enregistrement '" + StringUtils.capitalize(_moduleName) + "' effectué avec succés ! ";
				message = (isNewInsert)?message:"Modification '" + StringUtils.capitalize(_moduleName) + "' effectuée avec succés ! ";
				FacesMessage msg = new FacesMessage(message);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
		
		_actions.put("transformerEnBonCommande", new Action(){
			@Override
			public void run() {
//				System.out.println("Transformer en bon de commande : "  + _moduleName);
//				
//				String message ="";
//				if(_object.getTransforme()){
//					message = "Cet élément a  déjà été transformé !";
//				}else{
//					_service.save(_object); 
//					// save details Demande Prix				
//					for(DetailsDemandePrix ligneDetails : detailsDemandePrixList){
//						ligneDetails.setDemandePrix(_object);
//						detailsDemandePrixService.save(ligneDetails);
//					}
//					
//					message = "Transformation '" + StringUtils.capitalize(_moduleName) + "' effectuée avec succés ! ";
//				}	
//				
//				
//				FacesMessage msg = new FacesMessage(message);
//				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		});
		
	
	}
	
	@Override
	public void handleFilter(){
		Specification<DemandePrix> spec = null;
		System.out.println(" _filter.getFournisseur() " +   _filter.getFournisseur() );
		System.out.println(" _filter.getDepot() " +  _filter.getDepot() );
		System.out.println(" _filter.getValide() " +  _filter.getValide() );
//		System.out.println(" _filter.getDateDu() " +  _filter.getDateDu() );
//		System.out.println(" _filter.getDateAu() " +  _filter.getDateAu() );
		if( _filter.getFournisseur() != null)
			spec = new DemandePrix._Specification(new SearchCriteria("fournisseur", "=", _filter.getFournisseur()));
		if( _filter.getDepot() != null){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("depot", "=", _filter.getDepot())) );
		}
		if( _filter.getValide() != -1){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("valide", "=", _filter.getValide())) );
		}
		if( _filter.getTransforme() != -1){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("transforme", "=", _filter.getTransforme())) );
		}
		if( _filter.getDateDu() != null &&  _filter.getDateAu() != null){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("date", ">", new java.sql.Date(_filter.getDateDu().getTime()) )));
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("date", "<", new java.sql.Date(_filter.getDateAu().getTime()) )));
		}
		
		_list = _service.findAll(spec, _sortBy);
		_dataModel = new DataModel(_list);
	}
	
	
	/**************************************************************************************************/
	/********************************** UTIL FUNCTIONS ****************************************/
	/************************************************************************************************/
	
	public void addRowToDetailsDatatable(){
		addNewRowToDetailsDatatable();
		for(DetailsDemandePrix ligneDetails : detailsDemandePrixList){
			System.out.println("DetailsDemandePrix : " + ligneDetails.getArticle().getDisplayText());
		}
	}
	
	
	public void removeRowToDetailsDatatable(int index){
		detailsDemandePrixList.remove(index);
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
		for(DetailsDemandePrix ligneDetails : detailsDemandePrixList){
			totalHt += ligneDetails.getArticle().getPrixUnitaireAchat() * ligneDetails.getQte();
		}
		_object.setTotalHt(totalHt);
		RequestContext.getCurrentInstance().update(getComponentById("detailsDatatable").getClientId());
		RequestContext.getCurrentInstance().update(getComponentById("tabView").getClientId());
	}

	private void addNewRowToDetailsDatatable(){
		DetailsDemandePrix ligneDemandePrix = new DetailsDemandePrix();
		ligneDemandePrix.setDemandePrix(_object);
		emptyArticle.setLibelle("...");
		if(((ArticleController) Core.bean("article")).getList().size() > 0){
			ligneDemandePrix.setArticle(((ArticleController) Core.bean("article")).getList().get(0));
		}else{
			ligneDemandePrix.setArticle(emptyArticle);
		}
		detailsDemandePrixList.add(ligneDemandePrix);
	}
	
	
	/**************************************************************************************************/
	/********************************** GETTERS & SETTERS ****************************************/
	/************************************************************************************************/
	

	public List<DetailsDemandePrix> getDetailsDemandePrixList() {
		return detailsDemandePrixList;
	}

	public void setDetailsDemandePrixList(List<DetailsDemandePrix> detailsDemandePrixList) {
		this.detailsDemandePrixList = detailsDemandePrixList;
	}

	public Article getEmptyArticle() {
		return emptyArticle;
	}

	public void setEmptyArticle(Article emptyArticle) {
		this.emptyArticle = emptyArticle;
	}

	public DemandePrix.Filter getFilter() {
		return _filter;
	}

	public void setFilter(DemandePrix.Filter filter) {
		this._filter = filter;
	}

}
