package com.netease.example.demo.configuration;

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", 
		basePackages = {
		"com.netease.example.demo.repository" }) // 设置Repository所在位置
public class DataSourceConfigration {
	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	private DataSource dataSource;
	@Autowired
	MultiTenantConnectionProvider tenantProvider;
	@Autowired
	CurrentTenantIdentifierResolver tenantResolver;
	

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource)
				.properties(getVendorProperties(dataSource)).packages("com.netease.example.demo.entity")
				.persistenceUnit("primary").build();
		return em;
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}

	private Map<String, Object> getVendorProperties(DataSource dataSource) {
		Map<String, Object> prop = jpaProperties.getHibernateProperties(new HibernateSettings());
		prop.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
		prop.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, tenantProvider);
		prop.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
		return prop;
	}
	
//	@Primary
//	@Bean(name = "multiTenantConnectionProvider")
//	public MultiTenantConnectionProvider multiTenantConnectionProvider() {
//		return new MultiTenantConnectionProviderImpl();
//	}
//	
//	@Primary
//	@Bean(name = "TenantResolver")
//	public CurrentTenantIdentifierResolver tenantResolver() {
//		return new TenantResolver();
//	}

}
