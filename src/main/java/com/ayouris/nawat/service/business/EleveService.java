package com.ayouris.nawat.service.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ayouris.nawat.model.entity.Eleve;
import com.ayouris.nawat.service.generic.GenericService;

public interface EleveService extends GenericService<Eleve, String> {

	Page<Eleve> findByNomStartingWithAndPrenomStartingWith(String nom, String prenom, Pageable pageable);
}
