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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.seosoft.erp.service.business.ThemeService;
import com.seosoft.erp.service.business.impl.ThemeServiceImpl.Theme;
import com.seosoft.erp.util.scopes.session.SpringSessionScoped;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class TestController implements Serializable {
	
	private String p1;
    private String p2;
    private java.util.Date date1;


    @PostConstruct
    public void init() {
        p1 ="sss";
    }

    
    /* ***************************************************************************/
    /* *************************** ON EVENT FUNCTIONS ****************************/
    /* ***************************************************************************/
    
    public void doPersist(){
    	System.out.println(p1 + "," + p2);
    }
    
    public void doTest(){
    	FacesMessage msg = new FacesMessage("Date1 = " + date1);
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    /* ***************************************************************************/
    /* *************************** GETTERS & SETTERS *****************************/
    /* ***************************************************************************/
	public String getP1() {
		return p1;
	}



	public void setP1(String p1) {
		this.p1 = p1;
	}



	public String getP2() {
		return p2;
	}



	public void setP2(String p2) {
		this.p2 = p2;
	}

	public java.util.Date getDate1() {
		return date1;
	}

	public void setDate1(java.util.Date date1) {
		this.date1 = date1;
	}
    
    

}


