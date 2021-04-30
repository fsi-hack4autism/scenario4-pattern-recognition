package com.fsiautism_4.springboot.dao;

import java.io.IOException;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fsiautism_4.springboot.Application;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Configuration
public class DBDataSource {

	@Bean
	public JdbcTemplate getJdbcTemplate() throws IOException {
		Properties properties = new Properties();
		properties.load(Application.class.getClassLoader().getResourceAsStream("application.properties"));

		SQLServerDataSource dataSource = new SQLServerDataSource();

		dataSource.setUser(properties.get("user").toString());
		dataSource.setPassword(properties.get("password").toString());
		dataSource.setURL(properties.get("url").toString());
		return new JdbcTemplate(dataSource);
	}

}
