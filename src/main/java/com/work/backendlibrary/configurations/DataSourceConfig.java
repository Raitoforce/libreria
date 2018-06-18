package com.work.backendlibrary.configurations;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
		        .password("huevos123")
		        .url("jdbc:mysql://aakzfq4orgfq7f.chimtynwqqkf.us-east-2.rds.amazonaws.com:3306/DB_libreraLozano")
		        .driverClassName("com.mysql.jdbc.Driver")
		        .build();
	}
}

@Configuration
@EnableWebMvc
@ComponentScan("com.work.backendlibrary.configurations")
class MvcWebConfig implements WebMvcConfigurer{

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {

      // Register resources
	   registry
       .addResourceHandler("/**","/**")
       .addResourceLocations("/","/WEB-INF/classes/");
	   } 
}