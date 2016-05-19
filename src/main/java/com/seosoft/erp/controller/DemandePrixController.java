package com.seosoft.erp.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.controller.generic.GenericSimpleController;
import com.seosoft.erp.controller.generic.GenericUpdateOnlyController;
import com.seosoft.erp.model.base.SearchCriteria;
import com.seosoft.erp.model.entity.Article;
import com.seosoft.erp.model.entity.DemandePrix;
import com.seosoft.erp.model.entity.DetailsDemandePrix;
import com.seosoft.erp.model.entity.Parametre;
import com.seosoft.erp.service.business.DemandePrixService;
import com.seosoft.erp.service.business.DetailsDemandePrixService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class DemandePrixController extends GenericCRUDController<DemandePrix, DemandePrixService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;
		
	@Autowired
	private DetailsDemandePrixService detailsDemandePrixService;
	
	private List<DetailsDemandePrix> detailsDemandePrixList;
	private Article emptyArticle = new Article();

	protected void prepareData(){
		_moduleName = "demandePrix";
		prepareForCreateNew();
		super.prepareData();
	}
	

	public void prepareForCreateNew(){
		_object = new DemandePrix();
	}
	
	protected void onDataReady(){
		if(_object == null) _object = new DemandePrix();
		
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
				_object.setAdresseCommande(_object.getFournisseur().getAdresse());
				/*_object.getAdresseCommande().setLigne1(_object.getFournisseur().getAdresseLigne1());
				_object.getAdresseCommande().setLigne2(_object.getFournisseur().getAdresseLigne2());
				_object.getAdresseCommande().setCodePostal(_object.getFournisseur().getAdresseCodePostal());
				_object.getAdresseCommande().setVille(_object.getFournisseur().getAdresseVille());
				_object.getAdresseCommande().setPays(_object.getFournisseur().getAdressePays());*/
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
		
		_actions.put("print", new Action(){
			
			@Override
			@Transactional()
			public void run() {
				HashMap<String, Object> hm = null;
				  // System.out.println("Usage: ReportGenerator ....");

				try {
				   System.out.println("Start ....");
				   
				   ServletContext servletContext = (ServletContext) FacesContext
					        .getCurrentInstance().getExternalContext().getContext();
				   String contextPath = servletContext.getRealPath(File.separator);
					
				   // Get jasper report
				   String jrxmlFileName  = contextPath + "resources/_reports/report1.jrxml";
				   String jasperFileName = contextPath + "resources/_reports/report1.jasper";
				   String pdfFileName    = contextPath + "resources/_reports/report1.pdf";

				   JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
				   
				   GenericUpdateOnlyController parametreController = (GenericUpdateOnlyController) Core.bean("parametre");
				   
				   Parametre parametreSociete = (Parametre) parametreController.getService().findAll().get(0);
				   
				   // Create arguments
				   // Map params = new HashMap();
				   hm = new HashMap<String, Object>();
				   //hm.put("ID", "123");
				   //hm.put("DATENAME", "April 2006");
				   hm.put("COMPANY_ADDRESS", parametreSociete.getAdresseLigne1());
				   hm.put("COMPANY_NAME", parametreSociete.getRaisonSociale());
				   hm.put("COMPANY_PHOTO_NAME", parametreSociete.getImageSrc());
				   EntityManager em = getEntityManager();

				   //java.sql.Connection connection = em.unwrap(java.sql.Connection.class);
				   
				   // JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameterMap, new JREmptyDataSource());
				   
				   // Generate jasper print
				   //JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, connection);
				   JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, new JREmptyDataSource());
				   // Export pdf file
				   JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
				   
				   System.out.println("Done exporting the report to pdf");
				   FacesContext context = FacesContext.getCurrentInstance();
				   ExternalContext externalContext = context.getExternalContext();

				   externalContext.redirect("../../../resources/_reports/report1.pdf");
				   
				} catch (Exception e) {
					  System.out.print("Exceptiion" + e);
				}
			}
		});
		
	
	}
	
	@Override
	public void handleFilter(){
		Specification<DemandePrix> spec = null;
		System.out.println(" _object.filter().getFournisseur() " +   _object.filter().getFournisseur() );
		System.out.println(" _object.filter().getDepot() " +  _object.filter().getDepot() );
		System.out.println(" _object.filter().getValide() " +  _object.filter().getValide() );
//		System.out.println(" _object.filter().getDateDu() " +  _object.filter().getDateDu() );
//		System.out.println(" _object.filter().getDateAu() " +  _object.filter().getDateAu() );
		if( _object.filter().getFournisseur() != null)
			spec = new DemandePrix._Specification(new SearchCriteria("fournisseur", "=", _object.filter().getFournisseur()));
		if( _object.filter().getDepot() != null){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("depot", "=", _object.filter().getDepot())) );
		}
		if( _object.filter().getValide() != -1){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("valide", "=", _object.filter().getValide())) );
		}
		if( _object.filter().getTransforme() != -1){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("transforme", "=", _object.filter().getTransforme())) );
		}
		if( _object.filter().getDateDu() != null &&  _object.filter().getDateAu() != null){
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("date", ">", new java.sql.Date(_object.filter().getDateDu().getTime()) )));
			spec = Specifications.where(spec).and( new DemandePrix._Specification(new SearchCriteria("date", "<", new java.sql.Date(_object.filter().getDateAu().getTime()) )));
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

}
