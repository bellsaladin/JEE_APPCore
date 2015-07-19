/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ayouris.nawat.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.ayouris.nawat.service.business.ThemeService;
import com.ayouris.nawat.service.business.ThemeService.Theme;
import com.ayouris.nawat.util.scopes.session.SpringSessionScoped;
import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@Named
@SpringSessionScoped
//@ManagedBean
public class ThemeSwitcherView  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7424071584337162037L;

	private List<Theme> themes;
    
    //@Autowired
    private ThemeService service;

    @PostConstruct
    public void init() {
        //themes = service.getThemes();
    }
    
    public List<Theme> getThemes() {
        return themes;
    } 

    public void setService(ThemeService service) {
        this.service = service;
    }

}


