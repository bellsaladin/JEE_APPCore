package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.Favori;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface FavoriRepository extends GenericRepository<Favori, String> {

}