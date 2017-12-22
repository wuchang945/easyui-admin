package com.feed.ecp.common.init;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.feed.ecp.common.constants.Constants;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Properties props = new Properties(); 
        InputStream inputStream = null; 
        try { 
            inputStream = getClass().getResourceAsStream("/conf.properties"); 
            props.load(inputStream); 
            String basePath = (String) props.get("basePath"); 
            String filePath = (String) props.get("filePath");
            String filePathUpload = (String) props.get("filePathUpload");
            String project = (String) props.get("project");
            arg0.getServletContext().setAttribute("basePath", basePath);
            arg0.getServletContext().setAttribute("filePath", filePath);
            arg0.getServletContext().setAttribute("filePathUpload", filePathUpload);
            Constants.FILE_ADDRESS=filePathUpload;
            Constants.FILE_IP_ADDRESS=filePath;
            arg0.getServletContext().setAttribute("project", project);
        } catch (IOException ex) { 
            ex.printStackTrace(); 
        } 
	}

}
