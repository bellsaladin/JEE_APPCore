package com.ayouris.nawat.controller;

import java.util.Random;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class BasicView {
    
    private String text;
    private String title;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
	public String getTitle() {
		int val = new Random().nextInt(10);
		String[] candidateTitles = {"INTEGRIS", "CRUCIAL", "TARGET", "EXPERTIV","TOTAL CONTROL", "FOKUS","INSIGHT","HARMONIUM","MARVELO","GLACE"};
		return candidateTitles[val];
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
