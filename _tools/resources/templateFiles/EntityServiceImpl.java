package com.seosoft.erp.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.seosoft.erp.repository.generic.GenericRepository;
import com.seosoft.erp.service.generic.impl.GenericServiceImpl;
import com.seosoft.erp.service.business.{{ENTITY}}Service;
import com.seosoft.erp.repository.business.{{ENTITY}}Repository;
import com.seosoft.erp.model.entity.{{ENTITY}};

@Service
@Transactional(readOnly = true)
public class {{ENTITY}}ServiceImpl extends GenericServiceImpl<{{ENTITY}}, String> implements {{ENTITY}}Service{

	@Autowired
	{{ENTITY}}Repository repository;

	@Override
	public GenericRepository<{{ENTITY}}, String> getRepository() {
		return repository;
	}

}
