package com.seosoft.erp.controller.generic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.controller.BaseController;
import com.seosoft.erp.controller.ProfilController;
import com.seosoft.erp.model.base.BaseEntity;
import com.seosoft.erp.service.generic.GenericService;

public abstract class GenericController<Type extends BaseEntity, Service extends GenericService<Type, String>> extends BaseController{

	

}
