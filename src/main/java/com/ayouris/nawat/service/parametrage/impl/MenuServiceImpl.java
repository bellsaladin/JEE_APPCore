package com.ayouris.nawat.service.parametrage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.Menu;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.MenuRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.MenuService;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends GenericServiceImpl<Menu, String> implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public GenericRepository<Menu, String> getRepository() {
		return menuRepository;
	}

	@Override
	public List<Menu> findByParentMenuIsNull() {
		return menuRepository.findByParentMenuIsNull();
	}

}
