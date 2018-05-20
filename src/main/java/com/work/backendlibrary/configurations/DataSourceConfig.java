package com.work.backendlibrary.configurations;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class DataSourceConfig{
	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	@Primary
	public DataSource dataSource(){
		return DataSourceBuilder
				.create()
		        .username("root")
		        .password("root")
		        .url("jdbc:mysql://localhost:3308/db_libreralozano?useSSL=false")
		        .driverClassName("com.mysql.jdbc.Driver")
		        .build();
	}
}
