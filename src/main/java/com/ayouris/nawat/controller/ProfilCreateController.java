package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.model.entity.v2_Profil;
import com.ayouris.nawat.model.entity.v2_ProfilRole;
import com.ayouris.nawat.model.entity.v2_Role;
import com.ayouris.nawat.service.parametrage.v2_ProfilRoleService;
import com.ayouris.nawat.service.parametrage.v2_ProfilService;
import com.ayouris.nawat.service.parametrage.v2_RoleService;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ProfilCreateController extends BaseController implements Serializable {
	private static final long serialVersionUID = -7538138543740057682L;

	@Autowired
	private v2_ProfilService v2_profilService;

	@Autowired
	private v2_RoleService v2_roleService;

	@Autowired
	private v2_ProfilRoleService v2_profilRoleService;

	private List<v2_Role> roles;

	private String libelle;

	private String description;

	private TreeNode root2;

	private TreeNode[] selectedNodes;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		roles = v2_roleService.findByParentRoleIsNull();
		CreateTreeNodes();

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void CreateTreeNodes() {
		root2 = new CheckboxTreeNode("Root", null);
		for (v2_Role role : roles) {
			CheckboxTreeNode node = new CheckboxTreeNode(role.getLibelleKey(), root2);
			// FIXME setRowKey ne marche pas apparement
			node.setRowKey(role.getId());
			for (v2_Role childRole : role.getRoleChildren()) {
				CheckboxTreeNode childNode = new CheckboxTreeNode(childRole.getLibelleKey(), node);
				// FIXME setRowKey ne marche pas apparement
				childNode.setRowKey(childRole.getId());
			}

		}
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Déclanché suite à une action faite ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void doCreateProfil() {
		// Check for errors
		if (selectedNodes == null || selectedNodes.length == 0) {
			// Show success message
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Vous devez selectionner des droits à affecter", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			FacesContext.getCurrentInstance().validationFailed();
			return;
		}

		// Save the profil to database
		v2_Profil profil = new v2_Profil();
		profil.setLibelle(libelle);
		profil.setDescription(description);
		v2_profilService.save(profil);
		// affectation des roles au profil for (TreeNode treeNode : selectedNodes) {	
		for (TreeNode treeNode : selectedNodes) {
			v2_Role role = getRoleFromTreeNodeRowKey(treeNode.getRowKey());
			v2_ProfilRole profilRole = new v2_ProfilRole();
			profilRole.setProfil(profil);
			profilRole.setRole(role);
			v2_profilRoleService.save(profilRole);
		}

		// Try to update profilList
		ProfilListController profilListController = (ProfilListController) getApplicationContext().getBean("profilListController");
		if (profilListController != null)
			profilListController.updateProfilDataModel();

		// Try to update profilSelectOneMenu of userCreateDialog and userUpdateDialog
		UserCreateController userCreateController = (UserCreateController) getApplicationContext().getBean("userCreateController");
		UserUpdateController userUpdateController = (UserUpdateController) getApplicationContext().getBean("userUpdateController");
		if (userCreateController != null)
			userCreateController.updateProfilSelectOneMenu();
		if (userUpdateController != null)
			userUpdateController.updateProfilSelectOneMenu();

		// Show success message
		FacesMessage msg = new FacesMessage("Nouveau profil créé");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Methods utiles ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public v2_Role getRoleFromTreeNodeRowKey(String rowKey) {
		v2_Role role = null;
		String[] rolesIndexes = rowKey.split("_");
		// recuperer le role de premier niveau
		role = roles.get(Integer.valueOf(rolesIndexes[0]));
		if (rolesIndexes.length > 1)
			for (int i = 1; i < rolesIndexes.length; i++) {
				int roleIndex = Integer.valueOf(rolesIndexes[i]);
				role = role.getRoleChildren().get(roleIndex);
			}
		return role;
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

	public List<v2_Role> getRoles() {
		return roles;
	}

	public void setRoles(List<v2_Role> roles) {
		this.roles = roles;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TreeNode getRoot2() {
		return root2;
	}

	public void setRoot2(TreeNode root2) {
		this.root2 = root2;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public v2_RoleService getV2_roleService() {
		return v2_roleService;
	}

	public void setV2_roleService(v2_RoleService v2_roleService) {
		this.v2_roleService = v2_roleService;
	}

}
