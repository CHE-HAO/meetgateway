package com.tp.tpigateway.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Filter配置
 *
 */
@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean getCharacterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(characterEncodingFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}
	
}
