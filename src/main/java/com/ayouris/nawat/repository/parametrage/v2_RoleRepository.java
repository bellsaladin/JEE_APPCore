package com.ayouris.nawat.repository.parametrage;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.v2_Role;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface v2_RoleRepository extends GenericRepository<v2_Role, String> {

	/**
	 * Retourne une liste de roles de premier niveau (Racine)
	 */
	List<v2_Role> findByParentRoleIsNull();

}