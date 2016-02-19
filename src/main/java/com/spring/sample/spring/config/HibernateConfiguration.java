package com.spring.sample.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.spring.sample.repository")
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
public class HibernateConfiguration {

	private static final String HIBERNATE_DIALECT = "hibernate.dialect";

	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;

	@Value("{entitymanager.packages.to.scan}")
	private String entityManagerPackagesToScan;

	@Autowired
	private DataSource dataSource;

	/*
	 * @Bean public LocalSessionFactoryBean sessionFactory() {
	 * LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	 * sessionFactory.setDataSource(dataSource);
	 * sessionFactory.setPackagesToScan(entityManagerPackagesToScan);
	 * sessionFactory.setHibernateProperties(hibernateProperties()); return
	 * sessionFactory; }
	 */

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());		
		return localContainerEntityManagerFactoryBean.getObject();
	}

	private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		return hibernateJpaVendorAdapter;
	}	

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(HIBERNATE_DIALECT, hibernateDialect);
		properties.put(HIBERNATE_SHOW_SQL, hibernateShowSql);
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}

}
