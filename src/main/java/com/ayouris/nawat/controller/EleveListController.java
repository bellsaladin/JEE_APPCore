package com.ayouris.nawat.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.ayouris.nawat.model.entity.Eleve;
import com.ayouris.nawat.model.entity.Eleve.Scolarite;
import com.ayouris.nawat.service.business.EleveService;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class EleveListController extends BaseController implements Serializable {
	private static final long serialVersionUID = -6347182481536812576L;

	@Autowired
	private EleveService eleveService;

	private EleveDataModel elevesDataModel;

	private List<Eleve> eleves;
	private Eleve selectedEleve;

	private SelectItem[] scolariteOptions;

	private int filtreCessation;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		filtreCessation = 0;
		eleves = eleveService.findAll();
		elevesDataModel = new EleveDataModel(eleveService);
		scolariteOptions = createScolariteOptions(Eleve.Scolarite.values());
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	private SelectItem[] createScolariteOptions(Scolarite[] scolarites) {
		SelectItem[] options = new SelectItem[scolarites.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < scolarites.length; i++) {
			options[i + 1] = new SelectItem(scolarites[i].toString(), scolarites[i].toString());
		}

		return options;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Déclanché suite à une action faite ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void onRowSelect(SelectEvent event) throws IOException {
		Eleve selectedEleve = ((Eleve) event.getObject());
		Faces.redirect(Faces.getRequestContextPath() + "/eleveFiche?id=" + selectedEleve.getId());
	}

	public void doFilter() {
		FacesMessage msg = new FacesMessage("Do Filter");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void doEditEleve() {
		FacesMessage msg = new FacesMessage("Do Edit eleve");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void doRemoveEleve() {
		FacesMessage msg = new FacesMessage("Do Remove Eleve");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| EleveDataModel |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public class EleveDataModel extends LazyDataModel<Eleve> implements SelectableDataModel<Eleve> {
		private static final long serialVersionUID = 5496047134903833584L;

		private final EleveService eleveService;

		public EleveDataModel(EleveService eleveService) {
			this.eleveService = eleveService;
		}
//		public List<Eleve> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
//
//			//rowCount
//			int dataSize = (int) eleveService.count();
//			this.setRowCount(dataSize);
//
//			// get data using pagination 
//
//			List<Eleve> data = new ArrayList<Eleve>();
//
//			//paginate
//			Page<Eleve> pageEleves = null;
//
//			if (dataSize > pageSize) {
//				int pageNumber = first / pageSize;
//				//pageEleves = eleveRepository.findAll(new PageRequest(pageNumber, pageSize));				
//				/*
//				 * pageEleves = eleveService.findAll(new PageRequest(pageNumber, pageSize, new Sort(new Order(Direction.ASC, "nom"), new
//				 * Order(Direction.DESC, "prenom"))));
//				 */
//				pageEleves = eleveService.findByNomStartingWithAndPrenomStartingWith(null, "s", new PageRequest(pageNumber, pageSize,
//						new Sort(new Order(Direction.ASC, "nom"), new Order(Direction.DESC, "prenom"))));
//				data = pageEleves.getContent();
//				return data;
//				/*
//				 * try { return data.subList(first, first + pageSize); } catch (IndexOutOfBoundsException e) { return data.subList(first,
//				 * first + (dataSize % pageSize)); }
//				 */
//			} else {
//				return eleveService.findAll();
//			}
//
//			//filter
//			/*
//			 * for (Eleve eleve : data) { boolean match = true; for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) { try
//			 * { String filterProperty = it.next(); String filterValue = filters.get(filterProperty); String fieldValue =
//			 * String.valueOf(eleve.getClass().getDeclaredField(filterProperty).get(eleve)); //if (filterValue == null ||
//			 * fieldValue.startsWith(filterValue)) { if (filterValue == null || fieldValue.contains(filterValue) { match = true; } else {
//			 * match = false; break; } } catch (Exception e) { System.out.println("Catch : " + e.getMessage()); match = false; } } if
//			 * (match) { data.add(eleve); } }
//			 */
//
//			//sort
//
//			/*
//			 * if (sortField != null) { Collections.sort(data, new LazySorter(sortField, sortOrder)); }
//			 */
//
//		}

		@SuppressWarnings("unchecked")
		@Override
		public Eleve getRowData(String rowKey) {

			List<Eleve> eleves = (List<Eleve>) getWrappedData();

			for (Eleve eleve : eleves) {
				if (eleve.getId().equals(rowKey))
					return eleve;
			}

			return null;
		}

		@Override
		public Object getRowKey(Eleve eleve) {
			return eleve.getId();
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public List<Eleve> getEleves() {
		return eleves;
	}

	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}

	public Eleve getSelectedEleve() {
		return selectedEleve;
	}

	public void setSelectedEleve(Eleve selectedEleve) {
		this.selectedEleve = selectedEleve;
	}

	public SelectItem[] getScolariteOptions() {
		return scolariteOptions;
	}

	public EleveDataModel getElevesDataModel() {
		return elevesDataModel;
	}

	public int getFiltreCessation() {
		return filtreCessation;
	}

	public void setFiltreCessation(int filtreCessation) {
		this.filtreCessation = filtreCessation;
	}

}
