package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.model.entity.v2_Profil;
import com.seosoft.erp.service.parametrage.v2_ProfilService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ProfilListController extends BaseController implements Serializable {
	private static final long serialVersionUID = -4352626540220792149L;

	@Autowired
	private v2_ProfilService v2_profilService;

	private ProfilDataModel profilsDataModel;

	private List<v2_Profil> profils;

	private v2_Profil selectedProfil;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		updateProfilDataModel();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||||| Methodes utiles ||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void updateProfilDataModel() {
		profils = v2_profilService.findAll();
		profilsDataModel = new ProfilDataModel(profils);
		RequestContext.getCurrentInstance().update("mainForm:profilsDataTable");
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| ProfilDataModel |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public class ProfilDataModel extends ListDataModel<v2_Profil> implements SelectableDataModel<v2_Profil> {

		public ProfilDataModel(List<v2_Profil> profils) {
			super(profils);
		}

		@SuppressWarnings("unchecked")
		@Override
		public v2_Profil getRowData(String rowKey) {

			List<v2_Profil> profils = (List<v2_Profil>) getWrappedData();

			for (v2_Profil profil : profils) {
				if (profil.getId().equals(rowKey))
					return profil;
			}

			return null;
		}

		@Override
		public Object getRowKey(v2_Profil profil) {
			return profil.getId();
		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public v2_ProfilService getV2_profilService() {
		return v2_profilService;
	}

	public void setV2_profilService(v2_ProfilService v2_profilService) {
		this.v2_profilService = v2_profilService;
	}

	public ProfilDataModel getProfilsDataModel() {
		return profilsDataModel;
	}

	public void setProfilsDataModel(ProfilDataModel profilsDataModel) {
		this.profilsDataModel = profilsDataModel;
	}

	public v2_Profil getSelectedProfil() {
		return selectedProfil;
	}

	public void setSelectedProfil(v2_Profil selectedProfil) {
		this.selectedProfil = selectedProfil;
	}

}
