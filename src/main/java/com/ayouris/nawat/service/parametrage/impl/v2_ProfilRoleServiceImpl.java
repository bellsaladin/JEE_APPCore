package com.ayouris.nawat.service.parametrage.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.v2_ProfilRole;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.v2_ProfilRoleRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.v2_ProfilRoleService;

@Service
@Transactional(readOnly = true)
public class v2_ProfilRoleServiceImpl extends GenericServiceImpl<v2_ProfilRole, String> implements v2_ProfilRoleService {

	@Autowired
	v2_ProfilRoleRepository v2_profilRoleRepository;

	@Override
	public GenericRepository<v2_ProfilRole, String> getRepository() {
		return v2_profilRoleRepository;
	}

	/**
	 * Retourne une liste de roles d'un profil
	 */
	@Override
	public List<v2_ProfilRole> findByProfilId(String profilId) {
		return v2_profilRoleRepository.findByProfilId(profilId);
	}
}