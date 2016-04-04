package com.seosoft.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Commons {
	public static void copyFile(String fileName, String relativeWebPath, InputStream in) {
		System.out.println("copyFile");
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
		
        try {

             // write the inputStream to a FileOutputStream
             OutputStream out = new FileOutputStream(new File(absoluteDiskPath + "/" + fileName));
           
             int read = 0;
             byte[] bytes = new byte[1024];
           
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
             in.close();
             out.flush();
             out.close();
             System.out.println("New file created!");
         } catch (IOException e) {
             System.out.println(e.getMessage());
         }
	}
}
