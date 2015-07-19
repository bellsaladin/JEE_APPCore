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
public class ProfilDuplicateController extends BaseController implements Serializable {
	private static final long serialVersionUID = 7076489161017674124L;

	@Autowired
	private v2_ProfilService v2_profilService;

	@Autowired
	private v2_RoleService v2_roleService;

	@Autowired
	private v2_ProfilRoleService v2_profilRoleService;

	private v2_Profil profil;

	private List<v2_Role> roles;

	private List<v2_ProfilRole> selectedProfilRoles;

	private TreeNode root2;

	private TreeNode[] selectedNodes;

	private String profilId;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		profil = new v2_Profil();
		roles = v2_roleService.findByParentRoleIsNull();
		createTreeNodes();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes privates déclanchées localement |||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void createTreeNodes() {

		if (profil == null || selectedProfilRoles == null) {
			root2 = new CheckboxTreeNode("Root", null);
			CheckboxTreeNode node = new CheckboxTreeNode("", root2); // adding node to avoid error
			return;
		}

		root2 = new CheckboxTreeNode("Root", null);

		for (v2_Role role : roles) {
			// FIXME try to use partial selection (setPartialSelection) for parent nodes !!
			CheckboxTreeNode node = new CheckboxTreeNode(role.getLibelleKey(), root2);
			for (v2_Role childRole : role.getRoleChildren()) {
				CheckboxTreeNode childNode = new CheckboxTreeNode(childRole.getLibelleKey(), node);
				if (isRoleInListOfProfilRoles(childRole, selectedProfilRoles)) {
					childNode.setSelected(true);
					System.out.println(">SELECT " + childRole.getLibelleKey());
				} else {
					childNode.setSelected(false);
					System.out.println("UNSELECT " + childRole.getLibelleKey());
				}
			}
		}
	}

	public boolean isRoleInListOfProfilRoles(v2_Role comparedRole, List<v2_ProfilRole> profilRoles) {
		for (v2_ProfilRole profilRole : profilRoles) {
			if (profilRole.getRole().getId().equals(comparedRole.getId()))
				return true;
		}
		return false;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||| Déclanché suite à une action faite ||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void doDuplicateProfil() {
		// Check for errors
		if (selectedNodes == null || selectedNodes.length == 0) {
			// Show success message
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Vous devez selectionner des droits à affecter", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			FacesContext.getCurrentInstance().validationFailed();
			return;
		}

		// Save the profil to database
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

		// Update profilSelectOneMenu of userCreateDialog and userUpdateDialog
		UserCreateController userCreateController = (UserCreateController) getApplicationContext().getBean("userCreateController");
		UserUpdateController userUpdateController = (UserUpdateController) getApplicationContext().getBean("userUpdateController");
		if (userCreateController != null)
			userCreateController.updateProfilSelectOneMenu();
		if (userUpdateController != null)
			userUpdateController.updateProfilSelectOneMenu();
		// Show success message
		FacesMessage msg = new FacesMessage("Profil Dupliqué avec succés !!");
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

	public v2_RoleService getV2_roleService() {
		return v2_roleService;
	}

	public void setV2_roleService(v2_RoleService v2_roleService) {
		this.v2_roleService = v2_roleService;
	}

	public v2_ProfilRoleService getV2_profilRoleService() {
		return v2_profilRoleService;
	}

	public void setV2_profilRoleService(v2_ProfilRoleService v2_profilRoleService) {
		this.v2_profilRoleService = v2_profilRoleService;
	}

	public List<v2_Role> getRoles() {
		return roles;
	}

	public void setRoles(List<v2_Role> roles) {
		this.roles = roles;
	}

	public v2_Profil getProfil() {
		return profil;
	}

	public void setProfil(v2_Profil profil) {
		this.profil = profil;
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

	public String getProfilId() {
		return profilId;
	}

	public void setProfilId(String profilId) {
		System.out.println("ProfilUpdateController::setProfilId : triggered > " + profilId);
		this.profilId = profilId;
		cloneProfile(profilId);
	}

	public void cloneProfile(String profilId) {
		// load the original profil informations
		v2_Profil originalProfil = v2_profilService.findOne(profilId);
		// create copy of profil
		profil = new v2_Profil();
		profil.setLibelle(originalProfil.getLibelle() + " (copie)");
		profil.setDescription(originalProfil.getDescription());

		// load profileRoles
		selectedProfilRoles = v2_profilRoleService.findByProfilId(originalProfil.getId());
		System.out.println("hmmmmmmmmm " + profil);
		createTreeNodes();
	}

}
