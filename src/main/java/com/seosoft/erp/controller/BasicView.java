package com.seosoft.erp.controller;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@ManagedBean
@SpringViewScoped
public class BasicView {
    
    private String text;
    private String title;
    
    @PostConstruct
    public void initialize(){
    	String[] candidateTitles = {"INTEGRIS", "CRUCIAL", "EXPENCIO","EXTENCIO", "LINKINIO", "TARGET","DIREXio",
    								"MAXIMIO","MAXIMOR","CALCULOZ",
    								"ABSOLUS","ABSOLU-PRO","ABSOLUNO", 
    								"DIRECTROV","DIRECTRO","DIRECTRO-FORCE","DIRECTRO-MAX", // ,// inspired from  'Direction'
    								"XPER","LEXPER","XPERTIN","XPIRIO","AXPERTIN","EXPERTIN","XPERIX","XPERGGO",// inspired from  'EXPERT'
    								"Dorizon", "Orizontic",// inspired from  'Horizon'
    								"COPERIUM", "COPERIOS","COPERTIX","COPERTIGO",// inspired from 'Coopération'
    								"HOLOGRAMO","HOLOGRAX", // inspired from 'Hologramme'
    								"XTRACIO","XTRASSIO","XTRA-OP", "XTRA MARK",// inspired from EXTRA
    								"SYMBOLIO",
    								"FOKUS","GLACE","XTENTO","XTENXIO","XTENZ",
    								"SEOMAX","ORGANIZOMAX","ORGANIZ-SOFT",
    								"ORGANIS" // un peu comme INTEGRIS
    								};
		int val = new Random().nextInt(candidateTitles.length);
		title =  candidateTitles[val];
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
