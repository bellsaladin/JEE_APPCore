package com.ayouris.nawat.service.parametrage;

import java.util.List;

import com.ayouris.nawat.model.entity.v2_ProfilRole;
import com.ayouris.nawat.service.generic.GenericService;

public interface v2_ProfilRoleService extends GenericService<v2_ProfilRole, String> {

	/**
	 * Retourne une liste de roles d'un profil
	 */
	List<v2_ProfilRole> findByProfilId(String profilId);

}
