package com.ayouris.nawat.controller;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.ayouris.nawat.util.scopes.view.SpringViewScoped;

@ManagedBean
@SpringViewScoped
public class BasicView {
    
    private String text;
    private String title;
    
    @PostConstruct
    public void initialize(){
    	String[] candidateTitles = {"INTEGRIS", "CRUCIAL", "EXPENCIO","EXTENCIO", "LINKINIO", "TARGET","DIREXio",
    								"MAXIMIO","MAXIMOR","CALCULOZ",
    								"Absoluno",
    								"DIRECTROV","DIRECTRO","DIRECTRO-FORCE","DIRECTRO-MAX",
    								"XPER","LEXPER","XPERTIN","XPIRIO","AXPERTIN","EXPERTIN","XPERIX","XPERGGO",
    								"Dorizon", // The horizon
    								"Orizontic",
    								"FOKUS","GLACE","XTENTO","XTENXIO","XTenzio"};
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
