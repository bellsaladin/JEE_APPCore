package com.ayouris.nawat.service.parametrage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.v2_Profil;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.v2_ProfilRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.v2_ProfilService;

@Service
@Transactional(readOnly = true)
public class v2_ProfilServiceImpl extends GenericServiceImpl<v2_Profil, String> implements v2_ProfilService {

	@Autowired
	v2_ProfilRepository v2_profilRepository;

	@Override
	public GenericRepository<v2_Profil, String> getRepository() {
		return v2_profilRepository;
	}
}