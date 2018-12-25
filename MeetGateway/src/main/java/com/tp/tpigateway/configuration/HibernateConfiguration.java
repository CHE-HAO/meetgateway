package com.tp.tpigateway.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.tp.tpigateway.configuration" })
@PropertySource(value = { "classpath:application.properties" })

public class HibernateConfiguration {
	 
    @Autowired
    private Environment environment;
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws NamingException, ClassNotFoundException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.tp.tpigateway.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
     
    @Bean
    public DataSource dataSource() throws NamingException, ClassNotFoundException {
        JndiTemplate jndi = new JndiTemplate();
        DataSource ds = jndi.lookup(environment.getRequiredProperty("hibernate.jndi"), DataSource.class);
        return ds;
    }
     
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
        return properties;        
    }
     
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }

}

