package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericCRUDController;
import com.seosoft.erp.model.entity.v2_Profil;
import com.seosoft.erp.model.entity.v2_ProfilRole;
import com.seosoft.erp.model.entity.v2_Role;
import com.seosoft.erp.model.entity.v2_UserNawat;
import com.seosoft.erp.service.parametrage.v2_ProfilRoleService;
import com.seosoft.erp.service.parametrage.v2_ProfilService;
import com.seosoft.erp.service.parametrage.v2_RoleService;
import com.seosoft.erp.service.parametrage.v2_UserService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ProfilController extends GenericCRUDController<v2_Profil, v2_ProfilService> implements Serializable {
	private static final long serialVersionUID = 5858194089825501468L;
	
	@Autowired
	private v2_RoleService v2_roleService;
	@Autowired
	private v2_ProfilRoleService v2_profilRoleService;
	
	private List<v2_Role> roles;
	private List<v2_ProfilRole> selectedProfilRoles;
	private TreeNode root2;
	private TreeNode[] selectedNodes;
	
	private v2_UserNawat userSubjectOfQuickDialog; // FIXME : SHOULD BE DYNAMIC and embeded on a list
	private boolean quickDialogUpdateMode = false;

	protected void prepareData(){
		super.prepareData();
		_moduleName = "profil";
	
		prepareForCreateNew();
	}
	
	public void prepareForCreateNew(){
		_object = new v2_Profil();
		
		roles = v2_roleService.findByParentRoleIsNull();
		root2 = new CheckboxTreeNode("Root", null);
		CheckboxTreeNode node = new CheckboxTreeNode("", root2); // adding node to avoid error
		
		updateTreeNodes();
	}
	
	protected void registerActions(){
		_actions.put("persist", new Action(){
			@Override
			public void run() {
				_service.save(_object);
				
				// remove all affected roles for this profil
				v2_profilRoleService.delete(selectedProfilRoles);
				// affectation des roles au profil for (TreeNode treeNode : selectedNodes) {
				System.out.println("selectedNodes.lenght " + selectedNodes.length);

				for (TreeNode treeNode : selectedNodes) {
					v2_Role role = getRoleFromTreeNodeRowKey(treeNode.getRowKey());
					v2_ProfilRole profilRole = new v2_ProfilRole();
					profilRole.setProfil(_object);
					profilRole.setRole(role);
					v2_profilRoleService.save(profilRole);
				}
				
				FacesMessage msg = new FacesMessage(_moduleName + " enregistré");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				if(!quickDialogUpdateMode)
					_list.add(_object);
				// FIXME : FOR TEST PURPOSE ONLY
				if(userSubjectOfQuickDialog != null) {
					userSubjectOfQuickDialog.setProfil(_object);
					RequestContext.getCurrentInstance().update("mainForm:profil");
				}
			}
		});
	}
	
	public void updateTreeNodes() {

		if (_object != null)
			selectedProfilRoles = v2_profilRoleService.findByProfilId(_object.getId());
		
		if (roles == null && selectedProfilRoles == null) {
			root2 = new CheckboxTreeNode("Root", null);
			CheckboxTreeNode node = new CheckboxTreeNode("", root2); // adding node to avoid error
			return;
		}

		root2 = new CheckboxTreeNode("Root", null);

		for (v2_Role role : roles) {
			// FIXME try to use partial selection (setPartialSelection) for parent nodes !!
			CheckboxTreeNode node = new CheckboxTreeNode(role.getLibelleKey(), root2);
			node.setSelected(false);
			int selectedChildrenCount = 0;
			for (v2_Role childRole : role.getRoleChildren()) {
				CheckboxTreeNode childNode = new CheckboxTreeNode(childRole.getLibelleKey(), node);
				if (selectedProfilRoles != null && isRoleInListOfProfilRoles(childRole, selectedProfilRoles)) {
					childNode.setSelected(true);
					selectedChildrenCount++;
					System.out.println(">SELECT " + childRole.getLibelleKey());
				} else {
					childNode.setSelected(false);
					System.out.println("UNSELECT " + childRole.getLibelleKey());
				}
			}
			
			/*node.setSelected(false);
			if(selectedChildrenCount == node.getChildCount()){
				node.setSelected(true);
			}else if(selectedChildrenCount > 0){
				node.setPartialSelected(true);
			}*/

		}
		// TODO : the next block is just for test purpose 
		/*
		 * for (TreeNode mainNode : root2.getChildren()) { try { int x = Integer.valueOf(mainNode.getRowKey()); mainNode.setSelected(false);
		 * mainNode.setPartialSelected(true); } catch (Exception e) { } }
		 */
		RequestContext.getCurrentInstance().update(":mainForm");
	}

	public boolean isRoleInListOfProfilRoles(v2_Role comparedRole, List<v2_ProfilRole> profilRoles) {
		for (v2_ProfilRole profilRole : profilRoles) {
			if (profilRole.getRole().getId().equals(comparedRole.getId()))
				return true;
		}
		return false;
	}

	public List<v2_Role> getRoles() {
		return roles;
	}

	public void setRoles(List<v2_Role> roles) {
		this.roles = roles;
	}

	public List<v2_ProfilRole> getSelectedProfilRoles() {
		return selectedProfilRoles;
	}

	public void setSelectedProfilRoles(List<v2_ProfilRole> selectedProfilRoles) {
		this.selectedProfilRoles = selectedProfilRoles;
	}

	public TreeNode getRoot2() {
		updateTreeNodes();
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

	public v2_UserNawat getUserSubjectOfQuickDialog() {
		return userSubjectOfQuickDialog;
	}

	public void setUserSubjectOfQuickDialog(v2_UserNawat userSubjectOfQuickDialog) {
		this.userSubjectOfQuickDialog = userSubjectOfQuickDialog;
	}

	public boolean isQuickDialogUpdateMode() {
		return quickDialogUpdateMode;
	}

	public void setQuickDialogUpdateMode(boolean quickDialogUpdateMode) {
		this.quickDialogUpdateMode = quickDialogUpdateMode;
	}
}
