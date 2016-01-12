package com.seosoft.erp.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.controller.generic.GenericCRUDController.DataModel;
import com.seosoft.erp.model.base.SearchCriteria;
import com.seosoft.erp.model.entity.DemandePrix;
import com.seosoft.erp.model.entity.Stock;
import com.seosoft.erp.service.business.StockService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class StockController extends GenericCRUDController<Stock, StockService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;
	
	private Stock.Filter _filter;
	
	protected void prepareData(){
		super.prepareData();
		_moduleName = "stock";
		prepareForCreateNew();
		_filter = new Stock.Filter();
	}
	
	public void prepareForCreateNew(){
		_object = new Stock();
	}
	
	protected void registerActions(){

	}
	
	public void handleFilter(){
		Specification<Stock> spec = null;
		if( _filter.getFournisseur() != null)
			spec = new Stock._Specification(new SearchCriteria("article.fournisseurPrincipal", "=", _filter.getFournisseur()));
		if( _filter.getDepot() != null){
			spec = Specifications.where(spec).and( new Stock._Specification(new SearchCriteria("depot", "=", _filter.getDepot())) );
		}
		spec = new Stock._Specification(new SearchCriteria("article.code", ":", "ddddd"));
		_list = _service.findAll(spec);
		_dataModel = new DataModel(_list);
	}
	

	public Stock.Filter getFilter() {
		return _filter;
	}

	public void setFilter(Stock.Filter _filter) {
		this._filter = _filter;
	}
}
