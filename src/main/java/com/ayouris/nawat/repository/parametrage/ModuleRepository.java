package com.ayouris.nawat.repository.parametrage;

import org.springframework.stereotype.Repository;

import com.ayouris.nawat.model.entity.Module;
import com.ayouris.nawat.repository.generic.GenericRepository;

@Repository
public interface ModuleRepository extends GenericRepository<Module, String> {

}