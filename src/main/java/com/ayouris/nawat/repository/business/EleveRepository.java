package com.ayouris.nawat.repository.business;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.Eleve;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface EleveRepository extends GenericRepository<Eleve, String> {

	//Page<Eleve> findByNomStartingWithAndPrenomStartingWith(String nom, String prenom, Pageable pageable);

	// test this	
	//Page<Eleve> findAll(Predicate predicate, Pageable pageable);
}