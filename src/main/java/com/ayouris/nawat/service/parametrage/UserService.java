package com.ayouris.nawat.service.parametrage;

import com.ayouris.nawat.model.entity.UserNawat;
import com.ayouris.nawat.service.generic.GenericService;

public interface UserService extends GenericService<UserNawat, String> {

	/**
	 * Retourne l'utilisateur par le nom de login
	 * 
	 * @param login
	 * @return UserNawat
	 */
	UserNawat findUserNawatByLogin(String login);
}
