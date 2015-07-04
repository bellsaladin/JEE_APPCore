package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.v2_Profil;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface v2_ProfilRepository extends GenericRepository<v2_Profil, String> {

}