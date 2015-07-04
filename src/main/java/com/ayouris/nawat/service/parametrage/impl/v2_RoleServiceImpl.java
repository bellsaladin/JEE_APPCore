package com.ayouris.nawat.service.parametrage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.v2_Role;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.v2_RoleRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.v2_RoleService;

@Service
@Transactional(readOnly = true)
public class v2_RoleServiceImpl extends GenericServiceImpl<v2_Role, String> implements v2_RoleService {

	@Autowired
	v2_RoleRepository v2_roleRepository;

	@Override
	public GenericRepository<v2_Role, String> getRepository() {
		return v2_roleRepository;
	}

	@Override
	public List<v2_Role> findByParentRoleIsNull() {
		return v2_roleRepository.findByParentRoleIsNull();
	}
}