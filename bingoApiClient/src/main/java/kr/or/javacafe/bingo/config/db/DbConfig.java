package kr.or.javacafe.bingo.config.db;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.javacafe.core.spring.prop.DatabaseProperty;

@Configuration
public class DbConfig {

	@Autowired
	private DatabaseProperty databaseProperty;
	
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(databaseProperty.getDriverClassName());
		dataSource.setUrl(databaseProperty.getUrl());
		dataSource.setUsername(databaseProperty.getUsername());
		dataSource.setPassword(databaseProperty.getPassword());
		return dataSource;
	}
	
}
