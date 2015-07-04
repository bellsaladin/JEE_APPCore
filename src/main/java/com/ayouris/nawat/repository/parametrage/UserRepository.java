package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.UserNawat;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface UserRepository extends GenericRepository<UserNawat, String> {

	/**
	 * Retourne l'utilisateur par le nom de login
	 * 
	 * @param login
	 * @return UserNawat
	 */
	UserNawat findUserNawatByLogin(String login);

}