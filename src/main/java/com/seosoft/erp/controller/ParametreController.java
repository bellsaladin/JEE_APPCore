package com.seosoft.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import com.seosoft.erp.controller.generic.Action;
import com.seosoft.erp.controller.generic.GenericUpdateOnlyController;
import com.seosoft.erp.model.entity.Parametre;
import com.seosoft.erp.service.parametrage.ParametreService;
import com.seosoft.erp.util.scopes.view.SpringViewScoped;

@Named
@SpringViewScoped
public class ParametreController extends GenericUpdateOnlyController<Parametre, ParametreService> implements Serializable {
	private static final long serialVersionUID = 7838900790101299064L;

    private UploadedFile imageFile;
	
	protected void prepareData(){
		super.prepareData();
		_moduleName = "parametre";
		prepareForCreateNew();
		
	}
	
	protected void registerActions(){
		_actions.put("handleFileUpload", new Action(){
			@Override
			public void run() {
				
			}
		});
	}
	
	public void handleFileUpload(FileUploadEvent event) {
        try {
            Commons.copyFile(event.getFile().getFileName(), "/resources/images/societe/", event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
		_object.setImageSrc(event.getFile().getFileName());
    }
	
	
	public void prepareForCreateNew(){
		_object = new Parametre();
	}

	public UploadedFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(UploadedFile imageFile) {
		this.imageFile = imageFile;
	}
}
