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
package com.seosoft.erp.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.service.business.ThemeService;
import com.seosoft.erp.service.business.impl.ThemeServiceImpl.Theme;
import com.seosoft.erp.util.scopes.session.SpringSessionScoped;

@Named
@SpringSessionScoped
public class ThemeSwitcherController implements Serializable {
	private static final long serialVersionUID = -7898188270465625151L;
	
	private List<Theme> themes;
    private String theme = "delta";

    @Autowired
    private ThemeService service;

    @PostConstruct
    public void init() {
        themes = service.getThemes();
    }
    
    public void doChangeTheme() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params.containsKey("globaltheme")) {
			theme = params.get("globaltheme");
		}
    }
    
    public String getTheme() {		
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
    
    public List<Theme> getThemes() {
        return themes;
    } 

    public void setService(ThemeService service) {
        this.service = service;
    }

}


