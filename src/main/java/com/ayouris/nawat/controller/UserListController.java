package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.parametrage.v2_UserService;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class UserListController extends BaseController implements Serializable {
	private static final long serialVersionUID = 5820207595334200581L;

	@Autowired
	private v2_UserService v2_userService;

	List<v2_UserNawat> users;

	private UserDataModel usersDataModel;

	private v2_UserNawat selectedUser;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	@PostConstruct
	public void initialize() {
		System.out.println("UserListController initialized !");
		users = v2_userService.findAll();
		usersDataModel = new UserDataModel(users);
		//scolariteOptions = createScolariteOptions(Eleve.Scolarite.values());

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||||| Methodes utiles ||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void updateUserDataModel() {
		users = v2_userService.findAll();
		usersDataModel = new UserDataModel(users);
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||  UserDataModel ||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public class UserDataModel extends ListDataModel<v2_UserNawat> implements SelectableDataModel<v2_UserNawat> {

		public UserDataModel(List<v2_UserNawat> data) {
			super(data);
		}

		@SuppressWarnings("unchecked")
		@Override
		public v2_UserNawat getRowData(String rowKey) {

			List<v2_UserNawat> users = (List<v2_UserNawat>) getWrappedData();

			for (v2_UserNawat user : users) {
				if (user.getId().equals(rowKey))
					return user;
			}

			return null;
		}

		@Override
		public Object getRowKey(v2_UserNawat user) {
			return user.getId();
		}

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public v2_UserNawat getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(v2_UserNawat selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserDataModel getUsersDataModel() {
		return usersDataModel;
	}

	public List<v2_UserNawat> getUsers() {
		return users;
	}

	public void setUsers(List<v2_UserNawat> users) {
		this.users = users;
	}

}
