package com.ayouris.nawat.service.parametrage;

import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.service.generic.GenericService;

public interface v2_UserService extends GenericService<v2_UserNawat, String> {

	/**
	 * Retourne l'utilisateur par le nom de login
	 * 
	 * @param login
	 * @return UserNawat
	 */
	v2_UserNawat findUserNawatByLogin(String login);
}
