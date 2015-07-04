package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.Ecole;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface EcoleRepository extends GenericRepository<Ecole, String> {

}
