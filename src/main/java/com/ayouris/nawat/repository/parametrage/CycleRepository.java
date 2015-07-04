package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.Cycle;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface CycleRepository extends GenericRepository<Cycle, String> {

}
