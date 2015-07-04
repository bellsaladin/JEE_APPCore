package com.ayouris.nawat.service.parametrage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.Module;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.ModuleRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.ModuleService;

@Service
@Transactional(readOnly = true)
public class ModuleServiceImpl extends GenericServiceImpl<Module, String> implements ModuleService {

	@Autowired
	ModuleRepository moduleRepository;

	@Override
	public GenericRepository<Module, String> getRepository() {
		return moduleRepository;
	}

}
