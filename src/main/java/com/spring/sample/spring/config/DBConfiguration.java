package com.spring.sample.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class DBConfiguration {
	
	  @Value("${db.driverClassName}")
	  private String dbDriverClassName;
	  
	  @Value("${db.url}")
	  private String dbUrl;
	  
	  @Value("${db.username}")
	  private String dbUserName;
	  
	  @Value("${db.username}")
	  private String dbPassword;
	  
	  @Bean
	  public DataSource dataSource(){
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName(dbDriverClassName);
		  dataSource.setUrl(dbUrl);
		  dataSource.setUsername(dbUserName);
		  dataSource.setPassword(dbPassword);
		  return dataSource;
	  }
}
