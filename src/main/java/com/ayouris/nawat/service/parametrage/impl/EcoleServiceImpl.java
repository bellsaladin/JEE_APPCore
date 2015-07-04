package com.ayouris.nawat.service.parametrage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.Ecole;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.EcoleRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.EcoleService;

@Service
@Transactional(readOnly = true)
public class EcoleServiceImpl extends GenericServiceImpl<Ecole, String> implements EcoleService {

	@Autowired
	EcoleRepository ecoleRepository;

	@Override
	public GenericRepository<Ecole, String> getRepository() {
		return ecoleRepository;
	}

}
