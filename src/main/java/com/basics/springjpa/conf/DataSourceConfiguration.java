package com.basics.springjpa.conf;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.basics.springjpa.repositories" }, entityManagerFactoryRef = "dataSourceEntityManager")
public class DataSourceConfiguration {
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource bdsDatasource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dataSourceEntityManager")
	public LocalContainerEntityManagerFactoryBean dataSourceEntityManager(EntityManagerFactoryBuilder builder) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.show-sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "none");
		return builder.dataSource(bdsDatasource())
				.packages(new String[] { "com.basics.springjpa.entity" })
				.properties(properties).persistenceUnit("practiseDB").build();

	}
}
