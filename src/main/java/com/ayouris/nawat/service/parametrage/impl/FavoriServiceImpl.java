package com.ayouris.nawat.service.parametrage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.Favori;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.FavoriRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.FavoriService;

@Service
@Transactional(readOnly = true)
public class FavoriServiceImpl extends GenericServiceImpl<Favori, String> implements FavoriService {

	@Autowired
	FavoriRepository favoriRepository;

	@Override
	public GenericRepository<Favori, String> getRepository() {
		return favoriRepository;
	}

}
