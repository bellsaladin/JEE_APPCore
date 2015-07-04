package com.ayouris.nawat.repository.parametrage;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.Menu;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface MenuRepository extends GenericRepository<Menu, String> {

	/**
	 * Retourne une liste de menus du premier niveau (Racine) d'un module en passant en paramètre son identifiant
	 * 
	 * @param ModuleId
	 * @return List<Menu>
	 */
	List<Menu> findByParentMenuIsNull();
}