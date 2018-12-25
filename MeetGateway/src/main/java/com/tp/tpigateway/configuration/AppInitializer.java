package com.tp.tpigateway.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
 
public class AppInitializer implements WebApplicationInitializer {
 
    public void onStartup(ServletContext container) throws ServletException {
    	//定義webAppRootKey 防止多個工程衝突,主要由log4j引發
    	container.setInitParameter("webAppRootKey","TPIGATEWAY_PATH");
    }
 
}
