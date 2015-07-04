package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.v2_UserNawat;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface v2_UserRepository extends GenericRepository<v2_UserNawat, String> {

	/**
	 * Retourne l'utilisateur par le nom de login
	 * 
	 * @param login
	 * @return UserNawat
	 */
	v2_UserNawat findUserNawatByLogin(String login);

}