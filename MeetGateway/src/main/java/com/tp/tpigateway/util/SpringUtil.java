package com.tp.tpigateway.util;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class SpringUtil implements ApplicationContextAware, ServletContextAware {

	private static ApplicationContext context;
	
	private static ServletContext servletContext;

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringUtil.context = context;
	}
	
	public void setServletContext(ServletContext servletContext) {
		SpringUtil.servletContext = servletContext;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}
	
	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> className) {
		return context.getBean(className);
	}
}