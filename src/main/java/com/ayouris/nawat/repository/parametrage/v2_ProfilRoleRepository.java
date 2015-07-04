package com.ayouris.nawat.repository.parametrage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.v2_ProfilRole;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface v2_ProfilRoleRepository extends GenericRepository<v2_ProfilRole, String> {

	/**
	 * Retourne une liste de roles d'un profil
	 */
	@Query("select pr from v2_ProfilRole pr where pr.profil.id = ?1")
	List<v2_ProfilRole> findByProfilId(String profilId);

}