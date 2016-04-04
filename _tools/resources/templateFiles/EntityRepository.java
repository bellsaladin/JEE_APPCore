package com.seosoft.erp.repository.business;

import org.springframework.stereotype.Repository;
import com.seosoft.erp.model.entity.{{ENTITY}};
import com.seosoft.erp.repository.generic.GenericRepository;

@Repository
public interface {{ENTITY}}Repository extends GenericRepository<{{ENTITY}}, String> {

}