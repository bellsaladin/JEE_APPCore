package com.ayouris.nawat.controller.generic;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.controller.BaseController;
import com.ayouris.nawat.model.base.BaseEntity;
import com.ayouris.nawat.service.generic.GenericService;

public abstract class GenericController<Type extends BaseEntity, Service extends GenericService<Type, String>> extends BaseController{
	

}
