package com.ayouris.nawat.service.parametrage;

import java.util.List;

import com.ayouris.nawat.model.entity.v2_Role;
import com.ayouris.nawat.service.generic.GenericService;

public interface v2_RoleService extends GenericService<v2_Role, String> {

	/**
	 * Retourne une liste de roles de premier niveau (Racine)
	 */
	List<v2_Role> findByParentRoleIsNull();

}
