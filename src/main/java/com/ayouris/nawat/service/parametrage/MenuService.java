package com.ayouris.nawat.service.parametrage;

import java.util.List;

import com.ayouris.nawat.model.entity.Menu;
import com.ayouris.nawat.service.generic.GenericService;

public interface MenuService extends GenericService<Menu, String> {

	/**
	 * Retourne une liste de menus du premier niveau (Racine) d'un module en passant en paramètre son identifiant
	 * 
	 * @param ModuleId
	 * @return List<Menu>
	 */
	List<Menu> findByParentMenuIsNull();
}
